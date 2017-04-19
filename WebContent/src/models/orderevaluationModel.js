/**
 * this model is created by jcxu
 * description
*/

import _ from '../utils'
import {submitEvalution} from '../services/orderEvalution'
import {Toast} from 'antd-mobile'

export default {
    namespace: 'orderEvaluationModel',

    state: {
        orderid: '',
        goodsList: [],
        prdpacking: 1,
        deliveryspeed: 1,
        deliveryservice: 1
    },

    subscriptions: {

    },

    effects: {
        *initGoodList({ payload }, { call, put }) {
            yield put({
                type: 'goodsList',
                payload: payload
            })
        },
        *textChange({ payload }, { call, put }) {
            yield put({
                type: 'textChanger',
                payload: payload
            })
        },
        *raterChange({ payload }, { call, put }) {
            yield put({
                type: 'raterChanger',
                payload: payload
            })
        },
        *serviceRater({ payload }, { call, put }) {
            yield put({
                type: 'serviceRaterr',
                payload: payload
            })
        },
        *submit({ payload }, { call, put }) {
            const data = yield call(submitEvalution, payload);
            if (data.data.success) {
                Toast.success('订单评价成功！！！', 1);
                history.go(-1);
            }
            else {
                Toast.fail('订单评价失败！！！', 1)
            }
        }
},

reducers: {
    //页面加载时获取商品列表
    goodsList(state, action) {
        const {goodsList,orderid} = action.payload;
        return { ...state, goodsList: goodsList, orderid: orderid }
    },
    textChanger(state, action) {
        return { ...state, goodsList: action.payload }
    },
    raterChanger(state, action) {
        return { ...state, goodsList: action.payload }
    },
    serviceRaterr(state, action) {
        const { type, value } = action.payload;
        switch (type) {
            case 'prdpacking':
                return { ...state, prdpacking: value }
                break;
            case 'deliveryspeed':
                return { ...state, deliveryspeed: value }
                break;
            case 'deliveryservice':
                return { ...state, deliveryservice: value }
                break;
            default:
            //

        }
    }
}
}