<template>
	<uni-card :margin='margin' :is-shadow='isShadow' shadow="3px" 
	class="card">
		<view class="title">
			<text class="main">{{mainTitle}}</text>
			<text class="sub">{{subTitle}}</text>
		</view>
		<view>
			<slot></slot>
		</view>
	</uni-card>
</template>

<script>
	export default{
		name:'CustCard',
		props:{
			margin:{
				type: String,
				default: '6px'
			},
			isShadow:{
				type: Boolean,
				default: false
			},
			shadow:{
				type: String ,
				default: ''
			},
			mainTitle:{
				type: String,
				default: '主标题'
			},
			subTitle:{
				type: String,
				default: '副标题'
			}
		}
	}
</script>

<style scoped>
	.title{
		padding: 4px;
		display: flex;
		margin: 10px 0px;
		justify-content: space-between;
	}
	
	.main{
		font-weight: bold;
		font-size: 110%;
	}
	
	.sub{
		font-size: 100%;
		
	}
</style>