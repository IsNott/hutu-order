<template>
	<scroll-view>
		<view class="login-card">
			<image
			src="@/static/image/avatar/default.jpg" 
			mode="aspectFit" 
			></image>
			<view class="info">
				<view>
					<text class="nick-name">
						{{hasUserInfo ? userInfo.nickName : '点击授权登录' }}
					</text>
				</view>
				<!-- <text class="phone">
					{{hasUserInfo ? userInfo.phone : '点击绑定手机号' }}
				</text> -->
			</view>
		</view>
		<setting-card/>
	</scroll-view>
</template>

<script>
	import SettingCard from './component/SettingCard.vue';
	import CustCard from '../../component/CustCard.vue';
	export default{
		name:'my',
		components: {SettingCard,CustCard},
		data(){
			return{
				userInfo: '',
				loginAuth: false,
				phoneAuth: false
			}
		},
		created() {
			this.userInfo = uni.getAccountInfoSync('user_info');
		},
		computed:{
			hasUserInfo(){
				if(this.userInfo){
					return true;
				} else{
					return false;
				}
			}
		}
	}
</script>

<style scoped>
	.login-card{
		display: flex;
		padding: 20px;
		width: 100%;
		box-sizing: border-box;
	}
	
	.info{
		margin-left: 16px;
		padding-top: 6px;
		display: block;
	}
	
	.nick-name{
		font-size: 18px;
		font-weight: bold;
	}
	
	.login-card image{
		width: 70px;
		height: 70px;
		border: 0px;
		border-radius: 50%;
	}
	
	/* .card{
		background-color: white;
		border-radius: 6%;
		margin: 6px;
		box-shadow: 0px 0px 3px -1px #000000;
	} */
	

</style>