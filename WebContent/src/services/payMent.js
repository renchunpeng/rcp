import request from '../utils/request'
import { parse } from 'qs'

export async function wxPayAuth(params) {
  return request('/lflweb/wx/wxPayAuth/' + params.custId + '/' + params.orderId, {
    method: 'get',
    data: params
  })
}

export async function getPayParams(params) {
  return request('/lflweb/wx/GetPreOrderID/' + params.custId + '/' + params.orderId, {
    method: 'get',
    data: params
  })
}

export async function getAliPay(params) {
  return request('/lflweb/zfb/zfbpay/' + params, {
    method: 'get',
    data: params
  })
}