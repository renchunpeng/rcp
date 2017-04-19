import React, { PropTypes } from 'react'
import { Router } from 'dva/router'
import { getJSSDK } from './services/userAction'

//import Login from './page/login/Login'
//import List from './page/testList/List'
//import Material from './page/testMaterial/Material'

//测试用的默认首页
//import IndexPage from './page/IndexPage'
//商城导航页
//import Wrapper from './page/wrapper/Wrapper'
//商城页面
//import Index from './page/index/index'
//import Swiper from './page/index/Swiper'
//import ShoppingCart from './page/onlineMarket/ShoppinCart'
//import Classify from './page/index/Classify'
//import GoodSearch from './page/onlineMarket/GoodSearch'
//import Cart from './page/onlineMarket/Cart'
//import Drawer1 from './page/index/Drawer1'
//import Menu1 from './page/index/Menu1'
//import TypeMenu from './page/index/TypeMenu'
//import ItemList from './page/public/ItemList'
//import Personal from './page/user/Personal'
//import GoodList from './page/public/GoodList'
//import GoodDetail from './page/onlineMarket/GoodDetail'
//地址相关页面
import AddAddress from './page/address/AddAddress'
import AddressList from './page/address/AddressList'
//密码重置页面
//import ResetPassword from './page/user/ResetPassword'
//import ChangeNumber from './page/user/ChangeNumber'
//import MyCount from './page/user/MyCount'
//import CountManage from './page/user/CountManage'
import PayMent from './page/onlineMarket/PayMent'
//订单相关页面
//import OrderList from './page/order/OrderList'
//import OrderDetail from './page/order/OrderDetail'
//import OrderConfirm from './page/order/OrderConfirm'
//import OrderEvaluation from './page/order/OrderEvaluation'
//暂时用于测试的列表页面
//import OrderListData from './page/order/OrderListData'
//import GiftCardListTest from './page/public/GiftCardList'
//import ShopPrdListTest from './page/public/ShopPrdList'
//import ShopOrderListTest from './page/public/ShopOrderList'
import ImageUpload from './page/testList/ImageUpload'
import ShopSet from './page/shop/ShopSet'
//优惠券相关页面
//import CouponList from './page/coupon/CouponList'
//import ChoiceCoupon from './page/coupon/ChoiceCoupon'
//积分相关页面
//import Itergration from './page/user/Itergration'
//商城其他页面
import Scan from './page/other/Scan'
import TestList from './page/testList/TestList'

//该方法用于获取微信jsSDK调用授权
function weixin() {
  const url = 'http://n157392v64.imwork.net/lflweb/???';
  const result = getJSSDK(url);
  result.then((data) => {
    if (data.data.success) {
      let config = data.data.data;
      const { appId, nonceStr, signature, timestamp } = config;
      wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: [
          'scanQRCode'
        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
      });
      wx.ready(function () {
        console.log('微信验证通过！！！');
      });
      wx.error(function (res) {
        console.log('错误信息', JSON.stringify(res));
      });
      wx.checkJsApi({
        jsApiList: ['scanQRCode'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
        success: function (res) {
          console.log('scanQRCode', JSON.stringify(res))
        }
      });
      console.log('数据获取成功！！！');
    }
    else {
      console.log('数据获取失败！！！');
    }
  })
}

//该方法用于检验用户是否登录
function login() {
  let isLogin = document.cookie.indexOf("openId=");
  if (isLogin != -1) {
    console.log('用户登录了。');
  }
  else {
    console.log('用户尚未登录。');
    location.href = 'http://n157392v64.imwork.net/lflweb/WXuser/getUserSession/'
  }
}

