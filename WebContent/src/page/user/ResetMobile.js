/**
 * this page is created by jcxu
 * description for reset password
 * component ES6
 */

import React, { Component, propTypes } from 'react'
import { List, InputItem, Switch, Stepper, Slider, Button, NavBar, Icon, Picker, Radio } from 'antd-mobile';
import { createForm } from 'rc-form';
import styles from './styles/resetPassword.less'

const Item = List.Item;

class ResetMobile extends Component {
  constructor(props) {
    super(props);
    this.onSubmit = this.onSubmit.bind(this);
    this.onReset = this.onReset.bind(this);
    this.validateAccount = this.validateAccount.bind(this);
    this.validatePhoneNumber = this.validatePhoneNumber.bind(this);
    this.onChange = this.onChange.bind(this);
    this.state = {
      account: '',
      phoneNumber: '18862237873',
      district: [],
      addressDetail: '',
      isdefault: true,
      radioValue: 0
    }
  }
  //提交表单
  onSubmit() {
    //TODO:connect with backend
    this.props.form.validateFields({ force: true }, (error) => {
      if (!error) {
        console.log(this.props.form.getFieldsValue());
      } else {
        alert('校验失败');
      }
    });
  }
  //重置表单
  onReset() {
    this.props.form.resetFields();
  }
  //收货人姓名的校验规则
  validateAccount(rule, value, callback) {
    if (value && value.length > 4) {
      callback();
    } else {
      callback(new Error('帐号至少4个字符'));
    }
  }
  //手机号码的校验规则
  validatePhoneNumber(rule, value, callback) {
    if (value && value.length > 4) {
      callback();
    } else {
      callback(new Error('帐号至少5个字符'));
    }
  }
  onChange(value) {
    console.log('checkbox');
    this.setState({
      radioValue: value
    });
  }
  render() {
    const { getFieldProps, getFieldError } = this.props.form;
    return (
      <form>
        <NavBar leftContent="返回" mode="dark" onLeftClick={() => console.log('onLeftClick')}
                rightContent={false}
        >账户管理</NavBar>
        <List renderHeader={() => <span style={{color:'#ABD133',fontSize:'0.4rem',margin:'0.2rem'}}><Icon type="phone" style={{margin:'30px 30px'}} />重置手机号码</span>} renderFooter={() => getFieldError('account') && getFieldError('account').join(',')}>
          <InputItem style={{borderRadius: '30px'}}
            {...getFieldProps('account', {
              initialValue: this.state.phoneNumber,
              rules: [
                { required: true, message: '请输入验证码' },
                { validator: this.validateAccount },
              ],
            }) }
            clear
            error={!!getFieldError('account')}
            onErrorClick={() => {
                            alert(getFieldError('account').join('、'));
                        }}
            placeholder="请输入验证码"
          >当前号码</InputItem>
          <InputItem
            {...getFieldProps('idenCode')}
            placeholder="请输入验证码"
            extra={<Button className="btn" style={{padding: '0 20px',height: '60px',lineHeight: '60px',borderRadius: '30px',background:'#ABD133'}} type="primary">获取验证码</Button>}
          />


          <InputItem {...getFieldProps('newMobile') } placeholder="新手机号码" type="phone">
            新号码
          </InputItem>
          <InputItem {...getFieldProps('confirmMobile') } placeholder="确认手机号码" type="phone">
            确认号码
          </InputItem>
        </List>

        <div style={{ margin: 12 }}>
          <Button style={{ width: '100%',background:'#ABD133' }} type="primary" onClick={this.onSubmit} inline>确认修改</Button>
        </div>

      </form>
    )
  }
}

ResetMobile = createForm()(ResetMobile)

export default ResetMobile
