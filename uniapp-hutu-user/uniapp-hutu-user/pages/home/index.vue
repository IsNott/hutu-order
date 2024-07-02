<template>
	<scroll-view>
		<view class="header">
			<text>logo</text>
		</view>
		<view class="body">
			<view class="row">
				<view @click="handlerRoute('/pages/order/index')" class="colum">
					<text>点餐</text>
					</view>
				<view class="colum">
					<text>商城</text>
				</view>
			</view>
			<view class="row">
				<view class="colum">
					<text>积分</text>
				</view>
				<view class="colum">
					<text>我的</text>
				</view>
			</view>
			<view class="card">
				营销卡片1
			</view>
			<view class="card">
				营销卡片2
			</view>
			<view class="card">
				营销卡片2
			</view>
			<view class="card">
				营销卡片2
			</view>
			<view class="card">
				营销卡片2
			</view>
			<view class="card">
				营销卡片2
			</view><view class="card">
				营销卡片2
			</view><view class="card">
				营销卡片2
			</view><view class="card">
				营销卡片2
			</view>
		</view>
	</scroll-view>
</template>

<script>
	export default {
		name: 'home',
		data(){
			return{}
		},
		methods:{
			handlerRoute(path){
				console.log(path)
				uni.switchTab({
					url: path
				});
			}
		}
	}
</script>
<style scoped>
.header{
	padding: 20px;
	text-align: center;
	background-color: white;
}

.body{
	padding: 10px 0px;
	margin: 0;
	width: 100%;
}

.row{
	display: flex;
	flex-direction: row;
}

.colum{
	background-color: white;
	border-radius: 4px;
	width: 50%;
	padding: 20px;
	text-align: center;
	margin: 6px;
	box-shadow: 10px 10px 14px 0px #DBDBDB;
}

.card{
	margin: 18px 8px;
	padding: 12px;
	border-radius: 12px;
	background-color: antiquewhite;
}

.header h1{
	color: cadetblue;
}

.colum text{
	color: darkred;
	font-size: 16px;
}
</style>