/**
 * this page is created by ypli
 * description for 入驻店铺设置页
 * component function stateless
 */
import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import { List, WhiteSpace, ImagePicker, Card, Icon } from 'antd-mobile';
import { Link } from 'react-router'
import shop from '../../img/personal/shop.png';
import styles from '../styles/ShopSet.less'

const Item = List.Item;

const data = [{
    url: 'https://zos.alipayobjects.com/rmsportal/PZUUCKTRIHWiZSY.jpeg',
    id: '2121',
}];

function ShopSet({ shopDetail }) {
    const { shopid, shopname, shopdescription, address, shopavator } = shopDetail;
    const { addressid, addcontact, isdefault, addmobile, addprov, addcity, addtype, addcounty, district, adddetail } = address;

    console.log(address);

    function onChange(data, type, index) {
        console.log(data, type, index);
    }

    return (
        <div className={styles.shopSet}>
            <div className={styles.shopAvator}>
                <img src={shopavator} alt="" />
            </div>
            <List>
                <Item
                    extra=""
                    arrow="horizontal"
                    onClick={() => { location.href = '???#/changeShopImage' }}>店铺头像</Item>
            </List>
            <WhiteSpace size="xs" />
            <List>
                <Item
                    extra={shopname}
                    arrow="horizontal"
                    onClick={() => { location.href = '???#/changeShopName' }}>店铺名称</Item>
            </List>
            <WhiteSpace size="xs" />
            <List>
                <Item
                    extra={shopdescription}
                    arrow="horizontal"
                    onClick={() => { location.href = '???#/changeShopDesc' }}>店铺介绍</Item>
            </List>
            <WhiteSpace size="xs" />
            <Link to={{pathname:`/addressList/back/${custid}`}}>
                <Card full>
                    <Card.Header
                        title={<span>退货地址：</span>}
                        thumb={false}
                        extra={<span>{addmobile}</span>}
                    />
                    <Card.Body>
                        <div>
                            <div style={{ width: '80%', height: '100%', float: 'left' }}>{`${addprov}  ${addcity}  ${addcounty}  ${adddetail}`}</div>
                            <div style={{ width: '20%', height: '100%', float: 'left', textAlign: 'right' }}><Icon type="edit" />
                            </div>
                        </div>
                    </Card.Body>
                    <Card.Footer content="地址类型" extra={addtype == '01' ? <div>家庭地址</div> : <div>公司地址</div>} />
                </Card>
            </Link>
        </div>
    )
}

ShopSet.PropTypes = {};

function mapStateToProps({ shopDetail }) {
    return { shopDetail }
}

export default connect(mapStateToProps)(ShopSet)
