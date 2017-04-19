import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import { List, Checkbox, Flex } from 'antd-mobile';
import styles from '../styles/ShoppingCart.less'
import InputNumber from '../../components/InputNumber'
import cart from '../../img/cart.png'

const CheckboxItem = Checkbox.CheckboxItem;
const AgreeItem = Checkbox.AgreeItem;

const unit = {
    '00': '斤',
    '01': '公斤'
}

function Cart({ orderConfirmModel, user, dispatch }) {

    const { goodsList, totalPrice } = orderConfirmModel;
    let empty = goodsList.length == 0;

    function changeNumber(value, goodDetail) {
        dispatch({
            type: 'orderConfirmModel/changeGood',
            payload: {
                number: value,
                goodDetail: goodDetail,
                goodsList: goodsList
            }
        })
    }

    function orderConfirm() {
        location.href = '???#/orderConfirm/' + custid;
    }
    
    return (
        <div className={styles.shoppingCart}>
            {
                empty ? <div style={{paddingTop: '2rem'}}>
                    <img src={cart} alt="" />
                    <a style={{color: 'black'}} href="???#/classify">去商城看看！</a>
                </div> : goodsList.map((item, i) => {
                    return (
                        <div key={i} className={styles.goodItem}>
                            <img src="http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg" alt="" />
                            <div className={styles.description}>
                                <span>{`[${item.shopname}]${item.prdname}`}  </span>
                                <br />
                                <span style={{ bottom: '-0.5rem', position: 'relative' }}>{`¥${item.prdprice.toFixed(2)}/${unit[item.prdunit]}`}</span>
                            </div>
                            <div className={styles.select}>
                                <InputNumber style={{ float: 'right', marginTop: '0.4rem' }} goodDetail={item} initValue={item.prdcount} callBack={changeNumber} ></InputNumber>
                            </div>
                        </div>
                    )
                })
            }
            {
                empty ? '' : <div className={styles.bottom}>
                    <span>合计价格: ¥{totalPrice.toFixed(2)}</span>
                    <div className={styles.button} onClick={orderConfirm}>
                        确认
                </div>
                </div>
            }
        </div>
    )
}

Cart.PropTypes = {};

function mapStateToProps({ orderConfirmModel, user }) {
    return { orderConfirmModel, user }
}

export default connect(mapStateToProps)(Cart)
