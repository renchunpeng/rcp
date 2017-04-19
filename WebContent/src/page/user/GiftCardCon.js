/**
 * this page is created by ypli
 * description for giftcard
 * component function stateless
 */
import React, { Component, propTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'dva';
import { WhiteSpace, Card, Icon, ListView, NavBar, List } from 'antd-mobile';
import styles1 from '../styles/OrderConfirm.less';
import GiftCard from '../../img/vipcard.png';

const Item = List.Item;

function callback(key) {
  console.log(key);
}

function GiftCardCon({giftCard, dispatch}) {
  const { list, address } = giftCard;
  const { addressid, addcontact, addmobile, addprov, addcity, addcounty, district, adddetail, isdefault } = address;
  return (
    <div className={styles1.orderConfirm}>
       <a href='???#/addressList/choice/123456'>
          <div className={styles1.address}>
              <div className={styles1.title}>
                  <span className={styles1.name}>{addcontact}</span>
                  {
                      true ? <span className={styles1.default}>默认</span> : ''
                  }
                  <span className={styles1.number}>1234</span>
              </div>
              <div className={styles1.content}>
                  <div className={styles1.left}>
                      <span style={{ marginLeft: '0.3rem', display: 'block' }}>
                          <Icon type="environment" />
                          <span className={styles1.detail}>收货地址：</span>
                      </span>
                  </div>
                  <div className={styles1.right}>
                      <Icon type="right" />
                  </div>
              </div>
          </div>
      </a> 
      <WhiteSpace/>
      <div>
        <Card full>
          <Card.Header
            title={<span></span>}
            thumb={GiftCard}
          />
          <Card.Body>
            <div>
              <div style={{width: '100%',height: '100%',float: 'left'}}>有效期：</div>
              <div style={{width: '100%',marginTop:'10px', marginBottom:'10px',height: '100%',float: 'left'}}>剩余次数：</div>
              <hr  />
            </div>
          </Card.Body>
          <Card.Footer  extra={<div>使用中</div>}/>
        </Card>
        <WhiteSpace size="lg"/>
      </div>
      <div>
      <div style={{width: '100%', textalign: 'left', height: '0.9rem', lineheight: '0.9rem', paddingleft: '10rem'}}>使用记录</div>
      {
        list ? list.map((item, i) => {
            return <div key={i}>
              <List>
                <Item 
                extra="修改用户名" 
                arrow="horizontal" 
                onClick={() => {location.href='#/changeName'}}></Item>
            </List>
            </div>
          }) : ''
      }
    </div>
    </div>
  );
}

GiftCardCon.propsType = {};

function mapStateToProps({giftCard}) {
  return {giftCard};
}

export default connect(mapStateToProps)(GiftCardCon)
