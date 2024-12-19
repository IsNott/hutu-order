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
				<view class="queue-view" v-if="orderVo.orderStatus == 2">
					前面还有 {{frontOrderCount}} 单，预计还需等待 {{leftTime}} 分钟
				</view>
				<view class="queue-view" v-else>
					订单已完成
				</view>
			</view>

		</view>
		<view v-if="orderVo" class="middle">
			<view class="pay-info-title">
				<text>订单信息</text>
			</view>
			<divide />
			<view class="order-text-info">
				<view class="middle-left">
					<text class="pay-info">
						实付：
					</text><text class="pay-info">
						原价：
					</text>
					<text class="pay-info">
						减免：
					</text>
					<text class="pay-info">
						流水号：
					</text>
					<text v-if="orderVo.settleTime" class="pay-info">
						支付时间：
					</text>
					<text class="pay-info">
						订单门店：
					</text>
					<text class="pay-info">
						门店地址：
					</text>
				</view>
				<view class="middle-right">
					<text class="pay-info">
						￥{{orderVo.totalAmount}}
					</text>
					<text class="pay-info">
						￥{{orderVo.originAmount}}
					</text>
					<text class="pay-info" style="text-decoration: line-through">
						￥{{handleAmountDiff()}}
					</text>
					<text class="pay-info">
						{{orderVo.payOrderId}}
					</text>
					<text v-if="orderVo.settleTime" class="pay-info">
						{{orderVo.settleTime}}
					</text>
					<text class="pay-info">
						<uni-icons type="phone-filled" size="16" style="margin-right: 6px;" />
						{{orderVo.shopName}}
					</text>
					<text class="pay-info">
						<uni-icons type="location" size="16" style="margin-right: 6px;" />
						{{orderVo.shopAddress}}
					</text>
				</view>
			</view>
			<view class="btn-group">
				<button size="mini" type="primary" @click="handleClick('/pages/order/index')">
					再来一单
				</button>
				<button size="mini" type="default" @click="handleClick('/pages/home/index')">
					返回首页
				</button>
			</view>
		</view>

		<view class="bottom">
			<view class="pay-info-title">
				<text>商品列表</text>
			</view>
			<uni-list v-if="orderVo.itemInfo">
				<uni-list-item :key="item.itemId" v-for="item in orderVo.itemInfo" :border="false" :title="item.itemName"
					:note="handleSubContent(item)" :thumb="handleImgUrl(item.itemImageUrls)" thumb-size="lg"
					:rightText="handleAmount(item)" />
			</uni-list>
		</view>
	</scroll-view>
</template>

<script>
	const events = [{
		title: '挑选商品'
	}, {
		title: '支付订单'
	}, {
		title: '等待取餐'
	}]
	import dayjs from 'dayjs'
	import {
		queryOrderById,
		orderFront
	} from '@/api/settled'
	import {
		handleImageUrl,
		commonNavigate
	} from '@/utils/CommonUtils'
	import Divide from '@/component/Divide.vue'
	export default {
		name: 'Settled',
		components: {
			Divide
		},
		data() {
			return {
				orderId: '',
				orderVo: '',
				eventList: events,
				totalWaitTime: 0,
				frontOrderInfo: '',
				frontOrderCount: 0,
				noticeMsg: '请您留意取餐号及店员当前叫到的号数，为不影响口感请及时取餐，用餐过程中有问题可咨询店员，感谢您光临糊涂餐馆！'
			}
		},
		onLoad: function(opt) {
			this.orderId = opt.orderId
			this.queryOrderById()
			this.queryFrontOrder()
		},
		methods: {
			queryOrderById() {
				if (this.orderId) {
					queryOrderById(this.orderId).then(res => {
						this.orderVo = res.data
						if (this.orderVo && this.orderVo.waitTime) {
							this.totalWaitTime += this.orderVo.waitTime
						}
					})
				}
			},
			queryFrontOrder() {
				if (this.orderId && this.orderVo.orderStatus == 2) {
					orderFront(this.orderId).then(res => {
						if (res.data) {
							this.frontOrderInfo = res.data
							if (this.frontOrderInfo.orderCount) {
								this.frontOrderCount = this.frontOrderInfo.orderCount
							}
							if (this.frontOrderInfo.waitTime) {
								this.totalWaitTime += this.frontOrderInfo.waitTime
							}
						}
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
			},
			handleClick(url) {
				commonNavigate(url)
			}
		},
		computed: {
			leftTime() {
				var settleTime = this.orderVo.settleTime
				var dayjsTime = dayjs(settleTime, 'YYYY-MM-DD HH:mm:ss')
				dayjsTime.add(this.totalWaitTime, 'minute')
				var now = dayjs()
				// TODO diff
				const diff = dayjsTime.diff(now, 'minute')
				console.log('diff',diff);
				return diff >= 0 ? diff : 0
			}
		}
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

	.order-text-info {
		display: flex;
		font-size: 16px;
		margin-top: 6px;
	}

	.pay-info {
		display: block;
		padding: 4px;
	}

	.middle-left {
		width: 35%;
	}

	.middle-right {
		width: 65%;
	}

	.pay-info-title {
		padding: 10px;
		font-size: 20px;
		font-weight: 600;
	}

	.pay-info-row {
		margin: 8px 0px;
		text-align: justify;
	}

	.pay-info {
		margin: 0px 14px;
	}

	.pay-info-context {
		margin: 0px 20px;
	}

	.btn-group button {
		width: 40%;
		margin: auto;
		margin: 20px 40px;
		font-size: 14px;
	}

	.btn-group {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
</style>