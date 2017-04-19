import request from '../utils/request'
//获取订单详情
export async function getItergration(params) {
    return request('/lflweb/act/integral/'+custid, {
    method: 'get',
    data: params
  })
}