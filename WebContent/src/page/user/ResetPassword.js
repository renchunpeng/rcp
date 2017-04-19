/**
 * this page is created by jcxu
 * description for reset password
 * component ES6
 */

import React, {Component, propTypes} from 'react'
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
import {createForm} from 'rc-form'
import styles from './styles/resetPassword.less'

const Item = List.Item;

class ResetPassword extends Component {
  constructor(props) {
    super(props);
    this.onSubmit = this
      .onSubmit
      .bind(this);
    this.onReset = this
      .onReset
      .bind(this);
    this.validateAccount = this
      .validateAccount
      .bind(this);
    this.validatePhoneNumber = this
      .validatePhoneNumber
      .bind(this);
    this.onChange = this
      .onChange
      .bind(this);
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
    this
      .props
      .form
      .validateFields({
        force: true
      }, (error) => {
        if (!error) {
          console.log(this.props.form.getFieldsValue());
        } else {
          alert('校验失败');
        }
      });
  }

  //重置表单
  onReset() {
    this
      .props
      .form
      .resetFields();
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
    this.setState({radioValue: value});
  }

  render() {
    const {getFieldProps, getFieldError} = this.props.form;
    return (
      <form>
        <NavBar
          leftContent="返回"
          mode="dark"
          onLeftClick={() => console.log('onLeftClick')}
          rightContent={false}>账户管理</NavBar>
        <List
          renderHeader={() => <span><Icon type="lock"/>重置密码</span>}
          renderFooter={() => getFieldError('account') && getFieldError('account').join(',')}>
          <InputItem
            {...getFieldProps('account', {
              initialValue: this.state.phoneNumber,
              rules: [{required: true, message: '请输入收货人姓名'}, {validator: this.validateAccount},],
            }) }
            clear
            error={!!getFieldError('account')}
            onErrorClick={() => {
              alert(getFieldError('account').join('、'));
            }}
            placeholder="请输入收货人姓名">当前号码</InputItem>
          <InputItem
            {...getFieldProps('idenCode')}
            placeholder="无 label"
            extra={< Button className="btn"
                            style={{padding: '0 20px', height: '60px', lineHeight: '60px', borderRadius: '30px'}}
                            type="primary"> 获取验证码 </Button>}/>

          <InputItem
            {...getFieldProps('newPassword') }
            placeholder="请输入密码"
            type="password">
            新密码
          </InputItem>
          <InputItem
            {...getFieldProps('confirmPassword') }
            placeholder="请输入密码"
            type="password">
            确认密码
          </InputItem>
        </List>

        <div style={{
          margin: 12
        }}>
          <Button
            style={{
              width: '100%'
            }}
            type="primary"
            onClick={this.onSubmit}
            inline>确认修改</Button>
        </div>

      </form>
    )
  }
}

ResetPassword = createForm()(ResetPassword)

export default ResetPassword
