<template>
	<view class="main">
		<view class="header" @click="handlerSearchMerchant">
			<view class="header-left">
				<h4>{{ currentMerchantName }}</h4>
				<view style="font-size: 5px;">{{ currentDistance }}</view>
			</view>
			<view class="header-reight">
				<uni-icons type="right" size="16"></uni-icons>
			</view>
		</view>
		<view class="bottom">
			<scroll-view id="menuCatalogList">
				<uni-list :border="false">
					<uni-list-item direction="column" v-for="(item,index) in menuCatalog" :key="item.id"
						:clickable="true" @click="handlerCatalogClick(item.id)">
						<template v-slot:header>
							<!-- todo 增加图片 -->
							<view class="slot-box">
								<!-- <image class="slot-image" src="/static/logo.png" mode="aspectFill"> -->
								<!-- </image> -->
							</view>
						</template>
						<template v-slot:body>
							<text style="font-size: 8px;" class="slot-box slot-text">{{ item.catalogName}}</text>
						</template>
					</uni-list-item>

				</uni-list>
			</scroll-view>
			<scroll-view id="menuItemList">
				<view style="display: flex;flex-direction: column;margin-top: 4px;margin-bottom: 4px;">
					<text style="font-size: 16px;">{{ currentMenu ? currentMenu.catalogName : '' }}</text>
					<text style="font-size: 10px;color:darkgray;">{{ currentMenu ? currentMenu.catalogDesc : '' }}</text>
				</view>
				<view class="list" style="display: flex;flex-direction: column;">
					<view class="itemCard" style="margin-top: 12px;display: flex;flex-direction: row;justify-content: space-between;" v-for="(item,index) in currentItemList" :key="item.id">
						<image src="@/static/c1.png" style="width: 30px;height: 30px;"></image>
						<view style="display: flex;flex-direction: column;padding-left: 4px;text-align: left;width: 76%;">
							<text style="font-size: 14px;">{{item.itemName}}</text>
							<text style="font-size: 8px;color: gray;">{{item.itemDesc}}</text>
							<view style="display: flex;flex-direction: row;justify-content: space-between;">
								<text>￥ {{item.originAmount}}</text>
								<uni-icons size="14" type="plusempty" @click="handlerItemSku(item.itemId)"></uni-icons>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
		<uni-fab ref="fab" :pattern="pattern" :horizontal="'right'" :vertical="'bottom'" />
	</view>
</template>

<script>
	import {
		getMenuCatalog,
		listByCatalogId
	} from '@/api/order.js'
	import {
		request
	} from "@/request/request.js";
	export default {
		data() {
			return {
				searchText: '',
				pattern: {
					icon: 'search',
					buttonColor: '#EEEEE'
				},
				currentMerchantName: '',
				currentDistance: '',
				menuCatalog: [],
				menuCataActiveId: '',
				currentItemList: []
			};
		},
		onLoad() {
			this.queryCurrentMerchant();
		},
		methods: {
			queryMenuCatalog() {
				request('/bizMenuCatalog/list', 'GET').then(res => {
					this.menuCatalog = res.data
					if(!this.menuCataActiveId && this.menuCatalog.length > 0){
						this.handlerCatalogClick(this.menuCatalog[0].id)
					}
				})
			},
			handlerSearchMerchant() {
				console.log('click search');
			},
			handlerCatalogClick(id) {
				this.menuCataActiveId = id;
				this.searchItemByMenuCatalogId(id)
			},
			searchItemByMenuCatalogId(id) {
				listByCatalogId(id).then(res => {
					this.currentItemList = res.data
					console.log(this.currentItemList);
				})
			},
			handlerItemSku(id){
				console.log('itemClick',id);
			},
			queryCurrentMerchant(){
				const val = uni.getStorageSync('current_shop');
				if(val){
					this.currentMerchantName = val.shopName;
					this.currentDistance = val.distance;
					this.queryMenuCatalog();
				}else{
					uni.navigateTo({
						url: '/pages/shop/index'
					})
				}
			}

		},
		watch: {
			menuCatalog: {
				handler(oldVal, newVal) {
					if (newVal && this.menuCataActiveId.length > 0) {
						this.menuCataActiveId = newVal[0].id
					}
				},
				immediate:true
			}
		},
		computed: {
			currentMenu() {
				return this.menuCatalog.find(val => val.id === this.menuCataActiveId)
			},
		}
	}
</script>

<style lang="scss">
	.main {
		display: flex;
		flex-direction: column;
		background-color: white;
	}

	.header {
		margin: 10px;
		display: flex;
		flex-direction: row;
	}

	.bottom {
		margin: 10px;
		display: flex;
		flex-direction: row;
	}

	#menuItemList {
		display: flex;
		flex-direction: column;
		margin: 14px;
	}

	#menuCatalogList {
		margin-right: 10px;
		width: 34%;
	}

	
</style>