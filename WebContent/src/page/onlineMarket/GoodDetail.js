import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import styles from '../styles/GoodDetail.less'
import styles1 from '../styles/BottomButton.less'
import { WhiteSpace, Carousel, Tabs, Popup, List, button, Badge, Button, Card, Icon } from 'antd-mobile'
import InputNumber from '../../components/InputNumber'
import { Link } from 'react-router'
import moment from 'moment'
import star from '../../img/goodDetail/star.png'

const TabPane = Tabs.TabPane;

const unit = {
    '00': '斤',
    '01': '公斤'
};

const isIPhone = new RegExp('\\biPhone\\b|\\biPod\\b', 'i').test(window.navigator.userAgent);
let wrapProps;
if (isIPhone) {
    // Note: the popup content will not scroll.
    wrapProps = {
        // onTouchStart: e => e.preventDefault(),
    };
}

function GoodDetail({ gooddm, cart, orderConfirmModel, dispatch }) {
    const { prdkind, commentList } = gooddm;
    const { goodsList, totalNumber } = orderConfirmModel;
    //对商品和套餐进行兼容
    let goodDetail = {};
    if (prdkind == '02') {
        goodDetail['prdid'] = gooddm.packageid;
        goodDetail['prdbrief'] = gooddm.packagedescription;
        goodDetail['prdintroduction'] = gooddm.packageintro;
        goodDetail['prdname'] = gooddm.packagename;
        goodDetail['prdprice'] = gooddm.packageprice;
        goodDetail['prdtax'] = gooddm.packagetax;
        goodDetail['prdtype'] = gooddm.packagetype;
        goodDetail['prdunit'] = gooddm.packageunit;
        goodDetail['shopid'] = gooddm.shopid;
        goodDetail['shopname'] = gooddm.shopname;
        goodDetail['heat'] = gooddm.heat;
        goodDetail['prdImage'] = gooddm.prdPackageimg;
        goodDetail['prdPackagedetails'] = gooddm.prdPackagedetails;
        goodDetail['prdkind'] = gooddm.prdkind;

        let prdList = gooddm.prdPackagedetails;
        goodDetail['skudescription'] = [];
        for (let i = 0; i < prdList.length; i++) {
            goodDetail['skudescription'][i] = prdList[i].prdBase.skudescription;
        }
    }
    else {
        let description = gooddm.skudescription;
        goodDetail = gooddm;
        goodDetail.skudescription = [];
        goodDetail.skudescription[0] = description;
    }
    //对兼容后的商品数据进行结构赋值
    const { prdid, shopname, prdname, prdprice, prdImage, skudescription, prdunit } = goodDetail;
    //初始化商品数量
    let _prdcount = 0;
    for (let i = 0; i < goodsList.length; i++) {
        if (goodsList[i].prdid == prdid) {
            _prdcount = goodsList[i].prdcount
        }
    }
    const onClose = (sel) => {
        dispatch({
            type: 'gooddm/close',
            payload: sel
        })
        Popup.hide();
    };
    function changeNumber(value, goodDetail) {
        //console.log(goodsList);
        dispatch({
            type: 'orderConfirmModel/changeGood',
            payload: {
                number: value,
                goodDetail: goodDetail,
                goodsList: goodsList
            }
        })
    }
    //const {shopChart} = cart;
    //添加购物车的操作
    function addToCart() {
        /*dispatch({
            type: 'cart/addToCart',
            payload: {
                shopChart: '123'
            }
        })*/
        Popup.show(<div>
            <List renderHeader={() => (
                <div style={{ position: 'relative' }}>
                    <div style={{
                        width: '2rem',
                        height: '2rem',
                        position: 'relative',
                        marginTop: '-100px',
                        padding: '0.1rem',
                        backgroundColor: 'white',
                        borderRadius: '4px'
                    }}>
                        <img style={{width: '100%'}} src={prdImage.imageurl} alt="" />
                    </div>
                    <span style={{ position: 'absolute', right: 3, top: 95, }}
                        onClick={() => onClose('cancel')}
                    >
                        <Icon type="cross" />
                    </span>
                </div>)}
                className="popup-list"
            >

                <List.Item >商品名称----{prdname}</List.Item>
                <List.Item >商品价格----¥{prdprice}</List.Item>
                <List.Item><InputNumber style={{ float: 'right' }} goodDetail={goodDetail} initValue={_prdcount} callBack={changeNumber} ></InputNumber></List.Item>
            </List>
            <ul style={{ padding: '0.18rem 0.3rem', listStyle: 'none' }}>
                <li style={{ marginTop: '0.18rem' }}>
                    <Button type="primary" onClick={() => onClose('cancel')}>确认</Button>
                </li>
            </ul>
        </div>, { animationType: 'slide-up', wrapProps, maskClosable: false });
    }
    //此处添加的是html字符串
    function createMarkup(_html) {
        return {
            __html: _html
        };
    };
    function callback(key) {
        console.log(key);
    }
    return (
        <div className={styles.goodDetail}>
            <Carousel
                className="my-carousel" autoplay={false} infinite
                beforeChange={(from, to) => console.log(`slide from ${from} to ${to}`)}
                afterChange={index => console.log('slide to', index)}
            >

                <a href="#"><img src={prdImage.imageurl} /></a>

            </Carousel>
            <div className={styles.name}>
                <span className={styles.title}>{`【${shopname}】 ${prdname}`}</span>
                <span className={styles.price}> <span>{`¥ ${prdprice.toFixed(2)} `}</span>{`/${unit[prdunit]}`}</span>
            </div>

            <Tabs defaultActiveKey="1" swipeable={false} onChange={callback}>
                <TabPane tab="商品介绍" key="1">
                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', overflow: 'hidden', backgroundColor: '#fff' }}>
                        {
                            skudescription.map((item, i) => {
                                return (
                                    <div key={i} dangerouslySetInnerHTML={createMarkup(item)} />
                                )
                            })
                        }
                    </div>
                </TabPane>
                <TabPane tab="商品评价" key="2">
                    <div style={{ width: '100%', height: 'auto', paddingBottom: '0.3rem' }}>
                        {
                            commentList.length == 0 ? <div style={{
                                width: '100%',
                                height: '2rem',
                                textAlign: 'center',
                                lineHeight: '2rem',
                                borderRadius: '5px',
                                marginTop: '0.3rem'
                            }}>
                                该商品暂无评价！！！
                                </div> : commentList.map((item, i) => {
                                    const { pcommentid, orderid, prdid, prdsatisfaction, prdcomment, commdate } = item;
                                    let stars = [];
                                    for (let i = 0; i < prdsatisfaction; i++) {
                                        stars[i] = i;
                                    }
                                    return (
                                        <div key={i} style={{ width: '96%', padding: '0.2rem 0 0.2rem 0', marginRight: '2%', marginTop: '0.2rem', marginLeft: '2%', height: '1.6rem', backgroundColor: 'white', border: '1px solid #e6e6e6', borderRadius: '5px' }}>
                                            <div style={{ width: '100%', height: '0.4rem', lineHeight: '0.4rem' }}>
                                                {
                                                    stars.map((item, i) => {
                                                        return (
                                                            <img key={i} style={{ width: '0.4rem', height: '0.4rem', float: 'left', marginLeft: '0.3rem' }} src={star} alt="" />
                                                        )
                                                    })
                                                }
                                            </div>
                                            <div style={{ width: '100%', height: '0.8rem', lineHeight: '0.4rem' }}><span style={{ marginLeft: '0.3rem' }}>{prdcomment}</span></div>
                                            <div style={{ width: '100%', height: '0.4rem', lineHeight: '0.4rem', fontSize: '0.25rem', textAlign: 'right' }}><span style={{ marginRight: '0.3rem' }}>{moment(commdate).format('YYYY-MM-DD hh:mm:ss')}</span></div>
                                        </div>
                                    )
                                })
                        }
                    </div>
                </TabPane>
            </Tabs>
            <div className={styles1.buttomfixed}>
                <Link to='/onlineMarket/market' style={{ color: 'black' }}><div className={styles1.fourItem} style={{ borderRight: '0px solid #e6e6e6' }}><Icon type="home" /></div></Link>
                <Link to='/onlineMarket/cart' style={{ color: 'black' }}>
                    <div className={styles1.fourItem} style={{ borderRight: '0px solid #e6e6e6' }}>
                        <Icon type="shopping-cart" /><Badge text={totalNumber} style={{ marginBottom: '0.5rem' }} />
                    </div>
                </Link>
                <div onClick={addToCart} className={styles1.fourItem}>加入购物车</div>
                <div onClick={addToCart} className={styles1.fourItem} style={{ color: 'white', backgroundColor: '#1dbf63' }}>立即购买</div>
            </div>
        </div>
    )
}

GoodDetail.PropTypes = {};

function mapStateToProps({ gooddm, orderConfirmModel, cart }) {
    return { gooddm, orderConfirmModel, cart }
}

export default connect(mapStateToProps)(GoodDetail)