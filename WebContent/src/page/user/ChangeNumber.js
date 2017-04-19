/**
 * this page is created by jcxu
 * description for change phoneNumber
 * component ES6
 */
import React, { Component, propTypes } from 'react'
import {
  List,
  InputItem,
  Switch,
  Stepper,
  Slider,
  Button,
  NavBar,
  Icon,
  Picker,
  Radio
} from 'antd-mobile';
import { connect } from 'dva'
import { createForm } from 'rc-form'

const Item = List.Item;

function ChangeNumber({ user, dispatch, form }) {

  const { phoneNumber, custId, custid, custmobile } = user;

  function onSubmit() {
    form.validateFields({
      force: true
    }, (error) => {
      if (!error) {
        dispatch({
          type: 'user/changePhoneNumber',
          payload: {
            custid: custid,
            custmobile: form.getFieldsValue().confirmPassword
          }
        })
      } else {
        alert('校验失败');
      }
    });
  }

  function validateAccount(rule, value, callback) {
    if (value && value.length > 4) {
      callback();
    } else {
      callback(new Error('帐号至少4个字符'));
    }
  }

  function validatePhoneNumber(rule, value, callback) {
    if (value && value.length > 4) {
      callback();
    } else {
      callback(new Error('帐号至少5个字符'));
    }
  }

  const { getFieldProps, getFieldError } = form;

  return (
    <form>
      <List
        renderHeader={() => <span><Icon type="lock" />修改手机号码</span>}
        renderFooter={() => getFieldError('account') && getFieldError('account').join(',')}>
        <InputItem
          {...getFieldProps('account', { initialValue: custmobile, rules: [{ required: true, message: '请输入手机号码' }, { validator: validateAccount },], }) }
          clear
          error={!!getFieldError('account')}
          onErrorClick={() => {
            alert(getFieldError('account').join('、'));
          }}
          placeholder="请输入当前号码">当前号码</InputItem>
        {/*<InputItem
          {...getFieldProps('idenCode') }
          placeholder="请填写验证码"
          extra={< Button className="btn" style={{ padding: '0 20px',backgroundColor: 'yellowgreen' ,height: '60px', lineHeight: '60px', borderRadius: '30px' }} type="primary" > 获取验证码 </Button>} />*/}

        <InputItem
          {...getFieldProps('newPassword') }
          placeholder="请输入新手机号码">
          新手机号码
          </InputItem>
        <InputItem
          {...getFieldProps('confirmPassword') }
          placeholder="请再次输入新手机号码">
          确认手机号码
          </InputItem>
      </List>

      <div style={{
        margin: 12
      }}>
        <div style={{ width: '100%', height: '1rem', lineHeight: '1rem', textAlign: 'center', backgroundColor: 'yellowgreen', color: 'white' }} onClick={onSubmit}>
          确认
            </div>
      </div>

    </form>
  )
}

ChangeNumber.propTypes = {};

ChangeNumber = createForm()(ChangeNumber);

function mapStateToProps({ user }) {
  return { user }
}

export default connect(mapStateToProps)(ChangeNumber)
