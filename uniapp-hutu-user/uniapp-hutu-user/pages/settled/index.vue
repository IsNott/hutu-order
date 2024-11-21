<template>
	<scroll-view class="body">
		<view class="top">
			<uni-notice-bar v-if="noticeMsg" color="#2979FF" background-color="#EAF2FF" show-icon scrollable
				:text="noticeMsg" />
			</uni-section>
			<uni-steps class="order-steps" :options="eventList" :active="2" active-color="#15c460" />
			<view v-if="orderVo" class="order-no-view">
				<text class="order-no-text">
					取餐号
				</text>
				<text class="order-no">
					{{orderVo.orderNo}}
				</text>
				<view class="queue-view">
					商品制作中，预计还需等待5分钟
				</view>
			</view>

		</view>
		<view v-if="orderVo" class="middle">
			<view class="pay-info-title">
				<text>订单信息</text>
			</view>
			<view class="pay-info-row">
				<text class="pay-info">
					实付：
				</text>
				<text class="pay-info-context">
					￥{{orderVo.totalAmount}}
				</text>
			</view>
			<view class="pay-info-row">
				<text class="pay-info">
					原价：
				</text>
				<text class="pay-info-context">
					￥{{orderVo.originAmount}}
				</text>
			</view>
			<view v-if="handleAmountDiff() > 0" class="pay-info-row">
				<text class="pay-info">
					减免：
				</text>
				<text class="pay-info-context" style="text-decoration: line-through">
					￥{{handleAmountDiff()}}
				</text>
			</view>
			<view class="pay-info-row">
				<text class="pay-info">
					流水号：
				</text>
				<text class="pay-info-context">
					{{orderVo.payOrderId}}
				</text>
			</view>
			<view class="pay-info-row">
				<text class="pay-info">
					支付时间：
				</text>
				<text class="pay-info-context">
					{{orderVo.settleTime}}
				</text>
			</view>
			<view class="pay-info-row">
				<text class="pay-info">
					订单门店：
				</text>
				<text class="pay-info-context">
					<uni-icons type="phone-filled" size="16" style="margin-right: 6px;" />
					{{orderVo.shopName}}
				</text>
			</view>
			<view class="pay-info-row">
				<text class="pay-info">
					门店地址：
				</text>
				<text class="pay-info-context">
					<uni-icons type="location" size="16" style="margin-right: 6px;" />
					{{orderVo.shopAddress}}
				</text>
			</view>
		</view>
		<view class="bottom">
			<uni-list v-if="orderVo.itemInfo">
				<uni-list-item v-for="item in orderVo.itemInfo" :border="false" :title="item.itemName"
					:note="handleSubContent(item)" :thumb="handleImgUrl(item.itemImageUrls)" thumb-size="lg"
					:rightText="handleAmount(item)" />
			</uni-list>
		</view>
	</scroll-view>
</template>

<script>
	const example = {
		orderNo: 'H001',
		payOrderId: '1308752057510920192',
		settleTime: '2024-11-20 10:10:00',
		shopId: '593630146225766400',
		shopName: '糊涂餐馆（齐河路店）',
		shopAddress: '上海齐河路16号',
		originAmount: 100.00,
		totalAmount: 99.00,
		itemInfo: [{
				id: 1294610900124172300,
				itemId: 1,
				itemName: '拿铁',
				itemPiece: 2,
				itemImageUrls: 'https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg',
				skuItemContents: '全糖',
				singleActuallyAmount: 12
			},
			{
				id: 1306946134232531000,
				itemId: 2,
				itemName: '卡布奇诺',
				itemPiece: 2,
				itemImageUrls: '',
				skuItemContents: '',
				singleActuallyAmount: 15
			},
			{
				id: 1306946151630504000,
				itemId: 5,
				itemName: '浓缩咖啡',
				itemPiece: 1,
				itemImageUrls: '',
				skuItemContents: '',
				singleActuallyAmount: 20
			},
			{
				id: 1306946981003788300,
				itemId: 4,
				itemName: '美式咖啡',
				itemPiece: 1,
				itemImageUrls: '',
				skuItemContents: '',
				singleActuallyAmount: 22
			},
			{
				id: 1306947620819697700,
				itemId: 3,
				itemName: '摩卡',
				itemPiece: 1,
				itemImageUrls: '',
				skuItemContents: '',
				singleActuallyAmount: 18
			}
		]
	}
	const events = [{
		title: '挑选商品'
	}, {
		title: '支付订单'
	}, {
		title: '等待取餐'
	}]
	import {
		queryOrderById
	} from '@/api/settled'
	import {
		handleImageUrl
	} from '@/utils/CommonUtils'
	export default {
		name: 'Settled',
		data() {
			return {
				orderId: '',
				orderVo: '',
				eventList: events,
				noticeMsg: '请您留意取餐号及店员当前叫到的号数，为不影响口感请及时取餐，用餐过程中有问题可咨询店员，感谢您光临糊涂餐馆！'
			}
		},
		onLoad: function(opt) {
			this.orderId = opt.orderId
			// this.queryOrderById()
		},
		created() {
			this.queryOrderById()
		},
		methods: {
			queryOrderById() {
				if (this.orderId) {
					queryOrderById(this.orderId).then(res => {
						this.orderVo = res.data
					})
				}
			},
			handleImgUrl(url) {
				return handleImageUrl(url)
			},
			handleSubContent(item) {
				var piece = 'X ' + item.itemPiece
				return piece + '  ' + item.skuItemContents
			},
			handleAmount(item) {
				var symbol = '原价: ￥'
				return symbol + item.singleActuallyAmount
			},
			handleAmountDiff() {
				const order = this.orderVo
				return order.originAmount - order.totalAmount
			}
		},
		computed: {}
	}
</script>

<style scoped>
	.body {
		display: flex;
		flex-direction: row;
	}

	.top {
		width: 100%;
		height: 60%;
	}

	.order-no-view {
		text-align: center;
	}

	.order-no-text {
		font-size: 16px;
		display: block;
		font-weight: 600;
		padding-top: 40px;
	}

	.order-no {
		font-size: 32px;
		display: block;
		font-weight: 700;
		padding: 12px 0px;
	}

	.order-steps {
		padding-top: 14px;
	}

	.queue-view {
		padding: 10px;
		background-color: #f5f5f5;
		margin: 0px 30px;
		border-radius: 25px;
	}

	.middle {
		margin: 30px 0px;
	}

	.pay-info-title {
		/* margin: 0px 6px; */
		padding: 10px;
		font-size: 20px;
		font-weight: 600;
	}

	.pay-info-row {
		margin: 8px 0px;
	}

	.pay-info {
		margin: 0px 14px;
	}

	.pay-info-context {
		margin: 0px 20px;
	}
</style>