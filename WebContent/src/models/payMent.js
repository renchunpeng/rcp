import _ from '../utils'
import { parse } from 'qs'
import pathToRegexp from 'path-to-regexp'
import { Toast } from 'antd-mobile'
import { getPayParams, wxPayAuth } from '../services/payMent'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'pay',

    state: {
        PayParams: {},
        orderifo: {
            sons: [],
            fathor: {}
        },
        goodsList: [],
        totalPrice: 0.00
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/payMent/:custId/:orderId').exec(pathname);
                if (match) {
                    let custId = pathname.split('/')[2];
                    let orderId = pathname.split('/')[3]
                    dispatch({
                        type: 'init',
                        payload: {
                            custId: custId,
                            orderId: orderId
                        }
                    });
                    ChangeTitle('微信支付');
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
                const data1 = yield call(getPayParams, payload);
                if (data1.data.success) {
                    Toast.hide();
                    yield put({
                        type: 'initr',
                        payload: {
                            PayParams: data1.data.data,
                            orderifo: data1.data.orderifo
                        }
                    });
                }
                else {
                    Toast.fail(data1.data.msg, 1)
                }
        },
        *getShoppingCart({ payload }, { call, put }) {
            yield put({
                type: 'getShoppingCartr',
                payload: payload
            })
        }
    },

    reducers: {
        initr(state, action) {
            const { PayParams,orderifo } = action.payload;
            return { ...state, PayParams: PayParams,orderifo: orderifo }
        },
        getShoppingCartr(state, action) {
            const {goodsList,totalPrice} = action.payload;
            return { ...state, goodsList: goodsList,totalPrice: totalPrice}
        }
    }
}