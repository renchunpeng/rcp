import React, { Component, propTypes } from 'react'
import { List, InputItem, Icon, TextareaItem } from 'antd-mobile'
import { connect } from 'dva'
import { createForm } from 'rc-form'

function ChangeShopDesc({ shopDetail, dispatch, form }) {
    const { getFieldProps, getFieldError } = form;
    const { shopdescription, shopid } = shopDetail;
    function onSubmit() {
        form.validateFields({ force: true }, (error) => {
            if (!error) {
                let data = form.getFieldsValue();
                data['shopid'] = shopid;
                console.log(data);
                dispatch({
                    type: 'shopDetail/changeShopDesc',
                    payload: data
                })
            } else {
                alert('校验失败');
            }
        });
    }
    function validateShopDesc(rule, value, callback) {
        if (value) {
            callback();
        } else {
            callback(new Error('请输入店铺描述！'));
        }
    }
    return (
        <form>
            <List>
                <TextareaItem
                    {...getFieldProps('shopdesc', {
                        initialValue: shopdescription,
                        rules: [
                            { required: true, message: '店铺简介会在店铺索引中展现！' },
                            { validator: validateShopDesc }
                        ]
                    }) }
                    rows={5}
                    count={45}
                    clear
                    error={!!getFieldError('shopDesc')}
                    onErrorClick={() => {
                        alert(getFieldError('shopDesc').join('、'));
                    }}
                    placeholder={shopdescription}
                />
            </List>

             <div style={{ width: '100%',height:'1rem', lineHeight: '1rem', textAlign: 'center',
           backgroundColor: '#1dbf63', bottom:'0', color:'white', position:'fixed'}}  onClick={onSubmit}>
             保存
            </div>

        </form>
    )
}

ChangeShopDesc.propTypes = {};

ChangeShopDesc = createForm()(ChangeShopDesc);

function mapStateToProps({ shopDetail }) {
    return { shopDetail }
}

export default connect(mapStateToProps)(ChangeShopDesc);
