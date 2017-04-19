/**
 * Created by semantic on 2017/1/23.
 */
import React, { Component } from 'react';
import $ from 'jquery'
//必须要将React进行引入的
import { connect } from 'dva';
import request from '../utils/request';
import fetch from 'dva/fetch';

class Test2 extends Component{
  constructor(props){
    super(props);
    this.state={
      content: 'show xujingchen'
    }
  }

  render(){
    return(
      <div>
        {this.state.content}
      </div>
    )
  }

  componentDidMount(){
    //测试数据获取
    fetch('../src/demoJson/test1.json')
      .then(function(response) {
        return response.json()
      }).then(function(json) {
      console.log('parsed json', json)
    }).catch(function(ex) {
      console.log('parsing failed', ex)
    });
  }
}
//可以不用连接到数据模型
export default Test2
