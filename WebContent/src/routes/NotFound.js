import React from 'react';
import { Button } from 'antd-mobile';
import styles from './NotFound.less';

const NotFound = () =>
  <div className={styles.normal}>
    <div className={styles.container}>
      <h1 className={styles.title}>404</h1>
      <p className={styles.desc}>未找到该页面</p>
      <a href="#/onlineMarket/market"><Button type="primary" style={{ marginTop: 5 }}>返回首页</Button></a>
    </div>
  </div>;


export default NotFound;
