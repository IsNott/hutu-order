<template>
	<scroll-view class="body">
		<view class="header">
			<swiper class="swiper" circular :indicator-dots="indicatorDots" autoplay="true">
				<swiper-item :key="index" v-for="(img,index) in item.itemImageUrls">
					<image :src="img" mode="aspectFit" class="img" />
				</swiper-item>
			</swiper>
		</view>
		<scroll-view class="context">
			<item-info :desc="item.itemDesc" :name="item.itemName" :tags="tagArray"></item-info>
			<sku-item-info v-for="(item,index) in skuList" :catalog-name="item.skuCatalogName"
			:sku-item="item.skuItems"
			></sku-item-info>
			<goods-footer :num="currentNum" @chooseItem="handleChooseItem" @addToPackage="handleAddPackage"/>
		</scroll-view>
	</scroll-view>
</template>

<script>
	const empty = {
		actuallyAmount: '120',
		itemDesc: '香甜焦糖与浓郁咖啡的美妙组合',
		itemId: '',
		itemImageUrls: [
			'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
			'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132'
		],
		itemName: '商品',
		itemTag: '测试,测试标签',
		memuCatalogId: '',
		originAmount: '123',
		special: ''
	}
	const emptySku = {
		skuCatalogName: '测试',
		displayOrder: 1,
		skuItems: [{
			id: 1,
			skuItemContent: '测试'
		}, {
			id: 2,
			skuItemContent: '测试1'
		}, {
			id: 3,
			skuItemContent: '测试2'
		}, {
			id: 4,
			skuItemContent: '测试2'
		}]
	}
	import ItemInfo from './component/ItemInfo.vue'
	import SkuItemInfo from './component/SkuItemInfo.vue'
	import GoodsFooter from './component/GoodsFooter.vue'
	export default {
		name: 'ItemDetail',
		components: {
			ItemInfo,SkuItemInfo,GoodsFooter
		},
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
				activeIndex: null,
				currentNum: 1
			}
		},
		methods: {
			changeColor(index, item) {
				console.log(index)
				this.activeIndex = index;
			},
			handleChooseItem(num){
				let result = this.currentNum + num;
				if(result >= 0){
					this.currentNum = result;
				}
			},
			handleAddPackage(){
				console.log('add package')
			}
		},
		computed: {
			tagArray() {
				var array = []
				const itemTag = this.item.itemTag;
				if (itemTag) {
					array = itemTag.split(",");
				}
				return array;
			},
			specialTag() {
				var array = []
				const itemTag = this.item.special;
				if (itemTag) {
					array = itemTag.split(",");
				}
				return array;
			}
		}
	}
</script>

<style scoped>
	.body {
		background-color: white;
	}

	.img {
		width: 300px;
		height: 300px;
	}

	.price {
		display: inline;
	}
	

</style>