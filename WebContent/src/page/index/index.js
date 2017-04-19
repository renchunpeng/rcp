import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import { Carousel, SearchBar, Modal, Icon, NavBar } from 'antd-mobile'
import styles from '../styles/MarketIndex.less'

function Index({ user, dispatch,index }) {

  const { custid } = user;
  const {visible} = index;
  function onClose() {
    dispatch({
      type: 'index/changeVisible'
    })
  }
  function showModal(e) {
    // 现象：如果弹出的弹框上的 x 按钮的位置、和手指点击 button 时所在的位置「重叠」起来，
    // 会触发 x 按钮的点击事件而导致关闭弹框 (注：弹框上的取消/确定等按钮遇到同样情况也会如此)
    e.preventDefault(); // 修复 Android 上点击穿透
    dispatch({
      type: 'index/changeVisible'
    })
  }
  return (
    <div>
      <Carousel
        className="my-carousel" autoplay={true} infinite
        beforeChange={(from, to) => console.log(`slide from ${from} to ${to}`)}
        afterChange={(index) => console.log('slide to', index)}
      >
        {['AiyWuByWklrrUDlFignR', 'TekJlZRVCjLFexlOCuWn', 'AiyWuByWklrrUDlFignR'].map((ii) => (
          <a style={{ display: 'inline-block', width: '100%', margin: '0', padding: '0' }} href="#" key={ii}><img
            style={{ width: '100%', verticalAlign: 'top' }}
            src={`https://zos.alipayobjects.com/rmsportal/${ii}.png`} /></a>
        ))}
      </Carousel>
      <div style={{ width: '100%', height: '1rem', lineHeight: '1rem' }}>
        <span style={{ marginLeft: '20px', float: 'left' }}>趣品牌</span>
        <span style={{ marginRight: '20px', float: 'right' }}>今日新入驻品牌<Icon type="arrow-right" /></span>

      </div>
      <div className={styles.moreGood}>
        <div className={styles.title}>
          好<br />货<br />等<br />你<br />来
        </div>
        <div style={{ width: document.body.clientWidth, height: '3rem', backgroundColor: 'red', overflowX: 'scroll' }}>
          <div className={styles.itemContainer}>
            <div className={styles.item}></div>
            <div className={styles.item}></div>
            <div className={styles.item}></div>
            <div className={styles.item}></div>
            <div className={styles.item}></div>
            <div className={styles.item}></div>
          </div>
        </div>
      </div>
      <div className={styles.goodList}>

      </div>
      <Modal
        title="这是 title"
        transparent
        maskClosable={false}
        visible={visible}
        onClose={onClose}
        footer={[{ text: '确定', onPress: () => { console.log('ok'); onClose(); } }]}
      >
        这是内容...<br />
        这是内容...<br />
      </Modal>
    </div>
  )
}

Index.PropTypes = {};

function mapStateToProps({ user,index }) {
  return { user,index }
}

export default connect(mapStateToProps)(Index)

