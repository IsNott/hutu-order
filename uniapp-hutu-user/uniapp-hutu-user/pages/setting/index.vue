<template>
	<view>
		<button id="infoBtn" open-type="getUserInfo" @getuserinfo="handlerUserInfo">测试</button>
	</view>
</template>

<script>
	export default{
		name: 'Profile',
		data(){
			return{}
		},
		methods:{
			handlerUserInfo(info){
				console.log('userinfo',info)
			}
		},
	}
</script>

<style scoped>
	#infoBtn{
		
	}
</style>