export default {
    namespace: 'cart',

    state: {//购物车中的数据
        shopCart: [{
            shopId: '123456',
            shopName: '一号店铺',
            goodList: [
                {
                    goodId: '1464564',
                    goodName: '商品1',
                    goodImg: 'http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg',
                    goodNumber: 2
                },
                {
                    goodId: '1464566',
                    goodName: '商品1',
                    goodImg: 'http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg',
                    goodNumber: 3
                }
            ]
        },
        {
            shopId: '123458',
            shopName: 'er号店铺',
            goodList: [
                {
                    goodId: '1465564',
                    goodName: '商品1',
                    goodImg: 'http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg',
                    goodNumber: 2
                },
                {
                    goodId: '1464524',
                    goodName: '商品1',
                    goodImg: 'http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg',
                    goodNumber: 3
                },
                {
                    goodId: '1466564',
                    goodName: '商品1',
                    goodImg: 'http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg',
                    goodNumber: 3
                }
            ]
        }]
    },

    subscriptions: {
        
    },

    effects: {
        //商品添加到购物车
        *addToCart({payload},{put}) {
            console.dir(payload)
            yield put({
                type: 'addToCartR',
                payload: payload
            })
        }
    },

    reducers: {
        addToCartR(state, action) {
            console.dir(action.payload);
            return {...state, ...action.payload}
        }
    }
}