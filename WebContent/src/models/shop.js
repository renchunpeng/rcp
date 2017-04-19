/**
 * this models is created by ypli
 * description
*/
import _ from '../utils'
import { parse } from 'qs'
import { getShopBase, saveShopRegister, saveShopBase } from '../services/shop'
import pathToRegexp from 'path-to-regexp'
import { Toast } from 'antd-mobile'

export default {
    namespace: 'shop',

    state: {
        aplid: '',
        aplperson: '',
        prdtype: '',
        idencode: '',
        bankcount: '',
        isapprove: true,
        shopstatus: '0',
        shopid: '',
        apldate: ''
    },

    effects: {
        *getShopBase({ payload }, {call, put }) {
            const data = yield call(getShopBase, payload);
            let shop = data.data.data[0];
            yield put({
                type:'getShopBase',
                payload: shop
            });

        },

        *saveShopRegister({ payload }, { call, put }) {
            const data = yield call(saveShopRegister, payload);
            if (data.data.success) {
                alert('店铺申请已提交，等待平台审核');
                yield put({
                    type: 'saveShopRegister'
                });
                location.href  = '???#/myShop'
            }
            else {
                alert('数据提交失败');
            }
        },

        *saveShopBase({ payload }, { call, put }) {
            const data = yield call(saveShopBase, payload);
            if (data.data.success) {
                alert('数据提交成功');
                yield put({
                    type: 'saveShopBase'
                });
            }
            else {
                alert('数据提交失败');
            }
        }
    },

    reducers: {
        getShopBase(state, action) {
            return { ...state, ...action.payload }
        },
        saveShopRegister(state, action) {
            return { ...state, addtype: action.payload }
        },
        saveShopBase(state, action) {
            return { ...state, addtype: action.payload }
        }
    }
}