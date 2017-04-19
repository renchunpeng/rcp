import React, { Component, PropTypes } from 'react'
import LazyLoading from '../../components/LazyLoading'
import Lazyload from 'react-lazy-load'

class TestList extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return (
            <div>
                {
                    [1,2,3,4,5,6,7,8].map((item,i)=>{
                        return (
                            <Lazyload height={762} offsetVertical={0}>
                                <img style={{width: '100%'}} src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490800621100&di=f0bf06cd445b30c4d761a99f78ed22db&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01aba657b1abaf0000018c1be7e44c.jpg%40900w_1l_2o_100sh.jpg" alt=""/>
                            </Lazyload>
                        )
                    })
                }
            </div>
        )
    }
}

export default TestList