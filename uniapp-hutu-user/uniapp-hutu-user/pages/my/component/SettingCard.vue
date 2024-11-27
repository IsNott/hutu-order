<template>
	<uni-card class="setting-card" margin="6px" :is-shadow="true" shadow="3px">
		<uni-grid :column="4" :showBorder="false"  :square="false">
			<uni-grid-item>
				<view class="icon">
					<uni-icons type="vip" size="24"></uni-icons>
					<text>
						会员卡
					</text>
				</view>
			</uni-grid-item>
			<uni-grid-item>
				<view class="icon">
					<uni-icons type="wallet" size="24"></uni-icons>
					<text>
						卡券
					</text>
				</view>
			</uni-grid-item>
			<uni-grid-item>
				<view class="icon">
					<uni-icons type="gift" size="24"></uni-icons>
					<text>
						礼品卡
					</text>
				</view>
			</uni-grid-item>
			<uni-grid-item>
				<view class="icon">
					<uni-icons type="cart-filled" size="24"></uni-icons>
					<text>
						订单
					</text>
				</view>
			</uni-grid-item>
			<uni-grid-item>
				<view class="icon">
					<uni-icons type="gear" size="24"></uni-icons>
					<text>
						设置
					</text>
				</view>
			</uni-grid-item>
		</uni-grid>
		
	</uni-card>
</template>

<script>
</script>

<style scoped>
	.setting-card text{
		/* margin: 20px; */
		/* width: 25%; */
		text-align: center;
	}
	
	.setting-card {
		/* float: left; */
		border-radius: 20px;
	}
	
	.icon{
		margin: 24px 0px;
		padding: 0px 0px;
		display: flex;
		flex-direction: column;
	}
	
	.icon uni-icons{
			text-align: center;
	}
</style>