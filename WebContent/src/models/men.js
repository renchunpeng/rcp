import _ from '../utils'
import { getMenuList } from '../services/menu'
import { parse } from 'qs'

export default {
    namespace: 'men',

    state: {
        list: [],
        show: true
    },

    subscriptions: {
        setup({dispatch,history}) {
            dispatch({ type: 'init' });
        }
    },

    effects: {
        *init({payload}, {call, put}) {
            const data = yield call(getMenuList, parse(payload));
            yield put({
                type: 'getMenuListr',
                payload: {
                    list: data.data.data
                }
            });
        }
    },

    reducers: {
        getMenuListr(state, action) {
            const list = action.payload.list
            return {...state, list: list}
        }
    }
}