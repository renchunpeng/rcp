import fetch from 'dva/fetch';
//返回的参数为response对象
function parseJSON(response) {
  return response.json();
}

function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
/*    console.log(`======>response头部为：${response.headers}`);
    console.log(`======>response的请求是否正确回复：${response.ok}`);
    console.log(`======>返回的状态内容是什么：${response.statusText}`);
    console.log(`======>response的状态是什么：${response.type}`);
    console.log(`======>请求的状态为：${response.status}`);
    console.log(`======>此时实在状态200到300之间的时候${response}`);*/
    return response;
  }

  const error = new Error(response.statusText);
  error.response = response;
  throw error;
}

/**
 * Requests a URL, returning a promise.
 *
 * @param  {string} url       The URL we want to request
 * @param  {object} [options] The options we want to pass to "fetch"
 * @return {object}           An object containing either "data" or "err"翻译：返回一个对象，这个对象可能时"data"
 */
export default function request(url, options) {
  return fetch(url, options)
    .then(checkStatus)
    .then(parseJSON)
    .then((data) => ({ data }))
    .catch((err) => ({ err }));
}
