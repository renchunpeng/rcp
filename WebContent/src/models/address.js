/**
 * this models is created by jcxu
 * description
*/
import _ from '../utils'
import { getAddressList, getAddressDetail, submit, deleteAddress } from '../services/addressList'
import { parse } from 'qs'
import pathToRegexp from 'path-to-regexp'
import { Toast } from 'antd-mobile'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'address',

    state: {
        list: [1, 2, 3],
        isdefault: true,
        showDelete: false,
        showAlert: false,//地址添加或修改页面检测到填写为空时的弹窗
        isChoice: false,//地址被点击后是否返回上一页
        listType: '',//记录来到地址列表页之前的页面
        addcontact: '',
        adddetail: '',
        addmobile: '',
        addprov: '',
        addcity: '',
        addcounty: '',
        addtype: '01',
        addtypename: '家庭地址',
        provid: '',
        cityid: '',
        countyid: ''
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/addressList/:type/:custId').exec(pathname);
                const match1 = pathToRegexp('/addAddress/:type/:addressId').exec(pathname);
                if (match) {
                    let type = pathname.split('/')[2];
                    let custId = pathname.split('/')[3];
                    //查看地址列表
                    if (type === 'show') {
                        dispatch({
                            type: 'init',
                            payload: custid
                        });
                        ChangeTitle('地址管理');
                    }
                    //订单确认地址选择
                    else if (type === 'choice') {
                        dispatch({
                            type: 'choice',
                            payload: custid
                        });
                        ChangeTitle('地址选择');
                    }
                    //选退送地址
                    else if (type === 'back') {
                        dispatch({
                            type: 'back',
                            payload: custid
                        });
                        ChangeTitle('选择退送地址');
                    }
                }
                else if (match1) {
                    let type = pathname.split('/')[2];
                    let addressId = pathname.split('/')[3];
                    if (type === 'edit') {
                        //地址可编辑
                        dispatch({
                            type: 'addressDetail',
                            payload: addressId
                        })
                    }
                    else if (type === 'add') {
                        //新增地址
                        dispatch({
                            type: 'clean'
                        })
                    }
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getAddressList, payload);
            yield put({
                type: 'getAddList',
                payload: {
                    list: data.data.data
                }
            });
            yield put({
                type: 'getAddListr'
            });
        },
        *back({ payload }, { call, put }) {
            //更新地址列表
            const data = yield call(getAddressList, payload);
            yield put({
                type: 'getAddList',
                payload: {
                    list: data.data.data
                }
            });
            yield put({
                type: 'backer'
            });
        },
        *choice({ payload }, { call, put }) {
            const data = yield call(getAddressList, payload);
            yield put({
                type: 'getAddList',
                payload: {
                    list: data.data.data
                }
            });
            yield put({
                type: 'choicer'
            });
        },
        *addressDetail({ payload }, { call, put }) {
            const data = yield call(getAddressDetail, payload);
            let address = data.data.data[0];
            yield put({
                type: 'getAddDetail',
                payload: address
            })
        },
        *clean({ payload }, { call, put }) {
            let init = {
                addcontact: '',
                adddetail: '',
                addmobile: '',
                addprov: '',
                addcity: '',
                addcounty: '',
                addtype: '01',
                addtypename: '家庭地址',
                provid: '110000',
                cityid: '110100',
                countyid: '110101'
            };
            yield put({
                type: 'cleanr',
                payload: init
            })
        },
        *radioValue({ payload }, { call, put }) {
            yield put({
                type: 'radioValuer',
                payload: payload
            })
        },
        *submit({ payload }, { call, put }) {
            const data = yield call(submit, parse(payload));
            if (data.data.success) {
                Toast.success('地址新增成功!!!', 1)
                yield put({
                    type: 'submitr'
                })
                history.go(-1);
            }
            else {
                alert('数据提交失败');
            }

        },
        *deleteAddress({ payload }, { call, put }) {
            const data = yield call(deleteAddress, payload);
            if (data.data.success) {
                Toast.success('删除成功!!!', 1);
                location.href = '#/addressList/12341'
            }
            else {
                Toast.fail('删除失败!!!', 1)
            }
        },
        *changeAlert({ payload }, { call, put }) {
            yield put({
                type: 'changeAlertr'
            })
        }
    },

    reducers: {
        getAddList(state, action) {
            const list = action.payload.list
            return { ...state, list: list, isChoice: false };
        },
        getAddDetail(state, action) {
            return { ...state, ...action.payload, showDelete: false }
        },
        cleanr(state, action) {
            return { ...state, ...action.payload }
        },
        radioValuer(state, action) {
            return { ...state, addtype: action.payload }
        },
        submitr(state, action) {
            return { ...state, addtype: action.payload }
        },
        //来自订单列表展示页
        getAddListr(state, action) {
            return { ...state, isChoice: false, listType: 'show' }
        },
        //来自订单确认页
        choicer(state, action) {
            return { ...state, isChoice: true, listType: 'confirm' }
        },
        choicers(state, action) {
            return { ...state, isChoice: true, listType: 'shopset' }
        },
        //用于选择退货的配送地址
        backer(state, action) {
            return { ...state, isChoice: true, listType: 'return' }
        },
        deleteAddressr(state, action) {
            return { ...state, ...action.payload }
        },
        changeAlertr(state, action) {
            const {showAlert} = state;
            return { ...state, showAlert: !showAlert}
        }
    }
}
