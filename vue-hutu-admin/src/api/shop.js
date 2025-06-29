import request from '@/utils/request'

export function shopPage(params) {
  return request({
    url: `/sys/bizShopInfo/page/${params.page}/${params.size}`,
    method: 'post',
    data: params
  })
}
