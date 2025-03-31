<template>
	<view class="mark-info" @click="handleClick">
		<text class="title">{{title}}</text>
		<view class="radio-tab" v-if="useRadio">
			<radio value="r1" :checked="select" @click="handleRadioChange" />{{defValue}}</label>
		</view>
		<view v-else>
			<text v-if="!value" class="def-placeholder">{{defValue}}</text>
			<text v-else class="placeholder">{{value}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'MarkTab',
		components: {},
		data() {
			return {
				select: false
			}
		},
		props: {
			value: {
				type: String,
				default: '无'
			},
			defValue: {
				type: String,
				default: '无'
			},
			title: {
				type: String,
				default: 'Tab标题'
			},
			useRadio: {
				type: Boolean,
				default: false
			},
			useSelect: {
				type: Boolean,
				default:false
			}
		},
		methods: {
			handleClick() {
				this.$emit('tabClick')
			},
			handleRadioChange() {
				let select = this.select
				this.select = !select
				this.$emit('tabSelect', this.select)
			}
		}
	}
</script>

<style scoped>
	.mark-info {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	text {
		padding: 12px 8px;
	}

	.title {
		font-size: 18px;
		font-weight: bold;
	}

	.def-placeholder {
		color: #b4b4b4;
		font-size: 16px;
		font-weight: 500;
		display: table-cell;
	}

	.placeholder {
		color: black;
		font-size: 16px;
		display: table-cell;
	}
	
	.text{
		text-align: center;
	}
	
	.radio-tab{
		align-items: center;
		display: flex;
	}
	
	radio {
		/* font-size: 8px; */
		transform: scale(0.66);
	}
</style>