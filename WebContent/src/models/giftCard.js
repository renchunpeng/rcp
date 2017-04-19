/**
 * this models is created by ypli
 * description 会员卡使用记录列表
 */
import _ from '../utils'
import { getCardSendList } from '../services/giftCard'
import { parse } from 'qs'
import pathToRegexp from 'path-to-regexp'
import { Toast } from 'antd-mobile'
import ChangeTitle from '../utils/changeTitle'

export default {
  namespace: 'giftCard',

  state: {
    address: {
      addcontact: ''
    },
    list: [1,2,3]
  },

  subscriptions: {
    setup({ dispatch, history }) {
      history.listen(({ pathname }) => {
         const match = pathToRegexp('/giftCardCon/:memId').exec(pathname);
         if (match) {
            let memId = pathname.split('/')[2];
            dispatch({ 
               type: 'init',
               payload: memId
            });
            ChangeTitle('订单详情');
        }
      })
    }
  },

  effects: {
    *init({payload}, {call, put}) {
       console.log(payload);
      const data = yield call(getCardSendList, payload);
      console.log(`礼品卡获取倒的数据${data}`);
      yield put({
        type: 'getCardSendList',
        payload: {
          list: data.data.data
        }
      });
    },
    //将会员卡列表页面的数据获取到卡配送列表页,其中主要是地址信息
    *getAddress({ payload }, { call, put }) {
        console.log("前一个页面带过来的数据"+payload);
        yield put({
            type: 'addressr',
            payload: payload
        })
    }
  },

  reducers: {
    getCardSendList(state, action) {
      const { list } = action.payload;
      return { ...state, list: list };
    },
    addressr(state, action) {    
      return { ...state, ...action.payload }
    }
  }
}