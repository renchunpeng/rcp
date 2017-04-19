import React, { Component, PropTypes } from 'react'
import { Checkbox } from 'antd-mobile'
import { connect } from 'dva'
import styles from '../styles/CouponList.less'

const AgreeItem = Checkbox.AgreeItem;

function ChoiceCoupon({ orderConfirmModel, dispatch }) {
    const { couponList } = orderConfirmModel;
    return (
        <div className={styles.couponList}>
            {
                couponList.length == 0 ? <div>您暂时还没有优惠券</div> : couponList.map((item, i) => {
                    const { bdcouponid,ischoiced,couponmoney,bewrite,ismixuse,checked } = item;
                    return (
                        <div key={i} className={styles.item}>
                            <div className={styles.number}>
                                <span>¥<span style={{ fontSize: '0.5rem' }}>{couponmoney.toFixed(2)}</span></span>
                            </div>
                            <div className={styles.content}>
                                <span>{bewrite}</span>
                            </div>
                            <div className={styles.icon}>
                                <AgreeItem data-seed="logId" checked={checked} onClick={(e) => {
                                    console.log('checkbox', e)
                                    dispatch({
                                        type: 'orderConfirmModel/choiceCoupon',
                                        payload: {
                                            bdcouponid: bdcouponid,
                                            ismixuse: ismixuse
                                        }
                                    })
                                }}>
                                </AgreeItem>
                            </div>

                        </div>
                    )
                })
            }

        </div>
    )
}

ChoiceCoupon.propTypes = {}

function mapStateToProps({ orderConfirmModel }) {
    return { orderConfirmModel }
}

export default connect(mapStateToProps)(ChoiceCoupon)