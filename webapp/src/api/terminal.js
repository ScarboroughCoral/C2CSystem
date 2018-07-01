import request from '@/utils/request'
import qs from "qs";

export function getListTerminals(meetingId) {
  return request({
    url: '/terminals?meetingId='+meetingId,
    method: 'get',
  })
}

export function addTerminal(params) {
    return request({
        url:'/addTerminal',
        method:'post',
        data:qs.stringify(params)
    })
}

export function changeTerminalMeeting(meetingId,params) {
    
    return request({
        url:'/changeTerminalMeeting',
        contentType:'application/json',
        method:'post',
        data:params,
        params:{
            meetingId
        }
    })
}