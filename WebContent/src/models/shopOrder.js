/**
 * this models is created by ypli
 * description 店铺订单tab切换
*/
export default {
    namespace: 'shopOrderList',

    state: {
        indexTab: '1'
    },

    subscriptions: {

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