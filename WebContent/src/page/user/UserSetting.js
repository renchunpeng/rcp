import React, { Component, propTypes } from 'react'
import { List,Icon,WhiteSpace,NavBar } from 'antd-mobile';
import { createForm } from 'rc-form';

const Item = List.Item;

class UserSetting  extends Component{
  ResetUserName(value){
    location.href="???#/resetUserName";
  }
  ResetPassword(value){
    location.href="???#/resetPassword";
  }
  ResetMobile(value){
    location.href="???#/resetMobile";
  }
  render() {
    return (<div>
      <NavBar leftContent="返回" mode="dark" onLeftClick={() => console.log('onLeftClick')}
              rightContent={false}
      >账户管理</NavBar>
      <List renderHeader={() => '帐号设置'}>
        <Item extra="修改用户名" arrow="horizontal" onClick={this.ResetUserName}>
          <Icon type="user" style={{marginRight:'30px'}} />TeatUserName</Item>
      </List>
      <WhiteSpace size='md' />
      <List>
        <Item  extra="修改密码" arrow="horizontal" onClick={this.ResetPassword}>
          <Icon type="lock" style={{marginRight:'30px'}} />*******</Item>
      </List>
      <WhiteSpace size='md' />
      <List>
        <Item extra="修改绑定手机" arrow="horizontal" onClick={this.ResetMobile}>
          <Icon type="phone" style={{marginRight:'30px'}} />
          15868400032</Item>
         <WhiteSpace size='md' />
      </List>
    </div>);
  }
};

UserSetting = createForm()(UserSetting);

export default UserSetting

