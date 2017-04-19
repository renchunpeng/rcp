//商品详情数据模型
import _ from '../utils'
import { getGoodDetail,getCommentLists } from '../services/goodDetail'
import { parse } from 'qs'
import pathToRegexp from 'path-to-regexp'
import ChangeTitle from '../utils/changeTitle'

export default {
    //需要和文件名保持一致
    namespace: 'gooddm',

    state: {
        createdate: '',
        createoperator: '',
        custgrade: '',
        custsource: '',
        custtype: '',
        heat: 0,
        keyword: '',
        meaid: '',
        modifydate: '',
        offsaledate: '',
        onsaledate: '',
        paytype: '',
        prdbrief: '',
        prdid: '',
        prdintroduction: '',
        prdkind: '',
        prdname: '',
        prdprice: 0,
        prdstatus: '',
        prdImage: {
            imageUrl: ''
        },
        prdtax: '',
        prdtype: '',
        prdunit: '00',
        prdvedio: '',
        remark: '',
        salezone: '',
        shopid: '',
        skuid: '',
        sel: '',
        actionType: 1,//数据提交类型
        commentList: []
    },

    subscriptions: {
        //一开始就监听路由跳转,模拟componentDidMount
        setup({ dispatch, history }) {
            //history监听到的是一个对象，对象中有pathname这个属性
            history.listen(({ pathname }) => {
                const match = pathToRegexp('/goodDetail/:prdId/:prdKind').exec(pathname);
                //获取到商品id
                let prdId = pathname.split('/')[2];
                let prdKind = pathname.split('/')[3];
                if (match) {
                    dispatch({
                        type: 'init',
                        payload: {
                            prdid: prdId,
                            prdkind: prdKind
                        }
                    });
                    dispatch({
                        type: 'getCommentList',
                        payload: prdId
                    });
                    ChangeTitle('商品详情');
                }
            })
        }
    },

    effects: {
        //订阅事件的时候可以传入参数
        *init({ payload }, { call, put }) {
            const data = yield call(getGoodDetail, payload);
            //console.dir(data);
            yield put({
                type: 'initr',
                payload: data.data.data[0]
            })
        },
        *getCommentList({ payload }, { call, put }) {
            const data = yield call(getCommentLists, payload);
            if (data.data.success) {
                yield put({
                    type: 'getCommentListr',
                    payload: data.data.data
                })
            }
            else {
                Toast.fail('商品评价获取失败！！！', 1)
            }
        },
        *changeType({ payload }, { call, put }) {
            yield put({
                type: 'changeTyper',
                payload: payload
            })
        },
        *close({ payload }, { call, put }) {
            yield put({
                type: 'closer',
                payload: payload
            })
        }
    },

    reducers: {
        initr(state, action) {
            return { ...state, ...action.payload };
        },
        changeTyper() {
            return { ...state, actionType: action.payload }
        },
        getCommentListr(state, action) {
            return { ...state, commentList: action.payload}
        },
        closer(state, action) {
            return { ...state, sel: action.payload }
        }
    }
}