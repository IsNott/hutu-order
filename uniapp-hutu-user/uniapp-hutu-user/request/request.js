import {
	fail
} from "assert";

const BASE_URL = 'http://localhost:10001'


export const request = (api, method, data) => {
	return new Promise((resolve, rejects) => {
		handleRequest(api, method, data, resolve, rejects)
	})


	function handleRequest(api, method, data, resolve, reject) {
		uni.showLoading({
			title: '加载中'
		})
		uni.request({
			url: BASE_URL + api,
			data: data,
			method: method,
			header: {
				'Content-Type': 'application/json'
			},
			success: (response) => {
				const res = response.data
				if (response.statusCode == 401) {
					uni.showToast({
						icon: 'error',
						position: 'top',
						title: '请重新登录'
					});
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
				return reject(err);
			},
			complete() {
				uni.hideLoading()
			}
		});
	}
}