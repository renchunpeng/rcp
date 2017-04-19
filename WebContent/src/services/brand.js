import request from '../utils/request'
//获取品牌列表
export async function getBrandList(params) {
    return request(`/lflweb/prd/shprdbrand/${params}`, {
    method: 'get',
    data: params
  })
}