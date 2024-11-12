<template>
	<scroll-view>
		<scroll-view class="shop-list">
			<ww-bottom-drawerapp :key="key" :is-expand="true" :proportionShow='0.3' :dragHandleHeight="20" :proportionMiniShow="0.1">
				<slot>
					<view style="width: 88px;margin: auto;padding-top: 6px;padding-bottom: 6px;">
						<text style="font-size: 22px;text-align: center;" class="title">门店列表</text>
					</view>
					<view class="header" v-if="false">
						<input class="uni-input" confirm-type="search" @confirm="onConfirm" placeholder="输入关键字"/>
					</view>
					<view v-if="shopInfos.length > 0">
						<shop-card @chooseShop="setCurrentShop"  v-for="shop in shopInfos" :key="shop.id" :shop-info="shop"/>
					</view>
					<view v-else>
						<text>没有更多信息了</text>
					</view>
				</slot>
			</ww-bottom-drawerapp>
		</scroll-view>
	</scroll-view>
</template>

<script>
	import ShopCard from './component/ShopCard.vue';
	import { shopList,search } from '@/api/shop';
	export default {
		name: 'shop',
		components: {
			ShopCard
		},
		data() {
			return {
				shopInfos: [],
				keyWord: '',
				key: new Date().getTime()
			}
		},
		watch:{
			// 关键字为空时获取全部门店
			// keyWord(o,n){
			// 	if(!n){
			// 		this.getShopList();
			// 	}
			// }
		},
		methods:{
			getShopList(){
				shopList().then(res=>{
					this.shopInfos = res.data
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
			},
			onKeyInput(event){
			  this.keyWord = event.target.value
			},
			onConfirm(e){
				this.keyWord = e.detail.value;
				search(this.keyWord).then(res =>{
					this.key = new Date().getTime()
					this.shopInfos = res.data
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
	.header {
		margin: 20px;
		margin-top: 12px;
		padding: 10px;
		border: 1px solid black;
		border-radius: 6px;
	}

</style>