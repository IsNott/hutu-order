<template>
	<view class="boby">
		<view class="header">
			<uni-search-bar class="uni-mt-10" radius="5" @confirm="queryOrder()" v-model="keyWord" placeholder="搜索订单信息" clearButton="auto"
				cancelButton="none" />
			<view class="state-bar">
				<view v-for="(state,index) in status" :key="state" @click="currentIndex = index"
					:class="{'bar-tabs-active':currentIndex == index}">
					{{state.label}}
				</view>
			</view>
		</view>
		<view class="list" v-if="orderList.length > 0" :key="listKey">
			<view v-for="order in orderList" :key="order.id" class="order-card">
				<view class="title">
					<view class="shop-info" @click="handleShopClick(order.shopId)">
						{{order.shopName}} >
					</view>
					<view class="right">
						<text>{{getOrderStatusVal(order.orderStatus)}}</text>
					</view>
				</view>

				<view class="order-info"  @click="handleOrderCardClick(order.id)">
					<scroll-view class="imgs-scroll" :scroll-x="true" :show-scrollbar="false">
						<view class="img-view">
							<view v-for="item in order.items" :key="item.id">
								<view class="item-view" v-if="item.itemImageUrls && item.itemName">
									<image mode="aspectFill" :src="item.itemImageUrls" class="img" />
									<view class="desc">
										<text>{{item.itemName}}</text>
									</view>
								</view>
							</view>
						</view>
					</scroll-view>
					<view class="order-amount">
						<view>共 <text style=" font-size: 14px;">{{order.items.length}}</text> 件</view>
						<view><text style="font-weight: 600; font-size: 20px;">￥{{order.totalAmount}}</text></view>
					</view>
				</view>
				<view class="bottom">
					<view class="extra">
						<view>下单时间：{{order.createTime}}</view>
						<view>就餐方式：{{getPickTypeVal(order.pickType)}}</view>
					</view>
					<view class="btn-group">
						<view class="left" style="text-align: center;">
							<uni-icons type="more-filled" size="24" />
						</view>
						<view class="right">
							<button v-if="order.orderStatus == 0" class="child-btn" type="default" size="mini"
								style="color: #3030ff;border:1px solid #3030ff;">去支付</button>
							<button v-if="order.orderStatus == 0" class="child-btn" type="default" size="mini"
								style="color: #3030ff;border:1px solid #3030ff;">取消</button>
							<button v-if="order.orderStatus == 6" class="child-btn" size="mini"
								style="color: #000000;border:1px solid #000000;" @click="handleCommentClick(order.id)">去评价</button>
							<button v-if="order.orderStatus == 2 && showRefund" class="child-btn" size="mini"
								style="color: black;border:1px solid black;">申请退款</button>
							<button v-if="order.orderStatus == 2 || order.orderStatus == 6" class="child-btn"
								style="color: #3030ff;border:1px solid #3030ff;" size="mini" @click="handleMoreOrderClick(order.shopId)">再来一单</button>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="no-info" v-else>
			<view>暂无更多信息，先去挑选商品吧</view>
		</view>
	</view>
</template>

<script>
	import {
		getPickType,
		getOrderStatus,
		commonNavigate
	} from '@/utils/CommonUtils'
	import {
		queryMyOrder,queryShopInfoById
	} from '@/api/my-order'
	export default {
		name: 'MyOrder',
		data() {
			return {
				keyWord: '',
				listKey: new Date().getTime(),
				status: [{
						label: '全部',
						val: undefined
					},
					{
						label: '待付款',
						val: 0
					},
					{
						label: '已支付',
						val: 2
					},
					{
						label: '已退款',
						val: 5
					},
					{
						label: '已完成',
						val: 6
					},
				],
				orderList: [],
				currentIndex: 0,
				showRefund: false
			}
		},
		watch: {
			currentIndex(n, o) {
				this.queryOrder()
			}
		},
		created() {
			this.queryOrder()
		},
		methods: {
			handleOrderCardClick(orderId){
				commonNavigate('/pages/settled/index?orderId=' + orderId)
			},
			getImgs(order) {
				var array = []
				if (order && order.items && order.items.length > 0) {
					order.items.forEach(item => {
						if (item.itemImageUrls) {
							array.push(item.itemImageUrls)
						}
					})
				}
				return array
			},
			handleShopClick(shopId) {
				this.handleMoreOrderClick(shopId)
			},
			handleCommentClick(orderId){
				uni.showToast({
					icon: 'success',
					title: '开发中'
				})
			},
			getPickTypeVal(val) {
				return getPickType(val)
			},
			getOrderStatusVal(val) {
				return getOrderStatus(val)
			},
			queryOrder() {
				const param = {
					status: this.getStatus,
					keyWord: this.keyWord
				}
				queryMyOrder(param, 1, 30).then(res => this.orderList = res.data.records)
			},
			handleMoreOrderClick(shopId){
				queryShopInfoById(shopId).then(res =>{
					const shopInfo = res.data
					uni.setStorage({
						data: shopInfo,
						key: 'current_shop',
						success:() => {
							uni.switchTab({
								url: '/pages/order/index'
							})
						}
					})
				})
			}
		},
		computed: {
			getStatus() {
				return this.status[this.currentIndex].val
			}
		}
	}
</script>

<style scoped>
	.state-bar {
		display: flex;
		flex-direction: row;
		text-align: center;
		padding: 10px 20px;
		margin: 0px 20px;
		justify-content: space-between;
	}

	.uni-mt-10 {
		margin: 0px;
	}

	.header {
		margin-bottom: 20px;
	}

	.bar-tabs-active {
		color: #23abff;
		border-bottom: 2px solid #06a8ff;
	}

	.list {
		display: flex;
		flex-direction: column;
	}

	.title {
		margin: 10px 0px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.shop-info {
		/* margin: 14px 0px; */
		font-weight: 600;
		font-size: 16px;
	}

	.imgs-scroll {
		width: 70%;
	}

	.img-view {
		display: flex;
		flex-direction: row;
	}

	.img {
		width: 76px;
		margin-right: 6px;
		height: 76px;
		border-radius: 10px;
		overflow: hidden;
	}

	.desc {
		text-align: center;
		font-size: 12px;
		color: gray;
	}

	.order-card {
		border-radius: 8px;
		padding: 20px;
		margin: 0px 24px;
		border: 1px solid #c7c9ce;
		margin-bottom: 20px;
		/* background-color: #d6d6d6; */
	}

	.order-info {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.order-amount {
		margin: auto;
		text-align: center;
		background-color: transparent;

		/* background-color: rgba(255, 255, 255, 0.2); */
		backdrop-filter: blur(10px);
	}

	.extra {
		margin: 12px 0px;
		color: gray;
	}

	.btn-group {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.bottom {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.left {
		text-align: center;
	}

	.child-btn {
		margin: 0px 8px;
		border-radius: 24px;
		font-size: 12px;
	}

	.item-view {
		display: flex;
		flex-direction: column;
	}

	.no-info {
		text-align: center;
		color: gray;
		font-size: 18px;
		margin: 30px 0px;
	}
</style>