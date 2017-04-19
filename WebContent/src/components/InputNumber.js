import React, { Component, PropTypes } from 'react'

function getStyles() {
    return {
        icon: {
            width: '0.6rem',
            height: '0.6rem',
            textAlign: 'center',
            backgroundColor: '#1dbf63',
            color: 'white',
            borderRadius: '0.3rem',
            float: 'left',
            lineHeight: '0.6rem',
            fontSize: '0.5rem'
        },
        input: {
            float: 'left',
            height: '0.6rem',
            width: '0.6rem',
            lineHeight: '0.6rem',
            textAlign: 'center',
            borderBottom: '1px solid #e6e6e6',
            backgroundColor: 'white'
        }
    }
}

class InputNumber extends Component {
    constructor(props) {
        super(props);
        this.add = this.add.bind(this);
        this.sub = this.sub.bind(this);
        this.log = this.log.bind(this);
        this.state = {
            value: this.props.initValue?this.props.initValue:0
        };
    }

    render() {
        const styles = getStyles();
        return (
            <span style={this.props.style}>
                <span style={styles.icon} onClick={this.sub}>-</span>
                <span style={styles.input}>{this.state.value}</span>
                <span style={styles.icon} onClick={this.add}>+</span>
            </span>
        )
    }

    log() {
        console.log('点击了按钮！')
    }

    add() {
        this.setState({
            value: this.state.value + 1
        })
        this.props.callBack(this.state.value+1,this.props.goodDetail)
    }

    sub() {
        if (this.state.value > 0) {
            this.setState({
                value: this.state.value - 1
            })
            this.props.callBack(this.state.value-1,this.props.goodDetail)
        }
        else {

        }
    }
}

export default InputNumber
