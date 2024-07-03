import { request } from "@/request/request";

export function register(param){
	return request('/bizUser/register','POST',param)
}
