import { ListView, Toast, Button, Card, Icon } from 'antd-mobile'
import fetch from 'isomorphic-fetch'
import { Link } from 'react-router'
import React from 'react'
import ReactDOM from 'react-dom'
import styles from '../styles/GoodList.less'
import InputNumber from '../../components/InputNumber'
import Lazyload from 'react-lazy-load'

const unit = {
    '00': '斤',
    '01': '公斤'
}

function MyBody(props) {
    return (
        <div style={{ display: 'flex' }}>
            {props.children}
        </div>
    );
}

let GoodList = React.createClass({
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

        Toast.loading('加载中...',10)

        //通过外部传入的参数来决定显示的类型
        //fetch('/lflweb/prd/shprdbase/'+this.props.listType+'?page='+this.state.pageNum)
        //fetch('/lflweb/prd/shprdbase/'+this.props.listType+'/'+this.state.pageNum)
        fetch('/lflweb/'+this.props.baseUrl+'/'+this.props.listType+'/'+this.state.pageNum).then(response => response.json()).then(items => {
            this.setState({ isLoading: false })
            //一个全局的销毁toast方法
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

    renderListViewRow(rowData) {
        let goodsList = this.props.goodsList;
        let initValue = 0;
        //为了使数据格式统一,如果是套餐也先将数据格式整理为商品的数据格式

        let _rowData = {};
        if(rowData.prdkind == '02') {
            _rowData['brandid'] = rowData.brandid;
            _rowData['heat'] = rowData.heat;
            _rowData['keyword'] = rowData.keyword;
            _rowData['prdid'] = rowData.packageid;
            _rowData['prdbrief'] = rowData.packageintro;
            _rowData['prdname'] = rowData.packagename;
            _rowData['prdprice'] = rowData.packageprice;
            _rowData['prdtax'] = rowData.packagetax;
            _rowData['prdtype'] = rowData.packagetype;
            _rowData['prdunit'] = rowData.packageunit;
            _rowData['prdImage'] = rowData.prdPackageimg;
            _rowData['prdkind'] = rowData.prdkind;
            _rowData['shopid'] = rowData.shopid;
            _rowData['shopname'] = rowData.shopname;
            _rowData['prdunitname'] = unit[rowData.packageunit];
            _rowData['imageurl'] = rowData.prdPackageimg.imageurl;
        }
        else {
            _rowData = rowData;
            _rowData['imageurl'] = rowData.prdImage.imageurl;
            _rowData['prdunitname'] = unit[rowData.packageunit];
        }
        
        //读取购物车中的数据，如果购物车中存在则显示在列表当中
        for (let i=0;i<goodsList.length;i++) {
            if (goodsList[i].prdid == _rowData.prdid){
                initValue = goodsList[i].prdcount
            }
        }
        let floatRight = rowData.index % 2 == 0;
        let _width = (document.body.clientWidth - 10) / 2;
        return (
            <div style={{ width: _width, backgroundColor: 'white', height: 300, marginBottom: '10px', height: 'auto', float: floatRight ? 'right' : 'left' }}>
                <div style={{ width: _width - 20, margin: '10px auto', height: 'auto' }}>
                    <Link to={'/goodDetail/' + _rowData.prdid + '/' + _rowData.prdkind}>
                    <Lazyload height={_width - 20} offsetVertical={0}>
                        <img style={{ width: '100%', height: _width - 20 }} src={_rowData.imageurl} alt="" />
                        </Lazyload>
                    </Link>
                    <div className={styles.goodListContainer}>
                        <div className={styles.goodName}>{`【${_rowData.shopname}】 ${_rowData.prdname}`}</div>
                        {/*<div className={styles.description}>{_rowData.prdbrief}</div>*/}
                        <div className={styles.action}>
                            <span>¥{_rowData.prdprice.toFixed(2)}</span>
                            <InputNumber style={{float: 'right',marginTop: '0.2rem'}} goodDetail={_rowData} initValue={initValue} callBack={this.changeNumber} ></InputNumber>
                        </div>
                    </div>
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

    changeNumber(value,goodDetail) {
        //console.log('-------'+value+'-----------'+goodDetail)
        //需要将完整的商品数据提交到redux，在页面进行加载时，遍历购物车中的数据，将数据进行填充
        this.props.dispatch({
            type: 'orderConfirmModel/changeGood',
            payload: {
                number: value,//该商品的数量
                goodDetail: goodDetail,//该商品的详细信息
                goodsList: this.props.goodsList//目前购物车中的数据
            }
        })
    },

    render() {
        return (
            <div style={{ height: '100%', display: 'flex', flexDirection: 'row', flexWrap: 'wrap', backgroundColor: '#e6e6e6' }}>
                <ListView
                    dataSource={this.state.dataSource}
                    renderRow={this.renderListViewRow}
                    renderBodyComponent={() => <MyBody />}
                    onEndReached={this.getData}
                    style={{
                        height: document.body.clientHeight,//设置list包裹容器的高度
                        overflow: 'auto',
                    }}
                    initialListSize={0}
                    pageSize={10}
                />
            </div>
        );
    },
});

export default GoodList
