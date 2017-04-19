/**
 * Created by semantic on 2017/1/24.
 */
import request from '../utils/request'
//这个应该是redux-soga中的异步方案；
export async function query(params) {
  return request('../src/demoJson/test1.json', {
    method: 'get',
    data: params
  })
}

export async function submit(params) {

  /*    return request('/api/users', {
   method: 'post',
   data: params
   })*/
  //这边接受到的是前台传过来的全部的数据
  console.log(`测试一下获取到的数据是什么${JSON.stringify(params)}`);
  return {success: 'success'}

}

export async function test(params) {
  return request('/list/column', {
    method: 'get',
    data: params
  })
}
