import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import styles from '../styles/Scan.less'
import Scans from '../../img/scan/scan.png'
import { Toast } from 'antd-mobile'

function Scan({ scan, dispatch }) {
  const { result } = scan;

  function getResult(results) {
    dispatch({
      type: 'scan/getResult',
      payload: {
        result: results
      }
    })
  }
  return (
    <div className={styles.scan}>
      <div className={styles.result}>
        {result}
      </div>
      <div className={styles.button} onClick={function () {
        wx.scanQRCode({
          needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
          scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
          success: function (res) {
            var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
            getResult(result);
          },
          fail: function (res) {
            Toast.fail('二维码调用失败！！！', 1)
          }
        });
      }}>
        <img style={{ width: '100%', height: '100%' }} src={Scans} alt="" />
      </div>
    </div>
  )
}

Scan.PropTypes = {};

function mapStateToProps({ scan }) {
  return { scan }
}

export default connect(mapStateToProps)(Scan)