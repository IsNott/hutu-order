<template>
	<view>
		<!-- #ifdef MP-ALIPAY -->
		<view class="drawer-content" @touchstart.stop="drag_start" @touchmove.stop="drag_move" @touchend.stop="drag_end"
			@touchcancel="drag_end" :data-top="dragTop" :style="transition + 'bottom:' + (dragTop + 'px')">
			<view id="drag-content" class="drag-content" ref="drag-content">
				<view class="drag-handle drag-handle-ali" :style="
        		    'height:' +
        		    dragHandleHeight +
        		    'px;'
        		  ">
					<view class="drag-handle-line"></view>
				</view>
				<slot>

				</slot>
			</view>
		</view>
		<!-- #endif -->
		<!-- #ifndef MP-ALIPAY -->
		<view class="drawer-content" @touchstart="drag_start" @touchmove.stop="drag_move" @touchend="drag_end"
			@touchcancel="drag_end" :data-top="dragTop"
			:style="transition + 'bottom:' + (dragTop + 'px;') + styleCss()">
			<view id="drag-content" class="drag-content" ref="drag-content">
				<view class="drag-handle" :style="
				    'height:' +
				    dragHandleHeight +
				    'px;'
				  ">
					<view class="drag-handle-line"></view>
				</view>
				<slot>

				</slot>
			</view>
		</view>
		<!-- #endif -->


	</view>
</template>

