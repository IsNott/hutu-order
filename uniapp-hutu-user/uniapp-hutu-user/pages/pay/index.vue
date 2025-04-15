<template>
	<scroll-view class="pay">
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
				<view :key="payway.id" class="payway" v-for="(payway,index) in payways">
					<view class="pay-name">
						<image :src="baseUrl + payway.icon" />
						<text>{{payway.paymentName}}</text>
					</view>
					<radio :value="payway.id" :checked="selectIndex == index" />
				</view>
			</radio-group>
		</scroll-view>
		<view class="footer">
			<button type="primary" :disabled="submitDisable || disable" @click="sumitPay">确认支付</button>
		</view>
	</scroll-view>
</template>


<script>
	import { commonNavigate } from '@/utils/CommonUtils'
	import { OrderStatus } from '@/enums/orderStatusEnum'
import {
		queryPayWay, gateway
	} from '@/api/pay'
	import { orderQuery } from '@/api/user-package'
	const orderExpire = 300
	const unit = '￥'
	export default {
		name: 'pay',
		data() {
			return {
				payways: [],
				amount: 0.00,
				expire: orderExpire,
				selectIndex: null,
				orderNo: '',
				orderId: '',
				timeer: null,
				disable: false,
				loading: false
			}
		},
		watch:{
			loading: function(o,n){
				if(n){
					uni.showLoading({
						title: '加载中'
					});
				}
			}
		},
		onLoad: function(option) {
			if(!option.payOrderVo){
				return
			}
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
				uni.showModal({
					title: '提示',
					content: '订单已关闭',
					showCancel: false,
					success: function (res) {
						if (res.confirm) {
							commonNavigate('/pages/order/index')
						} 
					}
				});
			},
			sumitPay(){
				let index = this.selectIndex
				const selectPayWay = this.payways[index]
				const param = {
					orderNo: this.orderNo,
					businessCode: 'pay',
					payDTO: {
						payNo: this.orderNo,
						payCode: selectPayWay.payCode
					}
				}

				gateway(param).then(res => {
					this.loading = true
					this.disable = true
					this.orderId = res.data.payOrderId
					this.doQuery()
				})
			},
			doQuery(){
				const vm = this
				this.timeer = setInterval(() => {
					try{
						orderQuery(vm.orderId).then(res => {
							if (res.data.orderStatus = OrderStatus.PAYED) {
								commonNavigate('/pages/settled/index?orderId=' + vm.orderId)
								clearInterval(vm.timeer)
								vm.timeer = null
							}
						})
					}finally{
						this.loading = false
					}
				}, 1000)
			}
		},
		computed: {
			payStr() {
				return unit + this.amount.toFixed(2)
			},
			submitDisable(){
				return this.selectIndex == null
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
		display: flex;
		width: 100%;
		align-items: center;
		line-height: 28upx;
		/* text-align: center; */
	}

	.pay-name text {
		margin-left: 10px;
		font-size: 18px;
	}

	.payway image {
		width: 40px;
		height: 40px;
	}

	.footer {
		padding: 30px 0px;
		left: 0;
		right: 0;
		bottom: 0;
		position: fixed;
		display: flex;
		background-color: white;
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