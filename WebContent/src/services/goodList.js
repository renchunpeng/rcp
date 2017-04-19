import request from '../utils/request'

export async function getGoodList(params) {
    return request('../src/demoJson/goodList.json', {
    method: 'get',
    data: params
  })
}

export async function searchList(params) {
  console.log('kakan1-----------'+JSON.stringify(params));
  let number = params.lenth;
  return request('../src/demoJson/goodList'+'.json', {
    method: 'get',
    data: params
  })
}