import React, { Component, PropTypes } from 'react'
import styles from '../styles/Index.less'
import { Icon, Flex, SearchBar, Carousel, Button, Modal, WhiteSpace, WingBlank } from 'antd-mobile'
import GoodsItem from './components/GoodsItem'
import { Link } from 'react-router'
import { connect } from 'dva';
import moment from 'moment'
//import Flow from '../../img/market/flow.png'
import Transverse from '../../img/market/transverse.png'
import Vertical from '../../img/market/vertical.png'
import MarketConfig from './MarketConfig'

const type = '../../lflweb/src/img/onlineMarket/type.png';
const Flow = 'http://123.57.207.196:88/flow.png';
const coupon = 'http://123.57.207.196:88/youhuiquan.png';

const PlaceHolder = (props) => {
    function link() {
        props.dispatch({
            type: 'cla/changePrePrd',
            payload: { ...props }
        })
    }
    return (
        <div onClick={link} style={{
            backgroundColor: 'white',
            height: 'auto',
            width: '100%',
        }}
        >
            <img style={{ width: '100%' }} src={props.columnimgurl} alt="" />
            <div style={{ width: '100%', height: '0.5rem', lineHeight: '0.5rem', textAlign: 'center' }}>
                {props.columnname}
            </div>
        </div>
    )
};

function Market({ index, dispatch }) {
    const swiperUrl = '../../lflweb/src/img/onlineMarket/youhuiquan.png';
    const avertisment = '../../lflweb/src/img/onlineMarket/advertisment.png';
    const vegetiables = '../../lflweb/src/img/onlineMarket/vegetiables.png';
    const more = '../../lflweb/src/img/onlineMarket/more.png';
    let { visible, freeCoupons, activityList, getAdvertisement, bigTypeList } = index;
    for (let i = 0; i < freeCoupons.length; i++) {
        moment(freeCoupons[i].effmonth).format('YYYY-MM-DD HH:mm:ss')
        freeCoupons[i].effmonth = moment(freeCoupons[i].effmonth).format('YYYY-MM-DD HH:mm:ss');
    }
    function onClose() {
        dispatch({
            type: 'index/changeVisible'
        })
    }
    function showModal(e) {
        // 现象：如果弹出的弹框上的 x 按钮的位置、和手指点击 button 时所在的位置「重叠」起来，
        // 会触发 x 按钮的点击事件而导致关闭弹框 (注：弹框上的取消/确定等按钮遇到同样情况也会如此)
        e.preventDefault(); // 修复 Android 上点击穿透
        dispatch({
            type: 'index/changeVisible'
        })
    }
    return (
        <div className={styles.index}>
            <div className={styles.header}>
                <a href="???#/classify"><div className={styles.good}>
                    <Icon style={{ color: '#1dbf63' }} type="appstore"></Icon><span>全部商品</span>
                </div></a>
                <SearchBar
                    onFocus={() => { location.href = '???#/search' }}
                    placeholder="搜索" />
            </div>

            {
                freeCoupons.length == 0 ? '' : <div className={styles.swiper} onClick={showModal}>
                    <img src={coupon} alt="" />
                </div>
            }

            <div className={styles.subject1}>
                <div className={styles.advertisment}>
                    <Carousel
                        className="my-carousel" autoplay={true} infinite
                        beforeChange={(from, to) => console.log(`slide from ${from} to ${to}`)}
                        afterChange={index => console.log('slide to', index)}
                    >
                        {
                            getAdvertisement.map((item, i) => {
                                const { advredurl, uploadimgurl } = item;
                                return (
                                    <a href={advredurl} key={i}><img src={uploadimgurl} /></a>
                                )
                            })
                        }

                    </Carousel>
                </div>
                <div className={styles.flow}>
                    <img src={Flow} alt="" />
                </div>
                <div className={styles.items}>
                    {
                        bigTypeList.map((item, i) => {
                            let width = document.body.clientWidth;
                            const { columnimgurl, columnname } = item;
                            function Link() {
                                dispatch({
                                    type: 'cla/changePrePrd',
                                    payload: { ...item }
                                })
                            }
                            if (i < 5) {
                                return (
                                    <div key={i} className={styles.item} onClick={Link} style={{ width: width / 5 }}>
                                        <img src={columnimgurl} style={{ width: width / 5, height: width / 5 }} alt="" />
                                        <div className={styles.title}>
                                            {columnname}
                                        </div>
                                    </div>
                                )
                            }
                        })
                    }
                </div>
            </div>

            {
                activityList.map((item, i) => {
                    if (item.length > 0) {
                        let width = document.body.clientWidth;
                        return (
                            <div key={i}>
                                <div className={styles.title}>
                                    <span className={styles.main}>----安心蔬菜----</span><br />
                                    <span className={styles.extra}>精心挑选的超值好货</span>
                                </div>

                                <div className={styles.typeContainer}>
                                    <div className={styles.main} style={{ width: width - 20, marginLeft: '10px', height: (width - 20) / 2 }}>
                                        <img style={{ width: '100%', height: '100%' }} src={Transverse} alt="" />
                                    </div>
                                    {
                                        item.map((item, i) => {
                                            const {prdid,prdtype} = item;
                                            function link() {
                                                location.href=`???#/goodDetail/${prdid}/${prdtype}`
                                            }
                                            return (
                                                <div onClick={link} key={i} className={styles.itemContainer} style={{ width: (width - 30) / 2, margin: '0 0 10px 10px', height: ((width - 30) / 10) * 8 }}>
                                                    <img style={{ width: '100%', height: '100%' }} src={Vertical} alt="" />
                                                </div>
                                            )
                                        })
                                    }
                                    <div className={styles.itemContainer} style={{ width: (width - 30) / 2, margin: '0 0 10px 10px', height: ((width - 30) / 10) * 8 }}>
                                        <img src={more} style={{ width: '100%', height: '100%' }} alt="" />
                                    </div>
                                </div>
                            </div>
                        )
                    }
                })
            }

            <Modal
                title="优惠券"
                transparent
                maskClosable={false}
                visible={visible}
                onClose={onClose}
                footer={[{
                    text: '领取', onPress: () => {
                        dispatch({
                            type: 'index/confirmCoupons',
                            payload: {
                                custid: custid,
                                item: freeCoupons
                            }
                        }); onClose();
                    }
                }]}
            >
                <div style={{ width: '100%', height: '5rem', overflowY: 'scroll' }}>
                    {
                        freeCoupons.map((item, i) => {
                            return (
                                <div key={i} style={{ width: '95%', height: '1.2rem', textAlign: 'left', lineHeight: '0.6rem', border: '1px solid #e6e6e6', marginBottom: '0.2rem', borderRadius: '5px', borderLeft: '5px solid green' }}>
                                    <div style={{ width: '1.2rem', marginRight: '0.2rem', float: 'left', height: '1rem', marginTop: '0.1rem', borderRight: '1px solid #e6e6e6', lineHeight: '1rem', textAlign: 'center' }}>
                                        ¥<span style={{ fontSize: '0.3rem' }}>{item.couponmoney.toFixed(2)}</span>
                                    </div>
                                    <span>{item.bewrite}</span>
                                </div>
                            )
                        })
                    }
                </div>
            </Modal>
        </div>
    )
}

Market.PropTypes = {};

function mapStateToProps({ index }) {
    return { index };
}

export default connect(mapStateToProps)(Market);
