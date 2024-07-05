<template>
	<uni-list :border="false">
		<uni-list-item direction="column" :custom-style="{padding:'8px'}">
			<template v-slot:body>
				<view class="catalog-item">
					<image src="@/static/item/coffee.png"
					/>
					<view class="catalog-item-info">
						<view class="catalog-colum">
							<text class="item-name">{{item.itemName}}</text>
							<text class="item-desc">{{item.itemDesc}}</text>
						</view>
						<view class="item-tag">
							<text v-for="(tag,index) in splitTag(item.itemTag)" 
							v-if="item.itemTag" :key="index">
								{{tag}}
							</text>
							<text class="item-special" v-for="(s,index) in splitTag(item.special)" :key="index + key" v-if="item.special">
								{{s}}
							</text>
						</view>
						
						<view class="bottom">
							<text class="item-amout">${{item.actuallyAmount}}</text>
							<uni-icons @click="handlerClick" type="plusempty" size="10"></uni-icons>
						</view>
					</view>	
				</view>
			</template>
		</uni-list-item>
	</uni-list>
</template>

<script>
	const empty = {
		itemId: '',
		itemName: '',
		itemDesc: '',
		itemTag: '',
		special: [],
		itemImgeUrls: [],
		actuallyAmount: 0
	}
	import { getDateStr } from '../../../utils/CommonUtils'
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
				key: new Date().getTime().toString()
			}
		},
		methods:{
			splitTag(str){
				return str.split(',')
			},
			handlerClick(){
				this.$emit('selectItem',this.item.itemId)
			}
		}
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
		width: 76px;
		height: 76px;
		border-radius: 6%;
		margin-left: 10px;
		object-fit: cover;
	}
	
	.catalog-item-info{
		margin: 0px 8px;
		width: 60%;
	}
	
	.item-tag {
		display: inline;
		font-size: 10px;
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