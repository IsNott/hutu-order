import {
	request
} from "@/request/request";

export function queryOrderById(id) {
	return request('/bizPayOrder/queryById/' + id, 'GET')
}

export function orderFront(id){
	return request('/bizPayOrder/orderFront/' + id, 'GET')
}