import _ from '../utils'
import { getList } from '../services/getList'
import { getMenuList } from '../services/menu'
import { getShoppingCart } from '../services/shoppingCart'
import { parse } from 'qs'
import { Toast } from 'antd-mobile'
import ChangeTitle from '../utils/changeTitle'

export default {
    namespace: 'cla',

    state: {
        classTitle: '时令蔬菜',
        open: false,
        position: 'left',
        list: [],
        menuList: [//如果不进行初始化则会报错
            {
                value: '1',
                label: '分类暂无',
                isLeaf: true,
            }
        ],
        goodType: '01',//获取的参数类别
        shoppingCartList: [],
        listKey: 0,
        baseUrl: 'prd/shprdbase'//决定调用的是商品分类接口还是商品预售分类接口
    },

    subscriptions: {
        //当路由跳转到onlineMarket/classify时，获取商品列表的数据
        setup({ dispatch, history }) {
            history.listen(({ pathname }) => {
                if (pathname === '/classify') {
                    dispatch({
                        type: 'init'
                    });
                    ChangeTitle('商品分类');
                }
                else if (pathname === '/onlineMarket/cart') {
                    dispatch({
                        type: 'initShoppingCart'
                    });
                    ChangeTitle('购物车');
                }
            })
        }
    },

    effects: {
        *init({ payload }, { call, put }) {
            const data = yield call(getMenuList, parse(payload));
            yield put({
                type: 'initr',
                payload: data.data.data
            })
        },
        //切换左侧商品菜单的显隐性
        *changeDraw({ payload }, { put }) {
            yield put({
                type: 'changeDrawr',
                payload: {}
            })
        },
        //初始化购物车数据
        *initShoppingCart({ payload }, { call, put }) {
            const data = yield call(getShoppingCart, payload);
            yield put({
                type: 'initShoppingCartr',
                payload: data.data.data
            })
        },
        //切换左侧的商品类型菜单
        *changeListType({ payload }, { put }) {
            yield put({
                type: 'changeType',
                payload: payload
            })
        },
        //切换预售商品列表
        *changePrePrd({ payload }, { put }) {
            //console.log(payload);
            const { columnid, columnname } = payload;
            switch (columnname) {
                case '商品分类':
                    location.href = '???#/classify'
                    break;
                case '限时促销':
                    Toast.info('敬请期待!!!', 1);
                    break;
                case '品牌精选':
                    Toast.info('敬请期待!!!', 1);
                    break;
                case '新品上架':
                    yield put({
                        type: 'changePrePrdr',
                        payload: {
                            flag: '02',
                            listKey: '2016',//修改key值，用于强制刷新组件
                            label: '新品上架'
                        }
                    });
                    location.href = '???#/classify'
                    break;
                case '预售产品':
                    yield put({
                        type: 'changePrePrdr',
                        payload: {
                            flag: '01',
                            listKey: '2017',
                            label: '预售产品'
                        }
                    })
                    location.href = '???#/classify'
                    break;
                case '更多':
                    location.href = '???#/classify'
                    break;
                default:
                    console.log('什么都没有！！！');
            }
        }
    },

    reducers: {
        initr(state, action) {
            //获取到商品类别列表
            let menuList = action.payload;
            let list = [];
            for (let i = 0; i < menuList.length; i++) {
                list[i] = {
                    value: menuList[i].prdtype,
                    label: menuList[i].prdtypename,
                    isLeaf: true,
                }
            }
            return { ...state, menuList: list };
        },
        //控制主页面左侧菜单的切换
        changeDrawr(state, action) {
            return { ...state, open: !state.open };
        },
        changeType(state, action) {
            const { menuList } = state;
            let label = '';
            let value = action.payload;
            for (let i = 0; i < menuList.length; i++) {
                if (menuList[i].value == value) {
                    label = menuList[i].label;
                }
            }
            return { ...state, goodType: action.payload, listKey: action.payload, baseUrl: 'prd/shprdbase', classTitle: label };
        },
        initShoppingCartr(state, action) {
            return { ...state, shoppingCartList: action.payload }
        },
        changePrePrdr(state, action) {
            const { flag, listKey, label } = action.payload;
            return { ...state, goodType: flag, listKey: listKey, baseUrl: 'prd/searchpreprd', classTitle: label }
        }
    }
}
