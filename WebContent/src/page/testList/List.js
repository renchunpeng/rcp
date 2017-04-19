/**
 * Created by semantic on 2017/2/3.
 */
import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import { ListView } from 'antd-mobile';

function List({ list }) {
  console.log(`======>list接口中获取到的数据为：${JSON.stringify(list)}`);
  return (
    <div>
      {
        list.list.map((item, i)=>{
          return <div key={i}>
            {item.title}
          </div>
        })
      }
    </div>
  )
}

List.PropTypes = {};

function mapStateToProps({list}) {
  return {list};
}

export default connect(mapStateToProps)(List);

