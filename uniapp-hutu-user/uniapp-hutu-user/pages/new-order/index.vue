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
				<uni-list :border="false">
					<uni-list-item direction="column" :custom-style="{padding:'8px'}">
						<template v-slot:body>
							<view class="catalog-item">
								<image src="@/static/item/coffee.png"
								/>
								<view class="catalog-item-info">
									<text class="item-name">{{item.name}}</text>
									<text class="item-desc">{{item.desc}}</text>
									<view class="item-tag">
										<text v-for="tag in item.tags" v-if="item.tags">
											{{tag}}
										</text>
									</view>
									<text class="item-special" v-for="s in item.special" v-if="item.special">
										{{s}}
									</text>
									<view class="bottom">
										<text class="item-amout">${{item.amount}}</text>
										<uni-icons type="plusempty" size="10"></uni-icons>
									</view>
								</view>	
							</view>
						</template>
					</uni-list-item>
				</uni-list>
			</scroll-view>
		</view>
	</scroll-view>
</template>

<script>
	import ShopInfoCard from './component/ShopInfoCard.vue'
	import MenuCatalogPanel from './component/MenuCatalogPanel.vue'
	export default{
		name:'NewOrder',
		components:{ShopInfoCard,MenuCatalogPanel},
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
					catalogDesc: '测试描述'
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
	
	.current-catalog-desc{
		font-size: 8px;
		color: gray;
	}
	
	.catalog-item{
		display: flex;
		flex-direction: row;
	}
	
	.catalog-item view{
		display: flex;
		flex-direction: column;
	}
	
	.catalog-item image{
		width: 100%;
		height: 76px;
		border-radius: 6%;
		margin-left: 10px;
		object-fit: cover;
	}
	
	.catalog-item text{
		margin: 0px 8px;
	}
	
	.item-tag {
		display: flex;
		flex-direction: row;
	}
	
	.item-tag text{
		display: inline;
	}
	
	.item-name{
		font-size: 16px;
		font-weight: bold;
	}
	
	.item-desc{
		font-size: 10px;
		color: darkgrey;
		display: -webkit-box; /* 设置为弹性布局盒子 */
		-webkit-box-orient: vertical; /* 设置为垂直方向堆叠 */
		overflow: hidden; /* 超出部分隐藏 */
		-webkit-line-clamp: 2; /* 限制显示的行数 */
	}
	
	
	
</style>