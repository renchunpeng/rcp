import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import styles from '../index.less';

function mapStateToProps( menu ) {
  return {
    menus: menu.items,
    currentKey: menu.currentKey
  };
}
//此处创建的是一个无状态的组件，无状态的组件只能接受props
function IndexPage() {
  return (
    <div className={styles.normal}>
      <h1 className={styles.title}>show goodMessage!</h1>
    </div>
  );
}

/*class IndexPage extends Component{
  static defaultProps = {
    title: '测试组件'
  };

  render(){
    return (
      <div className={styles.normal}>
        <h1 className={styles.title}>show goodMessage!</h1>
        <h1>
          {this.props.title}
        </h1>
      </div>
    )
  }

  componentDidMount() {
    console.log('测试组件加载完成！这个是内部的');
  }
}*/
//无状态的组件还是可以对props进行类型校验的
IndexPage.propTypes = {
};

IndexPage.componentDidMount=()=>{
  console.log('测试组件加载完成！');
};


//组件再连接到数据模型之后，由props将数据返回。
export default connect(mapStateToProps)(IndexPage);
