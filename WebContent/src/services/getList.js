import request from '../utils/request'

export async function getList(params) {
    return request('../src/demoJson/list/list0.json', {
    method: 'get',
    data: params
  })
}