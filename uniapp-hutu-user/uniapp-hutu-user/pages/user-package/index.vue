<template>
	<view class="page">
		<scroll-view class="boby">
			<uni-section :title="currentShopInfo.shopName" type="square" titleFontSize="16px"
				:subTitle="currentShopInfo.address">
				<uni-notice-bar v-if="noticeTitle" color="#2979FF" background-color="#EAF2FF" show-icon scrollable
					:text="noticeTitle" />
			</uni-section>
			<view class="pick-way">
				<text class="pick-way-title">就餐方式</text>
				<view class="pick-btn">
					<button size="mini" :key="item.type" v-for="(item,index) in pickUpTypeList"
						:class="{'pick-btn-chlid':true,'selected': activeIndex === index}"
						@click="selectPickUpWay(index,item.type)">
						{{item.name}}
					</button>
				</view>
			</view>
			<view class="pack-list">
				<item-card v-for="item in packageList" :key="item.id" :item="item" @iconClick="handleIconClick" />
			</view>
		</scroll-view>
		<view class="footer">
			<mark-tab key="markTab" title="备注" :def-value="markTabDefValue" :value="remark"
				@tabClick="handleMarkTabClick('mark')" />
			<mark-tab :key="key.amount" title="原价" :def-value="amountDefValue" :value="amountStr" />
			<mark-tab :key="key.pointToUse" title="可用积分" :value="useablePoint.toString()" :def-value="'0'" />
			<mark-tab :key="key.point" title="抵扣" :value="point.toString()" :def-value="pointDef"
				:useRadio="actuallyUseAblePoint != 0" @tabSelect="handlerSelect" v-if="actuallyUseAblePoint != 0" />
			<mark-tab :key="key.coupon" title="优惠券" :value="chooseCoupon" v-if="false" />
			<mark-tab :key="'card' + key()" title="礼品卡" :value="giftCard" v-if="false" />
			<view class="btn-group">
				<button @click="doOrderSettle" :disabled="settleBtnDisable" class="pay-btn"
					type="primary">￥{{totalActuallyAmount}}
					去结算</button>
			</view>
		</view>
	</view>
</template>

