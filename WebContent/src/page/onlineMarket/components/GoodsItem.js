import React, { Component, PropTypes } from 'react'
import { Icon } from 'antd-mobile'

const demo1 = '../../lflweb/src/img/onlineMarket/demo1.png';

function getStyles() {
    return {
        itemContainer: {
            width: '100%',
            height: '5rem',
            overflow: 'hidden'
        },
        extra: {
            width: '100%',
            height: '2rem',
            textAlign: 'left',
            paddingLeft: '0.1rem'
        },
        img: {
            width: '100%',
            height: '3rem',
            marginTop: '-1rem'
        },
        name: {
            width: '100%',
            height: '0.4rem',
            lineHeight: '0.4rem'
        },
        description: {
            width: '100%',
            height: '0.4rem',
            lineHeight: '0.4rem',
            color: '#6e6e6e',
        },
        action: {
            width: '100%',
            height: '0.8rem',
            lineHeight: '0.8rem',
            fontSize: '0.4rem'
        },
        cart: {
            width: '0.8rem',
            height: '0.8rem',
            display: 'block',
            float: 'right',
            textAlign: 'center',
            lineHeight: '0.8rem',
            borderRadius: '0.4rem',
            backgroundColor: '#6e6e6e',
            color: 'white',
            bottom: '0.2rem',
            position: 'relative',
            right: '0.2rem'
        },
        remark: {
            position: 'relative',
            backgroundColor: 'orange',
            width: '1.5rem',
            height: '0.6rem',
            lineHeight: '0.6rem',
            padding: '0 0.2rem',
            borderRadius: '0.3rem',
            color: 'white',
            margin: '0.2rem'
        }
    }
}

class GoodItem extends Component {
    constructor(props) {
        super(props);
        this.state = {

        };
    }

    render() {
        const styles = getStyles();
        return (
            <div style={styles.itemContainer}>
                <div style={styles.remark}>
                    限时抢购
                </div>
                <img style={styles.img} src={demo1} alt="" />
                <div style={styles.extra}>
                    <div style={styles.name}>
                        绿富隆有机栽培生菜
                    </div>
                    <div style={styles.description}>
                        无农药化肥
                    </div>
                    <div style={styles.description}>
                        600g
                    </div>
                    <div style={styles.action}>
                        <span>¥<span style={{ color: 'orange' }}>12.82</span></span>
                        <span style={{ fontSize: '0.3rem', color: '#6e6e6e' }}><s>¥20.00</s></span>
                        <span style={styles.cart}><Icon type="shopping-cart" /></span>
                    </div>
                </div>


            </div>
        )
    }
}

export default GoodItem
