import request from '../utils/request'
import Number from '../utils/Number'

export async function submit(params) {
    const { goodsList, radioValue, remark, address, totalNumber, senddate, totalprice, couponList } = params;
    const { addressid } = address;
    /*---------------------------------------商品列表------------------------------------------*/
    let _goodsList = [];
    let _totalNumber = 0;
    let _totalPrice = 0.00;
    for (let i = 0; i < goodsList.length; i++) {
            _goodsList[i] = {};
            _goodsList[i]['orderid'] = '';
            _goodsList[i]['prdid'] = goodsList[i].prdid;
            _goodsList[i]['prdnum'] = goodsList[i].prdcount;
            _goodsList[i]['prdprice'] = goodsList[i].prdprice;
            _goodsList[i]['prdtax'] = goodsList[i].prdtax;
            _goodsList[i]['prdunit'] = goodsList[i].prdunit;
            _goodsList[i]['taxamt'] = Number(Number(goodsList[i].prdprice).mul(goodsList[i].prdcount)).mul(goodsList[i].prdtax);
            _goodsList[i]['shopid'] = goodsList[i].shopid;
            _goodsList[i]['prdkind'] = goodsList[i].prdkind;
            _goodsList[i]['sumprice'] = Number(goodsList[i].prdprice).mul(goodsList[i].prdcount);
            _goodsList[i]['prdtype'] = goodsList[i].prdtype;
        //商品总数量
        _totalNumber = _totalNumber + goodsList[i].prdcount;
        //商品总价格
        _totalPrice = Number(_totalPrice).add(Number(goodsList[i].prdprice).mul(goodsList[i].prdcount));
    };
    /*---------------------------------------优惠券------------------------------------------*/
    //用户选择的所有优惠券
    let _couponList = [];
    let _discount = 0;
    console.log('优惠券列表',couponList);
    for (let i = 0; i < couponList.length; i++) {
        if (couponList[i].checked) {
            let temp = {};
            temp['bdcouponid'] = couponList[i].bdcouponid;
            temp['custid'] = custid;
            temp['couponid'] = couponList[i].couponid;
            temp['prdid'] = couponList[i].prdid;
            temp['uplimit'] = couponList[i].uplimit;
            temp['lowlimit'] = couponList[i].lowlimit;
            temp['couponmoney'] = couponList[i].couponmoney;
            temp['isuse'] = false;
            temp['orderid'] = '';
            temp['ismixuse'] = couponList[i].ismixuse;
            _discount = couponList[i].couponmoney + _discount;
            _couponList.push(temp);
        }
    }
    //实付金额
    let _realPrice = _totalPrice-_discount;
    console.log('所有商品',_goodsList,'总数量',_totalNumber,'总价格',_totalPrice,'优惠券列表',_couponList);
    //测试用模拟提交数据
    let data = {
        coupon: _couponList,
        flag: true,//是否显示发票
        invoicetransbean: {
            main: {
                bankaccount: '14313241234143214124',
                createdate: '2016-10-27 07:05:44',
                custid: custid,
                expressid: '00',
                expresscompany: '00',
                addressid: addressid,
                paytype: radioValue,//支付方式
                paystatus: '01',
                invoiceaddress: '测试注册地址',
                invoiceamt: _realPrice,
                invoicecode: '',
                invoicedate: '2016-10-27 07:05:44',
                invoiceid: '',
                invoicemobile: '18862237873',
                invoiceperson: '',
                invoiceproperty: '01',//发票性质
                invoicerelation: '',
                invoicestatus: '',
                invoicestatusname: '未开票',
                invoicetitle: '测试发票抬头',
                invoicetranid: '',
                invoicetype: '01',//发票类型
                invoicetypename: '',
                openbank: '测试开户银行',
                remark: '测试标记',
                taxid: '1412341324312413243'
            },
            son: [
                {
                    invoicetranid: '1432413412',
                    orderamt: _realPrice,
                    orderid: ''
                }
            ]
        },
        main: {
            addressid: addressid,
            commentdate: '2016-10-27 07:05:44',
            createdate: '2016-10-27 07:05:44',
            custid: custid,
            discountamt: 0,
            invoicetranid: '',
            iscomment: false,
            isinvoice: false,
            okdate: '2016-10-27 07:05:44',
            orderflag: '',
            orderid: '',
            ordersource: '03',
            orderstatus: '01',
            orderstatusname: '',
            ordesourcename: '',
            paydate: '2016-10-27 07:05:44',
            paystatus: '01',
            paysysid: '',
            paytype: radioValue,
            paytypename: '',
            printdate: '2016-10-27 07:05:44',
            realprice: _realPrice,
            relatedorder: '',
            scoreamt: 0,
            remark: remark,
            sendbegin: '2016-10-27 07:05:44',//注意要转化为时间格式
            senddate: senddate,//配送时间
            sendend: '2016-10-27 07:05:44',
            strategyid: '',
            totalprice: _totalPrice
        },
        son: _goodsList
    }
    return request('/lflweb/ord/seordbase', {
        method: 'post',
        body: JSON.stringify(data)
    })
}