const routes = {

  path: '/',
  name: '/',
  getIndexRoute(nextState, cb) {
    require.ensure([], require => {
      cb(null, { component: require('./page/IndexPage') })
    })
  },
  childRoutes: [
    {//网上商城带导航页
      path: 'onlineMarket',
      name: 'onlineMarket',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/wrapper/Wrapper'))
        })
      },
      getIndexRoute(nextState, cb) {
        require.ensure([], require => {
          cb(null, { component: require('./page/onlineMarket/Market') })
        })
      },
      childRoutes: [
        {
          path: 'market',
          name: 'Market',
          onEnter: login,
          getComponent(nextState, cb) {
            require.ensure([], require => {
              cb(null, require('./page/onlineMarket/Market'))
            })
          }
        },
        {
          path: 'brand',
          name: 'brand',
          getComponent(nextState, cb) {
            require.ensure([], require => {
              cb(null, require('./page/index/Brand'))
            })
          }
        },
        {
          path: 'personal',
          name: 'personal',
          onEnter: login,
          getComponent(nextState, cb) {
            require.ensure([], require => {
              cb(null, require('./page/user/Personal'))
            })
          }
        },
        {
          path: 'shoppingCart',
          name: 'shoppingCart',
          getComponent(nextState, cb) {
            require.ensure([], require => {
              cb(null, require('./page/onlineMarket/ShoppinCart'))
            })
          }
        },
        {
          path: 'cart',
          name: 'cart',
          getComponent(nextState, cb) {
            require.ensure([], require => {
              cb(null, require('./page/onlineMarket/Cart'))
            })
          }
        }
      ]
    },
    {
      path: 'swiper',
      name: 'swiper',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/index/Swiper'))
        })
      }
    },
    {
      path: 'classify',
      name: 'classify',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/index/Classify'))
        })
      }
    },
    {
      path: 'addAddress/:type/:addressId',
      component: AddAddress
    },
    {
      path: 'payMent/:custId/:orderId',
      component: PayMent
    },
    {
      path: 'addressList/:type/:custId',
      component: AddressList
    },
    {
      path: 'resetPassword',
      name: 'resetPassword',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/ResetPassword'))
        })
      }
    },
    {
      path: 'changeNumber',
      name: 'changeNumber',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/ChangeNumber'))
        })
      }
    },
    {
      path: 'changeName',
      name: 'ChangeName',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/ChangeName'))
        })
      }
    },
    {
      path: 'orderList',
      name: 'orderList',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/order/OrderList'))
        })
      }
    },
    {
      path: 'orderDetail/:orderId',
      name: 'orderDetail',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/order/OrderDetail'))
        })
      }
    },
    {
      path: 'orderConfirm/:custId',
      name: 'orderConfirm',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/order/OrderConfirm'))
        })
      }
    },
    {
      path: 'orderEvaluation',
      name: 'orderEvaluation',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/order/OrderEvaluation'))
        })
      }
    },
    {
      path: 'goodSearch',
      name: 'goodSearch',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/onlineMarket/GoodSearch'))
        })
      }
    },
    {
      path: 'goodDetail/:prdId/:prdKind',
      name: 'goodDetail',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/onlineMarket/GoodDetail'))
        })
      }
    },
    {
      path: 'countManage',
      name: 'countManage',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/CountManage'))
        })
      }
    },
    {
      path: 'myCount',
      name: 'myCount',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/MyCount'))
        })
      }
    },
    {
      path: 'giftCardList',
      name: 'giftCardList',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/GiftCardList'))
        })
      }
    },
    {
      path: 'giftCardCon/:memId',
      name: 'giftCardCon',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/GiftCardCon'))
        })
      }
    },
    {
      path: 'giftCardConDetail',
      name: 'giftCardConDetail',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/GiftCardConDetail'))
        })
      }
    },
    {
      path: 'addShop',
      name: 'addShop',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/AddShop'))
        })
      }
    },
    {
      path: 'myShop',
      name: 'myShop',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/MyShop'))
        })
      }
    },
    {
      path: 'shopPrdList',
      name: 'shopPrdList',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ShopPrdList'))
        })
      }
    },
    {
      path: 'shopOrderList',
      name: 'shopOrderList',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ShopOrderList'))
        })
      }
    },
    {
      path: 'shopOrderDetail/:orderId',
      name: 'shopOrderDetail',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ShopOrderDetail'))
        })
      }
    },
    {
      path: 'shopSet',
      component: ShopSet
    },
    {
      path: 'addShopPrd/:type',
      name: 'AddShopPrd',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/AddShopPrd'))
        })
      }
    },
    {
      path: 'addPrdDetail',
      name: 'addPrdDetail',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/AddPrdDetail'))
        })
      }
    },
    {
      path: 'shopRegister',
      name: 'shopRegister',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ShopRegister'))
        })
      }
    },
    {
      path: 'changeShopName',
      name: 'ChangeShopName',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ChangeShopName'))
        })
      }
    },
    {
      path: 'changeShopDesc',
      name: 'ChangeShopDesc',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ChangeShopDesc'))
        })
      }
    },
    {
      path: 'changeShopImage',
      name: 'changeShopImage',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ChangeShopImage'))
        })
      }
    },
    {
      path: 'changeReturnAddress',
      name: 'ChangeReturnAddress',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/shop/ChangeReturnAddress'))
        })
      }
    },
    {
      path: 'couponList/:type',
      name: 'couponList',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/coupon/CouponList'))
        })
      }
    },
    {
      path: 'choiceCoupon',
      name: 'choiceCoupon',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/coupon/ChoiceCoupon'))
        })
      }
    },
    {
      path: 'itergration',
      name: 'itergration',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/Itergration'))
        })
      }
    },
    {
      path: 'search',
      name: 'search',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./page/user/Search'))
        })
      }
    },
    {
      path: 'imageUpload',
      component: ImageUpload
    },
    {
      path: 'scan',
      onEnter: weixin,
      component: Scan
    },
    {
      path: 'testList',
      component: TestList
    },
    {
      path: '*',
      name: 'error',
      getComponent(nextState, cb) {
        require.ensure([], require => {
          cb(null, require('./routes/NotFound'))
        })
      }
    }
  ]

};

export default function ({ history }) {
  return (
    <Router history={history} routes={routes} />
  );
};
