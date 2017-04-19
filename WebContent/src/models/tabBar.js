/**
 * Created by semantic on 2017/2/3.
 */
export default {
  namespace: 'tabBar',
  //初始化state
  state: {
    hidden: false,
    selectedTab: 'blueTab'
  },

  subscriptions: {
    //页面一旦加载就会订阅到该事件
    setup({dispatch, history}) {
      history.listen(({pathname})=>{
        if (pathname === '/onlineMarket/index') {
          dispatch({
            type: 'changeBlue'
          })
        }
        else if (pathname === '/onlineMarket/classify') {
          dispatch({
            type: 'changeRed'
          })
        }
        else if (pathname === '/onlineMarket/shoppingCart') {
          dispatch({
            type: 'changePurple'
          })
        }
        else if (pathname === '/onlineMarket/personal') {
          dispatch({
            type: 'changeYellow'
          })
        }
      })
    }
  },
  //这个是对redux-soga的一层封装，为了更好的实现异步
  effects: {
    *getList({payload}, {call, put}) {
      const data = yield call(gettabBar, parse(payload));
      yield put({
        type: 'gettabBar',
        payload: {

        }
      });
    },
    //修改底部导航栏的显示状态
    *changeHidden({payload},{put}) {
      yield  put({
        type: 'changeHiddenEvent'
      })
    },
    //修改选中item
    *changeBlue({payload},{put}) {
      yield put({
        type: 'changeBlueEvent'
      })
    },
    *changeRed({payload},{put}) {
      yield put({
        type: 'changeRedEvent'
      })
    },
    *changeGreen({payload},{put}) {
      yield put({
        type: 'changeGreenEvent'
      })
    },
    *changeYellow({payload},{put}) {
      yield put({
        type: 'changeYellowEvent'
      })
    },
    *changePurple({payload},{put}) {
      yield put({
        type: 'changePurpleEvent'
      })
    }
  },
  //通过reducers来操作state
  reducers: {
    getListData(state, action) {
      console.log(`======>传入的action${action.payload}`);
      return {...state, ...action.payload};
    },
    changeHiddenEvent(state) {
      if (state.hidden){
        return {...state,hidden: false}
      }
      return {...state,hidden: true}
    },
    changeBlueEvent(state) {
      return {...state,selectedTab: 'blueTab'}
    },
    changeRedEvent(state) {
      return {...state,selectedTab: 'redTab'}
    },
    changeGreenEvent(state) {
      return {...state,selectedTab: 'greenTab'}
    },
    changeYellowEvent(state) {
      return {...state,selectedTab: 'yellowTab'}
    },
    changePurpleEvent(state) {
      return {...state,selectedTab: 'purpleTab'}
    }
  }
}
