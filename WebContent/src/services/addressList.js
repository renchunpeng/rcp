import request from '../utils/request'
import { districts } from '../page/address/District'

export async function getAddressList(params) {
  return request('/lflweb/address/shuseraddress/' + custid, {
    method: 'get',
    data: params
  })
}

export async function getAddressDetail(params) {
  return request('/lflweb/address/shoneaddress/' + params, {
    method: 'get',
    data: params
  })
}

export async function submit(params) {
  //console.log(params);
  const { addcontact, adddetail, addmobile, addressType, district, isdefault } = params;
  let _addprov = '';
  let _addcity = '';
  let _addcounty = '';
  for (let i = 0; i < districts.length; i++) {
    if (districts[i].value == district[0]) {
      _addprov = districts[i].label;
      let cityList = districts[i].children;
      for (let j = 0; j < cityList.length; j++) {
        if (cityList[j].value == district[1]) {
          _addcity = cityList[j].label;
          let countryList = cityList[j].children;
          for (let k = 0; k < countryList.length; k++) {
            if (countryList[k].value == district[2]) {
              _addcounty = countryList[k].label;
            }
          }
        }
      }
    }
  }
  let data = {
    custid: custid,
    addcontact: addcontact,
    addmobile: addmobile,
    contactsex: '01',
    companyname: '',
    addcounty: '',
    addprov: _addprov,
    addcity: _addcity,
    addcounty: _addcounty,
    addtown: '',
    adddetail: adddetail,
    provid: district[0],
    cityid: district[1],
    countyid: district[2],
    townid: '',
    addtype: addressType,
    isdefault: isdefault,
    addstatus: ''
  };
  return request('/lflweb/address/seuseraddress', {
    method: 'post',
    body: JSON.stringify(data)
  })
}