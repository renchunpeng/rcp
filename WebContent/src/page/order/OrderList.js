import React, { PropTypes } from 'react'
import { connect } from 'dva'
import { NavBar, Badge, Card, ListView, SearchBar, Icon, Tabs, WhiteSpace } from 'antd-mobile'
import OrderListData from '../public/OrderList'
const TabPane = Tabs.TabPane;

function callback(key) {
  console.log(key);
}

const OrderList = ({dispatch,orderList}) => {
  const {indexTab} = orderList;
  return (
    <div>
      {/*<NavBar leftContent="返回" mode="dark" onLeftClick={() => {
        console.log('onLeftClick')
      }}
              rightContent={false}
      >我的订单</NavBar>*/}
      <Tabs defaultActiveKey={indexTab} swipeable={false} onChange={callback}>
        <TabPane tab={<span>所有<span></span></span>} key="1">
          <div>
            <OrderListData listType='/items' dispatch={dispatch} type='110'></OrderListData>
          </div>
        </TabPane>
        <TabPane tab="待支付" key="2">
          <div>
            <OrderListData listType='/items' dispatch={dispatch} type='01'></OrderListData>
          </div>
        </TabPane>
        <TabPane tab="配送中" key="3">
          <div>
            <OrderListData listType='/items' dispatch={dispatch} type='04'></OrderListData>
          </div>
        </TabPane>
        <TabPane tab="待评价" key="4">
          <div>
            <OrderListData listType='/items' dispatch={dispatch} type='06'></OrderListData>
          </div>
        </TabPane>
        <TabPane tab="退换货" key="5">
          <div>
            <OrderListData listType='/items' dispatch={dispatch} type='08'></OrderListData>
          </div>
        </TabPane>
      </Tabs>

    </div>
  );
};

OrderList.PropTypes = {};

function mapStateToProps({orderList}){
  return {orderList}
}

export default connect(mapStateToProps)(OrderList)