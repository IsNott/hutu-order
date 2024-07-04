<template>
	<uni-card margin="0px" :border='false'>
		<view class="header-card">
			<text class="shop-name">
				{{currentMerchant.shopName}}\n
			</text>
			<text class="address">
				{{currentMerchant.address}}
			</text>
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
</style>