import { request } from "@/request/request.js";

export function shopList(){
		return request('/bizShopInfo/list', 'GET')
}

export function search(param){
		return request('/bizShopInfo/search','POST',param)
}