<template>
	<view class="add-penal">
		<view class="num">
			<uni-icons @click="handlerIconClick(-1)" class="left-icon" type="left" size="24"></uni-icons>
			<text>{{num}}</text>
			<uni-icons @click="handlerIconClick(1)" type="right" size="24"></uni-icons>
		</view>
		<view class="add-btn">
			<button :disabled="num <= 0" type="primary" @click="handlerAddToPackage">
				加入购物袋
			</button>
		</view>
	</view>
</template>

<script>
	export default{
		name:'GoodsFooter',
		props:{
			num:{
				type: Number,
				default: 1
			}
		},
		methods:{
			handlerIconClick(num){
				this.$emit('chooseItem',num)
			},
			handlerAddToPackage(){
				this.$emit('addToPackage')
			}
		}
	}
</script>

<style scoped>
	.add-penal{
		left: 0;
		right: 0;
		bottom: 0;
		position: fixed;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.num{
		width: 30%;
		text-align: center;
		margin: auto;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.add-btn{
		width: 70%;
	}
	
	.add-btn button{
		margin: 16px;
		border-radius: 20px;
	}
	
	.left-icon{
		margin-left: 8px;
	}
</style>