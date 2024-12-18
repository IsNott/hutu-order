<template>
	<view class="boby">
		<view class="header">
			<uni-search-bar class="uni-mt-10" radius="5" placeholder="搜索订单信息" clearButton="auto" cancelButton="none"
				@confirm="keyWord" />
			<view class="state-bar">
				<view v-for="(state,index) in status" :key="state" @click="currentIndex = index"
					:class="{'bar-tabs-active':currentIndex == index}">
					{{state}}
				</view>
			</view>
		</view>
		<view class="list">
			<view class="order-card">
				<view class="title">
					<view class="shop-info" @click="handleShopClick(example.shopName)">
						{{example.shopName}} >
					</view>
					<view class="right">
						<uni-icons type="more-filled" size="24"></uni-icons>
					</view>
				</view>

				<view class="order-info">
					<scroll-view class="imgs-scroll" :scroll-x="true" :show-scrollbar="false">
						<view class="img-view">
							<!-- <view v-for="img in getImgs(example)">
								<image mode="aspectFit" :src="img" class="img" />
							</view> -->
							<view v-for="item in example.items" :key="item.id">
								<view class="item-view" v-if="item.itemImageUrls && item.itemName">
									<image mode="aspectFill" :src="item.itemImageUrls" class="img" />
									<view class="desc">
										<text>{{item.itemName}}</text>
									</view>
								</view>
							</view>
						</view>
					</scroll-view>
					<view class="order-amount">
						<view>共 <text style=" font-size: 14px;">{{example.items.length}}</text> 件</view>
						<view><text style="font-weight: 600; font-size: 20px;">￥{{example.totalAmount}}</text></view>
					</view>
				</view>
				<view class="bottom">
					<view class="extra">
						下单时间：{{example.createTime}}
					</view>
					<view class="btn-group">

						<view class="left">
							<!-- <view>更多</view> -->
						</view>
						<view class="right">
							<button v-if="example.orderStatus == 0" class="child-btn" type="default" size="mini"
								style="color: #3030ff;border:1px solid #3030ff;">去支付</button>
							<button v-if="example.orderStatus == 6" class="child-btn" size="mini"
								style="color: #000000;border:1px solid #000000;">去评价</button>
							<button v-if="example.orderStatus == 2" class="child-btn" size="mini"
								style="color: black;border:1px solid black;">申请退款</button>
							<button v-if="example.orderStatus == 2 || example.orderStatus == 6" class="child-btn"
								style="color: #3030ff;border:1px solid #3030ff;" size="mini">再来一单</button>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	const btns = [{
		text: '去支付',
		type: 'default',
		size: 'mini',
		class: 'child-btn'
	}, {
		text: '去评价',
		type: 'primary',
		size: 'mini',
		class: 'child-btn'
	}, {
		text: '申请退款',
		type: 'default',
		size: 'mini',
		class: 'child-btn'
	}, {
		text: '再来一单',
		type: 'default',
		size: 'mini',
		class: 'child-btn'
	}]
	const order = {
		"id": "1308471084399460352",
		"orderType": 0,
		"pickType": 0,
		"totalAmount": 114.00,
		"originAmount": 114.00,
		"itemInfo": "[{\"itemId\": 1, \"itemPiece\": 2, \"skuItemContents\": \"全糖\"}, {\"itemId\": 2, \"itemPiece\": 2, \"skuItemContents\": \"\"}, {\"itemId\": 5, \"itemPiece\": 1, \"skuItemContents\": \"\"}, {\"itemId\": 4, \"itemPiece\": 1, \"skuItemContents\": \"\"}, {\"itemId\": 3, \"itemPiece\": 1, \"skuItemContents\": \"\"}]",
		"itemPiece": "",
		"waitTime": "",
		"paywayId": "",
		"outTradeNo": "",
		"refundOutTradeNo": "",
		"orderNo": "H027",
		"orderStatus": 2,
		"payNotifyMsg": "",
		"refundNotifyMsg": "",
		"remark": "",
		"createTime": "2024-11-19 16:36:57",
		"updateTime": "",
		"settleTime": "",
		"shopId": "593630146225766400",
		"userId": "1275384651644403712",
		"address": "上海市齐河路28弄31号303室",
		"shopName": "糊涂餐馆（齐河路店）",
		"items": [{
				"id": "1",
				"itemId": "1",
				"itemImageUrls": "",
				"itemName": "拿铁",
				"skuItemContents": "全糖",
				"itemPiece": 2,
				"singleActuallyAmount": "",
				"expectMakeTime": ""
			},
			{
				"id": "2",
				"itemId": "2",
				"itemImageUrls": "https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg",
				"itemName": "拿铁",
				"skuItemContents": "",
				"itemPiece": 2,
				"singleActuallyAmount": "",
				"expectMakeTime": ""
			},
			{
				"id": "3",
				"itemId": "5",
				"itemImageUrls": "https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg",
				"itemName": "拿铁",
				"skuItemContents": "",
				"itemPiece": 1,
				"singleActuallyAmount": "",
				"expectMakeTime": ""
			},
			{
				"id": "4",
				"itemId": "4",
				"itemImageUrls": "https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg",
				"itemName": "拿铁",
				"skuItemContents": "",
				"itemPiece": 1,
				"singleActuallyAmount": "",
				"expectMakeTime": ""
			},
			{
				"id": "5",
				"itemId": "3",
				"itemImageUrls": "https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg",
				"itemName": "拿铁",
				"skuItemContents": "",
				"itemPiece": 1,
				"singleActuallyAmount": "",
				"expectMakeTime": ""
			},
			{
				"id": "6",
				"itemId": "3",
				"itemImageUrls": "https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg",
				"itemName": "拿铁",
				"skuItemContents": "",
				"itemPiece": 1,
				"singleActuallyAmount": "",
				"expectMakeTime": ""
			},
			{
				"id": "7",
				"itemId": "3",
				"itemImageUrls": "https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg",
				"itemName": "拿铁",
				"skuItemContents": "",
				"itemPiece": 1,
				"singleActuallyAmount": "",
				"expectMakeTime": ""
			}
		]
	}
	export default {
		name: 'MyOrder',
		data() {
			return {
				keyWord: '',
				status: [
					'全部', '待付款', '已支付', '已退款', '已完成'
				],
				example: order,
				currentIndex: 0
			}
		},
		methods: {
			getImgs(order) {
				var array = []
				if (order && order.items && order.items.length > 0) {
					order.items.forEach(item => {
						if (item.itemImageUrls) {
							array.push(item.itemImageUrls)
						}
					})
				}
				return array
			},
			handleShopClick(shopName) {
				console.log('You click ' + shopName);
			}
		},
		computed: {

		}
	}
