import pathToRegexp from 'path-to-regexp'
import { getSearchResult } from '../services/search'
import { Toast } from 'antd-mobile'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'search',

    state: {
        msg: '请在输入框中输入关键字！！！',
        suggestion: ['测试商品1', '测试商品2', '测试商品3', '测试商品4'],
        resultList: []
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/search').exec(pathname);
                if (match) {
                    console.log('我们到达搜索页面！');
                    ChangeTitle('商品查询');
                };
            })
        }
    },

    effects: {
        *changeKey({ payload }, { call, put }) {

            const data = yield call(getSearchResult, payload);
            if (data.data.success) {
                yield put({
                    type: 'changeKeyr',
                    payload: data.data.data
                });
            }
            else {
                Toast.fail('商品信息查找失败！！！', 1)
            }
        }
    },

    reducers: {
        changeKeyr(state, action) {
            if(action.payload.length == 0) {
                return { ...state, resultList: action.payload,msg: '未查询到相关信息！！！' }
            }
            else {
                return { ...state, resultList: action.payload }
            }
        }
    }
}