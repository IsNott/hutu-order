import {
	request
} from "@/request/request.js";

export function queryMyOrder(param, page, size) {
	return request(`/bizPayOrder/myOrder/${page}/${size}`, 'POST', param)
}

export function queryShopInfoById(id){
	return request(`/bizShopInfo/getById/${id}`, 'GET')
}