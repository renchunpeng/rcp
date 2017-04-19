/**
 * this page is created by ypli
 * description for giftcard
 * component function stateless
 */
import React, { Component, propTypes } from 'react';
import { Link } from 'react-router';
import { connect } from 'dva';
import { WhiteSpace, Card, Icon, ListView, NavBar, List, Button } from 'antd-mobile';
import GiftCard from '../../img/vipcard.png';

const Item = List.Item;

function callback(key) {
  console.log(key);
}

function GiftCardConDetail(giftCard, dispatch) {
  const {list} = giftCard.giftCard;
  return (
    <div>
         <div style={{width: '100%', textalign: 'left', height: '0.9rem', lineheight: '0.9rem', paddingleft: '10rem'}}>
              配送时间：2017-02-04
         </div>             
         <div>       
          <Card full>
                <Card.Body>
                    <div style={{ width: '100%', height: '100px' }}>
                        <div style={{ width: '80%', float: 'left' }}>
                            <img style={{ width: '1.6rem', float: 'left', marginRight: '10px' }} src={GiftCard} alt="" />
                            <span style={{ color: 'black', fontSize: '0.4rem' }}>收货人：</span>
                            <br></br>
                            <span>收货地址：奥体大街69号</span>
                        </div>
                        <div style={{ width: '20%', float: 'left' }}>
                            <div style={{ width: '100%', height: '0.8rem', textAlign: 'right' }}>15950520242</div>
                        </div>
                    </div>                
                </Card.Body>
            </Card>
      </div>
      <WhiteSpace/>
      <div>
       {
        list ? list.map((item, i) => {
            return <div key={i}>
             <Card full>
                <Card.Body>
                    <div style={{ width: '100%', height: '100px' }}>
                        <div style={{ width: '80%', float: 'left' }}>
                            <img style={{ width: '1.6rem', float: 'left', marginRight: '10px' }} src="https://zos.alipayobjects.com/rmsportal/dKbkpPXKfvZzWCM.png" alt="" />
                            <span style={{ color: 'black', fontSize: '0.4rem' }}>gongli</span>
                            <br></br>
                            <span>cehishu</span>
                        </div>
                        <div style={{ width: '20%', float: 'left' }}>
                            <div style={{ width: '100%', height: '0.8rem', textAlign: 'right' }}>￥1234</div>
                            <div style={{ width: '100%', height: '0.8rem', textAlign: 'right' }}>X56</div>
                        </div>
                    </div>
                    <div style={{ width: '100%', height: '50px', lineHeight: '50px' }}>
                        <span style={{ float: 'right' }}>合计：67</span>
                    </div>
                </Card.Body>
            </Card>
            </div>
          }) : ''
      }
    </div>
    </div>
  );
}

GiftCardConDetail.propsType = {};

function mapStateToProps({giftCard}) {
  return {giftCard};
}

export default connect(mapStateToProps)(GiftCardConDetail)
