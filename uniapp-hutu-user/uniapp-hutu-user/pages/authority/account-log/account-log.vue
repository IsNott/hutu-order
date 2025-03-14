<template>
	<view class="body">
		<view class="header">
			<view class="title">登录</view>
			<view class="sub-title">欢迎使用糊涂点餐</view>
		</view>
		<view class="log-from">
			<uni-easyinput class="log" trim="all" type="number" v-model="phone" placeholder="手机号(未注册用户将自动注册)"/>
			<uni-easyinput class="log" trim="all" type="number" v-model="code" placeholder="验证码">
				<template #right>
					<text :style="{'margin-right': '10px','color': codeGetting ? 'gray' : 'black'}" @click="getCode">
						{{codeText}}
					</text>
				</template>
			</uni-easyinput>
			<radio-group class="radio-box">
				<radio style="vertical-align: 3px" value="1" @click="handleChange" :checked="select" />
				我已阅读并同意
				<text class="private">《隐私协议》</text> 和
				<text class="private">《用户协议》</text>
			</radio-group>
			<button type="primary" :disabled="!select" @click="handleLogin">登录</button>
		</view>
		<view class="bottom">
			<!-- #ifdef MP-WEIXIN -->
			<text style="font-size: 14px;color: gray;">其他登录方式</text>
			<image style="width: 40px; height: 40px;margin-top: 20px;" 
			src="@/static/image/weixin-logo.png" @click="otherLogin()"/>
			<!-- #endif -->
			<!-- #ifdef MP-ALIPAY -->
			<text style="font-size: 14px;color: gray;">其他登录方式</text>
			<image style="width: 40px; height: 40px;margin-top: 20px;" 
			src="@/static/image/ali-logo.png" @click="otherLogin()"/>
			<!-- #endif -->
		</view>
	</view>
</template>

<script>
	import { commonNavigate,checkPhone, storeUserInfo } from '@/utils/CommonUtils';
	import { login } from '@/api/member-log';
	export default {
		data() {
			return {
				phone: '',
				code: '',
				select: false,
				codeGetting: false,
				codeText: '获取验证码',
				codeTime: 60,
				timer: null
			}
		},
		onUnload() {
			clearInterval(this.timer)
		},
		methods: {
			returnOrderPage(){
				uni.switchTab({
					url: '/pages/order/index'
				});
			},
			handleChange() {
				var select = this.select;
				this.select = !select;
			},
			handleLogin(){
				if(this.code == null || this.code == ''){
					uni.showToast({
						title: '请输入验证码',
						icon: 'error',
						duration: 1000
					})
					return
				}
				login({phone: this.phone}).then(res => {
					storeUserInfo(res.data)
					uni.showToast({
						title:"登录成功",
						icon: 'success',
						duration: 1000
					})
					this.returnOrderPage()
				})
			},
			otherLogin(){
				commonNavigate('/pages/authority/index')
			},
			getCode(){
				if(this.codeGetting){
					return
				}
				if(!this.select){
					uni.showToast({
						title: '请勾选用户协议',
						icon: 'error',
						duration: 1000
					})
					return
				}
				if(this.phone == null || this.phone == ''){
					uni.showToast({
						title: '手机号不能为空',
						icon: 'error',
						duration: 1000
					})
					return
				}
				if(!checkPhone(this.phone)){
					uni.showToast({
						title: '手机号格式不正确',
						icon: 'error',
						duration: 1000
					})
					return
				}
				this.codeGetting = true
				var vm = this
				var code = Math.floor(Math.random() * 9000) + 1000
				setTimeout(() => {
					vm.timer = setInterval(() => {
						vm.codeTime--
						vm.codeText = vm.codeTime + 's'
						if(vm.code == ''){
							vm.code = code
						}
						if(vm.codeTime <= 0){
							vm.codeText = '获取验证码'
							vm.codeGetting = false
							clearInterval(vm.timer)
						}
					}, 1000)
				}, 1000)
			}
		}
	}
</script>

<style scoped>
	.body {
		padding: 30px;
		display: flex;
		flex-direction: column;
	}
	.header{
		padding-top: 20px;
	}
	.title{
		font-size: 26px;
		font-weight: 550;
	}
	.sub-title{
		font-size: 12px;
		padding-top: 10px;
	}
	
	.log-from{
		padding-top: 30px;
	}
	
	radio {
		font-size: 8px;
		transform: scale(0.77);
	}
	
	.log{
		padding: 10px 0px;
	}
	
	.radio-box{
		padding: 10px 0px;
		padding-bottom: 30px;
	}
	
	.private {
		color: #d37959;
	}
	
	.bottom{
		position: absolute;
		bottom: 0;
		margin: auto;
		margin-bottom: 100px;
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 88%;
	}

</style>