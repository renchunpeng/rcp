/**
 * this models is created by ypli
 * description 店铺商品tab切换
*/
export default {
    namespace: 'shopPrdList',

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