import {
	request
} from "@/request/request";

export function register(param) {
	return request('/bizUser/register', 'POST', param)
}

export function updateProfile(param) {
	return request('/bizUser/profile', 'POST', param)
}

export function myBalance() {
	return request('/bizUser/myBalance', 'GET')
}

export function isLogin() {
	return request('/bizUser/isLogin', 'GET')
}