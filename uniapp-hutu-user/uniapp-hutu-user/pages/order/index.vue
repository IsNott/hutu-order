<template>
	<OrderSkeleton v-if="showLoading" />
	<scroll-view v-else>
		<shop-info-card :current-merchant='currentMerchant' :key="key" @shopCardClick="handlerSearchMerchant" />
		<view class="body">
			<scroll-view class="left">
				<menu-catalog-panel :v-if="menuCatalog.length > 0" :menu-catalog="menuCatalog"
					@menuClick="handlerCatalogClick" />
			</scroll-view>
			<scroll-view class="right">
				<view class="body-right-header">
					<text class="current-catalog-name">{{currentMenu ? currentMenu.catalogName : ''}}\n</text>
					<text class="current-catalog-desc">{{currentMenu ?  currentMenu.catalogDesc : ''}}</text>
				</view>
				<product-card @selectItem="handlerItemSku" v-if="currentItemList" v-for="item in currentItemList"
					:key="item.itemId" :item="item" />
			</scroll-view>
		</view>
		<shop-package :key="key" v-if="packageNum > 0" @shopPackageClick="handleTransPackage" :num="packageNum" />
	</scroll-view>
</template>

<script>
	import {
		listByShopCatalogId,
		listByShop,
		findSkuByItemId,
		bizUserPackageNum
	} from '@/api/order.js'
	import {
		getStoreUserInfo,
		commonNavigate
	} from '@/utils/CommonUtils'
	import {
		shopList
	} from '@/api/shop'
	import ShopInfoCard from './component/ShopInfoCard.vue'
	import MenuCatalogPanel from './component/MenuCatalogPanel.vue'
	import ProductCard from './component/ProductCard.vue'
	import OrderSkeleton from '../skeleton/OrderSkeleton.vue'
	import ShopPackage from '@/component/ShopPackage.vue'
	export default {
		name: 'Order',
		components: {
			ShopInfoCard,
			MenuCatalogPanel,
			ProductCard,
			OrderSkeleton,
			ShopPackage
		},
		data() {
			return {
				menuCatalog: [],
				currentMerchant: '',
				menuCataActiveId: '',
				currentItem: '',
				currentItemList: [],
				currentPackage: [],
				currentSkuList: [],
				showSku: false,
				showLoading: false,
				packageNum: 1,
				hasLogin: false,
				pattern: {
					icon: 'cart'
				},
				packageNum: 0,
				key: new Date().getTime().toString()
			}
		},
		onLoad() {},
		onShow() {
			const vm = this;
			vm.showLoading = true;
			this.queryCurrentMerchant();
			setTimeout(() => {
				this.queryPackageNum();
				vm.showLoading = false;
			}, 300)
		},
		watch: {
			currentMerchant(o, n) {
				if (n !== o) {
					this.queryMenuCatalog();
				}
			}
		},
		methods: {
			// 当前门店卡片点击事件
			handlerSearchMerchant() {
				uni.switchTab({
					url: '/pages/shop/index'
				})
			},
			// 门店id查询菜单
			queryMenuCatalog() {
				listByShop(this.currentMerchant.id).then(res => {
					this.menuCatalog = res.data
					const id = res.data[0] ? res.data[0].id : ''
					this.handlerCatalogClick(id)
				})
			},
			// 菜单点击事件
			handlerCatalogClick(id) {
				this.menuCataActiveId = id;
				this.searchItemByMenuCatalogId(id)
			},
			// 根据菜单查询菜品
			searchItemByMenuCatalogId(id) {
				const shopId = this.currentMerchant.id
				listByShopCatalogId(shopId, id).then(res => {
					this.currentItemList = res.data
				})
			},
			// 添加商品点击事件
			handlerItemSku(id) {
				const userInfo = getStoreUserInfo();
				if (!userInfo) {
					uni.navigateTo({
						url: '/pages/authority/index'
					})
				} else {
					// this.hasLogin = true;
					this.currentItem = this.currentItemList.find(r => r.itemId === id);
					findSkuByItemId(this.currentItem.itemId).then(res => {
						this.currentSkuList = res.data
						const currentSkuList = encodeURIComponent(JSON.stringify(res.data))
						const item = encodeURIComponent(JSON.stringify(this.currentItem))
						uni.navigateTo({
							url: '/pages/detail/index?skuList=' + currentSkuList + '&item=' + item
						})

					})
				}
			},
			queryPackageNum() {
				// if (this.hasLogin) {
				bizUserPackageNum().then(res => {
					if (res.data) {
						this.packageNum = res.data
					}
				})
				// }
			},
			doPay(item) {
				console.log('pay', item)
			},
			// 查询当前门店
			queryCurrentMerchant() {
				const val = uni.getStorageSync('current_shop');
				if (val) {
					this.currentMerchant = val;
				} else {
					uni.showModal({
						title: '提示',
						content: '当前还未选择门店，是否跳转门店列表?',
						cancelText: '否',
						confirmText: '是',
						success: (res) => {
							if (res.confirm) {
								uni.switchTab({
									url: '/pages/shop/index'
								})
							}
							if (res.cancel) {
								shopList().then(res => {
									const data = res.data;
									const currentShop = data.find(obj => obj.closeNow !== 1 && obj.open)
									uni.setStorageSync('current_shop', currentShop)
									this.currentMerchant = currentShop
								})
							}
						}
					})
				}
			},
			// 点击购物车图标跳转购物袋页面
			handleTransPackage() {
				commonNavigate('/pages/user-package/index')
			}
		},
		computed: {
			currentMenu() {
				if (!this.menuCataActiveId) {
					return ''
				} else {
					return this.menuCatalog.find(val => val.id === this.menuCataActiveId)
				}
			},
		}
	}
</script>

<style scoped>
	.body {
		display: flex;
		flex-direction: row;
	}

	.left {
		text-align: center;
		height: 100%;
		width: 40%;
	}

	.right {
		/* width: 70%; */
	}

	.body-right-header {
		background-color: white;
		padding: 16px;
	}

	.current-catalog-name {
		font-size: 20px;
		font-weight: 590;
	}

	.current-catalog-desc {
		font-size: 16px;
		color: gray;
	}
</style>