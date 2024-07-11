<template>
	<scroll-view>
		<view class="header">
			<swiper class="swiper" circular :indicator-dots="indicatorDots" autoplay="true">
				<swiper-item :key="img" v-for="img in item.itemImageUrls">
					<image :src="img" mode="aspectFit" class="img"/>
				</swiper-item>
			</swiper>
		</view>
		<scroll-view class="context">
			<uni-title type="h3" title="h5 左对齐" align="left"></uni-title>
		</scroll-view>
	</scroll-view>
</template>

<script>
	const empty = {
		actuallyAmount: '',
		itemDesc: '',
		itemId: '',
		itemImageUrls: [
			'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132'
		],
		itemName: '',
		itemTag: '',
		memuCatalogId: '',
		originAmount: '',
		special: ''
	}
	const emptySku = {
		skuCatalogName:'测试',
		displayOrder: 1,
		skuItems:[{
			id: 1,
			skuItemContent:'测试'
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
				indicatorDots: true
			}
		},
		methods: {
			change() {}
		}
	}
</script>

<style scoped>
	.img {
		width: 300px;
		height: 300px;
	}
</style>