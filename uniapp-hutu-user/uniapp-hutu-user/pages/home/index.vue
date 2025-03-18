<template>
	<scroll-view>
		<view class="header">
			<!-- <text>logo</text> -->
			<!-- <image src="@/static/image/template-logo.png" :draggable="false" /> -->
			<uni-swiper-dot class="uni-swiper-dot-box" @clickItem=clickItem :info="info" :current="current" mode="default"
				:dots-styles="dotStyle" field="content">
				<swiper class="swiper-box" @change="changeBanner" :current="swiperDotIndex">
					<swiper-item v-for="(item, index) in info" :key="index">
						<view class="swiper-item" :class="'swiper-item' + index">
							<image :src="item.url" mode="aspectFit"></image>
						</view>
					</swiper-item>
				</swiper>
			</uni-swiper-dot>
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
			<cust-card main-title="庆贺新店开张" click-url="/pages/order/index">
				<view>
					<text>全场满99-20，上不设限!</text>
				</view>
			</cust-card>
			<cust-card main-title="糊涂餐馆" sub-title="招聘中...">
				<view>
					<text>诚邀全职、兼职员工</text>
				</view>
			</cust-card>
			<view class="footer">
				<view style="text-align: center;
				padding: 10px 0px;
				font-size: 16px;
				color: #c5c5c5;">
					<text>Hutu-Order© Since 2024</text>
				</view>
			</view>
		</view>
	</scroll-view>
</template>

<script>
	import {
		commonNavigate
	} from '../../utils/CommonUtils';
	import CustCard from '@/component/CustCard.vue';
	export default {
		name: 'home',
		components: {
			CustCard
		},
		data() {
			return {
				info: [{
						colorClass: 'uni-bg-red',
						url: 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/shuijiao.jpg',
						content: '内容 A'
					},
					{
						colorClass: 'uni-bg-green',
						url: 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/shuijiao.jpg',
						content: '内容 B'
					},
					{
						colorClass: 'uni-bg-blue',
						url: 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/shuijiao.jpg',
						content: '内容 C'
					}
				],
				current: 0,
				swiperDotIndex: 0,
				dotStyle: {
					backgroundColor: 'rgba(0, 0, 0, .3)',
					border: '1px rgba(0, 0, 0, .3) solid',
					color: '#fff',
					selectedBackgroundColor: 'rgba(0, 0, 0, .9)',
					selectedBorder: '1px rgba(0, 0, 0, .9) solid'
				}
			}
		},
		methods: {
			handlerRoute(path) {
				commonNavigate(path)
			},
			showDevMsg() {
				uni.showModal({
					title: '提示',
					content: '功能建设中...',
					showCancel: false
				})
			},
			clickItem(e) {
				this.swiperDotIndex = e
			},
			changeBanner(e) {
				this.current = e.detail.current
			}
		}
	}
</script>
<style scoped>
	.header {
		/* padding: 20px; */
		text-align: center;
		background-color: white;
	}
	
	.swiper-box{
		height: 200px;
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

	.card-images {
		height: 80px;
		width: 80px;
		display: block;
		margin: auto;
		margin-bottom: 6px;
	}
</style>