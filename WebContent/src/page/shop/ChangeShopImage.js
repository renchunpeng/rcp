import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import styles from '../styles/ChangeShopImage.less'

function ChangeShopImage({shopDetail,dispatch}) {
    const {shopavator,shopid} = shopDetail;
    function changeAvator(e) {
        console.log('文件发生了变化',(e.target).files);
        dispatch({
            type: 'shopDetail/changeShopImage',
            payload: {
                file: (e.target).files,
                shopid: shopid
            }
        })
    }
    return (
        <div className={styles.changeShopImage}>
            <div className={styles.container}>
                <img src={shopavator} alt=""/>
            </div>
            <div className={styles.button}>
                点击上传
                <input type="file" onChange={changeAvator}/>
            </div>
        </div>
    )
}

ChangeShopImage.PropTypes = {};

function mapStateToProps({ shopDetail }) {
    return { shopDetail }
}

export default connect(mapStateToProps)(ChangeShopImage)