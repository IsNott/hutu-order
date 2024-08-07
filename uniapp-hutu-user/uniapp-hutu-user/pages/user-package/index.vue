<template>
	<view>
		<scroll-view class="boby">
			<uni-section :title="currentShopName" type="square" titleFontSize="16px" :subTitle="currentShopAddress">
				<uni-notice-bar v-if="noticeTitle" color="#2979FF" background-color="#EAF2FF" show-icon scrollable
					:text="noticeTitle" />
			</uni-section>
			<item-card v-if="packageList.length > 0" v-for="item in packageList" :key="item.id" :item="item"
				@iconClick="handlerIconClick"></item-card>
		</scroll-view>
		<view class="footer">
			<mark-tab :key="remark" title="备注" :def-value="markTabDefValue" :value="remark"/>
			<mark-tab :key="totalOrginalAmount" title="原价" :def-value="amoutDefValue" :value="amountStr"/>
			<mark-tab :key="chooseCoupon" title="优惠券" :value="chooseCoupon"/>
			<mark-tab :key="point" title="可用积分" :value="point"/>
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
		itemName: ''
	}
	import ItemCard from './component/ItemCard.vue';
	import CustCard from '@/component/CustCard.vue';
	import MarkTab from './component/MarkTab.vue';
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
				currentShopName: '糊涂餐馆（齐河路店）',
				currentShopAddress: '上海齐河路',
				noticeTitle: '如您在点单过程中有任何问题请移步到前台咨询，如遇下单后需要更换商品请及时通知店员，谢谢！',
			}
		},
		onLoad: function(option) {
			const packageList = JSON.parse(decodeURIComponent(option.packageList));
			this.packageList = packageList;
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
			handlePay(){
				
			}
		},
		computed: {
			// 总价= 件数*实际单价 - 优惠券 - 抵扣积分金额
			totalActuallyAmount() {
				const packages = this.packageList;
				let sum = 0;
				if(packages.length > 0){
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
				if(packages.length > 0){
					for (let i = 0; i < packages.length; i++) {
						const item = packages[i];
						sum += parseFloat(item.singleActuallyAmount) * parseInt(item.itemPiece);
					}
				}
				return sum;
			},
			amountStr(){
				return '￥' + this.totalOrginalAmount
			}
		}
	}
</script>

<style scoped>
	.footer {
		width: 100%;
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		display: flex;
		flex-direction: column;
	}

	.footer view {
		padding: 6px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

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