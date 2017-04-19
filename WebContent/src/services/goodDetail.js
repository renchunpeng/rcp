import request from '../utils/request'

export async function getGoodDetail(params) {
    return request(`/lflweb/prd/shprdbasedetail/${params.prdid}/${params.prdkind}`, {
    method: 'get',
    data: params
  })
}
//获取商品评价列表
export async function getCommentLists(params) {
    //参数为订单编号
    return request('/lflweb/ord/SearchPrdComment/'+params, {
    method: 'get',
    data: params
  })
}