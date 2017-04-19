/**
 * fetch 默认是不会携带cookie的，如果需要携带cookie，则需要设置：credentials: 'include'
*/
import request from '../utils/request'

export async function getUserInformation(params) {
    //暂时模拟已获取到openid
    //lzz omEi30_jxNxYUIBVmlNgim3ktLoA
    var openId = "omEi307kdfaKm-wJ3YvFOjP3vJ0Y";
		document.cookie = "openId=" + openId;
    return request('/lflweb/WXuser/getUserDate', {
    method: 'get',
    data: params,
    credentials: 'include'
  })
}

export async function changeName(params) {
    let userName = encodeURIComponent(params.userName);
    return request(`/lflweb/account/UpdateUserName/${params.custId}/${userName}`, {
    method: 'get',
    data: params
  })
}

export async function changePhoneNumber(params) {
    return request(`/lflweb/account/UpdatePhone/${params.custid}/${params.custmobile}`, {
    method: 'get',
    data: params
  })
}

export async function getJSSDK(params) {
    let data = {
      url: params
    }
    return request(`/lflweb/wxjs/getjsjdk`, {
    method: 'post',
    body: JSON.stringify(data)
  })
}