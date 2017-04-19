import request from '../utils/request'
//获取订单详情
export async function getCouponList(params) {
    return request('/lflweb/act/mycoupon/'+custid, {
    method: 'get',
    data: params
  })
}

export async function getCouponCanUse(params) {
    let item = JSON.stringify(params);
    console.log(item);
    return request('/lflweb/act/ordercoupon/'+item, {
    method: 'get',
    data: params
  })
}

//获取可绑定的优惠券
export async function getCoupons(params) {
    return request(`/lflweb/act/coupon/${custid}`, {
    method: 'get',
    data: params
  })
}

//用户绑定优惠券
export async function bindCoupons(params) {
    console.log(params);
    return request(`/lflweb/act/savecoupon`, {
    method: 'post',
    body: JSON.stringify(params)
  })
}