<template>
	<view>
		<view class="uni-textarea">
			<uni-easyinput v-model="markStr" type="textarea" autoHeight placeholder="请输入您的口味要求,例如: 少冰/少糖/不要冰"/>
			<view class="fast-tip">
				<view class="title">快捷用语</view>
				<view class="tip-group">
					<view class="tip" :key="tip.id" v-for="(tip,index) in tips" @click="appendMark(tip.context)">
						{{tip.context}}
					</view>
				</view>
			</view>
		</view>
		<view class="footer">
			<button type="primary" @click="handleSubmit" class="child">完成</button>
		</view>
	</view>
</template>

<script>
	import { commonNavigate } from '@/utils/CommonUtils'
	import { queryCommonRemark } from '@/api/mark'
	const markKey = 'order-remark'
	export default {
		name: 'mark',
		components: {},
		data() {
			return {
				markStr: '',
				tips:[]
			}
		},
		created() {
			this.load()
		},
		methods: {
			handleSubmit(){
				if(this.markStr){
					uni.setStorageSync(markKey,this.markStr)
				}
				commonNavigate('/pages/user-package/index')
			},
			load(){
				queryCommonRemark(20).then(res => {
					this.tips = res.data
				})
				this.markStr = this.isNotEmpty(uni.getStorageSync(markKey)) ? uni.getStorageSync(markKey) : ''
			},
			appendMark(val){
				if(this.isEmpty(this.markStr)){
					this.markStr = val
				}else{
					this.markStr += "、" + val
				}
			}
		}
	}
</script>

<style scoped>
	.footer{
		width: 100%;
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
	}
	
	.uni-textarea{
		padding: 10px 6px;
	}
	
	.fast-tip{
		margin-top: 20px;
	}
	
	.fast-tip .title{
		font-size: 16px;
		font-weight: 500;
	}
	
	.tip-group{
		padding-top: 10px;
	}
	
	.tip{
		display: inline-block;
		border: 1px #909994 solid;
		margin-right: 6px;
		margin-top: 6px;
		border-radius: 6px;
		padding: 4px;
		color: gray;
	}
</style>