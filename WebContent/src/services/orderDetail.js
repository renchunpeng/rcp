import request from '../utils/request'
import {formatDeliveryTime} from '../utils/DateFormat'

export async function getOrderDetail(params) {
    return request('/lflweb/ord/shordsundetails/'+params, {
    method: 'get',
    data: params
  })
}

//修改订单状态
export async function updateOrderStatus(params) {
    //参数为订单编号
    const {orderid} = params;
    return request('/lflweb/ord/upordbase/'+orderid+'/'+custid, {
    method: 'get',
    data: params
  })
}
//订单取消
export async function cancelOrder(params) {
    console.log('订单取消数据',params);
    const {orderid,createdate,totalprice,returnreason} = params;
    //取消订单提交数据格式
    let data = {
      returnid: '',
      orderid: orderid,
      custid: custid,
      createdate: formatDeliveryTime(createdate),
      returnstatus: '',
      returnreason: returnreason,
      orderprice: totalprice,
      returnprice: totalprice,
      okdate: formatDeliveryTime(createdate),
      remark: '测试的标注信息！！！'
    };
    return request('/lflweb/ord/reordbase', {
    method: 'post',
    body: JSON.stringify(data)
  })
}
