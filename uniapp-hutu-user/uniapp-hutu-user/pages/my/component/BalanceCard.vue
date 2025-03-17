<template>
	<view class="bal-card">
		<view class="title">
			<view class="left">
				<text class="left-title">余额</text>
			</view>
			<view class="right">
				<uni-icons type="refreshempty" size="18" @click="loadBal" />
			</view>
		</view>
		<view class="bal">
			<text class="total">{{totalBalance}}</text>
			<text class="unit">元</text>
		</view>
		<text class="remark">(包含赠送金额：{{giftBalance}}元)</text>
		<!-- <view class="loading-bal" v-if="loading">
			<uni-icons type="spinner-cycle" size="50"></uni-icons>
		</view> -->
	</view>
</template>

<script>
	import {
		myBalance
	} from '@/api/user'
	export default {
		name: 'BalanceCard',
		data() {
			return {
				actualBalance: 100.10,
				giftBalance: 20.00,
				loading: false
			}
		},
		created() {
			this.loadBal()
		},
		methods: {
			loadBal() {
				this.loading = true
				myBalance().then(res => {
					this.actualBalance = res.data.actualBalance
					this.giftBalance = res.data.giftBalance
				}).finally(() => this.loading = false)
			}
		},
		computed: {
			totalBalance() {
				return this.actualBalance + this.giftBalance
			}
		}
	}
</script>

<style scoped>
	.bal-card {
		display: flex;
		flex-direction: column;
		border: 1px solid #e5e5e5;
		padding: 20px;
		margin: 6px;
		border-radius: 4px;
		box-shadow: 0 1px 8px 1px rgba(165, 165, 165, 0.2);
	}

	.title {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		margin-bottom: 16px;
	}

	.left-title {
		font-size: 26px;
		font-weight: 600;
	}

	.loading-bal {
		margin-top: 10px;
		color: gray;
	}

	.bal {
		margin-top: 4px;
	}

	.unit {
		margin-left: 4px;
		font-weight: 550;
	}

	.total {
		font-size: 30px;
	}

	.remark {
		font-size: 12px;
	}
</style>