<template>
	<view>
		<uni-list>
			<uni-list-item showArrow title="退出登录" :clickable="true" @click="handleLogout" />
			<uni-list-item showArrow title="清除缓存" :clickable="true" @click="handleClearCache" />
		</uni-list>
	</view>
</template>

<script>
	import {
		logout
	} from '@/api/setting'
	import {
		commonNavigate,
		getStoreUserInfo
	} from '@/utils/CommonUtils'
	export default {
		name: 'Setting',
		data() {
			return {}
		},
		methods: {
			handleLogout() {
				const info = getStoreUserInfo()
				if (!info) {
					uni.showToast({
						icon: 'error',
						title: '您还未登录'
					})
					setTimeout(() => uni.hideToast(), 1500)
				} else {
					uni.showModal({
						content: '确认退出登录？',
						success(res) {
							if (res.confirm) {
								uni.removeStorageSync('user_info')
								uni.removeStorageSync('current_shop')
								logout().then(res => {
									uni.showToast({
										icon: 'success',
										title: '退出成功'
									})
									commonNavigate('/pages/home/index')
								}).finally(uni.hideToast())
							}
						}
					})
				}
			},
			handleClearCache() {
				uni.showModal({
					content: '清除后需要重新选择门店进行点单，是否继续？',
					success(res) {
						if (res.confirm) {
							uni.removeStorageSync('current_shop')
							uni.showToast({
								icon: 'success',
								title: '清除成功'
							})
							setTimeout(() => uni.hideToast(), 1500)
						}
					}
				})
			}
		},
	}
</script>

<style scoped>

</style>