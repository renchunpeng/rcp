/**
 * this page is created by jcxu
 * description function component stateless
*/
import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import { Card, Radio, TextareaItem, NavBar, Icon, List, DatePicker, WhiteSpace } from 'antd-mobile'
import moment from 'moment';
import 'moment/locale/zh-cn';
import { createForm } from 'rc-form';
import styles from '../styles/BottomButton.less'
import styles1 from '../styles/OrderConfirm.less'
import InputNumber from '../../components/InputNumber'
import { Link } from 'react-router'

const RadioItem = Radio.RadioItem;

function OrderConfirm({ orderConfirmModel, params, dispatch, form }) {
    //解构赋值
    const { goodsList, radioValue, remark, senddate, address, totalNumber, totalPrice, coupon, couponList } = orderConfirmModel;
    const { addressid, addcontact, addmobile, addprov, addcity, addcounty, district, adddetail, isdefault } = address;
    const { custId } = params;
    //购物车中如果为空则跳转
    /*if (goodsList.length == 0) {
        location.href = '#/classify'
    }*/

    const { getFieldProps } = form;
    //支付方式切换
    function onChange1(value) {
        dispatch({
            type: 'orderConfirmModel/changeRadio',
            payload: value
        })
    }
    //日期修改同步至redux
    function onChange(date) {
        //console.log('onChange', date.format('YYYY-MM-DD HH:mm:ss'));
        dispatch({
            type: 'orderConfirmModel/changeDeliverTime',
            payload: date
        })
    }
    //修改商品数量
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
    //提交订单数据
    function submit() {
        let data = form.getFieldsValue();
        console.log(moment(data).format('LLL'));
        dispatch({
            type: 'orderConfirmModel/submit',
            payload: {
                goodsList: goodsList,//商品列表
                radioValue: radioValue,//支付方式切换
                remark: remark,//备注
                address: address,//配送地址
                totalNumber: totalNumber,//商品数量
                totalPrice: totalPrice,//商品总价
                senddate: senddate.format('YYYY-MM-DD HH:mm:ss'),//配送时间,格式化为所需格式
                couponList: couponList//优惠券信息
            }
        })
    }
    //备注的校验规则
    function validateRemark(rule, value, callback) {
        dispatch({
            type: 'orderConfirmModel/remarkChange',
            payload: value
        })
    }

    return (
        <div className={styles1.orderConfirm}>
            {
                address == 'NULL' ? <a href='???#/addressList/choice/123456'>
                    <div className={styles1.address}>
                        <div className={styles1.title}>
                            <span className={styles1.name}>{addcontact}</span>
                            {
                                isdefault ? <span className={styles1.default}>默认</span> : ''
                            }
                            <span className={styles1.number}>{addmobile}</span>
                        </div>
                        <div className={styles1.content}>
                            <div className={styles1.left}>
                                <span style={{ marginLeft: '0.3rem', display: 'block' }}>
                                    <Icon type="environment" />
                                    <span className={styles1.detail}>收货地址：{district} {adddetail}</span>
                                </span>
                            </div>
                            <div className={styles1.right}>
                                <Icon type="right" />
                            </div>
                        </div>
                    </div></a> : <a href='???#/addressList/choice/123456'>
                        <div className={styles1.address}>
                            <div className={styles1.title}>
                                <span className={styles1.name}>{addcontact}</span>
                                {
                                    isdefault ? <span className={styles1.default}>默认</span> : ''
                                }
                                <span className={styles1.number}>{addmobile}</span>
                            </div>
                            <div className={styles1.content}>
                                <div className={styles1.left}>
                                    <span style={{ marginLeft: '0.3rem', display: 'block' }}>
                                        <Icon type="environment" />
                                        <span className={styles1.detail}>收货地址：{`${addprov}  ${addcity}  ${addcounty}  ${adddetail}`}</span>
                                    </span>
                                </div>
                                <div className={styles1.right}>
                                    <Icon type="right" />
                                </div>
                            </div>
                        </div></a>
            }
            <WhiteSpace size="xs" />
            {
                goodsList.map((item, i) => <div key={i}>
                    <Card full>
                        <Card.Body>
                            <div>
                                <img style={{ height: '1.5rem', float: 'left', marginRight: '0.1rem' }} src="http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg" alt="" />
                                <div style={{ maxWidth: '30%', height: '1.5rem', float: 'left', textAlign: 'left' }}>
                                    <span style={{ width: '100%', height: '0.5rem', display: 'block' }}>{item.prdname}</span>
                                    <span style={{ width: '100%', height: '0.5rem', display: 'block' }}>规格：500g</span>
                                    <span style={{ width: '100%', height: '0.5rem', display: 'block' }}>¥{item.prdprice.toFixed(2)}</span>
                                </div>
                                <div style={{ width: '2.5rem', lineHeight: '1.5rem', height: '1.5rem', float: 'right', textAlign: 'right' }}>
                                    x{item.prdcount}
                                    {/*<InputNumber style={{ float: 'right', marginTop: '0.4rem' }} goodDetail={item} initValue={item.prdcount} callBack={changeNumber} ></InputNumber>*/}
                                </div>
                            </div>
                        </Card.Body>
                    </Card>
                    <WhiteSpace size="xs" /></div>)
            }

            <DatePicker className="forss"
                mode="datetime"
                onChange={onChange}
                value={senddate}
            >
                <List.Item arrow="horizontal">配送时间</List.Item>
            </DatePicker>
            <WhiteSpace size="xs" />

            <List>
                <RadioItem {...getFieldProps('addressType') } checked={radioValue === '01'} onChange={() => onChange1('01')}>
                    微信支付
                </RadioItem>
                <RadioItem {...getFieldProps('addressType') } checked={radioValue === '02'} onChange={() => onChange1('02')}>
                    支付宝支付
                </RadioItem>
            </List>

            <WhiteSpace size="xs" />
            <div className={styles1.invoice}>
                <span style={{ marginLeft: '0.3rem' }}>发票信息</span>
                <span style={{ marginRight: '0.3rem', float: 'right' }}>
                    <span style={{ color: '#676767', fontSize: '0.3rem' }}>暂不开发票</span><Icon type="right" />
                </span>
            </div>
            <WhiteSpace size="xs" />
            <div className={styles1.invoice}>
                <span style={{ marginLeft: '0.3rem' }}>优惠券</span>
                {
                    coupon=='暂无优惠券可用'?<span style={{ marginRight: '0.3rem', float: 'right' }}>
                    <span style={{ color: '#676767', fontSize: '0.3rem' }}>{coupon}</span><Icon type="right" />
                </span>:<span style={{ marginRight: '0.3rem', float: 'right' }} onClick={()=>{
                    location.href='#/choiceCoupon'
                    }}>
                    <span style={{ color: '#676767', fontSize: '0.3rem' }}>{coupon}</span><Icon type="right" />
                </span>
                }
            </div>
            <WhiteSpace size="xs" />
            <List>
                <TextareaItem
                    {...getFieldProps('remark', {
                        initialValue: remark,
                        rules: [
                            { required: true, message: '请输入备注信息' },
                            { validator: validateRemark }
                        ]
                    }) }
                    rows={5}
                    count={45}
                />
            </List>
            <div className={styles.buttomfixed} style={{ textAlign: 'left' }}>
                <div className={styles.leftcontainer}>应付金额：¥{totalPrice.toFixed(2)}</div>
                <div onClick={submit} className={styles.rightcontainer}>提交支付</div>
            </div>
        </div>
    )
}

OrderConfirm.PropTypes = {};

OrderConfirm = createForm()(OrderConfirm);

function mapStateToProps({ orderConfirmModel }) {
    return { orderConfirmModel };
}

export default connect(mapStateToProps)(OrderConfirm);
