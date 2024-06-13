import request from '@/utils/request'

export function countBizUser(){
  return request({
    url: '/sys/bizUser/count',
    method: 'get'
  })
}

export function countBizComment(){
  return request({
    url: '/sys/bizComment/count',
    method: 'get'
  })
}

export function countBizOrder(){
  return request({
    url: '/sys/bizPayOrder/count',
    method: 'get'
  })
}

export function countBizOrderPurchases(){
  return request({
    url: '/sys/bizPayOrder/countPurchases',
    method: 'get'
  })
}