<script>
	import ItemCard from './component/ItemCard.vue'
	import CustCard from '@/component/CustCard.vue'
	import MarkTab from './component/MarkTab.vue'
	import {
		OrderStatus
	} from '@/enums/orderStatusEnum'
	import {
		removeItemById,
		updateContext,
		orderQuery,
		simulateNotify
	} from '@/api/user-package'
	import {
		queryUserPackage,
		// queryPayWay,
		queryPoint
	} from '@/api/order.js'
	import {
		commonNavigate,
		getDateStr,
		getShopInfo
	} from '@/utils/CommonUtils'
	import {
		orderSettle
	} from '@/api/user-package'
	const pickUpType = [{
		type: 0,
		name: '堂食'
	}, {
		type: 1,
		name: '外带'
	}]
	// const defPayWay = {
	// 	id: '123',
	// 	paymentName: '微信支付',
	// 	icon: '',
	// 	displayOrder: 1
	// }
	const pointUseRate = 10
	const minPrice = 0.1
	const moneyUnit = '￥'
	const title = '如您在点单过程中有任何问题请移步到前台咨询，如遇下单后需要更换商品请及时通知店员，谢谢！'
	const def = {
		mark: '添加口味、糖度等备注',
		amount: '0.00元'
	}
	export default {
		name: 'UserPackage',
		components: {
			ItemCard,
			CustCard,
			MarkTab
		},
		data() {
			return {
				packageList: [],
				pickUpTypeList: pickUpType,
				remark: '',
				point: '',
				useablePoint: 0,
				coupons: '',
				giftCard: '',
				markTabDefValue: def.mark,
				amountDefValue: def.amount,
				chooseCoupon: '',
				// paywayList: [defPayWay],
				selectedPickUpType: 0,
				activeIndex: 0,
				pickKey: getDateStr(),
				currentShopInfo: {},
				settleBtnDisable: false,
				noticeTitle: title,
				orderId: '',
				timeer: null
			}
		},
		watch: {},
		onShow() {},
		created() {
			this.getShopInfo()
			this.queryPackage()
			this.assembleOtherInfo()
		},
		methods: {
			key() {
				return getDateStr()
			},
			assembleOtherInfo() {
				let remark = uni.getStorageSync('order-remark')
				if (this.isNotEmpty(remark)) {
					this.remark = remark
				}
				// queryPayWay().then(res => this.paywayList = res.data)
				queryPoint().then(res => {
					if (this.isNotEmpty(res.data)) {
						this.useablePoint = res.data
					}
				})
			},
			handleIconClick(id, num) {
				const currentPackageItem = this.packageList.find(r => r.id === id)
				if (currentPackageItem) {
					let result = currentPackageItem.itemPiece + num
					if (result == 0) {
						this.handlerPackageItemRemove(id)
						return;
					}
					this.handlePackageItemNumUpdate(currentPackageItem, result)
				}
			},
			handlePackageItemNumUpdate(item, newNum) {
				var temp = item
				temp.itemPiece = newNum
				updateContext(temp).then(res => {
					this.queryPackage()
				})
			},
			handlerPackageItemRemove(itemId) {
				removeItemById(itemId).then(res => {
					this.queryPackage()
				})
			},
			handlePickWay(val) {
				this.selectedPickWay = val;
				this.pickKey = this.selectPayway + getDateStr()
			},
			handleMarkTabClick(type) {
				switch (type) {
					case 'mark':
						commonNavigate('/pages/user-package/mark/index')
					case 'payway':

				}
			},
			// 切换就餐方式
			selectPickUpWay(index, type) {
				if (index !== this.activeIndex) {
					this.activeIndex = index;
					this.selectedPickUpType = type
				}
			},
			getShopInfo() {
				this.currentShopInfo = uni.getStorageSync('current_shop')
			},
			queryPackage() {
				queryUserPackage().then(res => {
					if (res.data) {
						let size = res.data.length
						if (size == 0) {
							commonNavigate('/pages/order/index')
						}
						this.packageNum = res.data.length
						this.packageList = res.data
					}
				})
			},
			doOrderSettle() {
				this.settleBtnDisable = true
				const shop = getShopInfo()
				var dto = {
					pointCount: this.point,
					remark: '',
					pickType: this.selectedPickUpType,
					orderType: 0,
					items: this.packageList,
					shopId: shop.id
				}
				orderSettle(dto).then(res => {
					// TODO 根据平台+用户选择的方式（Web端）拉起对应支付页面
					// this.orderId = res.data.orderId
					const data = res.data
					uni.showLoading({
						title: '等待支付'
					})
					// simulateNotify(this.orderId).then(() => {
					// 	this.doOrderQuery()
					// })
					const payOrderVo = encodeURIComponent(JSON.stringify(data))
					commonNavigate('/pages/pay/index?payOrderVo=' + payOrderVo)
				}).finally(this.settleBtnDisable = false)
			},
			doOrderQuery() {
				if (this.orderId) {
					const vm = this
					this.timeer = setInterval(() => {
						orderQuery(vm.orderId).then(res => {
							if (res.data.orderStatus = OrderStatus.PAYED) {
								commonNavigate('/pages/settled/index?orderId=' + vm.orderId)
								clearInterval(vm.timeer)
								vm.timeer = null
							}
						})
					}, 1000)
				}
			},
			handlerSelect(select) {
				this.point = select ? this.actuallyUseAblePoint : 0
			}
		},
		computed: {
			key() {
				return {
					amount: 'amount' + getDateStr(),
					coupon: 'coupon' + getDateStr(),
					pointToUse: 'pointToUse' + getDateStr(),
					point: 'point' + getDateStr()
				}
			},
			// 总价= 件数 * 实际单价 - 优惠券 - 抵扣积分金额
			totalActuallyAmount() {
				const packages = this.packageList;
				let sum = 0;
				if (packages.length > 0) {
					for (let i = 0; i < packages.length; i++) {
						const item = packages[i];
						sum += parseFloat(item.singleActuallyAmount) * parseInt(item.itemPiece)
					}
				}
				if (this.point > 0) {
					let discount = this.point / pointUseRate
					if (sum <= discount) {
						sum == minPrice
					} else {
						sum -= discount
					}
				}
				sum = Math.round(sum * 100) / 100
				return sum
			},
			// 原价= 件数*实际单价（不计优惠）
			totalOrginalAmount() {
				const packages = this.packageList
				let sum = 0
				if (packages.length > 0) {
					for (let i = 0; i < packages.length; i++) {
						const item = packages[i]
						sum += parseFloat(item.singleActuallyAmount) * parseInt(item.itemPiece)
					}
				}
				return sum;
			},
			amountStr() {
				return moneyUnit + this.totalOrginalAmount
			},
			// defPaywayByCurrPlatform() {
			// 	var payName = ''
			// 	if (this.paywayList.length > 0) {
			// 		payName = this.paywayList[0].paymentName
			// 		this.selectPayway = this.paywayList[0]
			// 	}
			// 	return payName
			// },
			actuallyUseAblePoint() {
				if (this.useablePoint === 0) {
					return 0
				} else {
					let discount = this.useablePoint / pointUseRate
					if (this.totalOrginalAmount <= discount) {
						return Math.floor((this.totalOrginalAmount - minPrice) * pointUseRate)
					} else {
						return this.useablePoint
					}
				}
			},
			pointDef() {
				if (this.actuallyUseAblePoint == 0) {
					return '无可用积分'
				} else {
					return `使用${this.actuallyUseAblePoint}积分抵扣${this.actuallyUseAblePoint / pointUseRate} 元`
				}
			}
		}
	}
</script>

<style scoped>
	.pick-way {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		background-color: #ffffff;
		padding: 6px 0px;
	}

	.pick-way text {
		font-size: 16px;
		align-self: center;
		font-weight: 600;
	}

	.pick-btn {
		width: 40%;
		display: flex;
		flex-direction: row;
	}

	.pick-btn-chlid {
		border-radius: 8px;
		align-self: center;
	}

	.pick-way-title {
		padding: 8px;
	}

	.selected {
		background-color: #007aff;
		color: #ffffff;
	}

	.unselect {
		background-color: #ffffff;
		color: black;
	}


	.footer {
		width: 100%;
		/* position: fixed; */
		bottom: 0;
		left: 0;
		right: 0;
		display: flex;
		flex-direction: column;
		z-index: 1;
		background: white;
	}

	.footer view {
		padding: 6px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.unselect {}

	.btn-group button {
		margin: 4px 20px;
		border-radius: 14px;
		width: 100%;
	}

	.pay-btn {
		color: white;
		background-color: #007aff;
	}
</style>