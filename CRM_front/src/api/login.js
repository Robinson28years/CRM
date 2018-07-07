import request from '@/utils/request'

export function loginByEmail(email, password) {
  const data = {
    email,
    password
  }
  return request({
    url: '/auth/login',
    method: 'post',
    data
  })
}

export function logout() {
  // return request({
  //   url: '/login/logout',
  //   method: 'post'
  // })
}

export function getUserInfo() {
  return request({
    url: '/user_info',
    method: 'get'
  })
}

