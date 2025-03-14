<template>
	<view class="body">
		<view class="title">
			<text>用户登录</text>
		</view>
		<view class="login-card">
			
		</view>
	</view>
</template>

<script>
	export default {
		name: 'MemberLog',
		data() {
			return {
				phone: ''
			}
		},
		methods: {
			
		}
	}
</script>

<style scoped>
	.body{
		display: flex;
		flex-direction: column;
	}
	.title{
		margin-top: 20%;
		font-size: 26px;
		text-align: center;
	}
</style>
