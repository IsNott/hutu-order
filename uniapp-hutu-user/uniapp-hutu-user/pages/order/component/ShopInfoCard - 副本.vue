<template>
	<view class="body" @click="handleClick">
		<view class="shop-info-card">
			<view class="header-card">
				<text class="shop-name">
					{{currentMerchant.shopName}}\n
				</text>
				<text class="address">
					{{currentMerchant.address}}
				</text>
				<text class="time">
					{{currentMerchant.startBusinessTime}} - {{currentMerchant.endBusinessTime}}
				</text>
			</view>
			<view class="right">
				<text>></text>
			</view>
		</view>
	</view>
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
			},
		},
		computed:{
			style(){
				if(this.currentMerchant.coverUrl){
					const shop = this.currentMerchant
					const data = {
						background: 'url(' + shop.coverUrl + ') no-repeat;',
						backgroundSize: '100% 100%;',
						backgroundAttachment: 'fixed;'
					}
					return data
				}else{
					return null
				}
			}
		}
	}
</script>

<style scoped>
	.body{
		padding: 20px 22px;
		padding-bottom: 10px;
		background: url('@/static/image/20250318102553.png') no-repeat;
		background-size: 100% 30%;
		background-attachment: fixed;
	}
	.header-card {
		padding: 2px;
	}
	
	.header-card text{
		display: block;
	}
	
	.shop-name{
		font-size: 16px;
		font-weight: 600;
	}
	
	.shop-info-card{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		width: 100%;
	}
	
	.right text{
		font-size: 24px;
		font-weight: 500;
	}
	
	.address{
		margin-top: 40px;
	}
	
	.address,.time{
		/* color: grey; */
	}
	
	text{
		color: white;
	}
</style>