import { request } from "@/request/request.js";

export function pay(param){
		return request('/user-package/pay', 'POST',param)
}