/**
 * this models is created by ypli
 * description 店铺设置
*/
import _ from '../utils'
import { parse } from 'qs'
import { getShopYesOrNo,changeShopImage, changeShopName, changeShopDesc, changeReturnAddress } from '../services/shop'
import pathToRegexp from 'path-to-regexp'
import { Toast } from 'antd-mobile'
import shopavatar from '../img/shopdefault.png'

export default {
    namespace: 'shopDetail',

    state: {
        shopid: '',
        shopname: '',
        shopdescription: '',
        shopavator: shopavatar,
        address: 'NULL'
    },

//    subscriptions: {
//    	//只会在页面第一次加载的时候执行
//        setup({ dispatch, history }) {
//            history.listen(({ pathname }) => {
//                const match = pathToRegexp('#/myShop').exec(pathname);
//                if (match) {
//                    dispatch({ type: 'defaultShop' });
//                }
//            })
//        }
//    },

    effects: {
        *defaultShop({ payload }, { call, put}) {
            const data = yield call(getShopYesOrNo, custid);
            console.log(data.data.data[0][0]);
            //如果用户已注册店铺
            if(data.data.data[0][0]) {
                location.href = '???#/myShop';
                let shopDetail = data.data.data[1][0];
                window.shopid = shopDetail.shopid;
                console.log(shopDetail);
                 yield put({
                    type: 'initShopDetail',
                    payload: shopDetail
                });
            }
            else { //用户尚未注册
                location.href = '???#/addShop';
            }
        },
        //店铺头像修改接口
        *changeShopImage({payload},{ call, put }) {
            const data = yield call(changeShopImage,payload);
            if(data.data.success) {
                Toast.success(data.data.msg, 1);
            }
            else {
                Toast.fail('头像修改失败！！！', 1);
            }
        },
        *changeShopName({ payload }, { call, put }) {
            const data = yield call(changeShopName, parse(payload));
            if (data.data.success) {
                Toast.success('修改成功!!!', 1);
                yield put({
                    type: 'shopNameSuccess',
                    payload: payload
                });
                history.go(-1)
            }
            else {
                Toast.fail('修改失败!!!', 1);
                yield put({
                    type: 'shopNameFaild'
                })
            }
        },
        *changeShopDesc({ payload }, { call, put }) {
            const data = yield call(changeShopDesc, parse(payload));
            if (data.data.success) {
                Toast.success('修改成功!!!', 1);
                yield put({
                    type: 'shopDescSuccess',
                    payload: payload
                });
                history.go(-1)
            }
            else {
                Toast.fail('修改失败!!!', 1);
                yield put({
                    type: 'shopDescFaild'
                })
            }
        },
        *changeReturnAddress({ payload }, { call, put }) {
            const data = yield call(changeReturnAddress, payload);
            if (data.data.success) {
                Toast.success('修改成功!!!', 1);
                yield put({
                    type: 'returnAddressSuccess',
                    payload: payload
                });
            }
            else {
                Toast.fail('修改失败!!!', 1);
                yield put({
                    type: 'returnAddressFaild'
                })
            }
        },
        *changeAddress({ payload }, { put }) {
            yield put({
                type: 'changeAddressr',
                payload: payload
            })
        },
        //处理店铺图表加载出错的情况
        *changeImage({ payload }, { put }) {
            yield put({
                type: 'changeImager'
            })
        }
    },

    reducers: {
        initShopDetail(state, action) {
            return { ...state, ...action.payload };
        },
        shopNameSuccess(state, action) {
            const { shopname } = action.payload
            return { ...state, shopname: shopname };
        },
        shopNameFaild(state, action) {
            return { ...state, changeShopName: false };
        },
        shopDescSuccess(state, action) {
            const { shopdesc } = action.payload
            return { ...state, shopdescription: shopdesc };
        },
        shopDescFaild(state, action) {
            return { ...state, changeShopDesc: false };
        },
        returnAddressSuccess(state, action) {
            const { addressid } = action.payload
            return { ...state, address: action.payload };
        },
        returnAddressFaild(state, action) {
            return { ...state, changeReturnAddress: false };
        },
        changeAddressr(state, action) {
            return { ...state, address: action.payload}
        },
        changeImager(state, action) {
            return { ...state, shopavator: shopavatar}
        }
    }

}