import request from '@/utils/request'
import md5 from 'md5'

export function login(loginName, password) {
  return request({
    url: '/login',
    dataType:'JSON',
    method:'POST',
    params: {
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

export function logout(userID) {
  return request({
    url: '/logout',
    method: 'post',
    params:{
      userID
    }
  })
}

export function regist(params) {
  return request({
    url:'/regist',
    method:'POST',
    params:params
  })
}
