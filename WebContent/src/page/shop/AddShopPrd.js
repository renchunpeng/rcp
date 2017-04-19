/**
 * this page is created by ypli
 * description for  入驻店铺商品维护
 * component ES6
 */
import React, { Component, propTypes } from 'react'
import {
  List,
  InputItem,
  Switch,
  Stepper,
  Slider,
  Button,
  NavBar,
  Icon,
  Picker,
  Radio,
  WhiteSpace,
  ImagePicker,
  TextareaItem
} from 'antd-mobile';
import { connect } from 'dva'
import { createForm } from 'rc-form'
import styles from '../styles/Personal.less'
import { district, provinceLite as province } from 'antd-mobile-demo-data';
import style1 from '../styles/AddShopPrd.less'
import { prdUnit, prdType } from '../Config'
import addShopPrd from '../../img/addShopPrd/takepic.png'

const Item = List.Item;

function AddShopPrd({ shopPrd, user, dispatch, form }) {
  const {
    prdid,
    prdname,
    prdbrief,
    prdtype,
    prdprice,
    prdspec,
    prdunit,
    prdtax,
    custsource,
    custtype,
    paytype,
    salezone,
    custgrade,
    prdstatus,
    onsaledate,
    offsaledate,
    createdate,
    createoperator,
    modifydate,
    remark,
    keyword,
    meaid,
    shopid,
    prdintroduction,
    prdvedio,
    skuid,
    prdkind,
    giveintegral,
    imageid,
    skudescription,
    imageList,
    prdImageUrl,
    pageType
  } = shopPrd;
  const { custid } = user;
  const { getFieldProps, getFieldError } = form
  //添加到仓库
  function onSave() {
    form.validateFields({
      force: true
    }, (error) => {
      if (!error) {
        let data = form.getFieldsValue();
        let _prdtype = data.prdtype[0];
        let _prdunit = data.prdunit[0];
        data.prdtype = _prdtype;
        data.prdunit = _prdunit;
        data['prdstatus'] = '01';
        data['imageList'] = imageList;
        data['prdid'] = prdid;
        switch (pageType) {
          case 'add':
            dispatch({ type: 'shopPrd/savePrd', payload: data })
          case 'edit':
            const { prdImage } = shopPrd;
            const { imageid } = prdImage;
            data['imageid'] = imageid;
            data['skuid'] = skuid;
            dispatch({ type: 'shopPrd/updatePrd', payload: data })
          default:
          //none
        }

      } else {
        alert('校验失败');
      }
    });
  }
  //立即发布
  function onPublish() {
    form.validateFields({
      force: true
    }, (error) => {
      if (!error) {
        let data = form.getFieldsValue();
        let _prdtype = data.prdtype[0];
        let _prdunit = data.prdunit[0];
        data.prdtype = _prdtype;
        data.prdunit = _prdunit;
        data['prdstatus'] = '00';
        data['imageList'] = imageList;
        data['prdid'] = prdid;
        switch (pageType) {
          case 'add':
            dispatch({ type: 'shopPrd/savePrd', payload: data })
          case 'edit':
            const { prdImage } = shopPrd;
            const { imageid } = prdImage;
            data['imageid'] = imageid;
            data['skuid'] = skuid;
            dispatch({ type: 'shopPrd/updatePrd', payload: data })
          default:
          //none
        }
      } else {
        alert('校验失败');
      }
    });
  }
  //校验商品名称
  function validateprdname(rule, value, callback) {
    if (value) {
      callback();
    } else {
      callback(new Error('请填写商品名称！！！'));
    }
  }

  function validatePhoneNumber(rule, value, callback) {
    if (value && value.length > 4) {
      callback();
    } else {
      callback(new Error('帐号至少5个字符'));
    }
  }
  //图片添加或删除时触发(多图片时用)
  function onChange(imgdata, type, index) {
    console.log(imgdata, type, index);
    dispatch({ type: 'shopPrd/addImages', payload: imgdata })
  }
  //单图片自制组件上传
  function changePrdImage(e) {
    console.log('商品图片修改', (e.target).files);
    //添加图片信息至redux
    dispatch({
      type: 'shopPrd/addImages',
      payload: (e.target).files
    });
    var reader = new FileReader();
    reader.onload = function (event) {
      //console.log('tupianjiazai', event.target.result)
      //实时预览
      dispatch({
        type: 'shopPrd/changeImage',
        payload: event.target.result
      })
    }
    reader.readAsDataURL((e.target).files[0]);
    //reader.readAsDataURL((e.target).files[0]);
    //console.log('图片的临时路径为：', (e.target).files, reader.readAsDataURL((e.target).files[0]))
  }

  return (
    <form className={style1.addShopPrd}>
      {/*<ImagePicker
        files={imageList}
        onChange={onChange}
        onImageClick={(index, fs) => console.log(index, fs)}
        selectable={imageList.length < 5} />*/}
      <div className={style1.container}>
        <div>
          <img src={prdImageUrl}></img>
        </div>
        <input type="file" accept="image/png,image/jpg" multiple={false} onChange={changePrdImage} />
      </div>


      <List>
        <InputItem
          {...getFieldProps('prdname',
            {
              initialValue: prdname,
              rules: [
                { required: true, message: '请输入商品名称' },
                { validator: validateprdname }
              ]
            }) }
          clear
          error={!!getFieldError('prdname')}
          onErrorClick={() => {
            alert(getFieldError('prdname').join('、'));
          }}
          placeholder="请输入商品名称">商品名称
        </InputItem>

        <Picker
          data={prdType}
          cols={1}
          {...getFieldProps('prdtype', {
            initialValue: prdtype
          }) }
          className="forss">
          <List.Item arrow="horizontal">商品类型</List.Item>
        </Picker>
      </List>
      <WhiteSpace size="sm" />
      <List>
        <InputItem {...getFieldProps('prdspec', {
          initialValue: prdspec
        }) } placeholder="请输入商品规格">
          规格
        </InputItem>
        <Picker
          data={prdUnit}
          cols={1}
          {...getFieldProps('prdunit', {
            initialValue: prdunit
          }) }
          className="forss">
          <List.Item arrow="horizontal">单位</List.Item>
        </Picker>
        <InputItem {...getFieldProps('prdprice', {
          initialValue: prdprice
        }) } placeholder="请输入商品价格" extra="元">
          价格
        </InputItem>
      </List>
      <WhiteSpace size="sm" />
      <TextareaItem
        {...getFieldProps('prdbrief', { initialValue: prdbrief, rules: [{ required: true, message: '请输入手机号码' }], }) }
        clear
        error={!!getFieldError('prdbrief')}
        onErrorClick={() => {
          alert(getFieldError('prdbrief').join('、'));
        }}
        title="宝贝描述"
        placeholder="请输入您的详细地址"
        data-seed="logId"
        autoHeight />
      <div>
        <div
          style={{
            width: '100%',
            height: '1rem',
            lineHeight: '1rem',
            position: 'fixed',
            bottom: '0',
            float: 'left'
          }}>
          <div
            style={{
              width: '50%',
              height: '1rem',
              lineHeight: '1rem',
              textAlign: 'center',
              backgroundColor: '#a0a0a0',
              bottom: '0',
              color: 'white',
              float: 'left'
            }}
            onClick={onSave}>放入仓库</div>
          <div
            style={{
              width: '50%',
              height: '1rem',
              lineHeight: '1rem',
              textAlign: 'center',
              backgroundColor: '#1dbf63',
              bottom: '0',
              color: 'white',
              float: 'left'
            }}
            onClick={onPublish}>立即发布</div>
        </div>
      </div>
    </form>
  )
}

AddShopPrd.propTypes = {};

AddShopPrd = createForm()(AddShopPrd);

function mapStateToProps({ shopPrd, user }) {
  return { shopPrd, user }
}

export default connect(mapStateToProps)(AddShopPrd)
