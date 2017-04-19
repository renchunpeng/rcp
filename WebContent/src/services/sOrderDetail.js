import request from '../utils/request'
import {formatDeliveryTime} from '../utils/DateFormat'

export async function getOrderDetail(params) {
    return request('/lflweb/ord/shordsundetails/'+params, {
    method: 'get',
    data: params
  })
}

//店铺发货
export async function updateOrderSend(params) {
    //参数为订单编号
    const {orderid} = params;
    return request('/lflweb/shop/upOrdSend/'+orderid+'/'+custid, {
    method: 'get',
    data: params
  })
}

//店铺配送完成
export async function updateOrderSendOk(params) {
    //参数为订单编号
    const {orderid} = params;
    return request('/lflweb/shop/upOrdSendOk/'+orderid+'/'+custid, {
    method: 'get',
    data: params
  })
}

//店铺同意取消
export async function updateOrderCancel(params) {
    //参数为订单编号
    const {orderid} = params;
    return request('/lflweb/shop/upOrdCancel/'+orderid+'/'+custid, {
    method: 'get',
    data: params
  })
}

