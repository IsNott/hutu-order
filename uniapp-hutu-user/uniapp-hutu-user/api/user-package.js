import {
	request
} from "@/request/request.js";

export function pay(param) {
	return request('/user-package/pay', 'POST', param)
}

export function getPayWay(param) {
	return request('/bizPayWay/list', 'POST', param)
}