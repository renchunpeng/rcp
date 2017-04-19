/**
 * Created by semantic on 2017/2/3.
 */
import request from '../utils/request'

export async function getList(params) {
  return request('../src/demoJson/list.json',{
    method: 'get',
    data: params
  })
}
