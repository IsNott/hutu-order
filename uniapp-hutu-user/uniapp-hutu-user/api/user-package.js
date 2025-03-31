import {
	request
} from "@/request/request.js";

export function pay(param) {
	return request('/user-package/pay', 'POST', param)
}

export function updateContext(param) {
	return request('/bizUserPackage/updateContext', 'POST', param)
}

export function removeItemById(id) {
	return request('/bizUserPackage/removeItemById/' + id, 'PUT')
}

export function orderSettle(param) {
	return request('/bizPayOrder/settle', 'POST', param)
}

export function simulateNotify(orderId) {
	return request('/bizPayOrder/simulateNotify/' + orderId, 'GET')
}

export function orderQuery(orderId) {
	return request('/bizPayOrder/orderQuery/' + orderId, 'GET')
}