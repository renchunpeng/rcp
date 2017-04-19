/**
 * this page is created by ypli
 * description for  店铺注册
 * component ES6
 */
import React, { Component, propTypes } from 'react'
import { List, InputItem, Switch, Stepper, Slider, Button, NavBar, Icon, Picker, Radio, WhiteSpace } from 'antd-mobile';
import { connect } from 'dva'
import { createForm } from 'rc-form'
import styles from '../styles/Personal.less'

const Item = List.Item;

function ShopRegister({ shop, user, dispatch, form }) {
  const { aplid, aplperson, prdtype, idencode, bankcount, isapprove, shopstatus, shopid, apldate } = shop;
  const { custid } = user;
  const { getFieldProps, getFieldError } = form;

  function onSubmit() {
    form.validateFields({
      force: true
    }, (error) => {
      if (!error) {
        let data = form.getFieldsValue();
        console.log(data);
        data['custid'] = custid;
        data['aplid'] = '12334';
        data['shopstatus'] = '0';       
        data['shopid'] = '';
        data['apldate'] = '2017-03-10 00:00:00.000';
        dispatch({
          type: 'shop/saveShopRegister',
          payload: data
        })
      } else {
        alert('校验失败');
      }
    });
  }

  function validateShopName(rule, value, callback) {
    if (value ) {
      callback();
    } else {
      callback(new Error('请输入真实姓名'));
    }
  }

  function validateIdenCode(rule, value, callback) {
    let idencode = '/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/'; 
    if (value) {
      callback();
    } else {
      callback(new Error('请输入正确的身份证号'));
    }
  }  

  function validateBankCount(rule, value, callback) {
    if (value) {
      callback();
    } else {
      callback(new Error('请输入有效的结算账号'));
    }
  }

  return (
    <form>                      
      <List> 
        <InputItem
          {...getFieldProps('aplperson', { 
              rules: [
                { require:true, message:'请输入真实姓名' },
                { validator:validateShopName}
              ]
          }) }
          clear
          error={!!getFieldError('aplperson')}
          onErrorClick={() => {
             alert(getFieldError('aplperson').join('、'))
          }}
          placeholder="请输入真实姓名"
          >
        真实姓名
        </InputItem> 
        <InputItem
          {...getFieldProps('idencode', { 
              rules: [
                { require:true, message:'请输入正确的身份证号' },
                { validator:validateIdenCode}
              ]
          }) }
          clear
          error={!!getFieldError('idencode')}
          onErrorClick={() => {
             alert(getFieldError('idencode').join('、'))
          }}
          placeholder="请输入正确的身份证号"
          >
        身份证号
        </InputItem>

         <InputItem
          {...getFieldProps('prdtype', { 
              rules: [
                { require:true, message:'请选择经营类别' },
                { validator:validateShopName}
              ]
          }) }
          clear
          error={!!getFieldError('prdtype')}
          onErrorClick={() => {
             alert(getFieldError('prdtype').join('、'))
          }}
          placeholder="请选择经营类别">
          经营类别
        </InputItem>
        
        <InputItem
          {...getFieldProps('bankcount', { 
              rules: [
                { require:true, message:'请输入有效的结算账号' },
                { validator:validateBankCount}
              ]
          }) }
          clear
          error={!!getFieldError('bankcount')}
          onErrorClick={() => {
             alert(getFieldError('bankcount').join('、'))
          }}
          placeholder="请输入有效的结算账号"
          >
          结算账号
        </InputItem>
        <List.Item
        extra={<Switch
          {...getFieldProps('isapprove', {
            initialValue: false,
            valuePropName: 'checked',
          })}
        />}
      >是否同意服务与质量承诺</List.Item>
      </List>    
      <div>
           
      <div style={{ width: '100%',height:'1rem', lineHeight: '1rem', textAlign: 'center',
           backgroundColor: 'red', bottom:'0', color:'white', position:'fixed'}}  onClick={onSubmit}>申请开店</div>
      
      </div>
    </form>
  )
}

ShopRegister.propTypes = {};

ShopRegister = createForm()(ShopRegister);

function mapStateToProps({ shop, user }) {
  return { shop, user }
}

export default connect(mapStateToProps)(ShopRegister)
