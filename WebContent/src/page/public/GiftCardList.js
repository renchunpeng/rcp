import { ListView, Toast, Button, Card, Icon, WhiteSpace } from 'antd-mobile';
import fetch from 'isomorphic-fetch';
import { Link } from 'react-router';
import React from 'react';
import ReactDOM from 'react-dom';
import styles from '../styles/GoodList.less';
import GiftCard from '../../img/vipcard.png';

let GiftCardList = React.createClass({
  getInitialState() {
    this.data = [];
    this.dataSource = new ListView.DataSource({
      rowHasChanged: (row1, row2) => row1.name !== row2.name
    });

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
    //通过外部传入的参数来决定显示的类型
    fetch('/lflweb/order/CardList/' + custid + '/' + this.state.pageNum + '/' + this.props.type).then(response => response.json()).then(items => {
      this.setState({ isLoading: false })
      if (items.data.length) {
        //存在数据则继续添加
        let list = [];
        for(let i=0; i<items.data.length; i++) {
          list[i] = items.data[i];
          //向数组张添加一个序号用于item定位
          list[i]["index"] = i+1;
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
        this.props.dispatch({
            type: 'giftCard/getAddress',
            payload: rowData
        });
        location.href = `???#/giftCardCon/${rowData.memid}`
  },

  renderListViewRow(rowData) {
    return (
      <div onClick={() => {this.linkTo(rowData)}}>
        <Card full>
          <Card.Header
            title={<span>{rowData.memid}</span>}
            thumb={GiftCard}
          />
          <Card.Body>
            <div>
              <div style={{width: '100%',height: '100%',float: 'left'}}>有效期：{rowData.validdate}</div>
              <div style={{width: '100%',height: '100%',float: 'left',marginTop:'10px', marginBottom:'10px'}}>剩余次数：{rowData.less}</div>
              <hr/>
            </div>
          </Card.Body>
        </Card>
        <WhiteSpace/>
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
                        overflow: 'auto'
                    }}
          initialListSize={0}
          pageSize={10}
        />
      </div>
    );
  }
});

export default GiftCardList
