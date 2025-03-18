<template>
	<scroll-view>
		<scroll-view class="shop-list">
			<!-- <view style="text-align: center;padding-bottom: 6px;">
				<uni-icons type="shop" size="22"></uni-icons>
				<text style="font-size: 20px;text-align: center;font-weight: 500;" class="title">门店列表</text>
			</view> -->
			<view class="header">
				<input class="uni-input" confirm-type="search" @confirm="onConfirm" v-model="keyWord" placeholder="输入关键字搜索"/>
			</view>
			<view v-if="shopInfos.length > 0">
				<shop-card @chooseShop="setCurrentShop"  v-for="shop in shopInfos" :key="shop.id" :shop-info="shop"/>
			</view>
			<view v-else>
				<view style="padding: 12px;font-size: 12px;text-align: center;">
					<text>没有找到任何信息...</text>
				</view>
			</view>
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
				search(e.detail.value).then(res =>{
					this.keyWord = e.detail.value
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
		/* margin-bottom: 20px; */
		padding: 10px;
		border: 1px solid black;
		border-radius: 6px;
	}
	
	/* .map{
		padding: 20px;
		margin: auto;
	} */
	
	.uni-input{
		height: 10px;
	}
	
	.shop-list{
		padding-top: 10px;
	}
</style>