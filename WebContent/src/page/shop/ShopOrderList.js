/**
 * this page is created by ypli
 * description for 入驻店铺销售订单列表页
 * component function stateless
 */
import React from 'react'
import {connect} from 'dva'
import {NavBar, Badge, Card, ListView, SearchBar, Icon, Tabs, WhiteSpace} from 'antd-mobile'
import ShopOrderListData from '../public/ShopOrderList'
const TabPane = Tabs.TabPane;

function callback(key) {
  console.log(key);
}

const ShopOrderList = ({dispatch,shopOrderList}) => {
    const {indexTab} = shopOrderList;
    return (
        <div>
          <Tabs defaultActiveKey={indexTab} swipeable={false} onChange={callback}>
          <TabPane tab={<span>所有<span></span></span>} key="1">
            <div>
              <ShopOrderListData listType='/items' dispatch={dispatch} type='110'></ShopOrderListData>
            </div>
          </TabPane>
          <TabPane tab="待支付" key="2">
            <div>
              <ShopOrderListData listType='/items' dispatch={dispatch} type='02'></ShopOrderListData>
            </div>
          </TabPane>
          <TabPane tab="待发货" key="3">
            <div>
              <ShopOrderListData listType='/items' dispatch={dispatch} type='03'></ShopOrderListData>
            </div>
          </TabPane>
          <TabPane tab="待评价" key="4">
            <div>
              <ShopOrderListData listType='/items' dispatch={dispatch} type='06'></ShopOrderListData>
            </div>
          </TabPane>
          <TabPane tab="退换货" key="5">
            <div>
              <ShopOrderListData listType='/items' dispatch={dispatch} type='07'></ShopOrderListData>
            </div>
          </TabPane>
        </Tabs>

        </div>
    );
};

ShopOrderList.PropTypes = {};

function mapStateToProps({shopOrderList}){
  return {shopOrderList}
}
export default connect(mapStateToProps)(ShopOrderList);