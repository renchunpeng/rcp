import React, { Component, propTypes } from 'react'
import { List, InputItem, Icon } from 'antd-mobile'
import { connect } from 'dva'
import { createForm } from 'rc-form'

function ChangeShopName({ shopDetail, dispatch, form }) {
    const { getFieldProps, getFieldError } = form;
    const { shopname, shopid } = shopDetail;
    function onSubmit() {
        form.validateFields({ force: true }, (error) => {
            if (!error) {
                let data = form.getFieldsValue();
                data['shopid'] = shopid;
                console.log(data);
                dispatch({
                    type: 'shopDetail/changeShopName',
                    payload: data
                })
            } else {
                alert('校验失败');
            }
        });
    }
    function validateShopName(rule, value, callback) {
        if (value) {
            callback();
        } else {
            callback(new Error('店铺名不能为空！'));
        }
    }
    return (
        <form>
            <List>
                <InputItem
                    {...getFieldProps('shopname', {
                        initialValue: shopname,
                        rules: [
                            { require: true, message: '请输入店铺名！' },
                            { validator: validateShopName }
                        ],
                    }) }
                    clear
                    error={!!getFieldError('shopName')}
                    onErrorClick={() => {
                        alert(getFieldError('shopName').join('、'));
                    }}
                    placeholder={shopname}
                />
            </List>
            <div style={{width:'100%', marginTop:'10px', marginLeft:'15px'}}>
                <span style={{ color:'grey', fontSize: '0.28rem'}}>店铺名称建议:</span>
                <br/>
                <span style={{ color:'grey', fontSize: '0.28rem'}}>1.不能包含“&*%￥#"特殊字符;</span>
                <br/>
                <span style={{ color:'grey', fontSize: '0.28rem'}}>2.名称长度不能超过30个字符;</span>
            </div>
            <div style={{ width: '100%',height:'1rem', lineHeight: '1rem', textAlign: 'center',
           backgroundColor: '#1dbf63', bottom:'0', color:'white', position:'fixed'}}  onClick={onSubmit}>
             保存
            </div>

        </form>
    )
}

ChangeShopName.propTypes = {};

ChangeShopName = createForm()(ChangeShopName);

function mapStateToProps({ shopDetail }) {
    return { shopDetail }
}

export default connect(mapStateToProps)(ChangeShopName);
