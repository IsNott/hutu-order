<template>
	<uni-card :margin='margin' :is-shadow='isShadow' shadow="3px" :style="backGroundImage" class="cust-card">
		<view class="title">
			<text class="main">{{mainTitle}}</text>
			<text class="sub">{{subTitle}}</text>
		</view>
		<view>
			<slot class="cust-info"></slot>
		</view>
	</uni-card>
</template>

<script>
	export default {
		name: 'CustCard',
		props: {
			margin: {
				type: String,
				default: '6px'
			},
			isShadow: {
				type: Boolean,
				default: false
			},
			shadow: {
				type: String,
				default: ''
			},
			mainTitle: {
				type: String,
				default: ''
			},
			subTitle: {
				type: String,
				default: ''
			},
			backGroudImg: {
				type: String,
				default: ''
			}
		},
		computed: {
			backGroundImage() {
				var styleValue = '';
				if (this.backGroudImg) {
					styleValue = {
						'background-image': `url(${this.backGroudImg})`,
						'background-repeat': 'no-repeat',
						'height': '100px'
					}
				}
				return styleValue;
			}
		}
	}
</script>

<style scoped>
	.title {
		padding: 0px;
		display: flex;
		margin: 10px 0px;
		justify-content: space-between;
	}

	.main {
		font-weight: bold;
		font-size: 110%;
	}

	.sub {
		font-size: 100%;

	}

	.cust-info {
		padding: 4px;
	}
</style>