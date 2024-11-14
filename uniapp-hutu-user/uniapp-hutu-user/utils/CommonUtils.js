export default function getWeekName(day) {
	if (day < 1 || day > 7 || day === '') {
		return ''
	}
	switch (day) {
		default:
			return ''
		case 1:
			return '周一';
		case 2:
			return '周二';
		case 3:
			return '周三';
		case 4:
			return '周四';
		case 5:
			return '周五';
		case 6:
			return '周六';
		case 7:
			return '周日';
	}
}

export function getStoreUserInfo() {
	return uni.getStorageSync('user_info')
}

export function storeUserInfo(info) {
	uni.setStorageSync("user_info", info)
	uni.setStorageSync('token',info.token)
}

export function getDateStr() {
	return new Date().getTime().toString();
}

export function getCurrentPlatform(){
	var str = '';
	// #ifdef MP-WEIXIN
	str = 'MP-WEIXIN'
	// #endif
	// #ifdef MP-ALIPAY
	str = 'MP-WEIXIN'
	// #endif
	// #ifdef H5
	str = "WEB"
	// #endif
	return str;
}

export function handleImageUrl(urlStr){
	if(urlStr == undefined){
		return ''
	}
	var url = ''
	if (urlStr.includes(',')) {
		url = urlStr.split(',')[0]
	} else {
		url = urlStr
	}
	return url == '' ? require('@/static/image/not-image.png') : url
}

export function commonNavigate(url){
	uni.navigateTo({
		url: url,
		fail(res) {
			uni.switchTab({
				url: url,
			})
		}
	})
}

// todo 此方法未完成
export function handleImageUrlArray(urlStr){
	console.log(urlStr)
	if(urlStr == undefined){
		return ''
	}
	var url = []
	if (urlStr.includes(',')) {
		url = urlStr.split(',')
	} else {
		url = [...urlStr]
	}
	return url == [] ? require('@/static/image/not-image.png') : url
}