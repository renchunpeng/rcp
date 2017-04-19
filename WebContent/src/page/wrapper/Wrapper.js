/**
 * Created by semantic on 2017/1/24.
 */
import React, { Component, PropTypes } from 'react'
import { connect } from 'dva';
import { Card, WhiteSpace } from 'antd-mobile'
import { NavBar, Icon } from 'antd-mobile'
import { NoticeBar } from 'antd-mobile'
import { TabBar } from 'antd-mobile'
import Alipay from '../../img/wrapper/home.png'
import AlipaySel from '../../img/wrapper/home-active.png'
import Brand from '../../img/wrapper/brand.png'
import BrandAc from '../../img/wrapper/brand-active.png'
import Cart from '../../img/wrapper/cart.png'
import CartAc from '../../img/wrapper/cart-active.png'
import Personal from '../../img/wrapper/personal.png'
import PersonalAc from '../../img/wrapper/personal-active.png'
import { Toast, WingBlank, Button, Carousel, SearchBar } from 'antd-mobile'

function renderContent(pageText, dispatch, children) {
  const renderContent = {
    changeHidden1() {
      dispatch({
        type: 'tabBar/changeHidden',
        payload: {
          hidden: true
        }
      })
    },
  };
  return (
    <div style={{ height: '100%', textAlign: 'center' }}>
      {children}
    </div>
  );
}

function Wrapper({ children, orderConfirmModel, tabBar, dispatch }) {
  const { totalNumber } = orderConfirmModel;
  const tabBar1 = {
    changeBlue() {
      location.href = '???#/onlineMarket/market'
      dispatch({
        type: 'tabBar/changeBlue',
      })
    },
    changeRed() {
      location.href = '???#/onlineMarket/brand'
      dispatch({
        type: 'tabBar/changeRed',
      })
    },
    changeGreen() {
      location.href = '???#/onlineMarket/brand'
      dispatch({
        type: 'tabBar/changeGreen',
      })
    },
    changeYellow() {
      location.href = '???#/onlineMarket/personal'
      dispatch({
        type: 'tabBar/changeYellow',
      })
    },
    changePurple() {
      location.href = '???#/onlineMarket/cart'
      dispatch({
        type: 'tabBar/changePurple'
      })
    }
  };

  return (
    <div>
      {/*底部导航栏*/}
      <TabBar
        //默认的图表下方的颜色
        unselectedTintColor="#949494"//灰色
        tintColor="#1dbf63"//激活色
        //整个底部导航栏的颜色
        barTintColor="white"
        //控制底部导航栏显示还是隐藏
        hidden={tabBar.hidden}
      >
        <TabBar.Item
          title="首页"
          key="首页"
          icon={Alipay}
          selectedIcon={AlipaySel}
          selected={tabBar.selectedTab === 'blueTab'}
          data-seed="logId"
          onPress={tabBar1.changeBlue}
        >
          {renderContent('生活', dispatch, children)}
        </TabBar.Item>
        <TabBar.Item
          icon={Brand}
          selectedIcon={BrandAc}
          title="品牌"
          key="品牌"
          selected={tabBar.selectedTab === 'greenTab'}
          onPress={tabBar1.changeGreen}
        >
          {renderContent('品牌', dispatch, children)}
        </TabBar.Item>
        <TabBar.Item
          icon={Cart}
          selectedIcon={CartAc}
          title="购物车"
          key="购物车"
          selected={tabBar.selectedTab === 'purpleTab'}
          badge={totalNumber == 0 ? false : totalNumber}
          onPress={tabBar1.changePurple}
        >
          {renderContent('我的', dispatch, children)}
        </TabBar.Item>
        <TabBar.Item
          icon={Personal}
          selectedIcon={PersonalAc}
          title="我的"
          key="我的"
          selected={tabBar.selectedTab === 'yellowTab'}
          onPress={tabBar1.changeYellow}
        >
          {renderContent('我的', dispatch, children)}
        </TabBar.Item>
      </TabBar>
    </div>
  );
}

Wrapper.propTypes = {};

function mapStateToProps({ tabBar, orderConfirmModel }) {
  return { tabBar, orderConfirmModel };
}

export default connect(mapStateToProps)(Wrapper);
