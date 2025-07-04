import request from '@/utils/request'

export function login(data) {
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/sys/admin/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/sys/admin/info',
    method: 'post',
    params: { token }
  })
}

export function logout() {
  return request({
    url: process.env.VUE_APP_ADMIN_API + '/sys/admin/logout',
    method: 'post'
  })
}
