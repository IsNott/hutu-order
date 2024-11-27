<template>
	<scroll-view class="body">
		<view class="header">
			<swiper class="swiper" :circular="true" :indicator-dots="false" :autoplay="true">
				<swiper-item :key="index" v-for="(img,index) in previewUrls">
					<image :src="img ? img : require('@/static/image/not-image.png')" mode="scaleToFill" class="img" />
				</swiper-item>
			</swiper>
		</view>
		<scroll-view class="context">
			<item-info v-if="item" :desc="item.itemDesc" :name="item.itemName" :tags="tagArray"></item-info>
			<sku-item-info :key="item.catalogId" :catalog-id="item.catalogId" @change-select="handleItemSelect"
				v-for="(item,index) in skuList" :catalog-name="item.skuCatalogName" :sku-item="item.skuItems" />
			<goods-footer :num="currentNum" @chooseItem="handleChooseItem" @addToPackage="handleAddPackage" />
		</scroll-view>
	</scroll-view>
</template>

<script>
	const empty = {
		actuallyAmount: '',
		itemDesc: '',
		itemId: '',
		itemImageUrls: '',
		itemName: '',
		itemTag: '',
		memuCatalogId: '',
		originAmount: '',
		special: ''
	}
	const emptySku = {
		skuCatalogName: '',
		displayOrder: '',
		catalogId: '',
		skuItems: [{
			id: '',
			skuItemContent: ''
		}]
	}
	import {
		addPackage
	} from '@/api/order'
	import {
		handleImageUrlArray
	} from '@/utils/CommonUtils'
	import ItemInfo from './component/ItemInfo.vue'
	import SkuItemInfo from './component/SkuItemInfo.vue'
	import GoodsFooter from './component/GoodsFooter.vue'
	export default {
		name: 'ItemDetail',
		components: {
			ItemInfo,
			SkuItemInfo,
			GoodsFooter
		},
		data() {
			return {
				current: 0,
				mode: 'default',
				dotsStyles: {},
				swiperDotIndex: 0,
				sku: emptySku,
				activeIndex: null,
				currentNum: 1,
				item: '',
				skuList: [],
				selectSkuList: []
			}
		},
		onLoad: function(option) {
			const item = JSON.parse(decodeURIComponent(option.item))
			const skuList = JSON.parse(decodeURIComponent(option.skuList))
			this.item = item
			this.skuList = skuList
		},
		methods: {
			changeColor(index, item) {
				this.activeIndex = index
			},
			handleChooseItem(num) {
				let result = this.currentNum + num
				if (result >= 1) {
					this.currentNum = result
				}
			},
			handleAddPackage() {
				addPackage({
					itemId: this.item.itemId,
					itemPiece: this.currentNum,
					skuItemContents: this.selectSkuList.map(r => r.content).join(',')
				}).then(res => {
					uni.navigateBack();
				})
			},
			handleItemSelect(param) {
				let selected = this.selectSkuList.find(r => r.catalogId === param.catalogId)
				if (selected) {
					selected.selectItemId = param.selectItemId
				} else {
					this.selectSkuList.push(param)
				}
				console.log(this.selectSkuList)
			}
		},
		computed: {
			tagArray() {
				var array = []
				const itemTag = this.item.itemTag
				if (itemTag) {
					array = itemTag.split(",")
				}
				return array;
			},
			specialTag() {
				var array = []
				const itemTag = this.item.special
				if (itemTag) {
					array = itemTag.split(",")
				}
				return array;
			},
			previewUrls() {
				var urls = handleImageUrlArray(this.item.itemImgeUrls)
				return urls
			}
		}
	}
</script>

<style scoped>
	.body {
		background-color: white;
	}

	.swiper-item {
		display: block;
		line-height: 300upx;
		text-align: center;
	}

	.img {
		width: 100%;
		height: 300upx;
	}

	.price {
		display: inline;
	}

	.header {
		height: 100%;
		margin: 0 0upx;
	}

	.swiper {
		height: 300upx;
	}
</style>