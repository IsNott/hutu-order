<template>
	<view class="item-card">
		<view class="left-info">
			<image :src="handleImageUrl" mode="aspectFit"/>
		</view>
		<view class="right-info">
			<view class="item-name-amount">
				<text class="item-name">{{item.itemName}}</text>
				<text class="item-amuont">￥{{item.singleActuallyAmount}}</text>
			</view>
			<view class="item-content">{{item.skuItemContents}}</view>
			<view class="item-piece-box">
				<uni-icons @click="handlerIconClick(-1)" class="item-piece-left" type="minus" size="22"/>
				<text class="item-piece-value">{{item.itemPiece}}</text>
				<uni-icons @click="handlerIconClick(1)" class="item-piece-right" type="plus" size="22"/>
			</view>
		</view>
	</view>
</template>

<script>
	import { handleImageUrl } from '../../../utils/CommonUtils'
	const empty = {
		id: '',
		userId: '',
		itemId: '',
		itemPiece: 0,
		skuItemContents: '',
		singleActuallyAmount: 0.00,
		itemImageUrls: '',
		itemName: ''
	}
	export default{
		name:'ItemCard',
		props:{
			item:{
				type: Object,
				default: empty
			}
		},
		methods:{
			handlerIconClick(num){
				this.$emit('iconClick',this.item.id,num)
			}
		},
		computed:{
			imageUrl(){
				return handleImageUrl(this.item.itemImageUrls)
			}
		}
	}
</script>

<style scoped>
	.item-card{
		display: flex;
		background-color: white;
		flex-direction: row;
	}
	
	.left-info{
		padding: 14px 0px;
		padding-left: 14px;
		width: 20%;
		display: inline;
		display: flex;
		flex-direction: row;
	}
	
	.right-info{
		width: 70%;
		padding: 14px 0px;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
	.item-content{
		margin-top: 6px;
		font-size: 12px;
		color: gray
	}
	
	.item-name-amount{
		font-size: 16px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.item-name{
		font-weight: bold;
	}
	
	.item-amuont{
		color: #2979FF;
	}
	
	
	.left-info image{
		border-radius: 4%;
		width: 60px;
		height: 60px;
	}
	
	.item-piece-box{
		background-color: #f8f8f8;
		border-radius: 8px;
		padding: 0px 2px;
		margin-left: 70%;
		width: 30%;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.item-piece-left{
		margin-right: 10px;
	}
	
	.item-piece-right{
		margin-left: 10px;
	}
	
	.item-piece-value{
		font-size: 18px;
	}
</style>