<template>
	<movable-area class="movable-area" @click="handerClick">
		<movable-view class="movable-view" :x="x" :y="y" direction="all">
			<uni-badge v-if="num" class="uni-badge-left-margin" :text="num" absolute="rightTop" size="small">
				<image src="@/static/image/package.png" />
			</uni-badge>
		</movable-view>
	</movable-area>
</template>

<script>
	export default {
		name: 'ShopPackage',
		props: {
			x: {
				type: Number,
				default: uni.getSystemInfoSync().windowWidth - 88
			},
			y: {
				type: Number,
				default: uni.getSystemInfoSync().windowHeight - 118
			},
			num: {
				type: Number,
				default: null
			}
		},
		data() {
			return {

			}
		},
		methods: {
			handerClick(){
				this.$emit('shop-package-click')
			}
		},
		computed:{
		}
	}
</script>


<style lang="scss" scoped>
	$all_width: 96rpx;
	$all_height: 96rpx;

	.movable-area {
		height: 100vh;
		width: 750rpx;
		top: 0;
		position: fixed;
		pointer-events: none; //此处要加，鼠标事件可以渗透

		.movable-view {
			width: $all_width;
			height: $all_height;
			pointer-events: auto; //恢复鼠标事件

			image {
				width: $all_width;
				height: $all_height;
			}
		}
	}

	image {
		background-color: #007aff;
		border-radius: 50%;
	}
</style>