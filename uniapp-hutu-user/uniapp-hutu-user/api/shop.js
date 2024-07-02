import { request } from "@/request/request.js";

export function shopList(){
		return request('/bizShopInfo/list', 'GET')
}