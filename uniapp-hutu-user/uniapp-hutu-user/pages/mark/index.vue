<template>
	<view>
		<view class="uni-textarea">
			<textarea v-model="markStr" placeholder-style="color:#cacaca" placeholder="请输入您的口味要求,例如:少冰,少糖" />
		</view>
		<view class="footer">
			<button type="primary" @click="handleSubmit" class="child">完成</button>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'mark',
		components: {},
		data() {
			return {
				markStr: ''
			}
		},
		methods: {
			handleSubmit(){
				uni.setStorageSync('order-remark',markStr)
				uni.navigateBack();
			}
		}
	}
</script>

<style scoped>
	.footer{
		width: 100%;
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
	}
</style>