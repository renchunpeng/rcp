import React from 'react'
import { connect } from 'dva'
import { List, Checkbox, Flex, Icon } from 'antd-mobile'
import InputNmuber from 'rc-input-number'
//require('rc-input-number/assets/index.less');

const CheckboxItem = Checkbox.CheckboxItem;
const AgreeItem = Checkbox.AgreeItem;

const onChange = (val) => {
    console.log(val);
}

function ShoppingCart({ cart, dispatch, user }) {
    const data = [
        { value: 0, label: '博士' },
        { value: 1, label: '本科' },
        { value: 2, label: '高中' },
    ];
    const { shopCart } = cart;
    const { custid } = user;
    const upHandler = (<Icon type='plus-circle'/>);
    const downHandler = (<Icon type='minus-circle'/>);
    function onChange(e) {
        console.log(`-------------------------${e}`)
    }
    function linkTo() {
        location.href='???#/orderConfirm/'+custid
    }
    return (
        <div style={{paddingBottom: '2rem'}}>
            {
                shopCart.map((item, i) => {
                    return (
                        <List key={i} renderHeader={() => <AgreeItem defaultChecked={true} data-seed="logId" onChange={e => console.log('checkbox', e)}>
            {item.shopName}
          </AgreeItem>}>
                            {item.goodList.map((item, i) => {
                                return (
                                    <CheckboxItem key={i} defaultChecked={true} onChange={() => onChange(item.goodId)}>
                                        <div style={{ width: '100%', height: '2rem' }}>
<img style={{width: '2rem',height: '2rem',float: 'left'}} src={item.goodImg} alt=""/>
    <span>{item.goodName}</span><br/>
    <span>商品参数</span><br/>
    <span style={{float: 'right'}}>200yuan</span><br/>
    {/*<span style={{float: 'right',marginTop: '0.3rem'}}><Icon type='plus-circle'/><Icon type='minus-circle'/></span>*/}
                                        </div>
                                    </CheckboxItem>
                                )
                            }

                            )}
                        </List>
                    )
                })
            }
            <div style={{ width: '100%',lineHeight:'1rem', height: '1rem', backgroundColor: 'white', position: 'fixed', bottom: '1rem', borderTop: '1px solid #e6e6e6' }}>
                <div style={{width: '2rem',height: '1rem',float: 'left'}}>
                    <AgreeItem defaultChecked={true} data-seed="logId" onChange={e => console.log('checkbox', e)}>
            全选
          </AgreeItem>
                </div>
                总计200元
                <div onClick={linkTo} style={{ width: '2rem', height: '1rem', textAlign: 'center', lineHeight: '1rem', color: 'white', backgroundColor: 'orange', float: 'right' }}>
                    去结算（）
                </div>

            </div>
        </div>
    )
}

ShoppingCart.PropTypes = {};

function mapStateToProps({ cart,user }) {
    return { cart,user }
}

export default connect(mapStateToProps)(ShoppingCart)