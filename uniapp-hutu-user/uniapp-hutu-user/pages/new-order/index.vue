<template>
	<scroll-view>
		<shop-info-card :current-merchant='currentMerchant'/>
		<view class="body">
			<scroll-view class="left">
				<menu-catalog-panel :menu-catalog="menuCatalog"/>
			</scroll-view>
			<scroll-view class="right">
				<view class="body-right-header">
					<text class="current-catalog-name">{{currentMenu.catalogName}}\n</text>
					<text class="current-catalog-desc">{{currentMenu.catalogDesc}}</text>
				</view>
				<product-card :item="item"/>
				<product-card :item="item"/>
				<product-card :item="item"/>
				<product-card :item="item"/>
				<product-card :item="item"/>
				<product-card :item="item"/>
			</scroll-view>
		</view>
	</scroll-view>
</template>

<script>
	import ShopInfoCard from './component/ShopInfoCard.vue'
	import MenuCatalogPanel from './component/MenuCatalogPanel.vue'
	import ProductCard from './component/ProductCard.vue'
	export default{
		name:'NewOrder',
		components:{ShopInfoCard,MenuCatalogPanel,ProductCard},
		data(){
			return{
				menuCatalog:[{
					id:1,
					catalogName: '美式咖啡'
				},{
					id:2,
					catalogName: '意式咖啡'
				},
				{
					catalogName: '澳式咖啡'
				}],
				currentMerchant:{
					shopName: '糊涂餐馆（桐城路）',
					address: '桐城路159号402室'
				},
				item:{
					name:'一杯Java',
					desc:'常常被赞誉为一种带有浓郁历史传统的咖啡，源自印度尼西亚的爪哇岛。这种咖啡以其独特的风味和口感而闻名于世。',
					tags:['好喝','热饮','适合冬天','推荐'],
					special:['暖和'],
					amount:20
				},
				currentMenu: {
					catalogName: '美式咖啡',
					catalogDesc: '测试描述这是一些文本。这是一些文本。这是一些文本。这是一些文本。这是一些文本'
				},
			}
		},
		methods:{
			
		}
	}
</script>

<style scoped>
	
	.body{
		display:flex;
		flex-direction: row;
	}
	
	.left{
		text-align: center;
		height: 100%;
		width: 40%;
	}
	.right{
		/* width: 70%; */
	}
	
	.body-right-header{
		background-color: white;
		padding: 16px;
	}
	
	.current-catalog-name{
		font-size: 16px;
		font-weight: 590;
	}
	
	.current-catalog-desc{
		font-size: 8px;
		color: gray;
	}
	
	
	
	
	
</style>