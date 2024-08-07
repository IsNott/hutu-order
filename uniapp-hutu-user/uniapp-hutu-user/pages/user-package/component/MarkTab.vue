<template>
	<view class="mark-info">
		<text class="title">{{title}}</text>
		<text v-if="!value" class="def-placeholder">{{defValue}}</text>
		<text v-else class="placeholder">{{value}}</text>
	</view>
</template>

<script>
	export default{
		name:'MarkTab',
		components:{},
		props:{
			value:{
				type: String,
				default: ''
			},
			defValue:{
				type: String,
				default: '无'
			},
			title:{
				type: String,
				default: 'Tab标题'
			}
		}
	}
</script>

<style scoped>
	.mark-info{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	text{
		padding: 4px 8px;
	}
	
	.title{
		font-size: 16px;
		font-weight: bold;
	}
	
	.def-placeholder{
		color: #b4b4b4;
	}
	
	.placeholder{
		color: black;
	}
</style>