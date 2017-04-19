import request from '../utils/request'
import { parse } from 'qs'

export async function getShoppingCart(params) {
    //传入的参数为custid
  return request('/lflweb/ord/ordershop/' + params, {
    method: 'get',
    data: params
  })
}