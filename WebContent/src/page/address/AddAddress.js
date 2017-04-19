import React, { Component, propTypes } from 'react'
import { List, InputItem, Switch, Stepper, Slider, Button, NavBar, Modal, Icon, Picker, Radio, TextareaItem } from 'antd-mobile';
import { createForm } from 'rc-form'
//地址配置暂时只有北京的数据
import { districts } from './District'
import { connect } from 'dva'

const Item = List.Item;
const RadioItem = Radio.RadioItem;

function AddAddress({ address, dispatch, form, user }) {
  const { addcontact, addmobile, adddetail, provid, cityid, countyid, isdefault, addtype, showAlert } = address;
  const { getFieldProps, getFieldError } = form;
  const { custid } = user;

  function modalChange() {
    dispatch({
      type: 'address/changeAlert'
    })
  }

  //DEMO
  //const addcontact = '测试用户名';
  //const addmobile = '1886223787';
  //const adddetail = '这里是详细地址';
  //地址类型配置数据
  const data = [
    { value: 0, label: '家庭地址' },
    { value: 1, label: '公司地址' }
  ];
  //地址数据提交
  function onSubmit() {
    form.validateFields({ force: true }, (error) => {
      if (!error) {
        let data = form.getFieldsValue();
        data['addressType'] = addtype;
        data['custid'] = custid;
        //console.log(data);
        dispatch({
          type: 'address/submit',
          payload: data
        })
      } else {
        modalChange();
      }
    });
  }
  //收货人姓名的校验规则
  function validateAddcontact(rule, value, callback) {
    if (value && value.length > 0) {
      callback();
    } else {
      callback(new Error('收货人姓名不得为空！'));
    }
  }
  //手机号码的校验规则
  function validateAddmobile(rule, value, callback) {
    if (value && (/^1(3|4|5|7|8)\d{9}$/.test(value))) {
      callback();
    } else {
      callback(new Error('手机号码输入有误！'));
    }
  }
  //详细地址的校验规则
  function validateAdddetail(rule, value, callback) {
    if (value) {
      callback();
    } else {
      callback(new Error('请输入您的详细地址！'));
    }
  }
  function onChange(value) {
    console.log('checkbox');
    dispatch({
      type: 'address/radioValue',
      payload: value
    })
  }
  return (
    <form>
      <List renderFooter={() => getFieldError('addmobile') && getFieldError('addmobile').join(',')}>
        <InputItem
          {...getFieldProps('addcontact', {
            initialValue: addcontact,
            rules: [
              { required: true, message: '请输入收货人姓名' },
              { validator: validateAddcontact },
            ],
          }) }
          clear
          error={!!getFieldError('addcontact')}
          onErrorClick={() => {
            alert(getFieldError('addcontact').join('、'));
          }}
          placeholder="请输入收货人姓名"
        >收货人姓名</InputItem>

        <InputItem
          {...getFieldProps('addmobile', {
            initialValue: addmobile,
            rules: [
              { required: true, message: '请输入手机号码' },
              { validator: validateAddmobile }
            ],
          }) }
          clear
          error={!!getFieldError('addmobile')}
          onErrorClick={() => {
            alert(getFieldError('addmobile').join('、'));
          }}
          placeholder="请输入手机号码">
          手机号码
        </InputItem>

        <Picker
          {...getFieldProps('district', {
            initialValue: [provid, cityid, countyid],
          }) }
          data={districts}
          title="选择地区"
          extra="请选择(可选)"
        >
          <List.Item arrow="horizontal">选择地址</List.Item>
        </Picker>

        <TextareaItem
          {...getFieldProps('adddetail', {
            initialValue: adddetail,
            rules: [
              { required: true, message: '请输入手机号码' },
              { validator: validateAdddetail }
            ],
          }) }
          clear
          error={!!getFieldError('adddetail')}
          onErrorClick={() => {
            alert(getFieldError('adddetail').join('、'));
          }}
          title="详细地址"
          placeholder="请输入您的详细地址"
          data-seed="logId"
          autoHeight
        />

        <Item
          extra={<Switch {...getFieldProps('isdefault', { initialValue: isdefault, valuePropName: 'checked' }) } />}
        >设为默认地址</Item>
        <RadioItem {...getFieldProps('addressType') } checked={addtype === '01'} onChange={() => onChange('01')}>
          家庭地址
        </RadioItem>
        <RadioItem {...getFieldProps('addressType') } checked={addtype === '02'} onChange={() => onChange('02')}>
          公司地址
        </RadioItem>
      </List>
      <div style={{ margin: 12 }}>
        <Button style={{ width: '100%' }} type="primary" onClick={onSubmit} inline>保存</Button>
      </div>

      <Modal
          title="地址信息填写有误"
          transparent
          maskClosable={false}
          visible={showAlert}
          onClose={modalChange}
          footer={[{ text: '确定', onPress: () => { console.log('ok'); modalChange(); } }]}
        >
        </Modal>
    </form>
  )
}

AddAddress.propTypes = {};

AddAddress = createForm()(AddAddress);

function mapStateToProps({ address, user }) {
  return { address, user }
}

export default connect(mapStateToProps)(AddAddress)
