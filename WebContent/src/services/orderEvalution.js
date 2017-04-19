import request from '../utils/request'
import moment from 'moment'
//提交订单数据
export async function submitEvalution(params) {
  const { goodsList, prdpacking, deliveryspeed, deliveryservice, orderid } = params;
  let now = moment().format('YYYY-MM-DD hh:mm:ss');
  let _goodsList = [];
  for (let i=0;i<goodsList.length;i++) {
    _goodsList[i] = {};
    _goodsList[i]['pcommentid'] = '';
    _goodsList[i]['orderid'] = orderid;
    _goodsList[i]['prdid'] = goodsList[i].prdid;
    _goodsList[i]['prdsatisfaction'] = `${goodsList[i].prdsatisfaction}`;
    _goodsList[i]['prdcomment'] = goodsList[i].prdcomment;
    _goodsList[i]['commdate'] = now;
    _goodsList[i]['commreply'] = '';
    _goodsList[i]['replyer'] = '';
    _goodsList[i]['replydate'] = now;
    _goodsList[i]['commstatus'] = '01';
  }
  //准备提交的数据格式
  let data = {
    delivery: {
      dcommentid: '',
      orderid: orderid,
      prdpacking: `0${prdpacking}`,
      deliveryspeed: `0${deliveryspeed}`,
      deliveryservice: `0${deliveryservice}`,
      commdate: now
    },
    prds: _goodsList
  };
  return request(`/lflweb/ord/SaveComment`, {
    method: 'post',
    body: JSON.stringify(data)
  })
}