<script>
	// #ifdef APP-NVUE
	const dom = uni.requireNativePlugin('dom');
	// #endif
	export default {
		name: 'ww-bottom-drawerapp',
		props: {
			dragHandleHeight: {
				type: Number,
				default: 20,
			},
			proportionShow: { //显示比例，内容的比例
				type: Number,
				default: 0.5,
			},
			proportionMiniShow: { //拖拽收缩的时候，不能收缩的内容的比例
				type: Number,
				default: 0.0,
			},
			proChangeWidth: {
				type: Boolean,
				default: false,
			},
			dragLength: { //收缩和展开的默认距离  50px
				type: Number,
				default: 50,
			},
			canDrag: { //收缩和展开的默认距离  50px
				type: Boolean,
				default: true,
			},
			isExpand: { //是否展开
				type: Boolean,
				default: false,
			},
			transitionTime: { //菜单完成收缩和展开的时间 单位秒
				type: Number,
				default: 0.3,
			},
			menuHeight: {
				type: Number,
				default: 0,
			}
		},
		watch: {

			isExpand(newVal, oldVal) {
				console.log(this.dragTop)
				if (newVal != this.isDrawerOpen) {
					console.log("isExpand" + newVal)
					this.$nextTick(() => {
						this.toTop = newVal;
						this.expand_end()
					})

				}

			}
		},
		data() {
			return {
				contentHeight: 0,

				startY: 0,
				dragTop: 0,
				dragTopTemp: 0,
				transition: '',
				windowHeight: 0,
				windowWidth: 0,
				toTop: true,
				isDrawerOpen: false,
				timer: null,
			}
		},
		created() {
			if (this.menuHeight != 0) {
				//this.dragTop = -Number((1 - this.proportionShow) * (this.menuHeight + this.dragHandleHeight));
				console.log(this.dragTop)
			}

		},
		beforeMount() {

		},
		unmounted() {
			if (this.timer) {
				clearInterval(this.timer);
			}
		},

		mounted() {
			const {
				windowWidth,
				windowHeight
			} = uni.getSystemInfoSync();
			this.windowWidth = windowWidth
			this.windowHeight = windowHeight

			if (!this.isExpand) {
				this.$nextTick(() => {
					this.initTop() //当菜单高度固定的时候，立即生效，当设置收缩的时候不会看到进入页面张开-收缩
					setTimeout(() => {
						this.initTop() //等待菜单高度渲染完成，如果是收缩，会出现张开-收缩
					}, 300)
				})
			}

			this.callExpand(this.isExpand)
		},
		methods: {
			initTop() {

				// #ifndef APP-NVUE
				const query = uni.createSelectorQuery().in(this);
				query
					.select("#drag-content")
					.boundingClientRect((data) => {
						this.contentHeight = data.height
						this.transition = ""
						this.dragTop = -Number((1 - this.proportionShow) * this.contentHeight);
						this.callExpand(this.dragTop == 0)

					})
					.exec();
				// #endif
				// #ifdef APP-NVUE
				dom.getComponentRect(this.$refs['drag-content'], (ret) => {
					this.contentHeight = ret.size.height
					this.transition = ""
					this.dragTop = -Number((1 - this.proportionShow) * this.contentHeight);
					this.callExpand(this.dragTop == 0)
					console.log(this.contentHeight)

				})
				// #endif
			},
			handleTap2() {},
			styleCss() {
				//发现同样无动画，vue里面有
				// #ifdef APP-NVUE
				// if (this.isDrawerOpen) {//展开
				//                 return "transform: translateY(0px);transition-property: transform;transition-duration: 3s;"
				// }else{//收缩
				// 	return "transform: translateY(50%);transition-property: transform;transition-duration: 3s;"
				// }
				// #endif
				return ''
			},
			drag_start(event) {
				console.log(this.canDrag)
				if (!this.canDrag) {
					return
				}
				if (this.timer) {
					clearInterval(this.timer);
				}
				this.transition = "";
				this.dragTopTemp = this.dragTop;
				// #ifdef APP-NVUE
				this.startY = event.touches[0].screenY;
				// #endif
				// #ifndef APP-NVUE
				this.startY = event.touches[0].clientY;
				// #endif
			},
			drag_move(event) {
				if (!this.canDrag) {
					return
				}
				// #ifdef APP-NVUE
				dom.getComponentRect(this.$refs['drag-content'], (ret) => {

					var len = (event.touches[0].screenY - this.startY);
					if (this.isDrawerOpen) { //当展开状态下
						if (len > 0) { //向下滑动
							this.toTop = len < this.dragLength;
						} else {
							this.toTop = true;
						}
					} else {
						if (len < 0) { //向上滑动
							this.toTop = Math.abs(len) > this.dragLength;
						} else {
							this.toTop = false;
						}
					}
					this.contentHeight = ret.size.height
					let temp = this.dragTopTemp - len
					if (temp > 0) {
						temp = 0
					} else if (temp < -this.contentHeight + 30) {
						temp = -this.contentHeight + 30
					}
					var temp_=Number(temp)
					var ss_= -Number((1 - this.proportionMiniShow) * this.contentHeight);
					if(Number(temp_)<ss_){
						this.dragTop = ss_
					}else{
						this.dragTop = temp_
					}
				})

				// #endif
				// #ifndef APP-NVUE
				const query = uni.createSelectorQuery().in(this);
				query
					.select("#drag-content")
					.boundingClientRect((data) => {
						var len = (event.touches[0].clientY - this.startY);
						//判断展开还是收缩
						if (this.isDrawerOpen) { //当展开状态下
							if (len > 0) { //向下滑动
								this.toTop = len < this.dragLength;
							} else {
								this.toTop = true;
							}
						} else {
							if (len < 0) { //向上滑动
								this.toTop = Math.abs(len) > this.dragLength;
							} else {
								this.toTop = false;
							}
						}
						this.contentHeight = data.height
						let temp = this.dragTopTemp - len
						if (temp > 0) {
							temp = 0
						} else if (temp < -this.contentHeight + 30) {
							temp = -this.contentHeight + 30
						}
						var temp_=Number(temp)
						var ss_= -Number((1 - this.proportionMiniShow) * this.contentHeight);
						if(Number(temp_)<ss_){
							this.dragTop = ss_
						}else{
							this.dragTop = temp_
						}
						
					})
					.exec();
				// #endif


				return false;
			},

			drag_end(event) {
				if (!this.canDrag) {
					return
				}
				this.expand_end()
				return false;
			},

			animationTop(from, to, callback) {
				if (this.timer) {
					clearInterval(this.timer);
				}
				if (from == to) {
					this.dragTop = to
					callback && callback();
					return
				}
				console.log(from + " - " + to)
				let time = this.transitionTime * 100;
				// 定义定时器的间隔
				var interval = 20;
				// 定义总次数
				var allCount = time / interval;

				// 定义步长
				var step = (to - from) / allCount;
				console.log(from + " - " + to + " step:" + step)
				// 定义计数器
				var count = 0;

				let self = this
				this.timer = setInterval(function() {
					count++;
					self.dragTop = from + step * count;
					console.log(from + " - " + to + " count:" + count + "  dragTop:" + self.dragTop)
					// 判断是否执行完毕
					if (count >= allCount) {
						// 停表
						clearInterval(self.timer);
						self.dragTop = to
						// 回调函数执行
						callback && callback();
					}
				}, interval);
			},
			expand_end() {
				// #ifdef APP-NVUE
				dom.getComponentRect(this.$refs['drag-content'], (ret) => {
					this.transition = `transition: bottom ${this.transitionTime}s;`
					console.log(ret)
					if (this.transitionTime <= 0.3) { //nvue的动不生效，自行处理
						this.contentHeight = ret.size.height
						if (this.toTop) {
							this.dragTop = 0
						} else {
							this.dragTop = -Number((1 - this.proportionShow) * this.contentHeight);
						}
						this.callExpand(this.dragTop == 0)
					} else {
						this.transition = ''
						this.contentHeight = ret.size.height
						if (this.toTop) {
							//this.dragTop = 0
							this.animationTop(this.dragTop, 0, () => {
								this.callExpand(this.dragTop == 0)
							})
						} else {
							let dragTop_ = -Number((1 - this.proportionShow) * this.contentHeight);
							this.animationTop(this.dragTop, dragTop_, () => {
								this.callExpand(this.dragTop == 0)
							})
						}

					}

				})
				// #endif
				// #ifndef APP-NVUE
				const query = uni.createSelectorQuery().in(this);
				query
					.select("#drag-content")
					.boundingClientRect((data) => {
						this.transition = `transition: bottom ${this.transitionTime}s;`
						this.contentHeight = data.height
						if (this.toTop) {
							this.dragTop = 0
						} else {
							this.dragTop = -Number((1 - this.proportionShow) * this.contentHeight);
						}
						this.callExpand(this.dragTop == 0)
					})
					.exec();
				// #endif
			},
			//通知是展开
			callExpand(expand) {

				this.$emit("callExpand", {
					value: expand
				});
				this.isDrawerOpen = expand

			}

		}
	}
</script>

<style lang="scss">
	.drawer-content {
		bottom: 0;
		left: 0;
		display: flex;
		position: fixed;
		width: 750rpx;
		background-color: #0A98D5;
	}

	.drag-handle-ali {
		background-color: #666;
	}

	.drag-handle {
		height: 20px;
		width: 750rpx;
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
		display: flex;
		width: 750rpx;
		left: 0;
		position: absolute;
	}

	.drawer-content-drag1 {
		display: flex;
		width: 750rpx;
		background-color: #000000;
	}


	.drag-content {
		background-color: #fff;
	}
</style>
