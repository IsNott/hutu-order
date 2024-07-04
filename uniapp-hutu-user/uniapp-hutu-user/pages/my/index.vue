<template>
	<scroll-view>
		<login-card/>
		<setting-card />
		<cust-card class="sell-card" main-title="营销卡片" sub-title="副标题">
			<text>故飘风不终朝，骤雨不终日。孰为此者？天地。天地尚不能久，而况于人乎？</text>
		</cust-card>
		<cust-card class="sell-card" main-title="营销卡片" sub-title="副标题">
			<text>故飘风不终朝，骤雨不终日。孰为此者？天地。天地尚不能久，而况于人乎？</text>
		</cust-card>
		<cust-card class="sell-card" main-title="营销卡片" sub-title="副标题">
			<text>故飘风不终朝，骤雨不终日。孰为此者？天地。天地尚不能久，而况于人乎？</text>
		</cust-card>
		<cust-card class="sell-card" main-title="营销卡片" sub-title="副标题">
			<text>故飘风不终朝，骤雨不终日。孰为此者？天地。天地尚不能久，而况于人乎？</text>
		</cust-card>
		<view class="footer">
			<text>Power by </text>
			<text style="font-weight: bold;">{{name}}</text>
		</view>
	</scroll-view>
</template>

<script>
	import SettingCard from './component/SettingCard.vue';
	import CustCard from '../../component/CustCard.vue';
	import LoginCard from './component/LoginCard.vue';
	export default {
		name: 'my',
		components: {
			SettingCard,
			CustCard,
			LoginCard
		},
		data() {
			return {
				name: '\xa0Hutu-Order',
				loginAuth: false,
				phoneAuth: false
			}
		},
		methods: {
			
		},
		created() {
			
		},
		computed: {
		}
	}
</script>

<style scoped>
	.footer{
		margin-top: 26px;
		margin-bottom: 8px;
		width: 100%;
		text-align: center;
	}
</style>