/**
 * this models is creacted by jxu
 * description
*/
import _ from '../utils'
import { parse } from 'qs'
import { submit, saveShoppingCart } from '../services/orderConfirm'
import { getAddressList } from '../services/addressList'
import { getPayParams, wxPayAuth, getAliPay } from '../services/payMent'
import { getShoppingCart } from '../services/shoppingCart'
import { getCouponCanUse } from '../services/coupon'
import pathToRegexp from 'path-to-regexp'
import moment from 'moment'
import 'moment/locale/zh-cn'
import { Toast } from 'antd-mobile'
import Number from '../utils/Number'
import ChangeTitle from '../utils/changeTitle'

const zhNow = moment().locale('zh-cn').utcOffset(8);

export default {
    namespace: 'orderConfirmModel',

    state: {
        goodsList: [],//被选中的商品数组
        address: 'NULL',//订单确认页显示的地址
        defaultAddress: {},//默认地址
        senddate: zhNow,//订单确认页面的配送时间
        invoiceInformation: {},//发票信息
        remark: '备注',
        radioValue: '01',//01表示的是微信支付
        totalNumber: 0,
        totalPrice: 0.00,
        coupon: '暂无优惠券可用',
        couponList: ''//当前订单所有能有的优惠券列表
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/orderConfirm/:custId').exec(pathname);
                if (match) {
                    dispatch({ type: 'defaultAddress' });
                    ChangeTitle('订单确认');
                }
            })
        }
    },

    effects: {
        *changeRadio({ payload }, { call, put }) {
            yield put({
                type: 'changeRadior',
                payload: payload
            })
        },
        *changeGood({ payload }, { call, put }) {
            //attention 如果有业务逻辑相关的操作请在effects中执行
            //购物车中现有的数据
            let { number, goodDetail, goodsList } = payload;
            //用于存放新的数据
            let _goodsList = [];
            let _totalNumber = 0;
            let _totalPrice = 0.00;
            let isHaved = false;
            //添加购物车的数据处理逻辑
            if (goodsList.length == 0) {
                //如果购物车中为空，则将该商品添加到购物车中
                goodDetail['prdcount'] = number;
                _goodsList.push(goodDetail);
                _totalNumber = number;
                _totalPrice = Number(goodDetail.prdprice).mul(number);
            }
            else {
                //通过遍历判断购物车中是否存在将要添加的商品，并计算出当前的商品总数和价格总数
                for (let i = 0; i < goodsList.length; i++) {
                    if (goodsList[i].prdid == goodDetail.prdid) {
                        isHaved = true;
                    }
                    _totalNumber = Number(goodsList[i].prdcount).add(_totalNumber);
                    _totalPrice = Number(_totalPrice).add(Number(goodsList[i].prdcount).mul(goodsList[i].prdprice));
                }
                if (isHaved) {
                    //如果购物车中存在该商品，则需要将该商品的数据进行更新
                    if (number == 0) {
                        for (let i = 0; i < goodsList.length; i++) {
                            if (goodsList[i].prdid == goodDetail.prdid) {
                                _totalNumber = Number(_totalNumber).sub(goodsList[i].prdcount);
                                _totalPrice = Number(_totalPrice).sub(Number(goodsList[i].prdcount).mul(goodsList[i].prdprice));
                            }
                            else {
                                _goodsList.push(goodsList[i])
                            }
                        }
                    }
                    else {
                        for (let i = 0; i < goodsList.length; i++) {
                            if (goodsList[i].prdid == goodDetail.prdid) {
                                _totalNumber = Number(Number(_totalNumber).sub(goodsList[i].prdcount)).add(number);
                                _totalPrice = Number(Number(_totalPrice).sub(Number(goodsList[i].prdcount).mul(goodsList[i].prdprice))).add(Number(number).mul(goodsList[i].prdprice));
                                goodsList[i].prdcount = number;
                                _goodsList = goodsList;
                            }
                        }
                    }
                }
                else {
                    //如果该购物车中不存在该商品则将该商品加入到购物车中
                    goodDetail['prdcount'] = number;
                    goodsList.push(goodDetail);
                    _goodsList = goodsList;
                    _totalNumber = Number(_totalNumber).add(goodDetail.prdcount);
                    _totalPrice = Number(_totalPrice).add(Number(goodDetail.prdprice).mul(number));
                }
            }
            //向后台数据中同步购物车数据,目前设置的是只有这里的数据同步成功了，后台才能继续执行数据
            //const data = yield call(saveShoppingCart, payload);
            yield put({
                type: 'changeGoodr',
                payload: {
                    goodsList: _goodsList,
                    totalNumber: _totalNumber,
                    totalPrice: _totalPrice
                }
            })
        },
        //页面开始加载的时候就初始化购物车数据
        *initCart({ payload }, { call, put }) {
            const data = yield call(getShoppingCart, payload);
            if (data.data.success) {
                let goodsList = data.data.data;
                let _totalNumber = 0;
                let _totalPrice = 0.00;
                for (let i = 0; i < goodsList.length; i++) {
                    _totalNumber = Number(_totalNumber).add(goodsList[i].prdcount);
                    _totalPrice = Number(_totalPrice).add(Number(goodsList[i].prdcount).mul(goodsList[i]
                        .prdprice));
                }
                yield put({
                    type: 'initCartr',
                    payload: {
                        goodsList: goodsList,
                        totalNumber: _totalNumber,
                        totalPrice: _totalPrice
                    }
                })
            }
        },
        //保存购物车
        *saveCart({ payload }, { call }) {
            location.href = `???#/orderConfirm/${custid}`;
            const data = yield call(saveShoppingCart, payload);
        },
        *changeAddress({ payload }, { put }) {
            yield put({
                type: 'changeAddressr',
                payload: payload
            })
        },
        //修改配送时间
        *changeDeliverTime({ payload }, { put }) {
            yield put({
                type: 'changeDeliverTimer',
                payload: payload
            })
        },
        *defaultAddress({ payload }, { call, put }) {
            const data = yield call(getAddressList, custid);
            //如果用户存在地址
            if (data.data.data[0]) {
                yield put({
                    type: 'defaultAddressr',
                    payload: data.data.data[0]
                })
            }
            else {
                location.href = '#/addAddress/add/123456'
            }
        },
        *remarkChange({ payload }, { call, put }) {//将remark保存到redux中
            yield put({
                type: 'remarkr',
                payload: payload
            })
        },
        //订单确认页面数据提交
        *submit({ payload }, { call, put }) {
            const { radioValue, goodsList, totalPrice } = payload;
            Toast.loading('加载中...', 10, () => { console.log('done'), true })
            const data = yield call(submit, payload);
            if (data.data.success) {
                //const data = yield call(wxPayAuth, payload);
                switch (radioValue) {
                    case '01':
                        location.href = '???#/payMent/' + custid + '/' + data.data.msg
                        break;
                    case '02':
                        location.href = `/lflweb/zfb/zfbpay/${data.data.msg}`
                        break;
                    default:
                    //
                }
                //订单提交成功之后对state进行操作
                yield put({
                    type: 'submitr'
                })
                //将商品数据传递给订单支付页面
                yield put({
                    type: 'pay/getShoppingCart',
                    payload: {
                        goodsList: goodsList,
                        totalPrice: totalPrice
                    }
                })
                //数据提交成功之后清空state中保留的数据
                yield put({
                    type: 'clean'
                })
            }
            else {
                alert('数据提交失败！');
            }
        },
        //获取该订单所能使用的优惠券列表,通过商品提交的操作来触发
        *getCouponCanUse({ payload }, { call, put }) {
            const data = yield call(getCouponCanUse, payload)
            if (data.data.success) {
                yield put({
                    type: 'getCouponCanUser',
                    payload: data.data.data
                })
            }
            else {
                Toast.fail('优惠券信息获取失败', 1)
            }
        },
        //选择优惠券操作
        *choiceCoupon({ payload }, { call, put }) {
            yield put({
                type: 'choiceCouponr',
                payload: payload
            })
        },
        *cleaner({ payload }, { call, put }) {//用于测试的数据初始化
            yield put({
                type: 'clean'
            })
        }
    },

    reducers: {
        //支付方式选择
        changeRadior(state, action) {
            return { ...state, radioValue: action.payload }
        },
        //购物车商品操作
        changeGoodr(state, action) {
            const { goodsList, totalNumber, totalPrice } = action.payload;
            return { ...state, goodsList: goodsList, totalNumber: totalNumber, totalPrice: totalPrice }
        },
        //地址切换选择
        changeAddressr(state, action) {
            return { ...state, address: action.payload }
        },
        //获取默认地址
        defaultAddressr(state, action) {
            const { address } = state;
            if (address == 'NULL') {
                return { ...state, defaultAddress: action.payload, address: action.payload }
            }
            else {
                return { ...state, defaultAddress: action.payload }
            }
        },
        initCartr(state, action) {
            const { goodsList, totalNumber, totalPrice } = action.payload;
            return { ...state, goodsList: goodsList, totalNumber: totalNumber, totalPrice: totalPrice }
        },
        remarkr(state, action) {
            return { ...state, remark: action.payload }
        },
        submitr(state, action) {
            return { ...state, test: 9 }
        },
        clean(state, action) {//订单提交成功之后，将数据进行初始化
            const init = {
                goodsList: [],
                remark: '备注',
                radioValue: '01',
                totalNumber: 0,
                totalPrice: 0.00
            }
            return { ...state, ...init }
        },
        //订单配送时间
        changeDeliverTimer(state, action) {
            return { ...state, senddate: action.payload }
        },
        //获取订单可使用优惠券
        getCouponCanUser(state, action) {
            const { mianjianActUserCoupons, prdidActUserCoupons } = action.payload;
            if (mianjianActUserCoupons.length == 0 && prdidActUserCoupons.length == 0) {
                console.log('该订单无可用优惠券！');
                return { ...state, couponList: '' }
            }
            else {
                const { mianjianActUserCoupons, prdidActUserCoupons } = action.payload;
                let _payload = mianjianActUserCoupons.concat(prdidActUserCoupons);
                for (let i = 0; i < _payload.length; i++) {
                    _payload[i]['checked'] = false;
                }
                return { ...state, couponList: _payload, coupon: '请选择优惠券' }
            }
        },
        //操作可使用的优惠券
        choiceCouponr(state, action) {
            const { bdcouponid, ismixuse } = action.payload;
            let { couponList } = state;
            if (ismixuse) {
                //选中的优惠券可与其他优惠券合用
                for (let i = 0; i < couponList.length; i++) {
                    if (couponList[i].bdcouponid == bdcouponid&&couponList[i].checked == true) {
                        couponList[i].checked = false;
                    }
                    else if(couponList[i].bdcouponid == bdcouponid&&couponList[i].checked == false){
                        couponList[i].checked = true;
                    }
                    else {
                        if(couponList[i].ismixuse == false && couponList[i].checked == true) {
                            couponList[i].checked = false;
                        }
                        else {
                            //对于当前选中项为可合用，但是其他没有选中不可合用的情况，不做处理
                        }
                    }
                }
            }
            else {
                //如果当前选中的优惠券不能合用
                for (let i = 0; i < couponList.length; i++) {
                    if (couponList[i].bdcouponid == bdcouponid && couponList[i].checked == false) {
                        //先将选中的标记
                        couponList[i].checked = true;
                    }
                    else if(couponList[i].bdcouponid == bdcouponid && couponList[i].checked == true) {
                        couponList[i].checked = false;
                    }
                    else {
                        couponList[i].checked = false;
                    }
                }
                Toast.info('该优惠券不可与其他优惠券同时使用！！！',1)
            }
            return { ...state, couponList: couponList }
        }
    }
}
