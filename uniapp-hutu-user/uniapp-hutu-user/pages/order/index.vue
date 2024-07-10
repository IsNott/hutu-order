<template>
	<scroll-view>
		<shop-info-card :current-merchant='currentMerchant' :key="key" @shopCardClick="handlerSearchMerchant"/>
		<view class="body">
			<scroll-view class="left">
				<menu-catalog-panel :v-if="menuCatalog.length > 0" :menu-catalog="menuCatalog" @menuClick="handlerCatalogClick"/>
			</scroll-view>
			<scroll-view class="right">
				<view class="body-right-header">
					<text class="current-catalog-name">{{currentMenu ? currentMenu.catalogName : ''}}\n</text>
					<text class="current-catalog-desc">{{currentMenu ?  currentMenu.catalogDesc : ''}}</text>
				</view>
				<product-card @selectItem="handlerItemSku" v-if="currentItemList" 
				v-for="item in currentItemList" :key="item.itemId" :item="item"/>
			</scroll-view>
			<vk-data-goods-sku-popup
			            ref="skuPopup"
			            v-model="showSku"
			            border-radius="20"
									max-buy-num="20"
			            :z-index="990"
			            :localdata="currentSkuList"
			            mode="1"
									theme="coffee"
			            @add-cart="addPackage"
			            @buy-now="doPay"
			></vk-data-goods-sku-popup>
		</view>
	</scroll-view>
</template>

<script>
	import { listByShopCatalogId,listByShop,findSkuByItemId } from '@/api/order.js'
	import { getStoreUserInfo } from '@/utils/CommonUtils'
	import { shopList } from '@/api/shop'
	import ShopInfoCard from './component/ShopInfoCard.vue'
	import MenuCatalogPanel from './component/MenuCatalogPanel.vue'
	import ProductCard from './component/ProductCard.vue'
	export default{
		name:'Order',
		components:{ShopInfoCard,MenuCatalogPanel,ProductCard},
		data(){
			return{
				menuCatalog:[],
				currentMerchant:'',
				menuCataActiveId:'',
				currentItem: '',
				currentItemList: [],
				currentPackage: [],
				currentSkuList:[],
				showSku: false,
				key: new Date().getTime().toString()
			}
		},
		onShow() {
			this.queryCurrentMerchant();
		},
		watch: {
			currentMerchant(o,n){
				if(n !== o){
					this.queryMenuCatalog();
				}
			}
			// ,
			// currentItem(o,n){
			// 	console.log(n)
			// 	this.querySku(n.itemId);
			// }
		},
		methods:{
			// 当前门店卡片点击事件
			handlerSearchMerchant() {
				uni.switchTab({
					url:'/pages/shop/index'
				})
			},
			// 门店id查询菜单
			queryMenuCatalog() {
				listByShop(this.currentMerchant.id).then(res=>{
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
				listByShopCatalogId(shopId,id).then(res => {
					this.currentItemList = res.data
				})
			},
			// 添加商品点击事件
			handlerItemSku(id){
				const userInfo = getStoreUserInfo();
				if(!userInfo){
					uni.navigateTo({
						url: '/pages/authority/index'
					})
				}else{
					this.currentItem = this.currentItemList.find(r => r.itemId === id);
					this.querySku(id);
					this.showSku = true;
				}
			},
			querySku(itemId){
				console.log(itemId)
				findSkuByItemId(itemId).then(res=>{
					this.currentSkuList = res.data
				})
			},
			addPackage(item){
				console.log('add',item)
			},
			doPay(item){
				console.log('pay',item)
			},
			// 查询当前门店
			queryCurrentMerchant(){
				const val = uni.getStorageSync('current_shop');
				if(val){
					this.currentMerchant = val;
				}else{
					uni.showModal({
						title:'提示',
						content:'当前还未选择门店，是否跳转门店列表?',
						cancelText:'否',
						confirmText:'是',
						success:(res)=>{
							if(res.success){
								uni.switchTab({
									url:'/pages/shop/index'
								})
							}else{
								shopList().then(res=>{
									const data = res.data;
									const currentShop = data.find(obj => obj.closeNow !== 1 && obj.open)
									uni.setStorageSync('current_shop',currentShop)
									this.currentMerchant = currentShop
								})
							}
						}
					})
				}
			}
		},
		computed: {
			currentMenu() {
				if(!this.menuCataActiveId){
					return ''
				}else{
					return this.menuCatalog.find(val => val.id === this.menuCataActiveId)
				}
			},
		}
	}
</script>

<style scoped>
	
	.body{
		display:flex;
		flex-direction: row;
	}
	
	.left{
		text-align: center;
		height: 100%;
		width: 40%;
	}
	.right{
		/* width: 70%; */
	}
	
	.body-right-header{
		background-color: white;
		padding: 16px;
	}
	
	.current-catalog-name{
		font-size: 16px;
		font-weight: 590;
	}
	
	.current-catalog-desc{
		font-size: 8px;
		color: gray;
	}

</style>