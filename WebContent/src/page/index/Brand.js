import { connect } from 'dva'
import React, { Component, PropTypes } from 'react'
import styles from '../styles/Brand.less'
import Number from '../../utils/Number'
import BrandIcon from '../../img/brand/brand.png'

function Brand({ brand, dispatch }) {
    const { brandList } = brand;
    function test() {
        /*dispatch({
            type: 'brand/test',
            payload: '1341243'
        })*/
        let good = Number(0.1).add(0.2);
        let bad = 0.1+0.2;
        console.log(`这个是good:${good},这个是bad:${bad}`);
    }

    return (
        <div className={styles.brand}>
            {
                brandList.map((item, i) => {
                    const {brandcompany,brandcreator,brandid,brandintroduction,brandlogo,brandname,brandpopularity} = item;
                    return (
                        <div key={i} className={styles.itemContainer} onClick={test}>
                            <div className={styles.title}>
                                <div className={styles.icon}>
                                    <img style={{width: '50%',height: '50%',marginTop: '25%'}} src={BrandIcon} alt=""/>
                                </div>
                                <span>{brandname}</span>
                            </div>
                            <div className={styles.description}>
                                <span>{`【${brandcompany}】${brandintroduction}`}</span>
                            </div>
                            <div className={styles.name}>
                                <span style={{marginRight: '0.2rem'}}>{brandcreator}</span>
                            </div>
                        </div>
                    )
                })
            }

        </div>
    )
}

Brand.PropTypes = {};

function mapStateToProps({ brand }) {
    return { brand }
}

export default connect(mapStateToProps)(Brand)