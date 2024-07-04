<template>
	<view v-if="hasUserInfo" class="login-card" key="1">
		<image :src="userInfo.avatarUrl" mode="aspectFit"></image>
		<view class="info">
			<view>
				<text class="nick-name">
					{{userInfo.nickName}}
				</text>
			</view>
		</view>
	</view>
	<view v-else class="login-card" key="2">
		<image src="@/static/image/avatar/default.jpg" mode="aspectFit"></image>
		<view class="info">
			<view>
				<text class="unlogin">
					点击授权登录
				</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		name:'LoginCard',
		components:{},
		data(){
			return{
				userInfo: ''
			}
		},
		methods: {
			getUserInfo() {
				this.userInfo = uni.getStorageSync('user_info');
			}
		},
		created() {
			this.getUserInfo();
		},
		computed: {
			hasUserInfo() {
				if (this.userInfo) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
</script>

<style scoped>
	.login-card {
		display: flex;
		padding: 20px;
		width: 100%;
		box-sizing: border-box;
	}
	
	.info {
		margin-left: 16px;
		padding-top: 6px;
		display: block;
	}
	
	.nick-name,
	.unlogin {
		font-size: 18px;
		font-weight: bold;
	}
	
	.unlogin {
		text-decoration: underline;
		text-decoration-color: gray;
	}
	
	.login-card image {
		width: 70px;
		height: 70px;
		border: 0px;
		border-radius: 50%;
	}
</style>