<template>
	<view>
		<view class="title">
			<uni-title type="h3" :title="catalogName" align="left" />
		</view>
		<uni-grid :showBorder="false" :square="false" :column="3" :highlight="false">
			<uni-grid-item class="grid-item" v-for="(item,index) in skuItem" :index="item.id" :key="item.id">
				<view>
					<button :key="item.id" @click="changeColor(index,item)"
						:class="{ 'btn': true, 'active': activeIndex === index }"
						>{{item.skuItemContent}}</button>
				</view>
			</uni-grid-item>
		</uni-grid>
	</view>
</template>

<script>
	export default{
		name: 'SkuItemInfo',
		components:{},
		props:{
			catalogId:{
				type: String,
				default: ''
			},
			catalogName:{
				type: String,
				default: ''
			},
			skuItem:{
				type: Array,
				default: []
			}
		},
		data(){
			return{
				activeIndex: 0
			}
		},
		methods:{
			changeColor(index, item) {
				this.activeIndex = index;
				this.$emit('change-select',{
					catalogId: this.catalogId,
					selectItemId: item.id,
					content: this.skuItem[0].skuItemContent
				})
			}
		},
		created() {
			if(this.skuItem.length > 0 && this.catalogId){
				this.$emit('change-select',{
					catalogId: this.catalogId,
					content: this.skuItem[0].skuItemContent,
					selectItemId: this.skuItem[0].id
				})
			}
		}
	}
</script>

<style scoped>
	.btn-group {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	.sku-item {
		display: inline;
		box-sizing: border-box;
		padding: 16px;
		background-color: gainsboro;
		border-radius: 15px;
	}
	.grid-item{
		text-align: center;
		margin: 12px 0px;
		font-size: 18px;
		/* padding: 12px 0px; */
		border-radius: 10px;
	}
	
	.title {
		padding-top: 16px;
		padding-left: 16px;
	}
	
	.btn{
		width: 80%;
		font-size: 80%;
		color: black;
		background-color: #f4f4f4;
	}
	
	.active {
		font-size: 80%;
		width: 80%;
		color: black;
		background-color: #dfd6c6;
	}
	
	button::after{
		border: none;
	}
</style>