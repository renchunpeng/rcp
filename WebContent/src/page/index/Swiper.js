import React, { Component, PropTypes } from 'react'
import { connect } from 'dva'
import { Carousel, SearchBar } from 'antd-mobile'

function Swiper() {
    return (
        <div>
            <Carousel
                className="my-carousel" autoplay={false} infinite
                beforeChange={(from, to) => console.log(`slide from ${from} to ${to}`)}
                afterChange={(index) => console.log('slide to', index)}
            >
                {['AiyWuByWklrrUDlFignR', 'TekJlZRVCjLFexlOCuWn', 'AiyWuByWklrrUDlFignR'].map((ii) => (
                    <a href="#" key={ii}><img src={`https://zos.alipayobjects.com/rmsportal/${ii}.png`} /></a>
                ))}
            </Carousel>
        </div>
    )
}

export default Swiper