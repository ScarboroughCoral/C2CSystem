import request from "@/utils/request";

export function setOrder(params) {
    return request({
        url: '/order/setOrder',
        method:'post',
        params:params
    })
}

export function listOrder(userId) {
    return request({
        url:'/order/getOrderMsg',
        method:'get',
        params:{
            userId
        }
    })
}