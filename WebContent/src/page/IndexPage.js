/**
 * Created by semantic on 2017/2/15
 * description wraper of app
 * 这个是一个带状态的react组件
 */
import React, { Component } from 'react'

class IndexPage extends Component{
    constructor(props){
        super(props);
        this.state = {

        }
    }

    render() {
        return (
            <div>
                {this.props.children}
            </div>
        )
    }
}

export default IndexPage