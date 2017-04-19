/**
 * this page is created by ypli
 * description for 未开店进入页
 * component function stateless
 */
import React, { Component, PropTypes } from 'react';
import styles from '../styles/AddShop.less'
import { Link } from 'react-router';
import { connect } from 'dva';

const shopAdd = '../../../src/img/shop/shopadd.png';

function AddShop() {
    return (
        <div className={styles.container}>
        <div className={styles.wrapper} >
            <Link to='/shopRegister'>
                <img src={shopAdd} alt="" />
            </Link>
        </div>
        </div>
    )
}

export default AddShop