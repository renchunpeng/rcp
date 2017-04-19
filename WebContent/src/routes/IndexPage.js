import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import styles from '../index.less';

function IndexPage() {
  return (
    <div className={styles.normal}>
      <h1 className={styles.title}>请点击左侧列表菜单查看统计报表</h1>
    </div>
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
