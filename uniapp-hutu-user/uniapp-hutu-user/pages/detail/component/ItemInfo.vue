<template>
	<view class="info">
		<view class="item-info">
			<text class="name">
				{{name}}
			</text>
		</view>
		<view class="tag-group" v-if="tags">
			<text v-for="(tag,index) in tags" :key="index" class="tag">
				{{tag}}
			</text>
			<text v-if="specialTag" v-for="(tag,index) in specialTag" :key="index" class="special-tag">
				{{tag}}
			</text>
		</view>
		<view class="item-desc" v-if="desc">
			{{desc}}
		</view>
	</view>
</template>

<script>
	export default {
		name: 'ItemInfo',
		props: {
			name: {
				type: String,
				default: ''
			},
			tags: {
				type: Array,
				default: () => ([])
			},
			specialTag: {
				type: Array,
				default: () => ([])
			},
			desc: {
				type: String,
				default: ''
			},
			orgAmount:{
				type: Number,
				default: 0
			},
			amount:{
				type: Number,
				default: 0
			}
		}
	}
</script>

<style scoped>
	.item-info {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.item-info {
		font-size: 24px;
		font-weight: 600;
		padding-top: 16px;
		padding-left: 16px;
	}

	.tag-group {
		padding-top: 8px;
		padding-left: 16px;
	}
	
	.tag-group text{
		margin-right: 10px;
		border-radius: 4%;
		font-size: 16px;
		padding: 2px;
		border: 1px black solid;
	}
	
	.item-desc {
		font-size: 18px;
		color: #bcbcbc;
		padding-left: 16px;
		padding-top: 8px;
	}

	.item-desc {
		padding-left: 16px;
	}
</style>