import request from '../utils/request'

export async function getMenuList(params) {
    return request('/lflweb/prd/shprdtype/{}', {
    method: 'get',
    data: params
  })
}