import { Menu, ActivityIndicator, NavBar } from 'antd-mobile'
import {connect} from 'dva'
import React, { Component, PropTypes } from 'react'

const onChange = (value) => {
    let label = '';
    data.forEach((dataItem) => {
        if (dataItem.value === value[0]) {
            label = dataItem.label;
            if (dataItem.children && value[1]) {
                dataItem.children.forEach((cItem) => {
                    if (cItem.value === value[1]) {
                        label += ` ${cItem.label}`;
                    }
                });
            }
        }
    });
    console.log(label);
}

function TypeMenu({ dispatch, men }) {

    const { list, show } = men;

    const menuEl = (
      <Menu
        className="foo-menu"
        data={list}
        level={1}
        value={['2', '22']}
        onChange={onChange}
        height={document.documentElement.clientHeight * 0.7}
      />
      );
    const loadingEl = (
      <div style={{ width: '100%', height: document.documentElement.clientHeight * 0.6, display: 'flex', justifyContent: 'center' }}>
        <ActivityIndicator size="large" />
      </div>
    );

    return (
        <div className="menu-active">
            {show ? list ? menuEl : loadingEl : null}
        </div>
    )
}

TypeMenu.PropTypes = {};

function mapStateToProps({ men }) {
    return { men };
}

export default connect(mapStateToProps)(TypeMenu)