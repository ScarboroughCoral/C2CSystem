import request from '@/utils/request'


export function getProvince() {

    return request({
        url: '/address/province',
        method:'get'
    })
}

export function getCity(provinceId) {
    
    return request({
        url: '/address/getCity',
        method:'get',
        params:{
            provinceId
        }
    })
}
export function getDistrict(cityId) {
    
    return request({
        url: '/address/getDistrict',
        method:'get',
        params:{
            cityId
        }
    })
}