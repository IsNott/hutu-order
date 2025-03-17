<template>
	<view class="body">
		<view class="avatar-box" @click="handleAvatarClick">
			<image v-if="form.avatarUrl" :src="form.avatarUrl" mode="aspectFit"/>
			<image v-else src="@/static/image/avatar/default.jpg" mode="aspectFit"/>
			<text>点击更换头像</text>
		</view>
		<view class="base-info-box">
			<text>用户名</text>
			<uni-easyinput placeholder="请输入用户名称" trim="all" v-model="form.username"/>
			<text>手机号</text>
			<uni-easyinput placeholder="请输入手机号" trim="all" v-model="form.phone" type="number"/>
			<text>性别</text>
			 <uni-data-select
							:clear="false"
			        v-model="form.gender"
			        :localdata="gender"
			      />
		</view>
		<view class="btn-group">
			<button @click="handleSubmit(-1)">取消</button>
			<button type="primary" @click="handleSubmit(1)">保存</button>
		</view>
	</view>
</template>

<script>
	import { getStoreUserInfo,storeUserInfo,checkPhone, commonNavigate } from '@/utils/CommonUtils';
	import { updateProfile } from '@/api/user';
	const empty = {
		avatarUrl:'',
		username:'',
		phone:'',
		gender: ''
	}
	export default {
		name: 'Profile',
		data() {
			return {
				form: empty,
				gender: [{ value: 0, text: "女" },
          { value: 1, text: "男" },
          { value: '', text: "暂不选择" },]
			}
		},
		created() {
			this.getUserInfo();
		},
		methods: {
			getUserInfo() {
				const data = getStoreUserInfo();
				this.form = JSON.parse(JSON.stringify(data))
			},
			handleAvatarClick(){
				uni.showActionSheet({
					itemList:['查看大图','更换头像'],
					success:(res => {
						console.log(res);
						if(res.tapIndex == 0){
							console.log(0);
							uni.previewImage({
								urls: [this.form.avatarUrl]
							})
						}
						if(res.tapIndex == 1){
							console.log(1);
							uni.chooseImage({
								count: 1,
								extension: ['jpg','png'],
								success:(res => {
									this.form.avatarUrl = res.tempFilePaths[0]
								})
							})
						}
					})
				})
			},
			handleSubmit(type){
				if(type == -1){
					uni.navigateBack()
				}
				if(type == 1){
					this.submitUpdate()
				}
			},
			submitUpdate(){
				if(this.form.phone){
					if(!checkPhone(this.form.phone)){
						uni.showToast({
							icon: 'error',
							title: '手机格式不正确',
							duration: 1000
						})
					}
				}
				updateProfile({
					...this.form
				}).then(res => {
					storeUserInfo(res.data)
					this.getUserInfo()
					uni.showToast({
						icon: 'success',
						title: '保存成功',
						duration: 1000
					})
				})
			}
		},
		computed:{
			
		}
	}
</script>

<style scoped>
	body,.avatar-box{
		display: flex;
		flex-direction: column;
		/* padding: 20px; */
	}
	
	.avatar-box{
		margin-top: 60px;
		margin-bottom: 20px;
		width: 100%;
		text-align: center;
	}
	
	.avatar-box image{
		margin: auto;
	}
	
	.avatar-box text{
		color: grey;
		margin-top: 14px;
	}
	
	.avatar-box image{
		width: 80px;
		height: 80px;
		border-radius: 80%;
	}
	
	.base-info-box{
		padding: 20px;
	}
	
	.base-info-box text{
		font-size: 14px;
		display: block;
		margin: 12px 4px;
	}
	
	.btn-group{
		display: flex;
		flex-direction: row;
		margin-top: 46px;
	}
	
	.btn-group button{
		width: 40%;
	}
</style>
