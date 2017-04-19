import pathToRegexp from 'path-to-regexp'
import { test } from '../services/test1'
import { getBrandList } from '../services/brand'
import {Toast} from 'antd-mobile'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'brand',

    state: {
        brandList: [1, 2, 3, 4]
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/onlineMarket/brand').exec(pathname);
                if (match) {
                    dispatch({
                        type: 'init',
                        payload: 'all'
                    });
                    ChangeTitle('品牌展示');
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getBrandList, payload)
            if (data.data.success) {
                yield put({
                    type: 'brandListr',
                    payload: data.data.data
                });
            }
            else {
                Toast.fail('信息获取失败！！！', 1)
            }
        },
        *test({ payload }, { call, put }) {
            const data = yield call(test, payload);
        }
    },

    reducers: {
        brandListr(state, action) {
            return { ...state, brandList: action.payload }
        }
    }
}