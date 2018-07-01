import request from '@/utils/request'
import Axios from 'axios';

export function fetchList(query) {
  // return Axios({
  //   url: 'https://api-dev/article/list',
  //   method: 'get',
  //   query
  // })
  request({
    url: '/article/list',
    method: 'get',
    params: query
  })
}

export function fetchArticle() {
  return Axios({
    url: 'https://api-dev/article/detail',
    method: 'get'
  })
  // return request({
  //   url: '/article/detail',
  //   method: 'get'
  // })
}

export function fetchPv(pv) {
  return Axios({
    url: 'https://api-dev/article/pv',
    method: 'get',
    params: {pv}
  })
  // return request({
  //   url: '/article/pv',
  //   method: 'get',
  //   params: { pv }
  // })
}

export function createArticle(data) {
  return Axios({
    url: 'https://api-dev/article/create',
    method: 'post',
    data
  })
  // return request({
  //   url: '/article/create',
  //   method: 'post',
  //   data
  // })
}

export function updateArticle(data) {
  return Axios({
    url: 'https://api-dev/article/update',
    method: 'post',
    data
  })
  // return request({
  //   url: '/article/update',
  //   method: 'post',
  //   data
  // })
}
