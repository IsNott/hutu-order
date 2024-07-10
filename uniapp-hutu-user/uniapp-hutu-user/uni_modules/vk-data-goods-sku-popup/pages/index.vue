<!-- unicloud动态数据演示版本 -->
<template>
	<view class="app">
		<button @click="openSkuPopup()">打开SKU组件</button>
		
		<view class="form-item" v-for="(item1,index1) in selectData" :key="index1">
			<view class="form-item-label">{{ item1.name }}</view>
			<template v-if="item1.type === 'radio'">
				<radio-group name="radio" @change="e => skuPopupData[item1.key] = e.detail.value">
					<view class="radio" v-for="(item2,index2) in item1.data" :key="index2">
						<label>
							<radio :value="item2.value" :checked="skuPopupData[item1.key] === item2.value" />
							<text class="radio-text">{{ item2.text }}</text>
						</label>
					</view>
				</radio-group>
			</template>
			<template v-if="item1.type === 'switch'">
				<switch :checked="skuPopupData[item1.key]" @change="e => skuPopupData[item1.key] = e.detail.value" />
			</template>
			<template v-if="item1.type === 'number-box'">
				<vk-data-input-number-box v-model="skuPopupData[item1.key]" :min="1" :max="10000000" :step="1" :positive-integer="true"></vk-data-input-number-box>
			</template>
			<template v-if="item1.type === 'text'">
				<input class="input" v-model="skuPopupData[item1.key]" />
			</template>
		</view>
		
		<vk-data-goods-sku-popup 
			ref="skuPopup" 
			v-model="skuPopupData.show" 
			border-radius="20"
			:z-index="990"
			:localdata="goodsInfo" 
			:selected-init="true" 
			:mode="skuPopupData.mode" 
			:theme="skuPopupData.theme"
			:show-close="skuPopupData.showClose"
			:hide-stock="skuPopupData.hideStock"
			:mask-close-able="skuPopupData.maskCloseAble"
			:buy-now-text="skuPopupData.buyNowText"
			:add-cart-text="skuPopupData.addCartText"
			:no-stock-text="skuPopupData.noStockText"
			:min-buy-num="skuPopupData.minBuyNum"
			:max-buy-num="skuPopupData.maxBuyNum"
			:step-buy-num="skuPopupData.stepBuyNum"
			:step-strictly="skuPopupData.stepStrictly"
			@open="onOpenSkuPopup"
			@close="onCloseSkuPopup" 
			@cart="addCart" 
			@buy="buyNow"
		></vk-data-goods-sku-popup>
	</view>
</template>

