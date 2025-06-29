import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/sys/admin/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/sys/admin/info',
    method: 'post',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/sys/admin/logout',
    method: 'post'
  })
}
