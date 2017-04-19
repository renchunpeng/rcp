import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import {List,WhiteSpace} from 'antd-mobile'
import name from '../../img/myCount/name.png'
import phone from '../../img/myCount/phone.png'

const Item = List.Item;

function MyCount({ user }) {
    const {custname,custmobile} = user;
    return (
        <div>
            <List>
                <Item 
                thumb={name} 
                extra="修改用户名" 
                arrow="horizontal" 
                onClick={() => {location.href='???#/changeName'}}>{custname}</Item>
            </List>
{/*            <WhiteSpace size="xs" />
            <List>
                <Item 
                thumb="https://zos.alipayobjects.com/rmsportal/dNuvNrtqUztHCwM.png" 
                extra="修改密码" 
                arrow="horizontal" 
                onClick={() => {}}>标题文字</Item>
            </List>*/}
            <WhiteSpace size="xs" />
            <List>
                <Item 
                thumb={phone} 
                extra="修改绑定手机" 
                arrow="horizontal" 
                onClick={() => {location.href='???#/changeNumber'}}>{custmobile}</Item>
            </List>
        </div>
    )
}

MyCount.PropTypes = {};

function mapStateToProps({user}) {
    return { user }
}

export default connect(mapStateToProps)(MyCount)