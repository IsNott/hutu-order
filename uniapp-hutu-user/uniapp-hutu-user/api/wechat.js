import {
	request
} from "../request/request";

export function miniProgramLogin(code) {
	return request(`/external/wechat/miniProgram/${code}`, 'GET')
}

export function loginByPhoneInfo(param) {
	return request(`/external/wechat/miniProgramLogin`, 'POST', param)
}