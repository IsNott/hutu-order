import { request } from "@/request/request.js";
import { httpGet } from "@/request/request.js";

export function getMenuCatalog(){
	return request('/bizMenuCatalog/list', 'POST')
}

export function listByShop(shopId){
	return httpGet(`/bizMenuCatalog/listByShop/${shopId}`)
}

export function listByShopCatalogId(shopId,catalogId){
	return request(`/bizMenu/listByShopCatalogId/${shopId}/${catalogId}`, 'GET')
}

export function listByCatalogId(catalogId){
	return request(`/bizMenu/listByCatalogId/${catalogId}`,'GET')
}

export function defaultShop(){
	return request(`/bizShopInfo/default`,'GET')
}

export function findSkuByItemId(itemId){
	return request(`/sku/${itemId}`)
}