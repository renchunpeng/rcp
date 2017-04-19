import request from '../utils/request'
import {getTimeNow} from '../utils/DateFormat'

//获取店铺基础信息
export async function getShopBase(params) {
  return request('/lflweb/shop/getShopBase/' + params, {
    method: 'get',
    data: params
  })
}

//店铺注册
export async function saveShopRegister(params) {
  console.log(params);
  const {
    aplid,
    aplperson,
    prdtype,
    idencode,
    bankcount,
    isapprove,
    shopstatus,
    shopid,
    apldate
  } = params;

  let data = {
    custid: custid,
    aplid: aplid,
    aplperson: aplperson,
    prdtype: prdtype,
    idencode: idencode,
    bankcount: bankcount,
    isapprove: isapprove,
    shopstatus: shopstatus,
    shopid: shopid,
    apldate: apldate
  };
  return request('/lflweb/shop/saveShopRegister', {
    method: 'post',
    body: JSON.stringify(data)
  })
}

//根据用户编号查看店铺是否已注册
export async function getShopYesOrNo(params) {
  return request('/lflweb/shop/getShopYesOrNo/' + custid, {
    method: 'get',
    data: params
  })
}

//修改店铺图表
export async function changeShopImage(params) {
  console.log('+++++_______+++++',params)
  const { file,shopid } = params;
  console.log('+++++___-_-___+++++',file[0])
  let _file = file[0];
  var formData = new FormData();
  formData.append(_file.name, _file);
  return request(`/lflweb/springUpload/${shopid}`, {
    method: 'post',
    body: formData
  })
}

//修改店铺名
export async function changeShopName(params) {
  return request('/lflweb/shop/UpdateShopName', {
    method: 'post',
    body: JSON.stringify(params)
  })
}

//修改店铺描述
export async function changeShopDesc(params) {
  return request(`/lflweb/shop/UpdateShopDesc`, {
    method: 'post',
    body: JSON.stringify(params)
  })
}

//修改店铺退货地址
export async function changeReturnAddress(params) {
  return request(`/lflweb/shop/UpdateReturnAddress/${shopid}/${params.addressid}`, {
    method: 'post',
    data: params
  })
}

//保存图片并保存返回地址
export async function saveImage(params) {
  console.log('show me the image',params);
  let formData = new FormData();
  for(let imageObj of params) {
    formData.append(imageObj.name,imageObj)
  }
  return request('lflweb/shop/ShopPrdUpload',{
    method: 'post',
    body: formData
  })
}

//保存店铺商品
export async function saveShopPrd(params) {
  console.log(params);
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
    //shopid,
    prdintroduction,
    prdvedio,
    skuid,
    prdkind,
    giveintegral,
    imageid,
    skudescription,
    imageurl
  } = params;

  let data = {
    prdid: '',
    prdname: prdname,
    prdbrief: prdbrief,
    prdtype: prdtype,
    prdprice: parseFloat(prdprice),
    prdspec: prdspec,
    prdunit: prdunit,
    prdtax: 0.2,
    custsource: '00',
    custtype: '00',
    paytype: '00',
    salezone: '00',
    custgrade: '00',
    prdstatus: prdstatus,
    onsaledate: getTimeNow(),
    offsaledate: getTimeNow(),
    createdate: getTimeNow(),
    createoperator: custid,
    modifydate: getTimeNow(),
    // remark: remark,
    keyword: prdname,
    // meaid: meaid,
    shopid: shopid,
    // prdintroduction: prdintroduction,
    // prdvedio: prdvedio,
    // skuid: skuid,
    prdkind: '01',
    //giveintegral: giveintegral,
    imageid: imageurl,
    skudescription: prdbrief
  };
  return request('/lflweb/shop/saveShopPrd', {
    method: 'post',
    body: JSON.stringify(data)
  })
}

//更新店铺商品
export async function updateShopPrd(params) {
  console.log(params);
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
    //shopid,
    prdintroduction,
    prdvedio,
    skuid,
    prdkind,
    giveintegral,
    imageid,
    skudescription
  } = params;

  let data = {
    prdid: prdid,
    prdname: prdname,
    prdbrief: prdbrief,
    prdtype: prdtype,
    prdprice: parseFloat(prdprice),
    prdspec: prdspec,
    prdunit: prdunit,
    prdtax: 0.2,
    custsource: '00',
    custtype: '00',
    paytype: '00',
    salezone: '00',
    custgrade: '00',
    prdstatus: prdstatus,
    onsaledate: getTimeNow(),
    offsaledate: getTimeNow(),
    createdate: getTimeNow(),
    createoperator: custid,
    modifydate: getTimeNow(),
    // remark: remark,
    keyword: prdname,
    // meaid: meaid,
    shopid: shopid,
    // prdintroduction: prdintroduction,
    // prdvedio: prdvedio,
    skuid: skuid,
    prdkind: '01',
    //giveintegral: giveintegral,
    imageid: imageid,
    skudescription: prdbrief
  };
  return request('/lflweb/shop/updateShopPrd', {
    method: 'post',
    body: JSON.stringify(data)
  })
}
