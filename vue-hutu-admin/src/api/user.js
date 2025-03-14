import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/sys/admin/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/sys/user/userInfo',
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}
