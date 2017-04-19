import pathToRegexp from 'path-to-regexp'
import { getItergration } from '../services/itergration'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'itergrations',

    state: {
        integralbalance: 0,
        actIntegralDetails: []
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/itergration').exec(pathname);
                if (match) {
                    dispatch({
                        type: 'init'
                    });
                    ChangeTitle('积分详情');
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getItergration, payload);
            if (data.data.success) {
                yield put({
                    type: 'itergrat',
                    payload: data.data.data
                });
            }
        }
    },

    reducers: {
        itergrat(state, action) {
            const {actIntegral,actIntegralDetails} = action.payload;
            const {integralbalance} = actIntegral;
            return { ...state, integralbalance: integralbalance,actIntegralDetails:actIntegralDetails }
        }
    }
}