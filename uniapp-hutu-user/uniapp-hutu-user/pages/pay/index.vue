<template>
	<view class="pay">
		<view class="top">
			<!-- <view class="title">支付订单</view> -->
			<view class="pay-no">订单号
				<text>{{orderNo}}</text>
			</view>
			<view class="time">
				<text>支付剩余时间</text>
				<uni-countdown :font-size="16" :show-day="false" :show-hour="false" :second="expire" @timeup="handleTimeUp()" />
			</view>
			<view class="amount">{{payStr}}</view>
		</view>
		<scroll-view class="pay-list">
			<radio-group @change="radioChange">
				<view class="payway" v-for="(payway,index) in payways">
					<view class="pay-name">
						<image :src="baseUrl + payway.icon" />
						<text>{{payway.paymentName}}</text>
					</view>
					<radio :value="payway.id" :checked="selectIndex == index" />
				</view>
			</radio-group>
		</scroll-view>
		<view class="footer">
			<button type="primary">确认支付</button>
		</view>
	</view>
</template>


<script>
	import {
		queryPayWay
	} from '@/api/order'
	const orderExpire = 300
	const unit = '￥'
	export default {
		name: 'pay',
		data() {
			return {
				payways: [],
				amount: 0.00,
				expire: orderExpire,
				selectIndex: 0,
				orderNo: ''
			}
		},
		onLoad: function(option) {
			const item = JSON.parse(decodeURIComponent(option.payOrderVo))
			if (item.totalAmount) {
				this.amount = item.totalAmount
			}
			if (item.expireTime) {
				this.expire = Number(item.expireTime)
			}
			if (item.orderNo) {
				this.orderNo = item.orderNo
			}
		},
		created() {
			this.queryPayways()
		},
		methods: {
			queryPayways() {
				queryPayWay().then(res => this.payways = res.data)
			},
			radioChange(evt) {
				for (let i = 0; i < this.payways.length; i++) {
					if (this.payways[i].id === evt.detail.value) {
						this.selectIndex = i;
						break;
					}
				}
			},
			handleTimeUp() {

			}
		},
		computed: {
			payStr() {
				return unit + this.amount.toFixed(2)
			}
		}
	}
</script>

<style scoped>
	.pay {
		display: flex;
		flex-direction: column;
	}


	.pay-list {
		display: flex;
		flex-direction: column;
	}

	.top {
		width: 100%;
		height: 40%;
		text-align: center;
		padding: 60px 0px;
	}

	.title {
		font-size: 30px;
		font-weight: 550;
	}

	.time {
		display: inline-block;
		font-size: 14px;
		color: gray;
		padding-bottom: 14px;
	}

	.amount {
		font-size: 34px;
		font-weight: 550;
		padding: 10px 0px;
	}

	.payway {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		padding: 10px 20px;
	}

	.pay-name {
		text-align: center;
	}

	.pay-name text {
		margin-left: 10px;
		font-size: 18px;
		text-align: center;
	}

	.payway image {
		width: 40px;
		height: 40px;
	}

	.footer {
		padding: 60px 0px;
	}

	.footer button {
		width: 80%;
	}
	
	.pay-no{
		font-size: 16	px;
		color: gray;
		margin-bottom: 6px;
	}
</style>