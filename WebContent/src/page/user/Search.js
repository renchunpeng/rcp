import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import { SearchBar } from 'antd-mobile'

function getStyles() {
    return {
        container: {
            width: '96%',
            margin: '2%',
            height: '2rem',
            border: '1px solid #e6e6e6',
            backgroundColor: 'white',
            display: 'block',
            lineHeight: '0.6rem',
            borderRadius: '5px',
            paddingTop: '0.2rem',
            color: 'black'
        },
        empty: {
            width: '96%',
        },
        img: {
            width: '1.6rem',
            display: 'block',
            float: 'left',
            margin: '0 0.2rem 0 0.2rem',
            height: '1.6rem'
        },
        price: {
            position: 'relative',
            bottom: '-0.6rem',
            right: '-2rem'
        },
        empty: {
            width: '96%',
            margin: '2%',
            height: '2rem',
            border: '1px solid #e6e6e6',
            backgroundColor: 'white',
            lineHeight: '2rem',
            textAlign: 'center'
        }
    }
}

function Search({ search, dispatch }) {
    const { resultList, suggestion, msg } = search;
    const styles = getStyles();

    let searcher = '';

    function searching(v) {
        clearTimeout(searcher);
        searcher = setTimeout(function() {
            dispatch({
            type: 'search/changeKey',
            payload: v || '12'
        })
        },800);
    }
    return (
        <div>
            <SearchBar
                onChange={(v) => { searching(v) }}
                autoFocus = {true}
                onCancel = {()=>{history.go(-1)}}
                placeholder="搜索" />
            {
                resultList.length == 0?<div style={styles.empty}>{msg}
                    </div>:resultList.map((item, i) => {
                    const {shopname,prdname,prdImage,prdprice,prdid,prdkind} = item;
                    const {imageurl} = prdImage;
                    return (
                        <a href={`???#/goodDetail/${prdid}/${prdkind}`} key={i} style={styles.container}>
                            <img style={styles.img} src={imageurl} alt=""/>
                            <span>{`【${shopname}】 ${prdname}`}</span>
                            <br/>
                            <span>¥{prdprice.toFixed(2)}</span>
                        </a>
                    )
                })
            }
        </div>
    )
}

Search.PropTypes = {}

function mapStateToProps({ search }) {
    return { search }
}

export default connect(mapStateToProps)(Search)