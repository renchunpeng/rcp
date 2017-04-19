/**
 * this page is created by ypli
 * description for 入驻店铺商品列表
 * component function stateless
 */
import React, {Component, propTypes} from 'react';
import {Link} from 'react-router';
import {connect} from 'dva';
import {
  ListView,
  SearchBar,
  Tabs,
  WhiteSpace,
  Card,
  Icon
} from 'antd-mobile';
import ShopPrdListData from '../public/ShopPrdList';

const TabPane = Tabs.TabPane;

function callback(key) {
  console.log(key);
}

const ShopPrdList = ({dispatch, shopPrdList}) => {
  const {indexTab} = shopPrdList;
  return (
    <div style={{paddingBottom: '1rem'}}>
      <Tabs defaultActiveKey={indexTab} swipeable={false} animated={true} onChange={callback}>
        <TabPane tab="出售中" key="1">
          <div>
            <ShopPrdListData listType='/items' dispatch={dispatch} type='00'></ShopPrdListData>
          </div>
        </TabPane>
        <TabPane tab="仓库中" key="2">
          <div>
            <ShopPrdListData listType='/items' dispatch={dispatch} type='01'></ShopPrdListData>
          </div>
        </TabPane>
      </Tabs>
      <WhiteSpace/>
      <Link to='/addShopPrd/add'>
        <div
          style={{
          width: '100%',
          height: '1rem',
          lineHeight: '1rem',
          textAlign: 'center',
          backgroundColor: '#1dbf63',
          color: 'white',
          position: 'fixed',
          bottom: '0'
        }}>
          发布宝贝
        </div>
      </Link>
    </div>
  );
};

ShopPrdList.PropTypes = {};

function mapStateToProps({shopPrdList}) {
  return {shopPrdList}
}
export default connect(mapStateToProps)(ShopPrdList);
