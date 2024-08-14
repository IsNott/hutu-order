<template>
	<view>
		<scroll-view class="boby">
			<uni-section :title="currentShopName" type="square" titleFontSize="16px" :subTitle="currentShopAddress">
				<uni-notice-bar v-if="noticeTitle" color="#2979FF" background-color="#EAF2FF" show-icon scrollable
					:text="noticeTitle" />
			</uni-section>
			<view class="pick-way">
				<text class="pick-way-title">就餐方式</text>
				<view class="pick-btn" >
					<button :key="selectedPickWay + '01'" size="mini" :class="isTake"
					@click="handlePickWay('take')"
					>外带</button>
					<text> / </text>
					<button :key="selectedPickWay + '02'" size="mini" :class="{'selected':selectedPickWay === 'take'}"
					@click="handlePickWay('eatIn')"
					>堂食</button>
				</view>
			</view>
			<item-card v-if="packageList.length > 0" v-for="item in packageList" :key="item.id" :item="item"
				@iconClick="handlerIconClick"></item-card>
		</scroll-view>
		<view class="footer">
			<mark-tab :key="remark" title="备注" :def-value="markTabDefValue" :value="remark" />
			<mark-tab :key="totalOrginalAmount" title="原价" :def-value="amoutDefValue" :value="amountStr" />
			<mark-tab :key="chooseCoupon" title="优惠券" :value="chooseCoupon" />
			<mark-tab :key="point" title="可用积分" :value="point" />
			<mark-tab :key="point" title="支付方式" :def-value="defPaywayByCurrPlatform" <!-- #ifdef WEB -->
				@click="handleSelectPayway"
				<!-- #endif -->
				:value="selectPayway.paymentName"/>
				<view class="btn-group">
					<button @click="handlePay" class="pay-btn">立即结算￥{{totalActuallyAmount}}</button>
				</view>
		</view>
	</view>
</template>

<script>
	const empty = {
		id: '',
		userId: '',
		itemId: '',
		itemPiece: 0,
		skuItemContents: '',
		singleActuallyAmount: '',
		itemSkeletonUrl: '',
		itemName: '',
		selectPayWayId: '',
		paywayList: [{
			id: '',
			paymentName: '微信支付',
			icon: ''
		}],
		selectedPickWay: ''
	}
	import ItemCard from './component/ItemCard.vue';
	import CustCard from '@/component/CustCard.vue';
	import MarkTab from './component/MarkTab.vue';
	import {
		getPayWay
	} from '@/api/user-package';
	import {
		getCurrentPlatform
	} from '@/utils/CommonUtils';
	export default {
		name: 'UserPackage',
		components: {
			ItemCard,
			CustCard,
			MarkTab
		},
		data() {
			return {
				packageList: [empty],
				remark: '',
				point: '',
				coupons: '',
				markTabDefValue: '添加口味、糖度等备注',
				amountDefValue: '0.00元',
				chooseCoupon: '',
				paywayList: [{
					id: '123',
					paymentName: '微信支付',
					icon: '',
					displayOrder: 1
				}],
				selectPayway: {},
				currentShopName: '糊涂餐馆（齐河路店）',
				currentShopAddress: '上海齐河路',
				noticeTitle: '如您在点单过程中有任何问题请移步到前台咨询，如遇下单后需要更换商品请及时通知店员，谢谢！',
			}
		},
		onLoad: function(option) {
			const packageList = JSON.parse(decodeURIComponent(option.packageList));
			this.packageList = packageList;
		},
		created() {
			//this.queryPayway();
		},
		methods: {
			handlerIconClick(id, num) {
				const currentPackageItem = this.packageList.find(r => r.id === id);
				if (currentPackageItem) {
					let result = currentPackageItem.itemPiece + num;
					if (result >= 1) {
						currentPackageItem.itemPiece = result;
					}
				}
			},
			handlePickWay(val){
				this.selectedPickWay = val;
			},
			queryPayway() {
				const platformName = getCurrentPlatform();
				getPayWay({
					...platformName
				}).then(res => {
					this.paywayList = res.data;
				})
			},
			// WEB 平台时选择支付方式
			handleSelectPayway() {

			}
		},
		computed: {
			// 总价= 件数*实际单价 - 优惠券 - 抵扣积分金额
			totalActuallyAmount() {
				const packages = this.packageList;
				let sum = 0;
				if (packages.length > 0) {
					for (let i = 0; i < packages.length; i++) {
						const item = packages[i];
						sum += parseFloat(item.singleActuallyAmount) * parseInt(item.itemPiece);
					}
				}
				return sum;
			},
			// 原价= 件数*实际单价（不计优惠）
			totalOrginalAmount() {
				const packages = this.packageList;
				let sum = 0;
				if (packages.length > 0) {
					for (let i = 0; i < packages.length; i++) {
						const item = packages[i];
						sum += parseFloat(item.singleActuallyAmount) * parseInt(item.itemPiece);
					}
				}
				return sum;
			},
			amountStr() {
				return '￥' + this.totalOrginalAmount
			},
			defPaywayByCurrPlatform() {
				var payName = '';
				if (this.paywayList.length > 0) {
					payName = this.paywayList[0].paymentName;
					this.selectPayway = this.paywayList[0];
				}
				return payName;
			},
			isTake(){
				return {
					'selected': this.selectedPickWay === 'take'
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
	}

	.pick-way text {
		font-size: 14px;
		align-self: center;
		font-weight: 600;
	}

	.pick-btn {
		width: 40%;
		display: flex;
		flex-direction: row;
	}

	.pick-btn button {
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
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		display: flex;
		flex-direction: column;
		z-index: 1;
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

	/* 	.pay-lab {
		display: flex;
	} */

	.pay-btn {
		color: white;
		background-color: #007aff;
	}
</style>