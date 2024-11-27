<template>
	<uni-card :margin='margin' :is-shadow='isShadow' shadow="3px" class="cust-card" @click="handleClick">
		<image v-if="headerImg" style="width: 100%;" :src="headerImg"></image>
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
	import { commonNavigate } from '../utils/CommonUtils';
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
			headerImg: {
				type: String,
				default: ''
			},
			clickUrl: {
				type: String,
				default: null
			}
		},
		methods:{
			handleClick(){
				if(this.clickUrl){
					commonNavigate(this.clickUrl)
				}
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