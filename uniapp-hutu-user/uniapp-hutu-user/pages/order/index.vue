<template>
	<scroll-view>
		<shop-info-card :current-merchant='currentMerchant' @click="handlerSearchMerchant"/>
		<view class="body">
			<scroll-view class="left">
				<menu-catalog-panel :menu-catalog="menuCatalog" @menuClick="handlerCatalogClick"/>
			</scroll-view>
			<scroll-view class="right">
				<view class="body-right-header">
					<text class="current-catalog-name">{{currentMenu ? currentMenu.catalogName : ''}}\n</text>
					<text class="current-catalog-desc">{{currentMenu ?  currentMenu.catalogDesc : ''}}</text>
				</view>
				<product-card @selectItem="handlerItemSku" v-if="currentItemList" 
				v-for="item in currentItemList" :key="item.itemId" :item="item"/>
			</scroll-view>
		</view>
	</scroll-view>
</template>

<script>
	import { listByShopCatalogId,listByShop,defaultShop } from '@/api/order.js'
	import ShopInfoCard from './component/ShopInfoCard.vue'
	import MenuCatalogPanel from './component/MenuCatalogPanel.vue'
	import ProductCard from './component/ProductCard.vue'
	export default{
		name:'NewOrder',
		components:{ShopInfoCard,MenuCatalogPanel,ProductCard},
		data(){
			return{
				menuCatalog:[],
				currentMerchant:'',
				menuCataActiveId:'',
				currentItemList: []
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
		},
		methods:{
			handlerSearchMerchant() {
				uni.switchTab({
					url:'/pages/shop/index'
				})
			},
			queryMenuCatalog() {
				listByShop(this.currentMerchant.id).then(res=>{
					this.menuCatalog = res.data
				})
			},
			handlerCatalogClick(id) {
				this.menuCataActiveId = id;
				this.searchItemByMenuCatalogId(id)
			},
			searchItemByMenuCatalogId(id) {
				const shopId = this.currentMerchant.id
				listByShopCatalogId(shopId,id).then(res => {
					this.currentItemList = res.data
					console.log(this.currentItemList);
				})
			},
			handlerItemSku(id){
				console.log('itemClick',id);
			},
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
								defaultShop().then(res=>{
									uni.setStorageSync('current_shop',res.data)
									this.currentMerchant = res.data
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