import request from '../utils/request'
//获取活动商品列表
export async function getActivityList(params) {
    return request(`/lflweb/prd/searchtypeselect`, {
    method: 'get',
    data: params
  })
}
//获取首页广告图片
export async function getAdvertisement(params) {
    return request(`/lflweb/mar/adv`, {
    method: 'get',
    data: params
  })
}
//获取首页大类列表
export async function getBigType(params) {
    return request(`/lflweb/mar/bigtype`, {
    method: 'get',
    data: params
  })
}