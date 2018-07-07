import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/visits',
    method: 'get',
    params: query
  })
}

export function fetchLastList(query) {
  return request({
    url: '/visit_latest',
    method: 'get',
    params: query
  })
}