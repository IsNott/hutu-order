<template>
  <view class="body" @click="handleClick" :style="bodyStyle">
    <view class="shop-info-card">
      <view class="header-card" :style="headerCardStyle">
        <text class="shop-name" :style="shopNameStyle">
          {{ currentMerchant.shopName }}
        </text>
        <text class="address" :style="addressStyle">
          {{ currentMerchant.address }}
        </text>
        <text class="time" :style="timeStyle">
          {{ currentMerchant.startBusinessTime }} - {{ currentMerchant.endBusinessTime }}
        </text>
      </view>
      <view class="right">
        <text :style="rightTextStyle">></text>
      </view>
    </view>
  </view>
</template>

<script>
	import { parseCssString } from '@/utils/CommonUtils';
	const empty = {
		shopName: '',
		address: ''
	};

	export default {
		name: 'ShopInfoCard',
		props: {
			currentMerchant: {
				type: Object,
				default: empty
			}
		},
		data() {
			return {
				parsedStyles: {}
			};
		},
		created() {
			this.fetchStyles();
		},
		methods: {
			handleClick() {
				this.$emit('shopCardClick');
			},
		 fetchStyles() {
				if(this.currentMerchant.pageStyle){
						this.parsedStyles = parseCssString(this.currentMerchant.pageStyle)
				}
			},
		},
		computed: {
		    bodyStyle() {
		      const baseStyle = this.parsedStyles['.body'] || {};
		      if (this.currentMerchant.coverUrl) {
		        return {
		          ...baseStyle,
		          background: `url(${this.currentMerchant.coverUrl}) no-repeat`
		        };
		      }
		      return baseStyle;
		    },
		    // 其他样式
		    headerCardStyle() {
		      return this.parsedStyles['.header-card'] || {};
		    },
		    shopNameStyle() {
		      return this.parsedStyles['.shop-name'] || {};
		    },
		    addressStyle() {
		      return this.parsedStyles['.address'] || {};
		    },
		    timeStyle() {
		      return this.parsedStyles['.time'] || {};
		    },
		    rightTextStyle() {
		      return this.parsedStyles['.right text'] || {};
		    }
		  }
	};
</script>

<style scoped>
	/* 默认样式，如果接口未返回样式时使用 */
	.body {
		padding: 20px 22px;
		padding-bottom: 10px;
	}

	.header-card {
		padding: 2px;
	}

	.header-card text {
		display: block;
	}

	.shop-name {
		font-size: 16px;
		font-weight: 600;
	}

	.shop-info-card {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		width: 100%;
	}

	.right text {
		font-size: 24px;
		font-weight: 500;
	}

	.address {
		margin-top: 40px;
	}

	.address,
	.time {
		color: grey;
	}

</style>