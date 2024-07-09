import {
	request
} from "@/request/request";

export function register(param) {
	return request('/bizUser/register', 'POST', param)
}

export function updateProfile(param) {
	return request('/bizUser/profile', 'POST', param)
}