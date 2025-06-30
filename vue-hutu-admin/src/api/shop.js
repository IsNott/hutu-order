import ApiService from './ApiService';

const baseUrl = '/sys/bizShopInfo'
const shopService = new ApiService(baseUrl)

export const page = shopService.page.bind(shopService);
export const deleteById = shopService.deleteById.bind(shopService);
export const save = shopService.save.bind(shopService);
export const update = shopService.update.bind(shopService);
export const getById = shopService.getById.bind(shopService);