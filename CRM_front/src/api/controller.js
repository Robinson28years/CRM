import request from '@/utils/request'

export function open_auth(data) {
  return request({
    url: '/switch_auth',
    method: 'post',
    data
  })
}