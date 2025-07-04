import request from '@/utils/request.js'

export function upload(param) {
  return request({
    url: process.env.VUE_APP_OSS_API + '/oss/upload',
    method: 'post',
    data: param,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
