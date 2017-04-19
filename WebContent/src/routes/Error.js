import React, { Component, propTypes } from 'react'
import { Result, Icon } from 'antd-mobile'
import error from '../img/error/error.png'

function Error() {
    return (
        <Result
                    img={<img src={error} alt="" />}
                    title="暂无优惠券"
                    message="您可以在商城首页面领取优惠券"
                />
    )
}

export default Error