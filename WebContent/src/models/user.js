import _ from '../utils'
import { getUserInformation, changeName, changePhoneNumber } from '../services/userAction'
import { parse } from 'qs'
import { Toast } from 'antd-mobile'
import ChangeTitle from '../utils/changeTitle'
import pathToRegexp from 'path-to-regexp'
import avatar from '../img/default.png'

export default {
    namespace: 'user',

    state: {
        custid: 'AC201703153115372960',
        openid: 'omEi307kdfaKm-wJ3YvFOjP3vJ0Y',
        custname: '用户名',
        custmobile: '18862237873',
        countyname: '默认',
        countyid: '01',
        zonecode: '002',
        integralbalance: 0,
        wechatavatar: avatar,
        sum: 0
    },

    subscriptions: {
        //只会在页面第一次加载的时候执行
        setup({ dispatch, history }) {
            dispatch({ type: 'init' });
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/onlineMarket/personal').exec(pathname);
                if (match) {
                    ChangeTitle('个人中心');
                };
                const match1 = pathToRegexp('/countManage').exec(pathname);
                if (match1) {
                    ChangeTitle('账号管理');
                };
                const match2 = pathToRegexp('/myCount').exec(pathname);
                if (match2) {
                    ChangeTitle('我的账户');
                };
                const match3 = pathToRegexp('/changeName').exec(pathname);
                if (match3) {
                    ChangeTitle('修改用户名');
                };
                const match4 = pathToRegexp('/changeNumber').exec(pathname);
                if (match4) {
                    ChangeTitle('修改手机号码');
                };
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            //console.log('看看接口调用的次数');
            const data = yield call(getUserInformation, parse(payload));
            if (data.data.success) {
                window.custid = data.data.data.custid;
                yield put({
                    type: 'getUserr',
                    payload: data.data.data
                });
                yield put({//可以调用其他数据类型的effects
                    type: 'orderConfirmModel/initCart',
                    payload: custid
                });
                yield put({
                    type: 'index/getFreeCoupons',
                    payload: custid
                })
            }
        },
        *changeName({ payload }, { call, put }) {
            const data = yield call(changeName, parse(payload));
            if (data.data.success) {
                Toast.success('修改成功!!!', 1);
                yield put({
                    type: 'nameSuccess',
                    payload: payload
                });
                location.href='#/myCount'
            }
            else {
                Toast.fail('修改失败!!!', 1);
                yield put({
                    type: 'nameFaild'
                })
            }
        },
        *changePhoneNumber({ payload }, { call, put }) {
            const data = yield call(changePhoneNumber, parse(payload));
            if (data.data.success) {
                Toast.success('修改成功!!!', 1);
                yield put({
                    type: 'phoneSuccess',
                    payload: payload
                });
                location.href='#/myCount'
            }
            else {
                Toast.fail('修改失败!!!', 1);
                yield put({
                    type: 'phoneFaild'
                })
            }
        },
        //处理在头像加载出错事情的情况
        *changeImage({payload},{put}) {
            yield put({
                type: 'changeImager'
            })
        }
    },

    reducers: {
        getUserr(state, action) {
            let userInformation = action.payload;
            userInformation.custmobile = userInformation.custmobile || '**********';
            return { ...state, ...userInformation };
        },
        nameSuccess(state, action) {
            const { userName } = action.payload
            return { ...state, custname: userName };
        },
        nameFaild(state, action) {
            return { ...state, changeName: false };
        },
        phoneSuccess(state, action) {
            return { ...state, ...action.payload };
        },
        phoneFaild(state, action) {
            return { ...state, changePhone: false };
        },
        changeImager(state, action) {
            return { ...state, wechatavatar: avatar}
        }
    }
}
