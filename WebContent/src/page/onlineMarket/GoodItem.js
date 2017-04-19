/**
 * this page is created by jcxu
 * description for goodItem
 * component ES6 with state
*/

import React, { Component, PropTypes } from 'react'
import styles from '../styles/GoodItem.less'
import { Card, Icon } from 'antd-mobile'
import { Link } from 'react-router'

class GoodItem extends Component {
    constructor(props) {
        super(props);
        this.addToCart = this.addToCart.bind(this);
        this.state = {
            clientWith: ''
        }
    }

    render() {
        return (
            
                <div className={styles.goodItemContainer} style={(this.props.order + 1) % 2 == 0 ? { float: 'right', width: this.state.clientWith } : { width: this.state.clientWith }}>
                    <div style={{ width: this.state.clientWith - 20, margin: '10px auto', height: 'auto' }}>
                        <Link to='/goodDetail'>
                        <img src="http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg" alt="" />
                        </Link>
                        <Card full>
                            <Card.Body>
                                <div><span style={{ fontSize: '0.4rem' }}>商品名称</span><br /><span>商品介绍内容</span></div>
                            </Card.Body>
                            <Card.Footer content="100元" extra={<Icon onClick={this.addToCart} style={{ fontSize: '0.5rem' }} type="plus-circle" />} />
                        </Card>
                    </div>
                </div>
        )
    }

    addToCart() {
        //向购物车中添加数据
        this.props.todo({
            type: 'cart/addToCart',
            payload: {
                shopChart: '123'
            }
        })
    }

    componentDidMount() {
        this.setState({
            clientWith: (document.body.clientWidth - 15) / 2
        })
    }
}

export default GoodItem