<script>
	/* eslint-disable */
	var goodsCache = {}; // 本地商品缓存
	export default {
		data() {
			return {
				// SKU弹窗模式
				// 后端返回的商品信息
				goodsInfo: {},
				// 默认sku信息
				defaultSku: {},
				// sku弹窗数据
				skuPopupData: { 
					show: false, // 是否打开SKU弹窗
					goods_id: "001",
					mode: "1", // 模式
					theme: "default", // 主题
					hideStock: false, // 是否隐藏库存
					showClose: true, // 显示关闭按钮
					maskCloseAble: true, // 点击遮罩是否允许关闭组件
					buyNowText: "立即购买",
					addCartText: "加入购物车",
					noStockText: "该商品已抢完",
					minBuyNum: 1, // 最小购买数量
					maxBuyNum: 10000, // 最大购买数量
					stepBuyNum: 1, // 步进器步长
					stepStrictly: false, // 是否只能输入 step 的倍数
				},
				selectData: [
					{
						key: "goods_id", 
						name: "模式",
						type: "radio",
						data: [
							{ text: "商品1：多组多规格商品", value: "001" },
							{ text: "商品2：单组多规格商品", value: "002" },
							{ text: "商品3：单组单规格商品", value: "003" },
							{ text: "商品4：暂无库存商品", value: "004" }
						]
					},
					{
						key: "mode", 
						name: "模式",
						type: "radio",
						data: [
							{ text: "都显示", value: "1" },
							{ text: "只显示购物车", value: "2" },
							{ text: "只显示立即购买", value: "3" }
						]
					},
					{
						key: "theme", 
						name: "主题风格",
						type: "radio",
						data: [
							{ text: "默认", value: "default" },
							{ text: "红黑", value: "red-black" },
							{ text: "黑白", value: "black-white" },
							{ text: "咖啡", value: "coffee" },
							{ text: "浅绿", value: "green" },
						]
					},
					{
						key: "showClose", 
						name: "显示关闭按钮",
						type: "switch"
					},
					{
						key: "maskCloseAble", 
						name: "允许点击遮罩关闭组件",
						type: "switch"
					},
					{
						key: "hideStock", 
						name: "隐藏库存",
						type: "switch"
					},
					{
						key: "buyNowText", 
						name: "立即购买按钮文案",
						type: "text"
					},
					{
						key: "addCartText", 
						name: "加入购物车按钮文案",
						type: "text"
					},
					{
						key: "noStockText", 
						name: "无库存时按钮文案",
						type: "text"
					},
					{
						key: "minBuyNum", 
						name: "最小购买数量",
						type: "number-box"
					},
					{
						key: "maxBuyNum", 
						name: "最大购买数量",
						type: "number-box"
					},
					{
						key: "stepBuyNum", 
						name: "步进器步长",
						type: "number-box"
					},
					{
						key: "stepStrictly", 
						name: "只能输入 step 的倍数",
						type: "switch"
					},
				]
			};
		},
		
		// 监听 - 页面每次【加载时】执行(如：前进)
		onLoad(options) {
			this.init(options);
		},
		methods: {
			// 初始化
			init(options = {}) {},
			// 获取商品信息，并打开sku弹窗
			openSkuPopup() {
				/**
				 * 获取商品信息
				 * 这里可以看到每次打开SKU都会去重新请求商品信息，为的是每次打开SKU组件可以实时看到剩余库存
				 */
				let goods_id = this.skuPopupData.goods_id;
				if (goodsCache[goods_id]) {
					this.goodsInfo = goodsCache[goods_id];
					this.skuPopupData.show = true;
				} else {
					uni.showLoading({
						title: "请求中...",
						mask: true
					});
				}
				// 不管是否有缓存，都会重新请求获取最新的商品数据
				uniCloud.callFunction({
					name: "findGoodsInfo",
					data: {
						goods_id: goods_id
					},
					success: (res) => {
						// 更新缓存
						this.goodsInfo = res.result.goodsInfo;
						this.skuPopupData.show = true;
						goodsCache[goods_id] = res.result.goodsInfo;
					},
					fail: (err) => {
						uni.showModal({
							content: "获取商品信息失败",
							showCancel: false
						});
						console.error('err: ', err);
					},
					complete: () => {
						uni.hideLoading();
					}
				})
			},
			// sku组件 开始-----------------------------------------------------------
			onOpenSkuPopup() {
				console.log("监听 - 打开sku组件");

				// 下面的代码作用是当弹窗打开时自动选中指定的sku（注意sku必须存在，且有库存）
				// if (this.defaultSku && this.defaultSku.sku && this.defaultSku.num) {
				// 	this.$refs.skuPopup.selectSku(this.defaultSku);
				// }
			},
			onCloseSkuPopup() {
				console.log("监听 - 关闭sku组件");
			},
			// 加入购物车前的判断
			addCartFn(obj) {
				let { selectShop } = obj;
				// 模拟添加到购物车，请替换成你自己的添加到购物车逻辑
				let res = {};
				let name = selectShop.goods_name;
				if (selectShop.sku_name != "默认") {
					name += "-" + selectShop.sku_name_arr;
				}
				res.msg = `${name} * ${selectShop.buy_num} 已添加到购物车`;
				if (typeof obj.success == "function") obj.success(res);
			},
			// 加入购物车按钮
			addCart(selectShop) {
				console.log("监听 - 加入购物车", selectShop);
				this.addCartFn({
					selectShop: selectShop,
					success: (res) => {
						// 实际业务时，请替换自己的加入购物车逻辑
						this.toast(res.msg);
						setTimeout(() => {
							this.skuPopupData.show = false;
						}, 300);
					}
				});
			},
			// 立即购买
			buyNow(selectShop) {
				console.log("监听 - 立即购买", selectShop);
				this.addCartFn({
					selectShop: selectShop,
					success: (res) => {
						// 实际业务时，请替换自己的立即购买逻辑
						this.toast("立即购买");
					}
				});
			},
			toast(msg) {
				uni.showToast({
					title: msg,
					icon: "none"
				});
			}
		}
	};
</script>

<style lang="scss" scoped>
	.app {
		padding: 30rpx;
		font-size: 28rpx;

		button {
			margin-top: 20rpx;
		}
		
		.tips{
			margin-top: 20rpx;
			color: #999;
		}
		
		.form-item {
			margin-top: 20rpx;
			display: flex;
			.form-item-label {
				font-size: 26rpx;
				width: 260rpx;
				padding: 10rpx 0;
			}
			.input-view {
				display: flex;
				align-items: center;
			}
			.input {
				margin-left: 40rpx;
				border: 1px solid #d6d6d6;
				border-radius: 10rpx;
				padding: 8rpx 30rpx;
				font-size: 28rpx;
			}
			.radio {
				padding: 6rpx 0rpx;
				.radio-text{
					margin-left: 10rpx;
				}
			}
		}
	}
</style>