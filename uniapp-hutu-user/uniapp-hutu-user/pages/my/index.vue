<template>
	<scroll-view>
		<login-card :key="refeshKey" 
		:user-info="userInfo" @refeshPage="handleRefesh"/>
		<balance-card :key="new Date().getTime()" />
		<setting-card />
		<cust-card class="sell-card" main-title="糊涂加盟" sub-title="查看Github">
			<view>
				<text>还在为没有做过开源项目烦恼？还在为技能拓展发愁？</text>
			</view>
			<view>
				<text>快来加入糊涂点餐开发团队吧!</text>
			</view>
		</cust-card>
		<view class="footer">
			<text>Powered by </text>
			<text style="font-weight: bold;color:#FF4500;">{{name}}</text>
		</view>
	</scroll-view>
</template>

<script>
	import { getStoreUserInfo } from '@/utils/CommonUtils';
	import SettingCard from './component/SettingCard.vue';
	import CustCard from '../../component/CustCard.vue';
	import LoginCard from './component/LoginCard.vue';
	import BalanceCard from './component/BalanceCard.vue';
	export default {
		name: 'my',
		components: {
			BalanceCard,
			SettingCard,
			CustCard,
			LoginCard
		},
		data() {
			return {
				userInfo: '',
				name: '\xa0Hutu-Order',
				loginAuth: false,
				phoneAuth: false,
				refeshKey: new Date().getMilliseconds()
			}
		},
		methods: {
			getUserInfo() {
				this.userInfo = getStoreUserInfo();
			},
			handleRefesh(){
				this.refeshKey++
			}
		},
		onShow() {
			this.getUserInfo();
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