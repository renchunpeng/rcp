/**
 * this models is created by ypli
 * description 用户礼品卡tab切换
*/
import pathToRegexp from 'path-to-regexp'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'giftCardList',

    state: {
        indexTab: '1'
    },

    subscriptions: {
        setup({dispatch, history}) {
            history.listen(({pathname}) => {
                const match = pathToRegexp('/giftCardList').exec(pathname);
                if(match) {
                    ChangeTitle('会员卡管理');
                }
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