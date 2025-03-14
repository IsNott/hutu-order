<template>
	<uni-card margin="0px" :border='false' :is-shadow="false" @click="handleClick">
		<view class="shop-info-card">
			<view class="header-card">
				<text class="shop-name">
					{{currentMerchant.shopName}}\n
				</text>
				<text class="address">
					{{currentMerchant.address}}
				</text>
			</view>
			<view class="right">
				<text>></text>
			</view>
		</view>
	</uni-card>
</template>

<script>
	const empty = {
		shopName: '',
		address: ''
	}
	export default {
		name: 'ShopInfoCard',
		props:{
			currentMerchant:{
				type: Object,
				default: empty
			}
		},
		methods:{
			handleClick(){
				this.$emit('shopCardClick')
			}
		}
	}
</script>

<style scoped>
	.header-card {
		padding: 2px;
	}
	
	.shop-name{
		font-size: 16px;
		font-weight: 600;
		color: black;
	}
	
	.shop-info-card{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.right text{
		font-size: 24px;
		font-weight: 500;
	}
</style>