<template>
	<view v-if="hasUserInfo" class="login-card" key="1">
		<image v-if="userInfo.avatarUrl" :src="userInfo.avatarUrl" mode="aspectFit"/>
		<image v-else src="@/static/image/avatar/default.jpg" mode="aspectFit"/>
		<view class="info">
			<view>
				<text class="nick-name">
					{{userInfo.username === '' ? '点击绑定微信昵称' : userInfo.username}}
				</text>
			</view>
		</view>
	</view>
	<view v-else class="login-card" key="2">
		<image src="@/static/image/avatar/default.jpg" mode="aspectFit"></image>
		<view class="info">
			<view>
				<text class="unlogin" @click="handleClick">
					点击授权登录
				</text>
			</view>
		</view>
	</view>
</template>

<script>
	const empty = {
		token:"",
		username:"",
		avatarUrl:"",
		gender:""
	}
	export default{
		name:'LoginCard',
		components:{},
		props:{
			userInfo:{
				type: Object,
				default: empty
			}
		},
		data(){
			return{
			}
		},
		methods: {
			handleClick(){
				uni.navigateTo({
					url: '/pages/authority/index'
				})
			}
		},
		created() {
			
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