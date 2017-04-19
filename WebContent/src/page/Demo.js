/**
 * Created by semantic on 2017/1/24.
 */
import React, {Component, PropTypes} from 'react';
import {connect} from 'dva';
import {Button} from 'antd'

/*function Demo({param2}) {
 return (
 <div>
 this is a demo!{'此处开始显示：'+param2}
 </div>
 )
 }*/

function Demo({test1, dispatch}) {
  const {param1, param2} = test1;
  const demo = {
    handleClick () {
      dispatch({
        type: 'test1/handleClick',
        payload: {
          param2: 'ha ha wo dianji le anniu1'
        }
      })
    }
  };
  function test(){
    console.log('======>按钮已经被点击了！');
    dispatch ({
      type: 'test1/submit',
      payload: {
        userName: 'jingchengxu',
        passWord: '134141341432141',
        param2: param2
      }
    })
  }
  return (
    <div>
      <div onClick={demo.handleClick}>
        this is a demo!{'此处开始显示：' + param2}
      </div>
      <Button type="primary" onClick={test}>ceshi</Button>
    </div>

  )
}

Demo.propTypes = {};

function mapStateToProps({test1}) {
  console.log('======>传入的test1数据模型为：' + test1.param1 + '---' + test1.param2);
  return {test1};
}

export default connect(mapStateToProps)(Demo);


