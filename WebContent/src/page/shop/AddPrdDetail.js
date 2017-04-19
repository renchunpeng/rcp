/**
 * this page is created by ypli
 * description for  入驻店铺商品描述
 * component ES6
 */
import React, { Component, propTypes } from 'react'
import { List, InputItem, Switch, Stepper, Slider, Button, NavBar, Icon, Picker, Radio, WhiteSpace } from 'antd-mobile';
import { connect } from 'dva'
import { createForm } from 'rc-form'
import styles from '../styles/Personal.less'

const Item = List.Item;

function AddPrdDetail({ user, dispatch, form }) {

  function onSubmit() {
    form.validateFields({
      force: true
    }, (error) => {
      if (!error) {
        dispatch({
          type: 'user/changePhoneNumber',
          payload: {
            custId: custid,
          phoneNumber: form.getFieldsValue().confirmPassword
          }
        })
      } else {
        alert('校验失败');
      }
    });
  }

  
  const { getFieldProps, getFieldError } = form;

  return (
    <form>               
      <div style={{width: '100%',height: '100%'}}></div>          
        <InputItem  
          clear
          error={!!getFieldError('account')}
          onErrorClick={() => {
            alert(getFieldError('account').join('、'));
          }}
          placeholder="宝贝描述">
        </InputItem>
      
      <div>
        
      <div style={{ width: '100%', height: '1rem', lineHeight: '1rem',  position:'fixed', bottom:'0',float:'left' }} > 
      <div style={{ width: '50%', height:'1rem', lineHeight: '1rem',   textAlign: 'center', 
           backgroundColor: '#a0a0a0', bottom:'0', color:'white', float:'left'}} onClick={onSubmit}>添加图片</div>
      <div style={{ width: '50%',height:'1rem', lineHeight: '1rem',   textAlign: 'center', 
           backgroundColor: '#1dbf63', bottom:'0', color:'white', float:'left'}}  onClick={onSubmit}>完成</div>
      </div>
      </div>
    </form>
  )
}

AddPrdDetail.propTypes = {};

AddPrdDetail = createForm()(AddPrdDetail);

export default AddPrdDetail
