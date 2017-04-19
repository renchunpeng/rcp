import { ListView, Toast, Button, Card, Icon } from 'antd-mobile';
import fetch from 'isomorphic-fetch'
import React from 'react';
import ReactDOM from 'react-dom';

let ItemList = React.createClass({
    getInitialState() {
        this.data = []
        this.dataSource = new ListView.DataSource({
            rowHasChanged: (row1, row2) => row1.name !== row2.name
        })
        return {
            isLoading: false,
            pageNum: 1,
            dataSource: this.dataSource.cloneWithRows(this.data),
            fetchDone: false
        }
    },

    componentWillMount() {
        this.getData()
    },

    getData() {
        if (this.state.isLoading || this.state.fetchDone) {
            return
        }

        this.setState({ isLoading: true })
        fetch('/items' + '?page=' + this.state.pageNum).then(response => response.json()).then(items => {
            this.setState({ isLoading: false })
            if (items.length) {
                //存在数据则继续添加
                this.data = this.data.concat(items)
                this.setState({
                    dataSource: this.dataSource.cloneWithRows(this.data),
                    pageNum: ++this.state.pageNum
                })
            } else {
                this.setState({ fetchDone: true })
                Toast.info('没有更多的记录', 1)
            }
        })
    },

    renderListViewRow(rowData) {
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
    },

    renderListViewSeparator(sectionID, rowID) {
        return (
            <div key={`separator-${sectionID}-${rowID}`} style={{
                backgroundColor: '#F5F5F9',
                height: 8,
                borderTop: '1px solid #ECECED',
                borderBottom: '1px solid #ECECED',
            }} />
        )
    },

    render() {
        return (
            <div style={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                <ListView
                    dataSource={this.state.dataSource}
                    renderRow={this.renderListViewRow}
                    renderSeparator={this.renderListViewSeparator}
                    onEndReached={this.getData}
                    style={{
                        height: document.body.clientHeight - 190,
                        overflow: 'auto',
                    }}
                    initialListSize={0}
                    pageSize={10}
                />
            </div>
        );
    },
});

export default ItemList
