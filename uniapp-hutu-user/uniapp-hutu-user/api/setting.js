import { request } from "../request/request";

export function logout(){
	return request('/bizUser/logout','GET')
}