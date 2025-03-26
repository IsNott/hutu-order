import {
	request
} from "@/request/request.js";

export function queryCommonRemark(size) {
	return request(`/bizCommonRemark/query/${size}`, 'GET')
}