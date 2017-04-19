import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import styles from '../styles/Itergration.less'
import moment from 'moment'

function Itergration({ itergrations }) {
    const { integralbalance, actIntegralDetails } = itergrations;
    return (
        <div className={styles.itergration}>
            <div className={styles.title}>
                <div className={styles.more}>
                    <span style={{ float: 'right', marginRight: '0.3rem' }}>使用帮助</span>
                </div>
                <div className={styles.number}>
                    <span style={{ textShadow: '2px 2px 0px orange', fontSize: '1rem', marginLeft: '0.4rem' }}>{integralbalance}</span>个
                </div>
                <div className={styles.introduction}>
                    <span style={{ marginLeft: '0.4rem' }}>积分的作用！！！</span>
                </div>
            </div>

            <div className={styles.detail}>
                <div className={styles.header}>
                    <span>收支明细</span>
                </div>
                {
                    actIntegralDetails.map((item, i) => {
                        const { integralid, traceid, trandate, trandesc, tranintegral, trantype } = item;
                        return (
                            <div key={i} style={{ width: '96%', paddingTop: '0.2rem', height: '1.8rem', margin: '0.1rem 0.1rem 0.2rem 0.1rem', border: '1px solid #e6e6e6', borderRadius: '5px' }}>
                                <span style={{marginLeft: '0.2rem'}}>{trandesc}</span>
                                <br />
                                <span style={{marginLeft: '0.2rem',lineHeight: '0.6rem',fontSize: '0.3rem'}}>{moment(trandate).format('YYYY-MM-DD')}</span>
                                <br/>
                                <span style={{textAlign: 'center',fontSize: '0.5rem',display: 'block'}}>{tranintegral}</span>
                            </div>
                        )
                    })
                }
            </div>
        </div>
    )
}

Itergration.PropTypes = {};

function mapStateToProps({ itergrations }) {
    return { itergrations }
}

export default connect(mapStateToProps)(Itergration)