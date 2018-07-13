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

export function checkout(orderId){
    return request({
        url:'/order/checkout',
        method:'post',
        params:{
            orderId
        }
    })
}