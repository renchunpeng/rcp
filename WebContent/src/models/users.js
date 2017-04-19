import _ from '../utils'
import { getUserInformation, changeName, changePhoneNumber } from '../services/userAction'
import { parse } from 'qs'

export default {
    namespace: 'user',

    state: {
        custId: '001',
        userName: 'jingchenxu',
        phoneNumber: '18862237873',
        custid: '还没有custid',
        remark: '222222222'
    },

    subscriptions: {
        setup({ dispatch, history }) {
            dispatch({ type: 'init' });
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getUserInformation, parse(payload));
            yield put({
                type: 'getUserr',
                payload: data.data.data
            });
        },
        *changeName({ payload }, { call, put }) {
            const data = yield call(changeName, parse(payload));
            if (data.success) {
                yield put({
                    type: 'nameSuccess',
                    payload: payload
                })
            }
            else {
                yield put({
                    type: 'nameFaild'
                })
            }
        },
        *changePhoneNumber({ payload }, { call, put }) {
            const data = yield call(changePhoneNumber, parse(payload));
            if (data.success) {
                yield put({
                    type: 'phoneSuccess',
                    payload: payload
                })
            }
            else {
                yield put({
                    type: 'phoneFaild'
                })
            }
        }
    },

    reducers: {
        getUserr(state, action) {
            return { ...state, ...action.payload };
        },
        nameSuccess(state, action) {
            return { ...state, ...action.payload };
        },
        nameFaild(state, action) {
            return { ...state, changeName: false};
        },
        phoneSuccess(state, action) {
            return { ...state, ...action.payload };
        },
        phoneFaild(state, action) {
            return { ...state, changePhone: false};
        }
    }
}