import _ from '../utils'
import { getGoodList,searchList } from '../services/goodList'
import { parse } from 'qs'

export default {
    namespace: 'glist',

    state: {

    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                if (pathname === '/goodSearch') {
                    dispatch({
                        type: 'init'
                    })
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getGoodList, parse(payload));
            yield put({
                type: 'initr',
                payload: data.data.data
            })
        },
        //搜索商品
        *search({ payload }, { call, put }) {
            const data = yield call(searchList, payload);
            yield put({
                type: 'searchr',
                payload: data.data.data
            })
        }
    },

    reducers: {
        initr(state, action) {
            return { ...state, ...action.payload }
        },
        searchr(state, action) {
            return { ...state, ...action.payload }
        }
    }
}