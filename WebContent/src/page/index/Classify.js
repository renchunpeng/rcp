/**
 * attention 通过修改组件的key值可以将组件进行强制刷新
 */

import { Drawer, Menu, Toast, List, NavBar, Icon } from 'antd-mobile'
import { connect } from 'dva'
import React, { Component, PropTypes } from 'react'
import ItemList from '../public/GoodList'
import { Link } from 'react-router'
import styles from '../styles/Classify.less'

function Classify({ cla, orderConfirmModel, dispatch, children, user }) {

  const { open, position, list, menuList, goodType, listKey,baseUrl,classTitle } = cla;
  const { goodsList, totalNumber, totalPrice } = orderConfirmModel;
  const { custid } = user;

  const drawerProps = {
    open: open,
    position: position,
    onOpenChange: () => {
      //控制左侧菜单的开闭
      dispatch({
        type: 'cla/changeDraw'
      })
    },
    onChange: (value) => {
      console.log(`输出菜单选中值${value}`)
      //点击左侧菜单触发时间
      dispatch({
        type: 'cla/changeListType',
        payload: value
      })
      dispatch({
        type: 'cla/changeDraw'
      })
    }
  };

  function saveCart() {
    if (goodsList.length != 0) {
      dispatch({
        type: 'orderConfirmModel/saveCart',
        payload: goodsList
      })
      let prdlist = [];
      let _prdlist = '';
      for (let i = 0; i < goodsList.length; i++) {
        //TODO 记得要过滤掉套餐
        if (true) {
          prdlist.push(goodsList[i].prdid);
        }
      }
      _prdlist = prdlist.join(",");
      //console.log(_prdlist);
      dispatch({
        type: 'orderConfirmModel/getCouponCanUse',
        payload: {
          custid: custid,
          prdlist: _prdlist,
          accountsum: totalPrice
        }
      })
    }
    else {
      Toast.fail('请先选购商品!!!', 1);
    }
  }

  return (
    <div>
      <NavBar iconName="bars"
       leftContent="分类"
       onLeftClick={() => {
        dispatch({
          type: 'cla/changeDraw'
        })
      }}>
        {classTitle}
        </NavBar>
      <Drawer
        className="my-drawer"
        style={{ minHeight: document.documentElement.clientHeight - 0 }}
        sidebar={<Menu
          className="foo-menu"
          data={menuList}
          onChange={drawerProps.onChange}
          level={1}
          value={['01']}
          height={document.documentElement.clientHeight * 1}
        />}
        dragHandleStyle={{ display: 'none' }}
        contentStyle={{ color: '#A6A6A6', textAlign: 'center' }}
        {...drawerProps}
      >
        <ItemList baseUrl={baseUrl} listType={goodType} goodsList={goodsList} dispatch={dispatch} key={listKey} />
      </Drawer>
      <div className={styles.button} onClick={saveCart}>
        <span className={styles.number}>
          {totalNumber}
        </span>
        <Icon type="shopping-cart" />
      </div>
    </div>
  )
}

Classify.PropTypes = {};

function mapStateToProps({ cla, orderConfirmModel, user }) {
  return { cla, orderConfirmModel, user }
}

export default connect(mapStateToProps)(Classify)
