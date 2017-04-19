import React, { Component, PropTypes } from 'react'
import { Link } from 'react-router'
import { connect } from 'dva'
import { NoticeBar, Flex, Icon, WhiteSpace, Grid } from 'antd-mobile'
import styles from '../styles/Personal.less'
import huiyuan from '../../img/personal/huiyuan.png'
import shop from '../../img/personal/shop.png'

const data = [{
    link: '???#/giftCardList',
    icon: huiyuan,
    text: `会员中心`,
}, {
    link: '#/myShop',
    icon: shop,
    text: `我是商家`,
}];

const listData = [{
    link: '/orderList',
    iconName: 'calculator',
    type: '待支付'
},
{
    link: '/orderList',
    iconName: 'book',
    type: '配送中'
},
{
    link: '/orderList',
    iconName: 'inbox',
    type: '待评价'
},
{
    link: '/orderList',
    iconName: 'star',
    type: '退换货'
}];

function Personal({ user, dispatch }) {
    let { custid, custname, integralbalance, sum,wechatavatar } = user;
    
    //跳转至我的卡
    // function gotoCard() {
    //     location.href = '???#/giftCardList';
    // }

    //跳转至我的店铺，判断该用户是否已开店
    function gotoShop() {
        dispatch({
            type: 'shopDetail/defaultShop',
            payload: {
                custid: custid  //用户编号
            }
        })
    }

    function changeImg() {
        console.warn('用户头像加载失败！！！');
        dispatch({
            type: 'user/changeImage'
        })
    }

    const pocketList = [{
        link: '/orderList',
        value: 0.00,
        type: '余额'
    },
    {
        link: '/orderList',
        value: 0,
        type: '礼金卡'
    },
    {
        link: '???#/couponList/show',
        value: sum,
        type: '优惠券'
    },
    {
        link: '???#/itergration',
        value: integralbalance,
        type: '积分'
    }];
    return (
        <div className={styles.personal}>
            <div className={styles.profile}>
                <div style={{width: '100%',height: '70px'}}><Link style={{color: 'black'}} to='/countManage'><Icon className={styles.icon} type="setting"/></Link></div>
                <img src={wechatavatar} onError={changeImg} alt="头像"/>
                <div style={{width: '100%',height: '36px',textAlign: 'center',color:'white'}}>{custname}</div>
            </div>

            {/*<NoticeBar mode="closable" icon={null}>这里显示的是一个可删除的信息</NoticeBar>*/}
            <div className={styles.all} onClick={() => {
                dispatch({
                    type: 'orderList/changeTab',
                    payload: "1"
                })
                location.href = '#/orderlist'
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
                                type: 'orderList/changeTab',
                                payload: String(i + 2)
                            })
                            location.href = '#/orderlist'
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
            <div style={{ marginTop: '0.2rem' }} className={styles.all} onClick={() => {
                dispatch({
                    type: 'orderList/changeTab',
                    payload: "1"
                })
                location.href = '#/orderlist'
            }}>
                <span style={{ marginLeft: '0.3rem' }}>
                    我的钱包
                </span>
            </div>
            <div className={styles.naviList}>
                {pocketList.map((item, i) => {
                    return (
                        <div key={i} className={styles.item} onClick={() => {
                            location.href = item.link
                        }}>
                            <div className={styles.icon}>
                                <span>{item.value}</span>
                            </div>
                            <div className={styles.name}>
                                <span>{item.type}</span>
                            </div>
                        </div>
                    )
                })}
            </div>

            <div className={styles.more}>必备工具</div>

            {/*<Grid data={data}  onClick={(item,index) => {location.href=item.link}}/>               */}
            <Grid data={data}  onClick={(item,index) =>{
                    if(index == 0) { 
                        location.href=item.link 
                    } else if(index == 1 ){ 
                        gotoShop() 
                    }
                }
            } />

        </div>
    )
}

Personal.PropTypes = {};

function mapStateToProps({ user }) {
    return { user }
}

export default connect(mapStateToProps)(Personal)
