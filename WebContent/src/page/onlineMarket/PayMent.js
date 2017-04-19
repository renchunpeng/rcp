import React, { Component, propTypes } from 'react'
import { connect } from 'dva'
import { Button, Flex, Toast } from 'antd-mobile'
import styles from '../styles/Payment.less'


function PayMent({ pay }) {

	const { PayParams, orderifo, goodsList,totalPrice } = pay;
	function callPay() {
		try {
			if (typeof WeixinJSBridge == "undefined") {
				if (document.addEventListener) {
					document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
				} else if (document.attachEvent) {
					document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
					document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				}
			} else {
				onBridgeReady();
			}
		} catch (e) {
			alert(e);
		}
		window.event.returnValue = false;
		return false;
	}
	function onBridgeReady() {
		WeixinJSBridge.invoke(
			'getBrandWCPayRequest',
			PayParams,//支付所需的参数
			function (res) {
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					Toast.success('支付成功!!!', 1)
					location.href = '???#/orderList'
				}
				else if (res.err_msg == "get_brand_wcpay_request:cancel") {
					Toast.show('支付过程中用户取消!!!', 1)
				} else {
					//alert(JSON.stringify(res));
					Toast.fail('支付失败!!!', 1)
				}
			}
		)
	}

	return (
		<div className={styles.payMent}>
			{/*<p>
				{JSON.stringify(PayParams)}
			</p>*/}
			<div className={styles.container}>
				{
					goodsList.map((item, i) => {
						const { prdname, prdcount, prdprice } = item;
						return (
							<div key={i} className={styles.item}>
								{prdname}<span style={{ float: 'right' }}>¥{prdprice.toFixed(2)}x{prdcount}</span>
							</div>
						)
					})
				}
				<div className={styles.item}>
					合计金额：<span style={{ float: 'right' }}>¥{totalPrice.toFixed(2)}</span>
				</div>
			</div>

			<Button onClick={callPay} style={{width: '96%',margin: '0 2%'}} className="btn" type="primary">确认支付</Button>

		</div>
	)
}

PayMent.propTypes = {};

function mapStateToProps({ pay }) {
	return { pay }
}

export default connect(mapStateToProps)(PayMent)