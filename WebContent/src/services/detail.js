import request from '../utils/request'
//获取订单详情
export async function getOrderDetail(params) {
    return request('/lflweb/ord/orderDetail/'+params, {
    method: 'get',
    data: params
  })
}

