<template>
	<view v-if="hasUserInfo" class="login-card" key="1" @click="handleToProfile">
		<image v-if="userInfo.avatarUrl" :src="baseUrl + userInfo.avatarUrl" mode="aspectFit"/>
		<image v-else src="@/static/image/avatar/default.jpg" mode="aspectFit"/>
		<view class="info">
			<view>
				<!-- <button v-if="userInfo.username === ''" id="infoBtn" style="background-color: #F5F5F5;" hover-class="none" open-type="getUserInfo"
					@getuserinfo="handlerUserInfo"><text class="nick-name">
					点击绑定微信
				</text></button> -->
				<text class="nick-name">
					{{userInfo.username}}
				</text>
			</view>
		</view>
	</view>
	<view v-else class="login-card" key="2">
		<image src="@/static/image/avatar/default.jpg" mode="aspectFit"></image>
		<view class="info">
			<view>
				<text class="unlogin" @click="handleToLogin">
					点击登录
				</text>
			</view>
		</view>
	</view>
</template>

<script>
	import { updateProfile } from '@/api/user'
	import { storeUserInfo,commonNavigate } from '@/utils/CommonUtils'
	const empty = {
		token: "",
		username: "",
		avatarUrl: "",
		gender: ""
	}
	export default {
		name: 'LoginCard',
		components: {},
		props: {
			userInfo: {
				type: Object,
				default: empty
			}
		},
		data() {
			return {}
		},
		methods: {
			handleToLogin() {
				commonNavigate('/pages/authority/index')
			},
			handleToProfile(){
				commonNavigate('/pages/my/component/Profile')
			},
			handlerUserInfo(resp) {
				const detail = resp.detail;
				if(detail.errMsg === 'getUserInfo:ok'){
						const userInfo = detail.userInfo;
						updateProfile(userInfo).then(res=>{
							const data = res.data
							storeUserInfo(data);
							this.$emit('handleRefesh')
						})
				}
			}
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
	
	button::after{
    border:none;
}
</style>