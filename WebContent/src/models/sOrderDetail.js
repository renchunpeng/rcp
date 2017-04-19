/**
 * this models is created by ypli
 * description 店铺订单详情页及操作
*/
import { parse } from 'qs'
import pathToRegexp from 'path-to-regexp'
import { Toast, Modal } from 'antd-mobile'
import React from 'react'
import _ from '../utils'
import { getOrderDetail, updateOrderSend, updateOrderCancel, updateOrderSendOk, getCommentLists } from '../services/sOrderDetail'
const alert = Modal.alert;

export default {
    namespace: 'sOrderDet',

    state: {
        address: {},
        goodsList: [],
        coupons: [],
        totalPrice: 0
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/shopOrderDetail/:orderId').exec(pathname);
                if (match) {
                    console.log('-------执行----');
                    let orderId = pathname.split('/')[2];
                    dispatch({
                        type: 'init',
                        payload: orderId
                    })
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getOrderDetail, payload);
            let { ordSubdetail } = data.data.data;
            let _ordSubdetail = [];
            let _totalPrice = 0;
            //对商品和套餐进行兼容
            for (let i = 0; i < ordSubdetail.length; i++) {
                let _temp = ordSubdetail[i];
                if (_temp.prdkind == '02') {
                    _ordSubdetail[i] = {};
                    _ordSubdetail[i]['prdid'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['prdnum'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['prdcount'] = _temp.prdnum;
                    _ordSubdetail[i]['prdbrief'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['prdprice'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['prdunit'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['prdtax'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['paytype'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['remark'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['heat'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['shopid'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['prdintroduction'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['prdImage'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['shopname'] = _temp.prdPackage.packageid;
                    _ordSubdetail[i]['sumprice'] = _temp.sumprice;
                    _totalPrice = _totalPrice + _temp.sumprice;
                }
                else {
                    _ordSubdetail[i] = {};
                    _ordSubdetail[i]['prdid'] = _temp.prdBase.prdid;
                    _ordSubdetail[i]['prdname'] = _temp.prdBase.prdname;
                    _ordSubdetail[i]['prdnum'] = _temp.prdnum;
                    _ordSubdetail[i]['prdbrief'] = _temp.prdBase.prdbrief;
                    _ordSubdetail[i]['prdprice'] = _temp.prdBase.prdprice;
                    _ordSubdetail[i]['prdunit'] = _temp.prdBase.prdunit;
                    _ordSubdetail[i]['prdtax'] = _temp.prdBase.prdtax;
                    _ordSubdetail[i]['paytype'] = _temp.prdBase.paytype;
                    _ordSubdetail[i]['remark'] = _temp.prdBase.remark;
                    _ordSubdetail[i]['heat'] = _temp.prdBase.heat;
                    _ordSubdetail[i]['shopid'] = _temp.prdBase.shopid;
                    _ordSubdetail[i]['prdintroduction'] = _temp.prdBase.prdintroduction;
                    _ordSubdetail[i]['prdImage'] = _temp.prdBase.prdImage;
                    _ordSubdetail[i]['shopname'] = _temp.prdBase.shopname;
                    _ordSubdetail[i]['sumprice'] = _temp.sumprice;
                    _totalPrice = _totalPrice + _temp.sumprice;
                }
            }
            yield put({
                type: 'getOrder',
                payload: {
                    coupons: data.data.data.actUserCoupons,
                    goodsList: _ordSubdetail,
                    totalPrice: _totalPrice
                }
            })
        },
        //将订单列表页面的数据获取到订单详情页,其中主要是地址信息
        *getAddress({ payload }, { call, put }) {
            yield put({
                type: 'addressr',
                payload: payload
            })
        },
        *action({ payload }, { call, put }) {
            //console.log(payload.actionType);参见OrderStatus.js文件
            //订单所有的数据都会传过来
            let { actionType, sOrderDet, goodsList } = payload;
            let { orderid, paytype } = sOrderDet;
            switch (actionType) {
                case '我要发货':
                    console.log('我要发货');
                    const predata = yield call(updateOrderSend, sOrderDet);
                    if (predata.data.success) {
                        Toast.success('已发货！', 1);
                        location.href = '???#/shopOrderList';
                    }
                    else {
                        Toast.fail('发货未成功!!!', 1)
                    }
                    break;
                case '同意取消':
                    //订单取消方法
                    console.log('同意取消');
                    const canceldata = yield call(updateOrderCancel, sOrderDet);
                    if (cancel.data.success) {
                        Toast.success('已取消，可申请退款！', 1);
                        location.href = '???#/shopOrderList';
                    }
                    else {
                        Toast.fail('同意取消未成功!!!', 1)
                    }
                    break;
                case '配送确认':
                    console.log('配送确认');
                    const senddata = yield call(updateOrderSendOk, sOrderDet);
                    if (senddata.data.success) {
                        Toast.success('已配送完成！', 1);
                        location.href = '???#/shopOrderList';
                    }
                    break;
                case '同意退款':                   
                    break;
                default:
                    console.log('什么都没有执行到');
            }
            yield put({
                type: 'actionr'
            })
        }
    },

    reducers: {
        getOrder(state, action) {
            const { coupons, goodsList, totalPrice } = action.payload;
            return { ...state, goodsList: goodsList, coupons: coupons, totalPrice: totalPrice }
        },
        addressr(state, action) {
            return { ...state, address: action.payload }
        },
        changePayStatus(state, action) {
            let { address } = state;
            address.orderstatus = '06';
            return { ...state, address: address }
        },
        getCommentListr(state, action) {
            return { ...state, commentList: action.payload }
        },
        actionr(state, action) {
            return { ...state, test1: 1 }
        }
    }
}