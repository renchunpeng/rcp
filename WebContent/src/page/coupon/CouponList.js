import React, { Component, propTypes } from 'react'
import { connect } from 'dva'
import styles from '../styles/CouponList.less'
import { Result, Icon } from 'antd-mobile'
import none from '../../img/coupon/none.png'
import moment from 'moment'

function CouponList({ couponList, dispatch }) {
    const { couponLists } = couponList;
    return (
        <div className={styles.couponList}>
            {
                couponLists.length == 0 ? <Result
                    img={<img src={none} alt="" />}
                    title="暂无优惠券"
                    message="您可以在商城首页面领取优惠券"
                /> : couponLists.map((item, i) => {
                    const { bdcouponid,couponmoney,bewrite,overdate } = item;
                    return (
                        <div key={i} className={styles.item}>
                            <div className={styles.number}>
                                <span>¥<span style={{ fontSize: '0.5rem' }}>{couponmoney}</span></span>
                            </div>
                            <div className={styles.content}>
                                <span>{bewrite}</span>
                            </div>
                            <span className={styles.time}>过期时间：{moment(overdate).format('YYYY-MM-DD')}</span>
                        </div>
                    )
                })
            }

        </div>
    )
}

CouponList.propTypes = {}

function mapStateToProps({ couponList, orderConfirmModel }) {
    return { couponList, orderConfirmModel }
}

export default connect(mapStateToProps)(CouponList)