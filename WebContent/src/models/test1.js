/**
 * Created by semantic on 2017/1/23.
 */
import  _ from '../utils';
import { query,submit } from '../services/test1'
import {parse} from 'qs'

export default {
  namespace: 'test1',

  //初始化state
  state: {
    param1: 'test',
    param2: ''
  },
  //在这里开始订阅数据源
  subscriptions: {
    setup({dispatch, history}) {
      /*      history.listen(({pathname}) => {
       if (pathname === '/demo') {
       dispatch({
       type: 'query'
       });
       }
       });*/
      dispatch({type: 'query'});
    },

    change({dispatch, history}) {
      dispatch({type: 'change'});
    }
  },

  effects: {
    *query({payload}, {call, put}) {
      const data = yield call(query, parse(payload));
      console.log(`======>接口中获取到的数据为：${JSON.stringify(data)}`);
      yield put({
        type: 'getMessage',
        payload: {
          param2: data.data.message
        }
      });
    },
    //如果是关键字的花这里要通过单引号,
    /*
     * 注意一下effects中的名字和reducers中的名字不能起一样的，不然会很好看！
     * */
    *handleClick({payload}, {call, put}) {
      console.log('到这里看看执行了几次！');
      yield put({
        type: 'handleClickEvent',
        payload: {
          param2: 'ha ha wo dianji le anniu'
        }
      });
    },

    *submit({payload}, {call, put}) {
      console.log('这里执行的数据提交操作');
      const data = yield call(submit, parse(payload));
      console.log(`请求获取到的数据为${JSON.stringify(data)}`);
      yield put({
        type: 'addMessage',
      });
    }
  },

  reducers: {
    getMessage(state, action) {
      console.log(`======>传入的state${JSON.stringify(state)}`);
      return {...state, ...action.payload};
    },
    handleClickEvent(state, action) {
      return {...state, ...action.payload};
    },
    submit(state, action) {
      console.log(`======>传入的action${JSON.stringify(action.payload)}`);
      return {...state, ...action.payload};
    }
  }
}
