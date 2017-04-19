import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import ReactEcharts from 'echarts-for-react';
import _ from '../utils';

import eo from '../services/statistic';
import styles from './Statistic.less';

import { Row, Col } from 'antd';

require('echarts/map/js/province/guizhou');

const Statistic = ( {children, menu} ) => {
  return <div className="iris-layout-statistic">
    <h1>Statistic - {eo[menu.currentKey].title}</h1>
    <hr />
    {children}
  </div>;
};


// --------------------------------dashboard----------------------------------------------

const Dashboard = ({item,menu_test}) => {
  console.log(item);
  return <div className="iris-statistic-default">
    {
      '此处显示的是什么'+menu_test
    }
    {
      _.map(
        item.data, (i, k) =>
          <Row key={k} style={{marginBottom: 20, borderBottom: 'solid 1px #eee'}}>
            <h3 style={{color: '#aaa'}}>{k}</h3>
            <h1>{i}<em style={{fontSize: 16, color: '#999'}}>个</em></h1>
          </Row>
      )
    }
  </div>;
};


//这里传入的是数据模型
function mapStateToProps({menu,test}){
  return {
    item: eo[menu.currentKey],//将当前页面所对应的echarts数据对应起来
    menu: menu,
    menu_test: test.currentKey_test
  }
}

//此处显示的是图表信息
const Common = ({item}) => {
  return (
    <div>
      {
        _.map(
          item.data, (i, k) =>
            <Row key={k} style={{marginBottom: 20, borderBottom: 'solid 1px #eee'}}>
              <h3 style={{color: '#aaa'}}>{k}</h3>
              <h1>{i}<em style={{fontSize: 16, color: '#999'}}></em></h1>
            </Row>
        )
      }
      <Row>
        <ReactEcharts notMerge={true} option={item.options} style={{minHeight: 500}} />
      </Row>
    </div>
  );
};

Common.componentDidMount = () => {
  console.log('common mount...');
};

export const Statistics = {
  //同一个页面的俩个组件
  Dashboard: connect(mapStateToProps)(Dashboard),
  Common: connect(mapStateToProps)(Common)
};

export default connect(mapStateToProps)(Statistic);
