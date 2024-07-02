<template>
	<view :disabled="open" @click="chooseShop()" hover-class="click" :class="open ? 'card' : 'closeCard'">
		<view>
			<uni-icons type="shop" size="18" />
			<text class="title">
				{{ shopInfo.shopName}}
			</text>
		</view>
		<view>
			<uni-icons type="location" size="18" />
			<text class="address">
				{{ shopInfo.address}}
			</text>
		</view>
		<view>
			<uni-icons :type='open ? "checkbox" : "close"' size="18" />
			<text class="close">
				{{ open ? '营业中' : '店休'}}
			</text>
		</view>
		<view>
			<uni-icons type="star" size="18" />
			<text class="day">
				{{_getWeekName(shopInfo.weekStartDate)}} - {{_getWeekName(shopInfo.weekEndDate)}} : {{ shopInfo.startBusinessTime}} - {{ shopInfo.endBusinessTime}}
			</text>
		</view>
	</view>
</template>

<script>
	const empty = {
		shopName:'',
		address:'',
		closeNow: '',
		weekStartDate: '',
		weekEndDate: '',
		startBusinessTime: '',
		endBusinessTime: '',
		open: ''
	}
	import getWeekName from '../../../utils/CommonUtils'
	export default{
		name:'ShopCard',
		data(){
			return{
				_getWeekName: ''
			}
		},
		props:{
			shopInfo:{
				type: Object,
				default: empty
			}
		},
		created() {
			this._getWeekName = getWeekName;
		},
		methods:{
			chooseShop(){
				if(this.open){
					this.$emit('chooseShop',this.shopInfo)
				}
			}
		},
		computed:{
			open(){
				return this.shopInfo.closeNow !== 1 && this.shopInfo.open
			}
		}
	}
</script>

<style scoped>
	.card {
		margin: 8px 20px;
		border: 1px solid whitesmoke;
		border-radius: 10px;
		padding: 12px;
		text-align: left;
	}
	
	.click{
		/* margin: 8px 20px; */
		border: 1px solid black;
		/* border-radius: 10px;
		padding: 12px;
		text-align: left; */
	}
	
	.closeCard {
		margin: 8px 20px;
		border: 1px solid whitesmoke;
		border-radius: 10px;
		padding: 12px;
		text-align: left;
	}
	
	.close view {
		margin: 6px 0px;
		display: block;
	}
	
	.card view {
		margin: 6px 0px;
		display: block;
	}
	
	uni-icons {
		margin: 0;
		margin-left: 2px;
	}
	.closeCard text{
		color: gray;
	}
</style>