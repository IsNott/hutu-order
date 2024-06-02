import { request } from "@/request/request.js";

export function getMenuCatalog(){
	return request('/bizMenuCatalog/list', 'POST')
}

export function listByCatalogId(catalogId){
	return request(`/bizMenu/listByCatalogId/${catalogId}`,'GET')
}