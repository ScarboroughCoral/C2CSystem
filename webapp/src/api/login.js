import request from '@/utils/request'
import md5 from 'md5'

export function login(username, password) {
  return request({
    url: '/login',
    dataType:'JSON',
    method:'POST',
    data: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function regist(params) {
  return request({
    url:'/regist',
    method:'POST',
    params:params
  })
}
