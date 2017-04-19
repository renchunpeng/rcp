import pathToRegexp from 'path-to-regexp'
import { test } from '../services/test1'
import { getJSSDK } from '../services/userAction'
import { Toast } from 'antd-mobile'
import ChangeTitle from '../utils/changeTitle'

export default {
  namespace: 'scan',

  state: {
    result: '请点击下方按钮扫描'
  },

  subscriptions: {
    setup({ dispatch, history }) {
      history.listen(({ pathname }) => {
        const match = pathToRegexp('/scan').exec(pathname);
        if (match) {
          ChangeTitle('扫码追溯');
        }
      })
    }
  },

  effects: {
    *getResult({ payload }, { call, put }) {
      yield put({
        type: 'getResultr',
        payload: payload
      })
    }
  },

  reducers: {
    getResultr(state, action) {
      return { ...state, ...action.payload }
    }
  }
}