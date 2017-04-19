import request from '../utils/request'

//获取礼品卡配送记录列表
export async function getCardSendList(params) {
  //参数为订单编号
  console.log("======订单编号======="+params);
  return request('/lflweb/order/cardSendList/' + params, {
    method: 'get',
    data: params
  })
}