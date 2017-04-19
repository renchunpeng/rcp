import pathToRegexp from 'path-to-regexp'
import { test } from '../services/test1'
import { getCouponList,getCoupons } from '../services/coupon'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'couponList',

    state: {
        couponLists: [1, 2, 3, 4]
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                //进入该页面，加载优惠券列表
                const match = pathToRegexp('/couponList/:type').exec(pathname);
                let type = pathname.split('/')[2];
                if (match) {
                    if (type == 'show') {
                        dispatch({
                            type: 'init',
                            payload: custid
                        });
                        ChangeTitle('优惠券列表');
                    }
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getCouponList, payload);
            const {mianjianActUserCoupons,prdidActUserCoupons} = data.data.data;
            let _payload = mianjianActUserCoupons.concat(prdidActUserCoupons);
            yield put({
                type: 'couponListr',
                payload: _payload
            });
        },
        *test({ payload }, { call, put }) {
            const data = yield call(test, payload);
        }
    },

    reducers: {
        couponListr(state, action) {
            return { ...state, couponLists: action.payload }
        }
    }
}