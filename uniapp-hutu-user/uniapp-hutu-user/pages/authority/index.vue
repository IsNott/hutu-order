<template>
	<view class="body">
		<view class="context">
			<image src="@/static/image/coffee.png" mode="aspectFit" />
			<view class="auth-button">
				<button size="default" :disabled="bottonDisable" open-type="getPhoneNumber" @getphonenumber="handleUserInfo"
					type="primary">
					微信授权登录</button>
					<button size="default" @click="toAccountLog"
						type="default">
						手机号登录</button>
			</view>
			<radio-group class="radio-box">
				<radio style="vertical-align: 3px" value="1" @click="handleChange" :checked="select" />
				我已阅读并同意
				<text class="private">《隐私协议》</text> 和
				<text class="private">《用户协议》</text>
			</radio-group>
		</view>
		<view class="footer">
			<text v-if="false" @click="handlerClick">免登录点单</text>
			
		</view>
	</view>
</template>

<script>
	import { loginByPhoneInfo } from '@/api/wechat';
	import { storeUserInfo, commonNavigate } from '@/utils/CommonUtils';
	export default {
		name: 'Authority',
		data() {
			return {
				select: false,
				phoneInfo: ''
			}
		},
		methods: {
			handleChange() {
				var select = this.select;
				this.select = !select;
			},
			handlerClick() {
				this.returnOrderPage();
			},
			returnOrderPage(){
				uni.switchTab({
					url: '/pages/order/index'
				});
			},
			toAccountLog(){
				commonNavigate('/pages/authority/account-log/account-log')
			},
			handleUserInfo(info) {
				// console.log('Wx UserInfo callBack: ', info)
				const detail = info.detail;
				if(detail.errMsg === 'getPhoneNumber:ok'){
					this.phoneInfo = info;
					loginByPhoneInfo(info.detail).then(res=>{
						storeUserInfo(res.data)
						this.returnOrderPage();
					})
				} else{
					console.log('用户取消授权')
				}
			}
		},
		computed: {
			bottonDisable() {
				return !this.select;
			}
		}
	}
</script>

<style scoped>
	.body {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.context {
		margin: 0px;
		display: flex;

		flex-direction: column;
		flex: 1;
		/* 让 body 部分占据剩余的空间 */
		display: flex;
		justify-content: center;
		/* 在 body 中水平居中 */
		align-items: center;
		/* 在 body 中垂直居中 */
	}

	.auth-button {
		margin: 14px 0px;
		margin-top: 40px;
		text-align: center;
		width: 80%;
	}

	.auth-button button {
		padding: 0px 16px;
		border-radius: 14px;
		margin: 10px;
	}

	.auth-button text {
		padding: 0px 20px;
	}

	.context image {
		margin: auto;
		margin-top: 200px;
	}
	
	.radio-box{
		margin: 0px 70px;
		text-align: center;
	}

	.footer {
		height: 20%;
		bottom: 0;
		margin-top: 80px;
		text-align: center;
	}

	.footer text {
		color: gray;
	}

	radio {
		font-size: 8px;
		transform: scale(0.77);
	}

	.private {
		color: #d37959;
	}
</style>