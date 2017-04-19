/**
 * this page is created by ypli
 * description for 入驻店铺销售订单明细
 * component ES6  with state
 */

import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import {NavBar, Button, TextareaItem, List, Card, Icon, WhiteSpace} from 'antd-mobile'
import styles from '../styles/OrderDetail.less'
import styles1 from '../styles/OrderList.less'
import { formatDeliveryTime } from '../../utils/DateFormat'
import OrderStatus from '../shop/OrderStatus'

function ShopOrderDetail({ sOrderDet, dispatch}) {
  const { address, goodsList, totalPrice } = sOrderDet;
  const { orderid, orderstatus, remark, ctmAddress, createdate, paytype } = address;

  console.log(sOrderDet);

  function action(actionType) {
    dispatch({
      type: 'sOrderDet/action',
      payload: {
        actionType: actionType,
        sOrderDet: address,
        goodsList: goodsList
      }
    })
  }

  return (
    <div className={styles.orderDetail}>
      <div className={styles1.orderItem} style={{ height: '3rem', marginBottom: '0.1rem' }}>
        <div className={styles1.title}>
          <span className={styles1.left}>{orderid}</span> <span className={styles1.right}>{OrderStatus[orderstatus].status}</span>
        </div>
        <div className={styles1.content} style={{padding: '0.2rem 0'}}>
          <span className={styles1.container}>
            <span>收货人:{ctmAddress.addcontact}</span>
            <br />
            <span>手机号:{ctmAddress.addmobile}</span>
            <br />
            <span>订单时间:{formatDeliveryTime(createdate)}</span>
            <br />
            <span>收货地址:{`${ctmAddress.addprov}  ${ctmAddress.addcity}  ${ctmAddress.addcounty}  ${ctmAddress.adddetail}`}</span>
          </span>
        </div>
      </div>

      {
        goodsList.map((item, i) => {
          return (
            <div key={i} className={styles.goodsItem}>
              <img src="http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg" alt="" />
              <span style={{ float: 'left' }}>
                <span>{item.prdname}</span>
                <br />
                <span>这里是商品介绍</span>
                <br />
                <span>¥{item.prdprice.toFixed(2)}</span>
              </span>
              <div className={styles.number}>x{item.prdnum}</div>
            </div>
          )
        })
      }

      <div className={styles.totalPrice}>
        <span className={styles.left}>合计金额：</span>
        <span className={styles.right}>¥{totalPrice.toFixed(2)}</span>
      </div>

      <div className={styles.totalPrice}>
        <span className={styles.left}>支付方式：</span>
        <span className={styles.right}>{paytype=='01'?'微信支付':'支付宝支付'}</span>
      </div>

      <div className={styles.remark}>
        <div className={styles.title}>
          <span>备注</span>
        </div>
        <div className={styles.content}>
          <span className={styles.container}>
            <span>{remark}</span>
          </span>
        </div>
      </div>

      <div className={styles.bottomButton}>
        {
          OrderStatus[orderstatus].action.map((item,i) => {
            return (
              <div key={i} className={styles.button} onClick={()=>{action(item)}}>
                {item}
              </div>
            )
          })
        }
      </div>
    </div>
  )
}

ShopOrderDetail.propTypes = {};

function mapStateToProps({sOrderDet}) {
  return { sOrderDet };
}

export default connect(mapStateToProps)(ShopOrderDetail);