import {
	miniProgramLogin
} from "@/api/wechat"
import {
	register
} from "@/api/user"
import Vue from 'vue'

const TIME_OUT = 60000

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

export const get = (api, data, header) => {
	// preHandle();
	return new Promise((resolve, rejects) => {
		handleRequest(api, 'GET', data, resolve, rejects,header)
	})
}

export const post = (api, data, header) => {
	// preHandle();
	return new Promise((resolve, rejects) => {
		handleRequest(api, 'POST', data, resolve, rejects, header)
	})
}

export const upload = (api, filePath, otherFormData) => {
	return new Promise((resolve, rejects) => {
		handleUpload(api, filePath,otherFormData, resolve, rejects)
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
						if (res.data.alreadyRegister) {
							storeTokenInfo(res.data);
						} else {
							var userInfo;
							wx.getUserInfo({
								// 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
								desc: '用于完善用户资料',
								success: (res) => {
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
					console.error('微信小程序登录失败！' + res.errMsg)
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

function handleRequest(api, method, data, resolve, reject, custHeader) {
	const token = uni.getStorageSync("token")
	var header = custHeader ? custHeader : {
		'content-type': 'application/json'
	}
	if (token) {
		header.token = token
	}
	var BASE_URL = Vue.prototype.baseUrl
	if(api.includes('oss')){
		BASE_URL += '/hutu-oss' 
	}else{
		BASE_URL += '/hutu-api'
	}
	uni.request({
		url: BASE_URL + api,
		data: data,
		method: method,
		header: {
			...header
		},
		timeout: TIME_OUT,
		success: (response) => {
			if(response.statusCode != 200){
				console.error("Request error: ", response)
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请求失败'
				})
				return reject(res)
			}
			const res = response.data
			if(!res.code){
				console.error("Request error: ", res)
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请求失败'
				})
				return reject(res)
			}
			if (res.code == 401) {
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请重新登录'
				});
				var pages = getCurrentPages()
				var page = pages[pages.length - 1]
				if (page.route !== '/pages/authority/index') {
					uni.navigateTo({
						url: '/pages/authority/index'
					})
				}
				return reject(res)
			}
			if (res.code != 200) {
				console.error("Request error: ", res.message)
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请求失败'
				})
				return reject(res)
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
			// uni.hideLoading()
		}
	});
}

function handleUpload(api, filePath,otherFormData, resolve, reject){
		const token = uni.getStorageSync("token")
		let header = {}
		if (token) {
			header.token = token
		}
		var BASE_URL = Vue.prototype.baseUrl
		if(api.includes('oss')){
			BASE_URL += '/hutu-oss' 
		}else{
			BASE_URL += '/hutu-api'
		}
		uni.uploadFile({
			url: BASE_URL + api,
			header: {...header},
			filePath: filePath,
			name: 'file',
			timeout: TIME_OUT,
			formData: {...otherFormData},
			success: (response) => {
			if(response.statusCode != 200){
				console.error("Request error: ", response)
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请求失败'
				})
				return reject(res)
			}
			const res = JSON.parse(response.data)
			if(!res.code){
				console.error("Request error: ", res)
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请求失败'
				})
				return reject(res)
			}
			if (res.code == 401) {
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请重新登录'
				});
				var pages = getCurrentPages()
				var page = pages[pages.length - 1]
				if (page.route !== '/pages/authority/index') {
					uni.navigateTo({
						url: '/pages/authority/index'
					})
				}
				return reject(res)
			}
			if (res.code != 200) {
				console.error("Request error: ", res.message)
				uni.showToast({
					icon: 'error',
					position: 'top',
					title: '请求失败'
				})
				return reject(res)
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
			// uni.hideLoading()
		}
	});
}