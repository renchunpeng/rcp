import ChangeTitle from '../utils/changeTitle'
import pathToRegexp from 'path-to-regexp'

export default {
    namespace: 'orderList',

    state: {
        indexTab: '1'
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/orderList').exec(pathname);
                if (match) {
                    ChangeTitle('订单列表');
                };
            })
        }
    },

    effects: {
        *changeTab({ payload }, { call, put }) {
            console.log(payload);
            yield put({
                type: 'changeTabr',
                payload: payload
            })
        }
    },

    reducers: {
        changeTabr(state, action) {
            return { ...state, indexTab: action.payload }
        }
    }
}