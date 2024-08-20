import {
	miniProgramLogin
} from "@/api/wechat"
import {
	register
} from "@/api/user"
const BASE_URL = 'http://localhost:10001'

export const request = (api, method, data) => {
	return new Promise((resolve, rejects) => {
		handleRequest(api, method, data, resolve, rejects)
	})
}

export const httpGet = (api, data) => {
	// preHandle();
	return new Promise((resolve, rejects) => {
		handleRequest(api, 'GET', data, resolve, rejects)
	})
}

export const httpPost = (api, data) => {
	// preHandle();
	return new Promise((resolve, rejects) => {
		handleRequest(api, 'POST', data, resolve, rejects)
	})
}

function preHandle() {
	const token = uni.getStorageSync("token");
	if (!token) {
		// #ifdef MP-WEIXIN
		wx.getSetting({
			success(res) {
				console.log('Wechat setting', res)
				if (!res.authSetting['scope.userInfo']) {
					['scope.userInfo'].forEach(r => {
						wx.authorize({
							scope: r,
							success() {
								console.error('用户授权', r)
							},
							fail() {
								console.error('用户取消授权', r)
							}
						})
					})
				}
			}
		})
		wx.login({
			success(res) {
				if (res.code) {
					const code = res.code
					miniProgramLogin(code).then(res => {
						console.log('login', res)
						if (res.data.alreadyRegister) {
							storeTokenInfo(res.data);
						} else {
							var userInfo;
							wx.getUserInfo({
								// 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
								desc: '用于完善用户资料',
								success: (res) => {
									console.log('info', res)
									userInfo = res.userInfo;
									register({
										code: code,
										...userInfo
									}).then(res => {
										storeTokenInfo(res.data);
									})
								}
							})
						}
					})
				} else {
					console.log('微信小程序登录失败！' + res.errMsg)
				}
			}
		})
		// #endif
	}
}

function storeTokenInfo(info) {
	uni.setStorageSync('user_info', info)
	uni.setStorageSync('token', info.token)
}

function handleRequest(api, method, data, resolve, reject) {
	uni.showLoading({
		title: '加载中'
	})
	const token = uni.getStorageSync("token")
	var header = {
		'Content-Type': 'application/json'
	}
	if(token){
		header.token = token
	}
	uni.request({
		url: BASE_URL + api,
		data: data,
		method: method,
		header: {
			...header
		},
		success: (response) => {
			const res = response.data
			if (res.code == 401) {
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请重新登录'
				});
				
				uni.navigateTo({
					url: '/pages/authority/index'
				})
				return reject(err)
			}
			if (res.code != 200) {
				console.error("Request error: ", res.message)
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请求失败'
				})
			}
			return resolve(res)
		},
		fail: (err) => {
			console.log('error', err)
			uni.showToast({
				icon: 'error',
				position: 'top',
				title: '网络异常'
			})
			return reject(err);
		},
		complete() {
			uni.hideLoading()
		}
	});
}