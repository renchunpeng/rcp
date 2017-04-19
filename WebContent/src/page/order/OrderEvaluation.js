import React, { Component, propTypes } from 'react'
import { connect } from 'dva'
import Rater from 'react-rater'
import { Card, Icon, List, TextareaItem } from 'antd-mobile'
import Evalution from '../../img/evalution/evalution.png'
import BottomStyle from '../styles/BottomButton.less'

function OrderEvalution({ orderDet, orderEvaluationModel, dispatch }) {
  let { goodsList, prdpacking, deliveryspeed, deliveryservice,orderid } = orderEvaluationModel;
  function serviceRater(type, v) {
    dispatch({
      type: 'orderEvaluationModel/serviceRater',
      payload: {
        type: type,
        value: v
      }
    })
  }
  function submit() {
    dispatch({
        type: 'orderEvaluationModel/submit',
        payload: {
          goodsList: goodsList,
          prdpacking: prdpacking,
          deliveryspeed: deliveryspeed,
          deliveryservice: deliveryservice,
          orderid: orderid
        }
    })
  }
  return (
    <div style={{ paddingBottom: '1rem' }}>
      {
        goodsList.map((item, i) => {
          const { prdid, prdsatisfaction, prdcomment } = item;
          function textChange(v) {
            let _goodsList = goodsList;
            _goodsList[i].prdcomment = v;
            dispatch({
              type: 'orderEvaluationModel/textChange',
              payload: _goodsList
            })
          }
          function raterChange(v) {
            let _goodsList = goodsList;
            _goodsList[i].prdsatisfaction = v;
            dispatch({
              type: 'orderEvaluationModel/raterChange',
              payload: _goodsList
            })
          }
          return (
            <div key={i}>
              <Card full>
                <Card.Body>
                  <div>
                    <img style={{ height: '1.5rem', float: 'left' }} src="http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg" alt="" />
                    <div style={{ width: '70%', height: '1.5rem', float: 'right' }}><span style={{ lineHeight: '0.7rem' }}>{item.prdname}</span><br /><Rater onRate={(obj) => { raterChange(obj.rating) }} total={5} rating={prdsatisfaction} /></div>
                  </div>
                </Card.Body>
              </Card>
              <List>
                <TextareaItem
                  onChange={(v) => { textChange(v) }}
                  rows={3}
                  placeholder={prdcomment}
                />
              </List>
            </div>
          )
        })
      }
      <Card full>
        <Card.Header
          title="物流服务评价"
          thumb={Evalution}
          extra={<span>满意请给五分哦</span>}
        />
        <Card.Body>
          <div>
            <div style={{ width: '100%', height: '0.8rem', lineHeight: '0.8rem', overflow: 'hidden' }}>
              <span>商品包装</span><Rater onRate={(obj) => { serviceRater('prdpacking', obj.rating) }} total={5} rating={prdpacking} />
            </div>
            <div style={{ width: '100%', height: '0.8rem', lineHeight: '0.8rem', overflow: 'hidden' }}>
              <span>物流服务</span><Rater onRate={(obj) => { serviceRater('deliveryspeed', obj.rating) }} total={5} rating={deliveryspeed} />
            </div>
            <div style={{ width: '100%', height: '0.8rem', lineHeight: '0.8rem', overflow: 'hidden' }}>
              <span>服务态度</span><Rater onRate={(obj) => { serviceRater('deliveryservice', obj.rating) }} total={5} rating={deliveryservice} />
            </div>
          </div>
        </Card.Body>
      </Card>
      <div className={BottomStyle.buttomfixed} onClick={submit} style={{textAlign: 'center',backgroundColor: '#1dbf63',color: 'white'}}>
               提交支付
            </div>
    </div>
  )
}

OrderEvalution.PropTypes = {};

function mapStateToProps({ orderEvaluationModel, orderDet }) {
  return { orderEvaluationModel, orderDet }
}

export default connect(mapStateToProps)(OrderEvalution)