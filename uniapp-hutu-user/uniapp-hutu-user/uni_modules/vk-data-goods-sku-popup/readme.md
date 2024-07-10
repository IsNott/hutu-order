### 【开箱即用】商品sku选择器组件豪华独立版（打造uni插件市场功能最全的SKU选择器组件）
### 插件名称：`vk-data-goods-sku-popup`
### 插件类型：`业务型数据驱动组件`
### 作者：`VK`

### 介绍

此插件为 [vk-unicloud-router](https://ext.dcloud.net.cn/plugin?id=2204) 插件的一部分独立出来而形成的。

商品SKU选择器组件一般用于电商商品详情页的规格选择时使用。

### 插件下载地址：[点击下载SKU组件](https://ext.dcloud.net.cn/plugin?id=2848)

### 【重要】自`1.1.0`版本起，组件已定义成`datacom`数据驱动式组件，组件名称已修改成`vk-data-goods-sku-popup` 原名称`vk-u-goods-sku-popup`
### 什么是datacom?

```
datacom，全称是data components，数据驱动的组件。

这种组件也是vue组件，是一种子类，是基础组件的再封装。

相比于普通的vue组件，datacom组件的特点是，给它绑定一个data，data中包含一组候选数据，即可自动渲染出结果。

```

**而业务型数据驱动组件是更高一级的封装，它直接服务于业务需求，做到开箱即用！**

### datacom对于开发者的好处

datacom组件，对服务器数据规范、前端组件的数据输入和输出规范，做了定义。它提升了产业的标准化程度、细化了分工，提升了效率。

且不论产业影响，对开发者个人而言，显而易见的好处也很多：

```
更少的代码量。从前述的传统写法对比可见，使用datacom的前端页面，代码量可减少一半以上。
设计更加清晰。服务器端给符合规范的数据，然后接受选择的结果数据。中间的ui交互无需关心。
```

### 体验地址

![](https://vkceyugu.cdn.bspapp.com/VKCEYUGU-cf0c5e69-620c-4f3c-84ab-f4619262939f/f1a5ba34-b536-41f0-8894-bef78ae0718e.png)

### 插件示例版运行步骤

1. 上传部署云函数cloudfunctions目录下的 `findGoodsInfo`
2. 运行项目即可

### 组件安装到自己项目步骤

1. 将`components`目录下的`vk-data-goods-sku-popup` 和 `vk-data-input-number-box` 复制到你项目根目录下的`components`目录下 (若你的项目根目录下无`components`则先新增一个`components`目录)
2. 通过下面的基本使用示例的方式使用组件，API文档 在最下面

#### 基本使用示例

```html
<!-- 静态数据演示版本 适合任何后端 -->
<template>
	<view class="app">
		<button @click="openSkuPopup()">打开SKU组件</button>

		<vk-data-goods-sku-popup
			ref="skuPopup"
			v-model="skuKey"
			border-radius="20"
			:z-index="990"
			:localdata="goodsInfo"
			:mode="skuMode"
			@open="onOpenSkuPopup"
			@close="onCloseSkuPopup"
			@add-cart="addCart"
			@buy-now="buyNow"
		></vk-data-goods-sku-popup>
	</view>
</template>

<script>
export default {
	data() {
		return {
			// 是否打开SKU弹窗
			skuKey: false,
			// SKU弹窗模式
			skuMode: 1,
			// 后端返回的商品信息
			goodsInfo: {}
		};
	},
	// 监听 - 页面每次【加载时】执行(如：前进)
	onLoad(options) {
		this.init(options);
	},
	methods: {
		// 初始化
		init(options = {}) {},
		// 获取商品信息，并打开sku弹出
		openSkuPopup() {
			/**
			 * 获取商品信息
			 * 这里可以看到每次打开SKU都会去重新请求商品信息,为的是每次打开SKU组件可以实时看到剩余库存
			 */
			// 此处写接口请求，并将返回的数据进行处理成goodsInfo的数据格式，
			// goodsInfo是后端返回的数据
			this.goodsInfo = {
				"_id": "001",
				"name": "iphone11",
				"goods_thumb": "https://img14.360buyimg.com/n0/jfs/t1/59022/28/10293/141808/5d78088fEf6e7862d/68836f52ffaaad96.jpg",
				"sku_list": [
					{
						"_id": "001",
						"goods_id": "001",
						"goods_name": "iphone11",
						"image": "https://img14.360buyimg.com/n0/jfs/t1/79668/22/9987/159271/5d780915Ebf9bf3f4/6a1b2703a9ed8737.jpg",
						"price": 19800,
						"sku_name_arr": ["红色", "128G", "公开版"],
						"stock": 1000
					},
					{
						"_id": "002",
						"goods_id": "001",
						"goods_name": "iphone11",
						"image": "https://img14.360buyimg.com/n0/jfs/t1/52252/35/10516/124064/5d7808e0E46202391/7100f3733a1c1f00.jpg",
						"price": 9800,
						"sku_name_arr": ["白色", "256G", "公开版"],
						"stock": 100
					},
					{
						"_id": "003",
						"goods_id": "001",
						"goods_name": "iphone11",
						"image": "https://img14.360buyimg.com/n0/jfs/t1/79668/22/9987/159271/5d780915Ebf9bf3f4/6a1b2703a9ed8737.jpg",
						"price": 19800,
						"sku_name_arr": ["红色", "256G", "公开版"],
						"stock": 1
					}
				],
				"spec_list": [
					{
						"name": "颜色",
						"list": [
							{ "name": "红色" },
							{ "name": "黑色" },
							{ "name": "白色" }
						]
					},
					{
						"name": "内存",
						"list": [
							{ "name": "128G" },
							{ "name": "256G" }
						],
					},
					{
						"name": "版本",
						"list": [
							{ "name": "公开版" },
							{ "name": "非公开版" }
						]
					}
				]
			};
			this.skuKey = true;
		},
		// sku组件 开始-----------------------------------------------------------
		onOpenSkuPopup() {
			console.log("监听 - 打开sku组件");
		},
		onCloseSkuPopup() {
			console.log("监听 - 关闭sku组件");
		},
		// 加入购物车前的判断
		addCartFn(obj) {
			let { selectShop } = obj;
			// 模拟添加到购物车,请替换成你自己的添加到购物车逻辑
			let res = {};
			let name = selectShop.goods_name;
			if (selectShop.sku_name != "默认") {
				name += "-" + selectShop.sku_name_arr;
			}
			res.msg = `${name} 已添加到购物车`;
			if (typeof obj.success == "function") obj.success(res);
		},
		// 加入购物车按钮
		addCart(selectShop) {
			console.log("监听 - 加入购物车");
			this.addCartFn({
				selectShop: selectShop,
				success: res => {
					// 实际业务时,请替换自己的加入购物车逻辑
					this.toast(res.msg);
					setTimeout(() => {
						this.skuKey = false;
					}, 300);
				}
			});
		},
		// 立即购买
		buyNow(selectShop) {
			console.log("监听 - 立即购买");
			this.addCartFn({
				selectShop: selectShop,
				success: res => {
					// 实际业务时,请替换自己的立即购买逻辑
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
}
</style>
```

## API

### Props

| 参数                   | 说明                                                | 类型      | 默认值    | 可选值        |
|------------------------|----------------------------------------------------|---------|--------|------------|
| v-model                | 双向绑定，true为打开组件，false为关闭组件             | Boolean | false  | true、false |
| no-stock-text          | 该商品已抢完时的按钮文字                              | String  | 该商品已抢完 | -          |
| stock-text             | 库存文字                                            | String | 库存           | - |
| mode                   | 模式 1:都显示  2:只显示购物车 3:只显示立即购买        | Number          | 1           | 1、2、3      |
| mask-close-able        | 点击遮罩是否关闭组件 true 关闭 false 不关闭 默认true  | Boolean         | true        | true、false |
| border-radius          | 顶部圆角值                                          | [String,Number] | 0           | -          |
| min-buy-num            | 最小购买数量                                        | Number          | 1           | -          |
| max-buy-num            | 最大购买数量                                        | Number          | 100000      | -          |
| step-buy-num           | 每次点击后的数量                                     | Number    
| step-strictly  | 是否只能输入 step 的倍数                             | Boolean      | false           | true、false          |
| hide-stock    | 是否隐藏库存的显示                             | Boolean      | false           | true、false          |
| theme        | 主题风格                             | String      | default     | default、red-black、black-white、coffee、green  |
| localdata    | 商品信息本地数据源                | Object      | -     | - |
| safe-area-inset-bottom    | 是否开启底部安全区适配            | Boolean      | true     | true、false  |
| amount-type            | 默认金额会除以100（即100=1元），若设置为0，则不会除以100（即1=1元）   | Number  | 1    | 0             |
| custom-action          | 自定义获取商品信息的函数（已知支付宝不支持，支付宝请改用localdata属性）                              | Function        | null        | -          |
| show-close             | 是否显示右上角关闭按钮                                | Boolean | true | true、false |
| close-image            | 关闭按钮的图片地址                                    | String  | -    | -             |
| z-index            | 弹窗的z-index值                                    | Number  | 990    | -             |
| price-color            | 价格的字体颜色                                        | String          | #fe560a     | -          |
| buy-now-text             | 立即购买 - 按钮的文字                      | String | 立即购买    | - |
| buy-now-color            | 立即购买 - 按钮的字体颜色                  | String | #ffffff | - |
| buy-now-background-color  | 立即购买 - 按钮的背景颜色                  | String | #fe560a | - |
| add-cart-text            | 加入购物车 - 按钮的文字                    | String | 加入购物车   | - |
| add-cart-color           | 加入购物车 - 按钮的字体颜色                | String | #ffffff | - |
| add-cart-background-color | 加入购物车 - 按钮的背景颜色                 | String | #ff9402 | - |
| goods-thumb-background-color         | 商品缩略图背景颜色                  | String          | - | -          |
| disable-style           | 样式 - 不可点击时,按钮的样式  | Object | null    | - |
| actived-style           | 样式 - 按钮点击时的样式     | Object | null    | - |
| btn-style               | 样式 - 按钮常态的样式      | Object | null    | - |
| goods-id-name            | 字段名 - 商品表id的字段名      | String | _id          | - |
| sku-id-name              | 字段名 - sku表id的字段名     | String | _id          | - |
| sku-list-name            | 字段名 - 商品对应的sku列表的字段名 | String | sku_list     | - |
| spec-list-name           | 字段名 - 商品规格名称的字段名     | String | spec_list    | - |
| stock-name              | 字段名 - sku库存的字段名      | String | stock        | - |
| sku-arr-name             | 字段名 - sku组合路径的字段名(数组元素的顺序需要和specListName对应，详情请看下方)                            | String | sku_name_arr | - |
| goods-thumb-name         | 字段名 - 商品缩略图字段名(未选择sku时)                   | String          | goods_thumb | -          |

### Event

| 事件名   | 说明                    | 回调参数 |
|----------|------------------------|------|
| open     | 打开组件时                  |      |
| close    | 关闭组件时                             |      |
| cart | 点击添加到购物车时（需选择完SKU才会触发） |   selectShop：当前选择的sku数据    |
| buy | 点击立即购买时（需选择完SKU才会触发） |    selectShop：当前选择的sku数据    |

## 重要说明

`skuArrName（sku_name_arr）` 和 `specListName（spec_list）` 对应顺序

```js
// 为了方便说明，这里只展示sku_name_arr和spec_list字段
{
	"_id":"001",
	"sku_list": [
		{
			"sku_name_arr": ["红色", "128G", "公开版"],
		}
	],
	"spec_list": [
		{
			"name": "颜色",
			"list": [{"name": "红色"},{"name": "黑色"},{"name": "白色"}]
		},
		{
			"name": "内存",
			"list": [{"name": "128G"},{"name": "256G"}]
		},
		{
			"name": "版本",
			"list": [{"name": "公开版"},{"name": "非公开版"}]
		}
	]
}

```

1. `sku_name_arr` 数组的第一个值 `sku_name_arr[0]` = `spec_list[0].list` 中的任意一个元素的 `name` 属性的值

2. `sku_name_arr` 数组的第二个值 `sku_name_arr[1]` = `spec_list[1].list` 中的任意一个元素的 `name` 属性的值

3. `sku_name_arr` 数组的第三个值 `sku_name_arr[2]` = `spec_list[2].list` 中的任意一个元素的 `name` 属性的值

4. 如 `sku_name_arr[0] = "红色"`，则 `spec_list[0].list` 中必须要 `有且只有` 一个元素的 `name` 属性的值为 `"红色"`

### 单规格（无规格）商品格式示例

```js
{
  "_id":"003",
  "name": "迪奥香水",
  "goods_thumb":"https://res.lancome.com.cn/resources/2020/9/11/15998112890781924_920X920.jpg?version=20200917220352530",
  "sku_list": [
    {
      "_id": "006",
      "goods_id": "003",
      "goods_name": "迪奥香水",
      "image": "https://res.lancome.com.cn/resources/2020/9/11/15998112890781924_920X920.jpg?version=20200917220352530",
      "price": 19800,
      "sku_name_arr": ["默认"],
      "stock": 100
    }
  ],
  "spec_list": [
    {
      "list": [
        {
          "name": "默认"
        }
      ],
      "name": "默认"
    }
  ]
}
```

### 单组规格商品格式示例

```js
{
  "_id":"002",
  "name": "迪奥香水",
  "goods_thumb":"https://res.lancome.com.cn/resources/2020/9/11/15998112890781924_920X920.jpg?version=20200917220352530",
  "sku_list": [
    {
      "_id": "004",
      "goods_id": "002",
      "goods_name": "迪奥香水",
      "image": "https://res.lancome.com.cn/resources/2020/9/11/15998112890781924_920X920.jpg?version=20200917220352530",
      "price": 19800,
      "sku_name_arr": ["50ml/瓶"],
      "stock": 100
    },
    {
      "_id": "005",
      "goods_id": "002",
      "goods_name": "迪奥香水",
      "image": "https://res.lancome.com.cn/resources/2020/9/11/15998112890781924_920X920.jpg?version=20200917220352530",
      "price": 9800,
      "sku_name_arr": ["70ml/瓶"],
      "stock": 100
    }
  ],
  "spec_list": [
    {
      "list": [
        {
          "name": "20ml/瓶"
        },
        {
          "name": "50ml/瓶"
        },
        {
          "name": "70ml/瓶"
        }
      ],
      "name": "规格"
    }
  ]
}
```

### 多组规格商品格式示例

```js
{
  "_id":"001",
  "name": "iphone11",
  "goods_thumb":"https://img14.360buyimg.com/n0/jfs/t1/59022/28/10293/141808/5d78088fEf6e7862d/68836f52ffaaad96.jpg",
  "sku_list": [
    {
      "_id": "001",
      "goods_id": "001",
      "goods_name": "iphone11",
      "image": "https://img14.360buyimg.com/n0/jfs/t1/79668/22/9987/159271/5d780915Ebf9bf3f4/6a1b2703a9ed8737.jpg",
      "price": 19800,
      "sku_name_arr": ["红色", "128G", "公开版"],
      "stock": 100
    },
    {
      "_id": "002",
      "goods_id": "001",
      "goods_name": "iphone11",
      "image": "https://img14.360buyimg.com/n0/jfs/t1/52252/35/10516/124064/5d7808e0E46202391/7100f3733a1c1f00.jpg",
      "price": 9800,
      "sku_name_arr": ["白色", "256G","公开版"],
      "stock": 100
    },
    {
      "_id": "003",
      "goods_id": "001",
      "goods_name": "iphone11",
      "image": "https://img14.360buyimg.com/n0/jfs/t1/79668/22/9987/159271/5d780915Ebf9bf3f4/6a1b2703a9ed8737.jpg",
      "price": 19800,
      "sku_name_arr": ["红色","256G","公开版"],
      "stock": 100
    }
  ],
  "spec_list": [
    {
      "list": [
        {
          "name": "红色"
        },
        {
          "name": "黑色"
        },
        {
          "name": "白色"
        }
      ],
      "name": "颜色"
    },
    {
      "list": [
        {
          "name": "128G"
        },
        {
          "name": "256G"
        }
      ],
      "name": "内存"
    },
    {
      "list": [
        {
          "name": "公开版"
        },
        {
          "name": "非公开版"
        }
      ],
      "name": "版本"
    }
  ]
}
```

### 各参数说明

```js
{
  "_id":"001", // 商品ID
  "name": "iphone11", // 商品名称
   // 商品头像
  "goods_thumb":"https://img14.360buyimg.com/n0/jfs/t1/59022/28/10293/141808/5d78088fEf6e7862d/68836f52ffaaad96.jpg",
  // 该商品对应的sku列表
  "sku_list": [
    {
      "_id": "001", // SKU ID
      "goods_id": "001", // 商品ID
      "goods_name": "iphone11", // 商品名称
       // SKU头像
      "image": "https://img14.360buyimg.com/n0/jfs/t1/79668/22/9987/159271/5d780915Ebf9bf3f4/6a1b2703a9ed8737.jpg",
      "price": 19800, // SKU 价格
      "sku_name_arr": ["红色", "128G", "公开版"], // 该SKU由哪些规格组成（规格是有顺序的，需要与spec_list的数组顺序对应）
      "stock": 100
    },
    {
      "_id": "002",
      "goods_id": "001",
      "goods_name": "iphone11",
      "image": "https://img14.360buyimg.com/n0/jfs/t1/52252/35/10516/124064/5d7808e0E46202391/7100f3733a1c1f00.jpg",
      "price": 9800,
      "sku_name_arr": ["白色", "256G","公开版"],
      "stock": 100
    },
    {
      "_id": "003",
      "goods_id": "001",
      "goods_name": "iphone11",
      "image": "https://img14.360buyimg.com/n0/jfs/t1/79668/22/9987/159271/5d780915Ebf9bf3f4/6a1b2703a9ed8737.jpg",
      "price": 19800,
      "sku_name_arr": ["红色","256G","公开版"],
      "stock": 100
    }
  ],
  // 商品规格列表
  "spec_list": [
    {
      "list": [
        {
          // 第一组规格的第一项规格值名称
          "name": "红色"
        },
        {
          // 第一组规格的第二项规格值名称
          "name": "黑色"
        },
        {
          // 第一组规格的第三项规格值名称
          "name": "白色"
        }
      ],
      // 第一组规格名
      "name": "颜色"
    },
    {
      "list": [
        {
          // 第二组规格的第一项规格值名称
          "name": "128G"
        },
        {
          // 第二组规格的第二项规格值名称
          "name": "256G"
        }
      ],
       // 第二组规格名
      "name": "内存"
    },
    {
      "list": [
        {
          // 第三组规格的第一项规格值名称
          "name": "公开版"
        },
        {
          // 第三组规格的第二项规格值名称
          "name": "非公开版"
        }
      ],
       // 第三组规格名
      "name": "版本"
    }
  ]
}
```