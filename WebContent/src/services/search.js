import request from '../utils/request'
//获取商品查询数据
export async function getSearchResult(params) {
  console.log(`=======================${params}`);
    let keyWord = params;
    return request(`/lflweb/prd/shprdbasedetail/${keyWord}`, {
    method: 'get',
    data: params
  })
}