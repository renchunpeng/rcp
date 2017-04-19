import pathToRegexp from 'path-to-regexp'
import { getCouponList, getCoupons, bindCoupons } from '../services/coupon'
import { Toast } from 'antd-mobile'
import { getActivityList, getAdvertisement, getBigType } from '../services/market'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'index',

    state: {
        visible: false,
        freeCoupons: [],
        activityList: [],
        getAdvertisement: [],
        bigTypeList: []
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/onlineMarket/market').exec(pathname);
                if (match) {
                    console.log('登录首页了！');
                    dispatch({
                        type: 'getAdvertisements'
                    });
                    dispatch({
                        type: 'getBigTypes'
                    });
                    dispatch({
                        type: 'activityLists'
                    });
                    ChangeTitle('绿富隆商城');
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            yield put({
                type: 'brandListr',
                payload: [1, 2, 3, 4, 5]
            });
        },
        *changeVisible({ payload }, { call, put }) {
            yield put({
                type: 'change'
            })
        },
        *getFreeCoupons({ payload }, { call, put }) {
            const data = yield call(getCoupons, payload);
            yield put({
                type: 'coupon',
                payload: data.data.data
            })
        },
        *confirmCoupons({ payload }, { call, put }) {
            const data = yield call(bindCoupons, payload);
            if (data.data.success) {
                Toast.success('优惠券绑定成功!!!', 1)
                yield put({
                    type: 'clean'
                })
            }
            else {
                Toast.fail('优惠券绑定失败!!!', 1)
            }
        },
        //首页获取商品活动列表
        *activityLists({ payload }, { call, put }) {
            const data = yield call(getActivityList, payload);
            if (data.data.success) {
                //处理一下获取到的数据
                let result = data.data.data;
                let resultList = [];
                let j = 0;
                for (var i in result) {
                    //console.log(i);
                    resultList[j] = result[i];
                    j++
                }
                //console.log('%%%%%%%%%%%',resultList)
                yield put({
                    type: 'activityListr',
                    payload: resultList
                })
            }
        },
        //首页获取广告图片列表
        *getAdvertisements({ payload }, { call, put }) {
            const data = yield call(getAdvertisement, payload);
            if (data.data.success) {
                yield put({
                    type: 'getAdvertisementsr',
                    payload: data.data.data
                })
            }
        },
        //获取首页大类列表
        *getBigTypes({ payload }, { call, put }) {
            const data = yield call(getBigType, payload);
            if (data.data.success) {
                yield put({
                    type: 'getBigTypesr',
                    payload: data.data.data
                })
            }
        }
    },

    reducers: {
        change(state, action) {
            const { visible } = state;
            return { ...state, visible: !visible }
        },
        coupon(state, action) {
            return { ...state, freeCoupons: action.payload }
        },
        clean(state) {
            return { ...state, freeCoupons: [] }
        },
        activityListr(state, action) {
            return { ...state, activityList: action.payload }
        },
        getAdvertisementsr(state, action) {
            return { ...state, getAdvertisement: action.payload }
        },
        getBigTypesr(state, action) {
            return { ...state, bigTypeList: action.payload }
        }
    }
}