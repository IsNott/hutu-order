<template>
	<view class="drawer-content">
		<view class="drawer-content-drag" :style="transition + 'top:' + (dragTop + 'px')" @touchstart="drag_start"
			@touchmove.stop="drag_move" @touchend="drag_end" @touchcancel="drag_end" :data-top="dragTop">
			<view class="drag-handle" :style="
	            'height:' +
	            dragHandleHeight +
	            'px;'
	          ">
				<view class="drag-handle-line"></view>
			</view>
			<view id="drag-content" class="drag-content">
				<slot>

				</slot>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'ww-bottom-drawer',
		props: {
			dragHandleHeight: {
				type: Number,
				default: 20,
			},
			proportionShow: { //显示比例，宽度的比例
				type: Number,
				default: 0.3,
			},
			
		},
		watch: {
			proportionShow() {
				const {
					windowWidth,
					windowHeight
				} = uni.getSystemInfoSync();
				this.windowWidth = windowWidth
				this.windowHeight = windowHeight
				this.foldHeight = this.windowWidth * this.proportionShow
			}
		},
		data() {
			return {
				startY: 0,
				foldHeight: 0,//折叠的高度 通过proportionShow比例计算
				dragTop: -this.dragHandleHeight,
				top: 0,
				transition: 'transition: top 0.1s;',
				windowHeight: '',
				windowWidth: '',
				toTop: true,

			}
		},
		mounted() {
			const {
				windowWidth,
				windowHeight
			} = uni.getSystemInfoSync();
			this.windowWidth = windowWidth
			this.windowHeight = windowHeight
			this.foldHeight = this.windowWidth * this.proportionShow
			this.dragTop = -this.dragHandleHeight - this.foldHeight
		},
		methods: {
			drag_start(event) {
				this.transition = "";
				this.top = event.currentTarget.dataset.top;
				this.startY = event.touches[0].clientY;
			},
			drag_move(event) {
				// console.log(event.currentTarget)
				var len = event.touches[0].clientY - this.startY;
				var temp = this.top + len;
				if (temp >= this.dragHandleHeight) {
					temp = this.dragHandleHeight;
				}
				this.toTop = len < 0;

				const query = uni.createSelectorQuery().in(this);
				query
					.select("#drag-content")
					.boundingClientRect((data) => {
						if (temp <= 0 - data.height - this.dragHandleHeight) {
							temp = 0 - data.height - this.dragHandleHeight;
						}
						if (temp <= 0 - this.windowHeight * 0.8 - this.dragHandleHeight) {
							temp = 0 - this.windowHeight * 0.8 - this.dragHandleHeight;
						}
						this.dragTop = temp;
					})
					.exec();
				return false;
			},
			drag_end(event) {
				this.transition = "transition: top 1s;";
				const query = uni.createSelectorQuery().in(this);
				query
					.select("#drag-content")
					.boundingClientRect((data) => {
						if (this.toTop) {
							this.dragTop = 0 - data.height - 20;
						} else {
							this.dragTop = -this.dragHandleHeight - this.foldHeight;
						}
					})
					.exec();
				return false;
			},
		}
	}
</script>

<style lang="scss">
	.drawer-content {
		bottom: 0;
		left: 0;
		position: fixed;
		width: 100vw;
	}

	.drag-handle {
		height: 20px;
		width: 100vw;
		background-color: #fff;
		border-radius: 15px 15px 0 0;
		color: #eee;
		font-weight: bold;
		font-size: 20px;
		line-height: 1px;
		display: flex;
		justify-content: center;
		align-items: center;

		.drag-handle-line {
			width: 60rpx;
			height: 8rpx;
			margin-top: 10rpx;
			background: #dfe5f1;
			border-radius: 4rpx;
		}
	}

	.drawer-content-drag {
		position: absolute;
		height: 60vh;
		max-height: 80vh;
		width: 100vw;
		left: 0;
	}

	.drag-content {
		background-color: #fff;
		height: auto;
		max-height: 80vh;
	}
</style>
