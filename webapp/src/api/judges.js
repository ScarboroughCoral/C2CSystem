import request from '@/utils/request'
import md5 from 'md5'
import qs from "qs";
//添加评委--孙云栋
export function addJudge(params) {
  return request({
    url: '/addUser',
    // dataType:'JSON',
    method:'POST',
    data:qs.stringify(params)
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
export function getList(params) {
  return request({
    url: '/listUser',
    method: 'post',
    data:qs.stringify(params),
  })
}

export function changeUserMeeting(meetingId,params) {
    
  return request({
      url:'/changeUserMeeting',
      contentType:'application/json',
      method:'post',
      data:params,
      params:{
          meetingId
      }
  })
}
export function changeUserVote(voteId,params) {
    
  return request({
      url:'/changeUserVote',
      contentType:'application/json',
      method:'post',
      data:params,
      params:{
          voteId
      }
  })
}