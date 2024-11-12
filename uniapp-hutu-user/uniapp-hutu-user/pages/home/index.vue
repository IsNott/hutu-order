<template>
	<scroll-view>
		<view class="header">
			<!-- <text>logo</text> -->
			<image src="@/static/image/template-logo.png" :draggable="false" />
		</view>
		<view class="body">
			<view class="row">
				<view @click="handlerRoute('/pages/order/index')" class="colum">
					<image class="card-images" src="@/static/image/order.png" :draggable="false" mode="aspectFit" />
					<text>点餐</text>
				</view>
				<view @click="showDevMsg" class="colum">
					<image class="card-images" src="@/static/image/home-shop.png" :draggable="false" mode="aspectFit" />
					<text>商城</text>
				</view>
			</view>
			<view class="row">
				<view @click="showDevMsg" class="colum">
					<image class="card-images" src="@/static/image/point.png" :draggable="false" mode="aspectFit" />
					<text>积分</text>
				</view>
				<view @click="handlerRoute('/pages/my/index')" class="colum">
					<image class="card-images" src="@/static/image/home-my.png" :draggable="false" mode="aspectFit" />
					<text>我的</text>
				</view>
			</view>
			<cust-card main-title="庆贺新店开张" sub-title="全场满99-20"/>
			<cust-card main-title="糊涂餐馆" sub-title="招聘中..."/>
		</view>
	</scroll-view>
</template>

<script>
	import CustCard from '@/component/CustCard.vue';
	export default {
		name: 'home',
		components: {
			CustCard
		},
		data() {
			return {}
		},
		methods: {
			handlerRoute(path) {
				console.log(path)
				uni.switchTab({
					url: path
				});
			},
			showDevMsg() {
				uni.showModal({
					title: '提示',
					content: '功能建设中...',
					showCancel: false
				})
			}
		}
	}
</script>
<style scoped>
	.header {
		padding: 20px;
		text-align: center;
		background-color: white;
	}

	.body {
		padding: 10px 0px;
		margin: 0;
		width: 100%;
	}

	.row {
		display: flex;
		flex-direction: row;
	}

	.colum {
		background-color: white;
		border-radius: 4px;
		width: 50%;
		padding: 20px;
		text-align: center;
		margin: 6px;
		box-shadow: 10px 10px 14px 0px #DBDBDB;
	}

	.card {
		margin: 18px 8px;
		padding: 12px;
		border-radius: 12px;
		background-color: antiquewhite;
	}

	.header h1 {
		color: cadetblue;
	}

	.colum text {
		color: darkred;
		font-size: 16px;
	}
	
	.card-images{
		height: 80px;
		width: 80px;
		display: block;
		margin: auto;
		margin-bottom: 6px;
	}
</style>