export async function saveShoppingCart(params) {
    //console.log(params);
    //处理页面购物车的数据
    let _goodsList = [];
    for (let i = 0; i < params.length; i++) {
        //对可能为空的数据进行容错
        _goodsList[i] = {};
        _goodsList[i]['createdate'] = '2016-10-27 07:05:44';
        _goodsList[i]['custid'] = custid;
        _goodsList[i]['imageurl'] = "file/20170224/29814afba2904ec48cc5d2ba7450dee3.gif";
        _goodsList[i]['prdcount'] = params[i].prdcount;
        _goodsList[i]['prdid'] = params[i].prdid;
        _goodsList[i]['prdintroduction'] = params[i].prdintroduction;
        _goodsList[i]['prdkind'] = params[i].prdkind;
        _goodsList[i]['prdkindname'] = params[i].prdkindname ? params[i].prdkindname : '未找到该数据';
        _goodsList[i]['prdname'] = params[i].prdname;
        _goodsList[i]['prdprice'] = params[i].prdprice;
        _goodsList[i]['prdtype'] = params[i].prdtype ? params[i].prdtype : '未找到该数据';
        _goodsList[i]['prdtypename'] = params[i].prdtypename ? params[i].prdtypename : '未找到该数据';
        _goodsList[i]['shopid'] = params[i].shopid ? params[i].shopid : '未找到该数据';
        _goodsList[i]['shopname'] = params[i].shopname ? params[i].shopname : '未找到该数据';
        _goodsList[i]['shopstatus'] = params[i].shopstatus ? params[i].shopstatus : '未找到该数据';
        _goodsList[i]['updatedate'] = '2016-10-27 07:05:44';
    }
    //demo
    let demo = [
        {
            createdate: "new Date(1488265539000)",
            custid: "0000",
            imageurl: "file/20170224/29814afba2904ec48cc5d2ba7450dee3.gif",
            prdcount: 1,
            prdid: "72ca420c1402d34e",
            prdintroduction: "1",
            prdkind: "02",
            prdkindname: "套餐商品",
            prdname: "1",
            prdprice: 1,
            prdtype: "01",
            prdtypename: "时令蔬菜",
            shopid: "",
            shopname: "",
            shopstatus: "1",
            updatedate: "new Date(1488265539000)"
        },
        {
            createdate: "new Date(1488265539000)",
            custid: "0000",
            imageurl: "file/20170223/dd5dc26cbb9a4a7688799b40a2c8359e.png",
            prdcount: 2,
            prdid: "e706e6e7029dd495",
            prdintroduction: "1",
            prdkind: "01",
            prdkindname: "普通商品",
            prdname: "name",
            prdprice: 1,
            prdtype: "01",
            prdtypename: "时令蔬菜",
            shopid: "",
            shopname: "",
            shopstatus: "1",
            updatedate: "new Date(1488265539000)"
        }
    ];
    let data = {
        type: 2,//采用2类保存方式,会覆盖原有的购物车数据
        list: _goodsList
    }
    return request(`/lflweb/ord/ordershop/save`, {
        method: 'post',
        body: JSON.stringify(data)
    })
}
