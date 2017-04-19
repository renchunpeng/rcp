/**
 * Created by semantic on 2017/2/3.
 */
//将数据获取及处理集中到一处
import { getList } from '../services/list'
import { parse } from 'qs'

export default {
  namespace: 'list',
  //初始化state
  state: {
    list: []
  },

  subscriptions: {
    //页面一旦加载就会订阅到该事件
    setup({dispatch, history}) {
      dispatch({type: 'getList'});
    }
  },
  //这个是对redux-soga的一层封装，为了更好的实现异步
  effects: {
    *getList({payload}, {call, put}) {
      const data = yield call(getList, parse(payload));
      console.log(`======>看看获取到的list-${data.data.data.list}`);
      yield put({
        type: 'getListData',
        payload: {
          list: data.data.data.list
        }
      });
    }
  },
  //通过reducers来操作state
  reducers: {
    getListData(state, action) {
      console.log(`======>传入的action${action.payload}`);
      return {...state, ...action.payload};
    }
  }
}
