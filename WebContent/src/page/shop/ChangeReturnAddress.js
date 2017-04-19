import React, { Component, propTypes } from 'react'
import { List, InputItem, Icon } from 'antd-mobile'
import { connect } from 'dva'
import { createForm } from 'rc-form'

function ChangeReturnAddress({ shopDetail, dispatch, form }) {
    const { getFieldProps, getFieldError } = form;
    const { address, shopid } = shopDetail;
    const { addressid, addcontact, addmobile, addprov, addcity, addcounty, district, adddetail, isdefault } = address;

    function onSubmit() {
        form.validateFields({ force: true }, (error) => {
            if (!error) {
                let data = form.getFieldsValue();
                data['shopid'] = shopid;
                data['addressid'] = addressid;
                console.log(data);
                dispatch({
                    type: 'shopDetail/changeReturnAddress',
                    payload: data
                })
            } else {
                alert('校验失败');
            }
        });
    }
    function validateReturnAddress(rule, value, callback) {
        if (value) {
            callback();
        } else {
            callback(new Error('请选择退货地址！'));
        }
    }
    return (
        <form>
            <List>
                <InputItem
                    {...getFieldProps('addressid', {
                        initialValue: adddetail,
                        rules: [
                            { require: true, message: '' },
                            { validator: validateReturnAddress }
                        ],
                    }) }
                    clear
                    error={!!getFieldError('addressid')}
                    onErrorClick={() => {
                        alert(getFieldError('addressid').join('、'));
                    }}
                    onClick={() => {location.href='???#/addressList/choice/123456'}}
                    placeholder={addressid}
                />
            </List>

             <div style={{ width: '100%',height:'0.8rem', lineHeight: '0.8rem', textAlign: 'center',
           backgroundColor: '#1dbf63', bottom:'0', color:'white', position:'fixed'}}  onClick={onSubmit}>
             保存
            </div>

        </form>
    )
}

ChangeReturnAddress.propTypes = {};

ChangeReturnAddress = createForm()(ChangeReturnAddress);

function mapStateToProps({ shopDetail }) {
    return { shopDetail }
}

export default connect(mapStateToProps)(ChangeReturnAddress);
