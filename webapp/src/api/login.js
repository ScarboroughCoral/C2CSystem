import request from '@/utils/request'
import md5 from 'md5'

export function login(loginName, password) {
  return request({
    url: '/admin',
    dataType:'JSON',
    method:'POST',
    data: {
      loginName,
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
