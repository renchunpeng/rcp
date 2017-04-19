import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import _ from '../utils'
import { routerRedux } from 'dva/router';

import styles from '../index.less';
import wrapperStyles from './Wrapper.less';

import hint from '../utils/hint';

import { Menu, Icon } from 'antd';
const SubMenu = Menu.SubMenu;

const Wrapper = ({menus, dispatch, children, currentKey}) => {

  const menuSelect = (item) => {
    dispatch({
      type: 'menu/redirect',
      payload: item
    });
    dispatch(routerRedux.push(item.key));
  };

  return (
    <div className="iris-layout-top">
      <div className="iris-layout-header">
        <div className="iris-layout-wrapper">
          <div className="iris-layout-logo">
            <Icon type="area-chart" />
            &nbsp;&nbsp;
            {hint.WEB_TITLE}
          </div>
          <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']} style={{lineHeight: '64px'}}>
            <Menu.Item key="1">数据分析应用</Menu.Item>
          </Menu>
        </div>
      </div>
      <div className="iris-layout-subheader">
        <div className="iris-layout-wrapper">
          <Menu mode="horizontal" defaultSelectedKeys={['1']} style={{marginLeft: 124}}>
          </Menu>
        </div>
      </div>
      <div className="iris-layout-wrapper">
        <div className="iris-layout-breadcrumb">
        </div>
        <div className="iris-layout-container">
          <aside className="iris-layout-sider">
            <Menu mode="inline" defaultSelectedKeys={[currentKey]} defaultOpenKeys={['sub1']} onSelect={menuSelect}>
              <SubMenu key="sub1" title={<span><Icon type="dot-chart" />统计报表</span>}>
                {
                  _.map(menus, (item, key) => <Menu.Item key={key}>{item.title}</Menu.Item>)
                }
              </SubMenu>
            </Menu>
          </aside>
          <div className="iris-layout-content">
            <div style={{ minHeight: 600 }}>
              <div style={{clear: 'both'}}>
                {children}
              </div>
            </div>
          </div>
        </div>
        <div className="iris-layout-footer" dangerouslySetInnerHTML={_.createMarkup(hint.COPYRIGHT)}></div>
      </div>
    </div>
  );

};

Wrapper.propTypes = {};

function mapStateToProps( { menu } ) {
  return {
    menus: menu.items,
    currentKey: menu.currentKey
  };
}

export default connect(mapStateToProps)(Wrapper);
