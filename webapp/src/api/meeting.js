import request from "@/utils/request";
import qs from "qs";

export function createMeeting(params) {
    return request({
        url:'/meeting/createMeeting',
        method:'POST',
        data:qs.stringify(params)
    })
}

export function startMeeting(meetingId) {
    return request({
        url: '/meeting/startMeeting',
        method: 'POST',
        data:qs.stringify({
            meetingId
        })
    })
}

export function endMeeting(meetingId) {
    return request({
        url: '/meeting/endMeeting',
        method: 'POST',
        data:qs.stringify({
            meetingId
        })
    })
}