<template>
	<scroll-view>
		<view class="header">
			<swiper class="swiper" circular :indicator-dots="indicatorDots" autoplay="true">
				<swiper-item :key="index" v-for="(img,index) in item.itemImageUrls">
					<image :src="img" mode="aspectFit" class="img"/>
				</swiper-item>
			</swiper>
		</view>
		<scroll-view class="context">
			<view class="info">
				<view class="item-name">
					<text class="name">
						{{item.itemName}}
					</text>
					<view class="price">
						<text class="origin-amount">
							{{item.originAmount}}
						</text>
						<text class="actually-amount">
							{{item.actuallyAmount}}
						</text>
					</view>
				</view>
			</view>
			<view class="title">
				<uni-title type="h3" :title="sku.skuCatalogName" align="left"/>
			</view>
			<view class="btn-group">
				<button 
				v-for="(skuItem,index) in sku.skuItems" 
				:key="skuItem.id"
				@click="changeColor(index,skuItem)"
				:class="{ 'mini-btn': true, 'active': activeIndex === index }"
				type="default" size="mini">{{skuItem.skuItemContent}}</button>
			</view>
			
		</scroll-view>
	</scroll-view>
</template>

<script>
	const empty = {
		actuallyAmount: '120',
		itemDesc: '',
		itemId: '',
		itemImageUrls: [
			'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
			'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132'
		],
		itemName: '商品',
		itemTag: '',
		memuCatalogId: '',
		originAmount: '123',
		special: ''
	}
	const emptySku = {
		skuCatalogName:'测试',
		displayOrder: 1,
		skuItems:[{
			id: 1,
			skuItemContent:'测试'
		},{
			id: 2,
			skuItemContent:'测试1'
		},{
			id: 3,
			skuItemContent:'测试2'
		},{
			id: 4,
			skuItemContent:'测试2'
		}]
	}
	export default {
		name: 'ItemDetail',
		props: {
			item: {
				type: Object,
				default: () => (empty)
			},
			skuList: {
				type: Array,
				default: () => ([emptySku])
			}
		},
		data() {
			return {
				current: 0,
				mode: 'default',
				dotsStyles: {},
				swiperDotIndex: 0,
				indicatorDots: true,
				sku: emptySku,
				activeIndex: null
			}
		},
		methods: {
			changeColor(index,item) {
			  this.activeIndex = index;
			}
		}
	}
</script>

<style scoped>
	.img {
		width: 300px;
		height: 300px;
	}
	
	.item-name{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	
	.item-name text{
		padding: 20px;
	}
	
	.price{
		display: inline;
	}
	
	.sku-item{
		display: inline;
		box-sizing: border-box;
		padding: 16px;
		background-color: gainsboro;
		border-radius: 15px;
	}
	
	.mini-btn{
		margin: 0px 20px;
		margin-bottom: 20px;
	}
	
	.active {
		color: rgba(165, 179, 169, 0.6);
		background-color: #ffd58c;
		border-color: #179b16;
	  }
		
	.title{
		padding: 16px;
	}
</style>