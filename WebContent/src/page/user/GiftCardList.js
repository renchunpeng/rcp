/**
 * this page is created by ypli
 * description for giftcard
 * component function stateless
 */
import React, { Component, propTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'dva';
import { ListView, SearchBar, Tabs, WhiteSpace, Card, Popover, NavBar, Icon } from 'antd-mobile';
import GiftCardListData from '../public/GiftCardList';

const TabPane = Tabs.TabPane;
const Item = Popover.Item;

function callback(key) {
  console.log(key);
}

const GiftCardList  = ({dispatch,giftCardList}) => {
  const {indexTab} = giftCardList;

  let state = {
    visible: false,
    selected: '',
  };
  
  function onSelect(data)  {
      state = {
      visible: false,
      selected: data.props.value,
    };
  }

  function handleVisibleChange()  {
      if(state.visible){
          state.visible = false;
      }
      else {
        state.visible = true;
      }
  }

  let offsetX = -10; // just for pc demo
  if (/(iPhone|iPad|iPod|iOS|Android)/i.test(navigator.userAgent)) {
    offsetX = -26;
  }

  return (
    <div>
{/*      <NavBar iconName={false} mode="light" rightContent={
        <Popover mask  
          visible={state.visible}       
          overlay={[
            (<Item key="4" value="scan" icon={<Icon type="edit" size="xs" />} data-seed="logId">购买新卡</Item>),
            (<Item key="5" value="special" icon={<Icon type="edit" size="xs" />} style={{ whiteSpace: 'nowrap' }}>添加新卡</Item>),
          ]}
          popupAlign={{
            overflow: { adjustY: 0, adjustX: 0 },
            offset: [offsetX, 15],
          }}
          //onVisibleChange={handleVisibleChange}
         // onSelect={onSelect}
        >
          <div style={{
            height: '100%',
            padding: '0 0.3rem',
            marginRight: '-0.3rem',
            display: 'flex',
            alignItems: 'center',
          }}
          >
            <Icon type="ellipsis" />
          </div>
        </Popover>
      }
      >
        我的礼品卡
      </NavBar>
      <WhiteSpace/>*/}
      <Tabs defaultActiveKey={indexTab} animated={false} onChange={callback}>
        <TabPane tab="使用中" key="1">
          <div>
            <WhiteSpace/>
            <GiftCardListData listType='/items' dispatch={dispatch} type='01'></GiftCardListData>
          </div>
        </TabPane>
        <TabPane tab="不可用" key="2">
          <div>
            <GiftCardListData listType='/items' dispatch={dispatch} type='02'></GiftCardListData>
          </div>
        </TabPane>
      </Tabs>
    </div>
  );
};

GiftCardList.propTypes = {};

function mapStateToProps({giftCardList}){
    return {giftCardList}
}

export default connect(mapStateToProps)(GiftCardList);
