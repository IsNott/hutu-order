import { upload } from "@/request/request";

export function uploadAvatar(param){
		return upload('/oss/upload', param)
}