import React, { Component, propTypes } from 'react'
import { List, InputItem, Icon } from 'antd-mobile'
import { connect } from 'dva'
import { createForm } from 'rc-form'

function ChangeName({ user, dispatch, form }) {
    const { getFieldProps, getFieldError } = form;
    const { custname, custid } = user;
    function onSubmit() {
        form.validateFields({ force: true }, (error) => {
            if (!error) {
                let data = form.getFieldsValue();
                data['custId'] = custid;
                console.log(data);
                dispatch({
                    type: 'user/changeName',
                    payload: data
                })
            } else {
                alert('校验失败');
            }
        });
    }
    function validateUserName(rule, value, callback) {
        if (value) {
            callback();
        } else {
            callback(new Error('用户名不能为空！'));
        }
    }
    return (
        <form>
            <List renderHeader={() => <span><Icon type="user" />修改用户名</span>}>
                <InputItem
                    {...getFieldProps('userName', {
                        initialValue: custname,
                        rules: [
                            { require: true, message: '请输入用户名！' },
                            { validator: validateUserName }
                        ],
                    }) }
                    clear
                    error={!!getFieldError('userName')}
                    onErrorClick={() => {
                        alert(getFieldError('userName').join('、'));
                    }}
                    placeholder={custname}
                />
            </List>
            <div style={{ width: '100%', height: '1rem', lineHeight: '1rem', textAlign: 'center', backgroundColor: 'yellowgreen', color: 'white' }} onClick={onSubmit}>
                确认
            </div>

        </form>
    )
}

ChangeName.propTypes = {};

ChangeName = createForm()(ChangeName);

function mapStateToProps({ user }) {
    return { user }
}

export default connect(mapStateToProps)(ChangeName);
