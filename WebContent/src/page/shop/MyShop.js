/**
 * this page is created by ypli
 * description for 我的店铺首页
 * component function stateless
 */
import React, { Component, PropTypes } from 'react'
import { Link } from 'react-router'
import { connect } from 'dva'
import { NoticeBar, Flex, Icon, WhiteSpace, Grid, Card } from 'antd-mobile'
import styles from '../styles/MyShop.less'
import shopprd from '../../img/shop/shopprd.png'
import shopset from '../../img/shop/shopset.png'

const shopData = [{
    link: '???#/shopPrdList',
    icon: shopprd,
    text: '宝贝管理'
},
{
    link: '???#/shopSet',
    icon: shopset,
    text: '店铺设置'
}];

const listData = [{
    link: '/orderList',
    iconName: 'pay-circle-o',
    type: '待支付'
},
{
    link: '/orderList',
    iconName: 'book',
    type: '待发货'
},
{
    link: '/orderList',
    iconName: 'copy',
    type: '待评价'
},
{
    link: '/orderList',
    iconName: 'inbox',
    type: '退换货'
}];

function MyShop({ shopDetail, dispatch }) {
    const { shopid, shopname, shopdescription, shopavator } = shopDetail;

    function changeImg() {
        console.warn('店铺头像加载失败！！！');
        dispatch({
            type: 'shopDetail/changeImage'
        })
    }

    return (
        <div className={styles.personal}>
            <div className={styles.profile}>
                <div style={{ width: '100%', height: '70px' }}></div>
                <img src={shopavator} onError={changeImg} alt="头像" />
                <div style={{ width: '100%', height: '36px', textAlign: 'center', color: 'white' }}>{shopname}</div>
            </div>

            <WhiteSpace />

            <div className={styles.all} onClick={() => {
                dispatch({
                    type: 'shopOrderList/changeTab',
                    payload: "1"
                })
                location.href = '#/shopOrderList'
            }}>
                <span style={{ marginLeft: '0.3rem' }}>
                    我的订单
                </span>
                <span className={styles.moreRight}>查看全部订单<Icon type="right"></Icon></span>
            </div>
            <div className={styles.naviList}>
                {listData.map((item, i) => {
                    return (
                        <div key={i} className={styles.item} onClick={() => {
                            dispatch({
                                type: 'shopOrderList/changeTab',
                                payload: String(i + 2)
                            })
                            location.href = '#/shopOrderList'
                        }}>
                            <div className={styles.icon}>
                                <Icon type={item.iconName}></Icon>
                            </div>
                            <div className={styles.name}>
                                <span>{item.type}</span>
                            </div>
                        </div>
                    )
                })}
            </div>

            <WhiteSpace />

            <div style={{ marginTop: '0.2rem' }} className={styles.all} >
                <span style={{ marginLeft: '0.3rem' }}>
                    我的小店
        </span>
            </div>

            <Grid data={shopData} onClick={(item, index) => { location.href = item.link }} />

            <WhiteSpace />

            <div className={styles.more} style={{ backgroundColor: 'white' }}>今日成交总额</div>

        </div>
    )
}

MyShop.PropTypes = {};

function mapStateToProps({ shopDetail }) {
    return { shopDetail }
}

export default connect(mapStateToProps)(MyShop)