import request from "@/utils/request";

// export function upload(param) {
//     return request({
//         url: '/file/upload',
//         method:'POST',
//         contentType:'multipart/form-data',
//         data: param,
//     })
// }
export function upload(meetingId) {
    return request({
        url: '/file/zipFile',
        method:'POST',
        params: {
            meetingId
        },
    })
}

export function getFolder(meetingId){
    return request({
        url: '/file/getFolder',
        method:'GET',
        params: {
            meetingId
        },
    })
}