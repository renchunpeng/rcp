import { ListView, Button, Card, Icon } from 'antd-mobile'
import { Link } from 'react-router'
import React from 'react'
//用于展示的数据
const data = [
  {
    img: 'https://zos.alipayobjects.com/rmsportal/dKbkpPXKfvZzWCM.png',
    title: '相约酒店',
    des: '不是所有的兼职汪都需要风吹日晒',
  },
  {
    img: 'https://zos.alipayobjects.com/rmsportal/XmwCzSeJiqpkuMB.png',
    title: '麦当劳邀您过周末',
    des: '不是所有的兼职汪都需要风吹日晒',
  },
  {
    img: 'https://zos.alipayobjects.com/rmsportal/hfVtzEhPzTUewPm.png',
    title: '食惠周',
    des: '不是所有的兼职汪都需要风吹日晒',
  }
];

//初始化每次加载展示的页数
const NUM_SECTIONS = 1;
//初始化每页展示的行数
const NUM_ROWS_PER_SECTION = 3;
//初始化页面序号
let pageIndex = 0;

function MyBody(props) {
  return (
    <div className="jingchenxu">
      <span>you can custom body wrap element</span>
      {props.children}
    </div>
  );
}

const OrderListData = React.createClass({
  getInitialState() {
    //获取每一页的数据
    const getSectionData = (dataBlob, sectionID) => dataBlob[sectionID];
    //获取每一行的数据
    const getRowData = (dataBlob, sectionID, rowID) => dataBlob[rowID];

    const dataSource = new ListView.DataSource({
      getRowData,
      getSectionHeaderData: getSectionData,
      rowHasChanged: (row1, row2) => row1 !== row2,
      sectionHeaderHasChanged: (s1, s2) => s1 !== s2,
    });

    this.dataBlob = {};
    this.sectionIDs = [];
    this.rowIDs = [];
    //初始化一组标记在html中的数据压入数组
    this.genData = (pIndex = 0) => {
      for (let i = 0; i < NUM_SECTIONS; i++) {
        const ii = (pIndex * NUM_SECTIONS) + i;
        const sectionName = `Section ${ii}`;
        this.sectionIDs.push(sectionName);
        this.dataBlob[sectionName] = sectionName;
        this.rowIDs[ii] = [];

        for (let jj = 0; jj < NUM_ROWS_PER_SECTION; jj++) {
          const rowName = `S${ii}, R${jj}`;
          this.rowIDs[ii].push(rowName);
          this.dataBlob[rowName] = rowName;
        }
      }
      // new object ref
      this.sectionIDs = [].concat(this.sectionIDs);
      this.rowIDs = [].concat(this.rowIDs);
    };
    //调用初始化的方法
    this.genData();
    return {
      dataSource: dataSource.cloneWithRowsAndSections(this.dataBlob, this.sectionIDs, this.rowIDs),
      isLoading: false
    };
  },

  componentDidMount() {
    // 组建加载完成后可以滑动到指定的位置
    // this.refs.lv.refs.listview.scrollTo(0, 200);
  },

  onEndReached(event) {
    // 加载新数据
    // 当一组数据加载完成后，则会开始加载新的数据
    // 通过ajax请求，如果做了分页的话，就要判断有没有更多的数据了！
    console.log('reach end', event);
    this.setState({ isLoading: true });
    setTimeout(() => {
      //继续增加页数
      console.log(`==========T_Y=======输出即将加载的页数${pageIndex}`);
      //将新的一组标记在html中的数据压入数组
      this.genData(++pageIndex);
      this.setState({
        dataSource: this.state.dataSource.cloneWithRowsAndSections(this.dataBlob, this.sectionIDs, this.rowIDs),
        isLoading: false,
      });
    }, 1000);
  },

  render() {
    //const data = this.props.data;
    console.log(this.props.data);
    //序号的最大长度
    let index = data.length - 1;
    //每行与每行之间的间隙的样式
    const separator = (sectionID, rowID) => (
      <div key={`${sectionID}-${rowID}`} style={{
        backgroundColor: '#F5F5F9',
        height: 8,
        borderTop: '1px solid #ECECED',
        borderBottom: '1px solid #ECECED',
      }}
      />
    );
    //每行的组件样式及结构
    const row = (rowData, sectionID, rowID) => {
      //如果序号小于0后，则重置序号
      if (index < 0) {
        index = data.length - 1;
      }
      //将data数组中的对象去除赋给obj后序号减1
      const obj = data[index--];
      //将obj中的数据填充到返回的组件当中
      return (
              <div key={rowID}>
                  {//页与页之间插入的空格
                      index==1?<div style={{
        backgroundColor: '#F5F5F9',
        height: 8,
        borderTop: '1px solid #ECECED',
        borderBottom: '1px solid #ECECED',
      }}></div>:''
                  }

<Link to='/orderDetail'>
                  <Card full>
                  <Card.Header
                    title={<span>{obj.title}</span>}
                    thumb={false}
                    extra={<span style={{color: 'orange'}}>配送中</span>}
                  />
                  <Card.Body>
                    <div style={{width: '100%',height: '100px'}}>
                        <div style={{width: '80%',float: 'left'}}>
                            <img style={{width: '1.6rem',float: 'left',marginRight: '10px'}} src={obj.img} alt=""/>
                        <span style={{color: 'black',fontSize: '0.4rem'}}>gongli</span>
                        <br></br>
                        <span>cehishu</span>
                        </div>
                        <div style={{width: '20%',float: 'left'}}>
                            <div style={{width: '100%',height: '0.8rem',textAlign: 'right'}}>￥1234</div>
                            <div style={{width: '100%',height: '0.8rem',textAlign: 'right'}}>X56</div>
                        </div>
                    </div>
                    <div style={{width: '100%',height: '50px',lineHeight: '50px'}}>
                        <span style={{float: 'left'}}>2017-02-17 12:00</span><span style={{float: 'right'}}>合计：67</span>
                    </div>
                  </Card.Body>
                  <Card.Footer content="" extra={<Button size="small" inline>确认收货</Button>}/>
                </Card>
</Link>
              </div>
      );
    };
    //加载listview组件
    return (<div style={{ margin: '0 auto', width: '100%' }}>
      <ListView ref="lv"
        dataSource={this.state.dataSource}/*获取数据源*/
        renderFooter={() => <div style={{ padding: 30, textAlign: 'center' }}>
          {this.state.isLoading ? '加载中...' : '加载完毕'}
        </div>}/*列表底部*/
        renderBodyComponent={() => <MyBody />}/*自定义的body包裹组件*/
        renderRow={row}
        renderSeparator={separator}/*用于隔开每行之间 */
        className="fortest"
        style={{/*外部滑动框的样式 */
          height: document.body.clientHeight * 7 / 8,
          overflow: 'auto',
          border: '1px solid #ddd',
          margin: '0 0 0.1rem 0',
        }}
        pageSize={10}//每页显示数据个数
        scrollRenderAheadDistance={500}
        scrollEventThrottle={20}
        onScroll={() => { console.log('scroll'); }}
        onEndReached={this.onEndReached}
        onEndReachedThreshold={10}
      />
    </div>);
  },
});

export default OrderListData
