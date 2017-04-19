/**
 * this page is created by jcxu
 * description for address manager
 * component function stateless
 */
import React, { Component, PropTypes } from 'react'
import { Link } from 'react-router'
import { connect } from 'dva'
import { Card, WingBlank, WhiteSpace, NavBar, Icon, Button } from 'antd-mobile';

function AddressList({ address, dispatch }) {
  const { list, isChoice, listType } = address;//获取到地址列表
  function test(value) {
    console.log('当前的数字为：' + value);
  }
  function link(addressId, i) {
    if (isChoice) {
      switch (listType) {
        case 'confirm'://来自订单确认页的选择地址
          //console.log(list[i]);
          dispatch({
            type: 'orderConfirmModel/changeAddress',
            payload: list[i]
          })
          break;
        case 'shopset': //来自店铺设置页的选择地址
          dispatch({
            type: 'shopDetail/changeAddress',
            payload: list[i]
          })
          break;
        case 'return': //来自店铺设置页的选择地址
          dispatch({
            type: 'shopDetail/changeReturnAddress',
            payload: list[i]
          })
          break;
      }
      history.go(-1);
    }
    else {
      location.href = '???#/addAddress/edit/' + addressId
    }
  }
  return (
    <div style={{ paddingBottom: '1.2rem' }}>
      {
        list ? list.map((item, i) => {
          return <div key={i} onClick={() => link(item.addressid, i)}>
            <Card full>
              <Card.Header
                title={<span>{item.addcontact}{item.isdefault ? <span>默认</span> : ''}</span>}
                thumb={false}
                extra={<span>{item.addmobile}</span>}
              />
              <Card.Body>
                <div>
                  <div style={{ width: '80%', height: '100%', float: 'left' }}>{`${item.addprov}  ${item.addcity}  ${item.addcounty}  ${item.adddetail}`}</div>
                  <div style={{ width: '20%', height: '100%', float: 'left', textAlign: 'right' }}>{isChoice ? '' : <Icon type="edit" />}
                  </div>
                </div>
              </Card.Body>
              <Card.Footer content="地址类型" extra={item.addtype == '01' ? <div>家庭地址</div> : <div>公司地址</div>} />
            </Card>
            <WhiteSpace size="lg" />
          </div>
        }) : ''
      }
      <Link to='/addAddress/add/123456'><div style={{ width: '100%', height: '1rem', position: 'fixed', bottom: '0px', lineHeight: '1rem', color: 'white', backgroundColor: 'yellowgreen', textAlign: 'center' }}>
        新增地址
</div></Link>
    </div>
  )
}

AddressList.propsType = {};

function mapStateToProps({ address }) {
  return { address };
}

export default connect(mapStateToProps)(AddressList)