</script>

<style scoped>
	.state-bar {
		display: flex;
		flex-direction: row;
		text-align: center;
		padding: 10px 20px;
		margin: 0px 20px;
		justify-content: space-between;
	}

	.uni-mt-10 {
		margin: 0px;
	}

	.header {
		margin-bottom: 20px;
	}

	.bar-tabs-active {
		color: #23abff;
		border-bottom: 2px solid #06a8ff;
	}

	.list {
		display: flex;
		flex-direction: column;
	}

	.title {
		margin: 10px 0px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.shop-info {
		/* margin: 14px 0px; */
		font-weight: 600;
		font-size: 16px;
	}

	.imgs-scroll {
		width: 70%;
	}

	.img-view {
		display: flex;
		flex-direction: row;
	}

	.img {
		width: 76px;
		margin-right: 6px;
		height: 76px;
		border-radius: 10px;
		overflow: hidden;
	}
	
	.desc{
		text-align: center;
		font-size: 12px;
		color: gray;
	}

	.order-card {
		border-radius: 8px;
		padding: 20px;
		margin: 0px 24px;
		border: 1px solid #c7c9ce;
		/* background-color: #d6d6d6; */
	}

	.order-info {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.order-amount {
		margin: auto;
		text-align: center;
		background-color: transparent;

		/* background-color: rgba(255, 255, 255, 0.2); */
		backdrop-filter: blur(10px);
	}

	.extra {
		margin: 12px 0px;
		color: gray;
	}

	.btn-group {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.bottom {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.left {
		text-align: center;
	}

	.child-btn {
		margin: 0px 8px;
		border-radius: 24px;
		font-size: 12px;
	}
	
	.item-view{
		display: flex;
		flex-direction: column;
	}
</style>