<template>
	<view class="body">
		<view class="context">
			<image src="@/static/image/coffee.png" mode="aspectFit"/>
			<view class="auth-button">
				<button size="default" type="primary">点击授权使用手机号登录</button>
			</view>
			<radio-group>
				<radio :value="select"/>阅读并已同意</radio><text style="color:#d37959;">《隐私协议》、《用户协议》</text>的内容
			</radio-group>
		</view>
		<view class="footer">
			<text>免登录点单</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'Authority',
		data() {
			return {
				select: false
			}
		},
	}
</script>

<style scoped>
	.body{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	.context{
		margin: 0px;
		display: flex;
		
		flex-direction: column;
		 flex: 1; /* 让 body 部分占据剩余的空间 */
		  display: flex;
		  justify-content: center; /* 在 body 中水平居中 */
		  align-items: center; /* 在 body 中垂直居中 */
	}
	
	.auth-button{
		margin: 20px 0px;
		margin-top: 60px;
		text-align: center;
		
	}
	
	.auth-button button{
		padding: 0px 20px;
		border-radius: 12px;
	}
	
	.auth-button text{
		padding: 0px 20px;
	}
	
	.context image{
		margin: auto;
		margin-top: 200px;
	}
	
	.footer{
		height: 20%;
		bottom: 0;
		margin-top: 80px;
		text-align: center;
	}
	
	.footer text{
		color: gray;
	}
	
	radio{
		font-size: 8px;
		transform: scale(0.77);
	}
	
	
</style>