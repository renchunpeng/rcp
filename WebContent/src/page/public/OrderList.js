import { ListView, Toast, Button, Card, Icon, WhiteSpace } from 'antd-mobile'
import fetch from 'isomorphic-fetch'
import { Link } from 'react-router'
import React from 'react'
import ReactDOM from 'react-dom'
import styles from '../styles/GoodList.less'
import styles1 from '../styles/OrderList.less'
import {formatDeliveryTime} from '../../utils/DateFormat'
import OrderStatus from '../order/OrderStatus'

let OrderList = React.createClass({
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
        Toast.loading('加载中...',10)
        this.setState({ isLoading: true })
        //通过外部传入的参数来决定显示的类型
        //测试使用的url:fetch('/lflweb/ord/shordbase/' + custid + '/' + this.props.type + '/?page=' + this.state.pageNum)
        //正式环境使用的url:fetch('/lflweb/ord/shordbase/' + custid + '/' + this.state.pageNum + '/' + this.props.type)
        fetch('/lflweb/ord/shordbase/' + custid + '/' + this.state.pageNum + '/' + this.props.type).then(response => response.json()).then(items => {
            this.setState({ isLoading: false })
            Toast.hide()
            if (items.data.length) {
                //存在数据则继续添加
                let list = [];
                for (let i = 0; i < items.data.length; i++) {
                    list[i] = items.data[i];
                    //向数组中添加一个序号用于item定位
                    list[i]["index"] = i + 1;
                }
                this.data = this.data.concat(items.data)
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

    linkTo(rowData) {
        location.href = `#/orderDetail/${rowData.orderid}`
    },

    renderListViewRow(rowData) {
        const {orderstatus} = rowData;
        
        return (
            <div className={styles1.orderItem} style={{height: '3rem'}} >
                <div className={styles1.title}>
                    <span className={styles1.left}>{rowData.orderid}</span> <span className={styles1.right}>{OrderStatus[orderstatus].status}</span>
                </div>
                <div className={styles1.content} style={{border: 'none',paddingTop: '0.2rem'}} onClick={()=>{this.linkTo(rowData)}}>
                    <span className={styles1.container}>
                        <span>收货人:{rowData.ctmAddress.addcontact}</span>
                        <br />
                        <span>手机号:{rowData.ctmAddress.addmobile}</span>
                        <br />
                        <span>订单时间:{formatDeliveryTime(rowData.createdate)}</span>
                        <br />
                        <span>{`${rowData.ctmAddress.addprov}  ${rowData.ctmAddress.addcity}  ${rowData.ctmAddress.addcounty}  ${rowData.ctmAddress.adddetail}`}</span>
                    </span>
                </div>
            </div>
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
            <div style={{ height: '100%', display: 'flex', flexDirection: 'row', flexWrap: 'wrap' }}>
                <ListView
                    dataSource={this.state.dataSource}
                    renderRow={this.renderListViewRow}
                    renderBodyComponent={() => <MyBody />}
                    onEndReached={this.getData}
                    style={{
                        height: document.body.clientHeight,
                        overflow: 'auto',
                    }}
                    initialListSize={0}
                    pageSize={10}
                />
            </div>
        );
    },
});

export default OrderList