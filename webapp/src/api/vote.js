import request from '@/utils/request'
import qs from "qs";

export function fetchList(meetingId){
    return request({
        url: '/vote/list?meetingId='+meetingId,
        method: 'get',
        
    })
}

export function createVote(params) {
    return request({
        url:'/vote/addVote',
        dataType:'JSON',
        method:'POST',
        data:params
    })
}

export  function getTraditionalResult(voteId) {
    return request({
        url:'/vote/getVoteResult',
        dataType:'JSON',
        method:'POST',
        params:{
            voteId
        }
    })
    
}

export  function changeVoteStatus(params) {
    return request({
        url:'/vote/changeVoteStatus',
        dataType:'JSON',
        method:'POST',
        data:qs.stringify(params)
    })
}

export function exportExcel(voteId){
    return request({
        url:'/vote/exportResult',
        method:'GET',
        params:{
            voteId
        }
    })
}