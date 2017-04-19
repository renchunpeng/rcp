import React, { Component, propTypes } from 'react'
import { connect } from 'dva'
import { SearchBar, Icon } from 'antd-mobile'
import styles from '../styles/GoodSearch.less'
import GoodItem from './GoodItem'

function GoodSearch({glist,cart,dispatch}) {
    const {list} = glist;
    return (
        <div>
            <SearchBar placeholder="搜索" onChange={(str)=>{
                dispatch({
            type: 'glist/search' ,
            payload: str
        })
                }} />
            <div className={styles.goodSearch}>
                <span style={{ margin: '0 20px 0 20px' }}>综合排序</span><span style={{ marginRight: '20px' }}>销量  <Icon type="down" /></span><span>价格<Icon type="down" /></span>
            </div>
            {
                list?list.map((item, i) => <GoodItem {...cart} todo={dispatch} key={i} order={i} />):''
            }
            <div className={styles.shoppingCart}>
                <Icon type="shopping-cart"/>
            </div>
        </div>
    )
}

GoodSearch.PropTYpes = {};

function mapStateToProps({glist,cart}) {
    return {glist,cart}
}

export default connect(mapStateToProps)(GoodSearch)