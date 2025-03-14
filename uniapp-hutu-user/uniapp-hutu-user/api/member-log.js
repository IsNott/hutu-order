import { request } from "../request/request";

export function login(param){
	return request('/bizUser/login','POST', param)
}