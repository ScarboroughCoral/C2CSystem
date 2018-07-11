import request from "@/utils/request";

export function listHouses(params) {
    return request({
        url: '/lobby/getHouseInfo',
        method:'GET'
    })
}

export function publishHouse(rentDTO) {
    return request({
        url: '/lobby/rentHouse',
        dataType:'JSON',
        contentType:'application/json',
        method:'POST',
        data:rentDTO
    })
}
export function getHouseType() {
    return request({
        url: '/lobby/getHouseType',
        method:'GET',
    })
}

export function renterHouseMsg(userID){
    return request({
        url: '/lobby/renterHouseMsg',
        method:'POST',
        params:{
            userID
        }
    })
}

export function uploadImg(params) {
    return request({
        url: '/img/setHouseImg',
        method:'POST',
        contentType:'multipart/form-data',
        data: params,
    })
}