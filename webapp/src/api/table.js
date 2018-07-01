import request from '@/utils/request'
import qs from "qs";
export function getList(params) {
  return request({
    url: '/listUser',
    method: 'post',
    data:qs.stringify(params),
  })
}
