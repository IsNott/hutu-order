<template>
	<uni-list :border="false">
		<uni-list-item direction="column" :custom-style="{padding:'8px'}">
			<template v-slot:body>
				<view class="catalog-item">
					<image src="@/static/item/coffee.png"
					/>
					<view class="catalog-item-info">
						<view class="catalog-colum">
							<text class="item-name">{{item.name}}</text>
							<text class="item-desc">{{item.desc}}</text>
						</view>
						<view class="item-tag">
							<text v-for="tag in item.tags" 
							v-if="item.tags">
								{{tag}}
							</text>
							<text class="item-special" v-for="s in item.special" v-if="item.special">
								{{s}}
							</text>
						</view>
						
						<view class="bottom">
							<text class="item-amout">${{item.amount}}</text>
							<uni-icons type="plusempty" size="10"></uni-icons>
						</view>
					</view>	
				</view>
			</template>
		</uni-list-item>
	</uni-list>
</template>

<script>
	const empty = {
		name: '',
		desc: '',
		tags: [],
		special: [],
		amount: 0
	}
	export default {
		name: 'ProductCard',
		components: {},
		props: {
			item: {
				type: Object,
				default: empty
			}
		},
		data() {
			return {

			}
		},
	}
</script>

<style scoped>
	.catalog-item{
		display: flex;
		flex-direction: row;
	}
	
	.catalog-colum{
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
	
	.catalog-item-info{
		margin: 0px 8px;
	}
	
	.item-tag {
		display: inline;
		font-size: 6px;
	}
	
	.item-tag text{
		padding: 2px;
		margin: 0px;
		font-weight: 550;
		margin-right: 4px;
		border: 1px black solid;
		border-radius: 2px;
	}
	
	.item-tag .item-special{
		color: #ff3318;
		background-color: #FFE2E2;
		border: none;
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
	
	.bottom{
		margin-top: 10px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
</style>