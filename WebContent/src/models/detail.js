import { parse } from 'qs'
import pathToRegexp from 'path-to-regexp'
import { Toast, Modal } from 'antd-mobile'
import React from 'react'
import _ from '../utils'
import { getOrderDetail, updateOrderStatus, getCommentLists, cancelOrder } from '../services/orderDetail'
import ChangeTitle from '../utils/changeTitle'
const alert = Modal.alert;

export default {
    namespace: 'orderDet',

    state: {
        goodsList: [],
        coupons: [],
        orderDetail: {
            orderid: '1324124',
            orderstatus: '01',
            remark: '备注',
            ctmAddress: {
                addcontact: '收货人',
                addmobile: '12345678',
                addprov: '北京',
                addcity: '北京市',
                addcounty: '朝阳区',
                adddetail: '测试详细地址'
            },
            createdate: 1490600483000,
            paytype: '01',
            totalprice: 0.00
        }
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/orderDetail/:orderId').exec(pathname);
                if (match) {
                    let orderId = pathname.split('/')[2];
                    dispatch({
                        type: 'init',
                        payload: orderId
                    });
                    ChangeTitle('订单详情');
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getOrderDetail, payload);
            let { ordSubdetail,main } = data.data.data;
            let { totalprice,ctmAddress } = main;
            let _ordSubdetail = [];
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
                }
            }
            yield put({
                type: 'getOrder',
                payload: {
                    coupons: data.data.data.actUserCoupons,
                    goodsList: _ordSubdetail,
                    totalPrice: totalprice,
                    orderDetail: main
                }
            })
        },
        *action({ payload }, { call, put }) {
            //console.log(payload.actionType);参见OrderStatus.js文件
            //订单所有的数据都会传过来
            let { actionType, orderDet, goodsList } = payload;
            let { orderid, paytype } = orderDet;
            switch (actionType) {
                case '确认支付':
                    console.log('确认支付');
                    switch (paytype) {
                        case '01':
                            location.href = '???#/payMent/' + custid + '/' + orderid
                            break;
                        case '02':
                            location.href = `/lflweb/zfb/zfbpay/${orderid}`
                            break;
                        default:
                        //
                    }
                    break;
                case '取消订单':
                    //订单取消方法
                    function cancel(orderDet) {
                        const result = cancelOrder(orderDet);
                        //console.log('==========',result);
                        result.then(
                            (data) => {
                                if (data.data.success) {
                                    Toast.success('订单取消成功!!!', 1)
                                    location.href = '???#/orderlist'
                                }
                                else {
                                    Toast.fail('订单取消失败!!!', 1)
                                }
                            }
                        ).catch((err) => { '出错了！！！', err })
                    }
                    alert('请选择退单理由！！！', '', [
                        { text: '退单理由1', onPress: () => { orderDet['returnreason']='01';cancel(orderDet) } },
                        { text: '退单理由2', onPress: () => { orderDet['returnreason']='01';cancel(orderDet) } },
                        { text: '退单理由3', onPress: () => { orderDet['returnreason']='01';cancel(orderDet) } },
                    ]);
                    break;
                case '确认收货':
                    console.log('确认收货');
                    const data = yield call(updateOrderStatus, orderDet);
                    if (data.data.success) {
                        //订单确认成功后修改订单的支付状态
                        yield put({
                            type: 'changePayStatus'
                        })
                        Toast.success('确认收货成功!!!', 1)
                    }
                    //TODO:确认收货后需要更新页面
                    break;
                case '订单评价':
                    let _goodsList = [];
                    for (let i = 0; i < goodsList.length; i++) {
                        _goodsList[i] = goodsList[i];
                        _goodsList[i]['prdcomment'] = '好评！';
                        _goodsList[i]['prdsatisfaction'] = 5;
                    }
                    yield put({
                        type: 'orderEvaluationModel/initGoodList',
                        payload: {
                            goodsList: _goodsList,
                            orderid: orderid
                        }
                    })
                    location.href = '???#/orderEvaluation'
                    break;
                default:
                    console.log('什么都没有执行到');
            }
            yield put({
                type: 'actionr'
            })
        },
        *cancelOrder({ payload }, { call, put }) {
            console.log('被触发');
        }
    },

    reducers: {
        getOrder(state, action) {
            const { coupons, goodsList, totalPrice, orderDetail } = action.payload;
            return { ...state, goodsList: goodsList, coupons: coupons, totalPrice: totalPrice, orderDetail: orderDetail }
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