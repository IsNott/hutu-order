import { request } from "@/request/request.js";

export function shopList(){
		return request('/bizShopInfo/list', 'GET')
}

export function search(param){
		var url = '/bizShopInfo/search';
		url += '?keyWord=' + param
		return request(url,'GET')
}