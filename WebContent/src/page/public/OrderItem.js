import React, { Component, PropTypes } from 'react'
import { Card } from 'antd-mobile'

class OrderItem extends Component {
    constructor(props) {
        super(props);
        this.state = {

        }
    }

    render() {
        return (
            <Card full>
                <Card.Header
                    title={<span>{rowData.name}</span>}
                    thumb={false}
                    extra={<span style={{ color: 'orange' }}>配送中</span>}
                />
                <Card.Body>
                    <div style={{ width: '100%', height: '100px' }}>
                        <div style={{ width: '80%', float: 'left' }}>
                            <img style={{ width: '1.6rem', float: 'left', marginRight: '10px' }} src="https://zos.alipayobjects.com/rmsportal/dKbkpPXKfvZzWCM.png" alt="" />
                            <span style={{ color: 'black', fontSize: '0.4rem' }}>gongli</span>
                            <br></br>
                            <span>cehishu</span>
                        </div>
                        <div style={{ width: '20%', float: 'left' }}>
                            <div style={{ width: '100%', height: '0.8rem', textAlign: 'right' }}>￥1234</div>
                            <div style={{ width: '100%', height: '0.8rem', textAlign: 'right' }}>X56</div>
                        </div>
                    </div>
                    <div style={{ width: '100%', height: '50px', lineHeight: '50px' }}>
                        <span style={{ float: 'left' }}>2017-02-17 12:00</span><span style={{ float: 'right' }}>合计：67</span>
                    </div>
                </Card.Body>
                <Card.Footer content="" extra={<Button size="small" inline>确认收货</Button>} />
            </Card>
        )
    }
}

export default OrderItem