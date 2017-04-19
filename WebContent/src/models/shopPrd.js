import _ from '../utils'
import { parse } from 'qs'
import { saveShopPrd, saveImage, updateShopPrd } from '../services/shop'
import pathToRegexp from 'path-to-regexp'
import { Toast } from 'antd-mobile'
import addShopPrd from '../img/addShopPrd/takepic.png'
import { prdUnit, prdType } from '../page/Config'

export default {
    namespace: 'shopPrd',

    state: {
        prdid: '',
        prdname: '',
        prdbrief: '',
        prdtype: ['01', '时令蔬菜'],
        prdprice: 0.00,
        prdspec: '',
        prdunit: ['00', '斤'],
        prdtax: '0.2',
        custsource: '00',
        custtype: '00',
        paytype: '00',
        salezone: '00',
        custgrade: '00',
        prdstatus: '',
        onsaledate: '',
        offsaledate: 'NULL',
        createdate: 'NULL',
        createoperator: '',
        modifydate: 'NULL',
        remark: '',
        keyword: '',
        meaid: '',
        shopid: '',
        prdintroduction: '',
        prdvedio: '',
        skuid: '',
        prdkind: '01',
        giveintegral: '',
        imageid: '',
        skudescription: '',
        imageList: [],
        prdImageUrl: addShopPrd,
        pageType: ''
    },

    subscriptions: {
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/addShopPrd/:type').exec(pathname);
                if (match) {
                    let type = pathname.split('/')[2];
                    console.log('看看类型是什么', type);
                    dispatch({
                        type: 'changeType',
                        payload: type
                    })
                }
            })
        }
    },

    effects: {
        /*        *saveShopPrd({ payload }, { call, put }) {
                    const { imageList } = payload;
                    //先上传图片
                    const data = yield call(saveImage, imageList)
                    if (data.data.success) {
                        //console.log('看看传过来的数据是什么', payload);
                        const data = yield call(saveShopPrd, payload);
                        if (data.data.success) {
                            Toast.success('商品保存成功！！！', 1);
                            yield put({ type: 'saveShopPrd' });
                            history.go(-1);
                        } else {
                            Toast.fail('商品保存失败！！！', 1);
                        }
                    }
                    else {
                        Toast.fail('图片上传失败！！！', 1);
                    }
                },*/
        *savePrd({ payload }, { call, put }) {
            const { imageList } = payload;
            const data = yield call(saveImage, imageList);
            if (data.data.success) {
                let _payload = payload;
                _payload.imageurl = data.data.msg;
                const _data = yield call(saveShopPrd, _payload);
                if (_data.data.success) {
                    Toast.success('商品新增成功！！！', 1);
                    history.go(-1);
                }
                else {
                    Toast.fail('商品新增失败！！！', 1)
                }
            }
            else {
                Toast.fail('图片上传失败！！！', 1)
            }
        },
        *updatePrd({ payload }, { call, put }) {
            //判断图片有没有重新上传
            const { imageList } = payload;
            if (imageList.length == 0) {
                //如果用户没有更换图片,直接提交数据就可以了
                const data = yield call(updateShopPrd, payload);
                if (data.data.success) {
                    Toast.success('商品信息修改成功！！！', 1);
                }
                else {
                    Toast.fail('商品信息修改失败！！！', 1);
                }
            }
            else {
                const data = yield call(saveImage, imageList);
                if (data.data.success) {
                    let _payload = payload;
                        _payload.imageid = data.data.msg;
                    const data1 = yield call(updateShopPrd, _payload);
                    if (data1.data.success) {
                        Toast.success('商品信息修改成功！！！', 1);
                    }
                    else {
                        Toast.fail('商品信息修改失败！！！', 1);
                    }
                }
            }
        },
        *addImages({ payload }, { call, put }) {
            //console.log('传过来的图片格式',payload);
            yield put({
                type: 'addImagesr',
                payload: payload
            })
        },
        *changeImage({ payload }, { call, put }) {
            yield put({
                type: 'changeImager',
                payload: payload
            })
        },
        *getPrdDetail({ payload }, { call, put }) {
            console.log('看看数据是什么', payload);
            const { prdtype, prdunit } = payload;
            let _payload = payload;
            for (let item of prdType) {
                if (item.value == prdtype) {
                    _payload.prdtype = [item.value, item.label];
                }
            }
            for (let item of prdUnit) {
                if (item.value == prdunit) {
                    _payload.prdunit = [item.value, item.label];
                }
            }
            yield put({
                type: 'getPrdDetailr',
                payload: _payload
            })
        },
        *changeType({ payload }, { call, put }) {
            yield put({
                type: 'changeTyper',
                payload: payload
            })
        }
    },

    reducers: {
        saveShopPrd(state, action) {
            return {
                ...state,
                addtype: action.payload
            }
        },
        addImagesr(state, action) {
            //console.log('测试：',action.payload);
            return { ...state, imageList: action.payload }
        },
        changeImager(state, action) {
            return { ...state, prdImageUrl: action.payload }
        },
        getPrdDetailr(state, action) {
            console.log('show me the code', action.payload);
            return { ...state, ...action.payload }
        },
        changeTyper(state, action) {
            console.log('+++_+_+_+_+_+_+_+', action.payload);
            return { ...state, pageType: action.payload }
        }
    }
}