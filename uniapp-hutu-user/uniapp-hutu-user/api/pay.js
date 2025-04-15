import { getCurrentPlatform } from "@/utils/CommonUtils";
import { request } from "@/request/request.js";

export function queryPayWay(){
	let platform = getCurrentPlatform()
	let param = {
		platformName: platform
	}
	return request('/bizPayWay/list', 'POST', param)
}

export function gateway(param){
	return request('/pay/gateway', 'POST', param)
}