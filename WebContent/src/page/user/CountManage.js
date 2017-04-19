import React, { Componnet, PropTypes } from 'react'
import { Card, WhiteSpace, List } from 'antd-mobile'
import { connect } from 'dva'
import count from '../../img/countManage/count.png'
import address from '../../img/countManage/address.png'

const Item = List.Item;

function CountManage({user}) {

    const {userName,custid,custname,wechatavatar} = user;

    function changeImg() {
        console.warn('用户头像加载失败！！！');
        dispatch({
            type: 'user/changeImage'
        })
    }
    
    return (
        <div>
            <Card full>
                <Card.Header
                    title="当前登录账号"
                    extra={<span></span>}
                />
                <Card.Body>
                    <div>
                        <img style={{width: '1.2rem',float: 'left',marginRight: '0.2rem',height: '1.2rem',borderRadius: '0.6rem'}} src={wechatavatar} onError={changeImg} alt="头像"/>
                        <span>{custname}</span>
                    </div>
                </Card.Body>
            </Card>
            <WhiteSpace size="xs" />
            <List>
        <Item
          thumb={count}
          arrow="horizontal"
          onClick={() => {location.href='???#/myCount'}}
        >账户管理</Item>
        <Item thumb={address}
         arrow="horizontal"
         onClick={() => {location.href='???#/addressList/show/'+custid}}
         >地址管理</Item>
      </List>

        </div>
    )
}

CountManage.PropTypes = {};

function mapStateToProps({user}) {
    return { user }
}


export default connect(mapStateToProps)(CountManage)