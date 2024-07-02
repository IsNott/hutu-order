<template>
	<scroll-view>
		<scroll-view class="shop-list">
			<ww-bottom-drawerapp :is-expand="true" :proportionShow='0.3' :dragHandleHeight="20" :proportionMiniShow="0.1">
				<slot>
					<view class="header">
						<input class="uni-input" confirm-type="search" placeholder="输入地址" />
					</view>
					<text style="margin-left: 20px;font-size: 12px;" class="title">门店列表</text>
					<shop-card @chooseShop="setCurrentShop"  v-for="shop in shopInfos" :key="shop.id" :shop-info="shop"/>
				</slot>
			</ww-bottom-drawerapp>
		</scroll-view>
	</scroll-view>
</template>

<script>
	import ShopCard from './component/ShopCard.vue';
	import { shopList } from '@/api/shop';
	export default {
		name: 'shop',
		components: {
			ShopCard
		},
		data() {
			return {
				shopInfos: []
			}
		},
		methods:{
			getShopList(){
				shopList().then(res=>{
					console.log(res)
					this.shopInfos = res.data
					console.log(this.shopInfos)
				})
			},
			setCurrentShop(shopInfo){
				uni.setStorage({
					data: shopInfo,
					key: 'current_shop',
					success:() => {
						uni.switchTab({
							url: '/pages/order/index'
						})
					}
				})
			}
		},
		computed: {
			isOpen(shopInfo){
				return shopInfo.closeNow === 0;
			}
		},
		created() {
			this.getShopList();
		}
	}
</script>

<style scoped>
	.header uni-input{
		margin: 20px;
		margin-top: 30px;
		padding: 10px;
		border: 1px solid black;
		border-radius: 6px;
	}

</style>