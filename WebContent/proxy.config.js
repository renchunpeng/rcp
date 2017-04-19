/**
 * 采用dora插件可高度模拟ajax请求
*/

//attention 不同的开发人员请在这里设置对应的服务端IP地址，用于开发调试
//在后端接口未开发完成的情况下，可通过function模拟服务端，后写的配置项可覆盖之前写的配置项
//cpren: 'http://192.168.0.125:80'
//jcxu: 'http://192.168.100.105:8080'
//forme: 'http://localhost:8080'
//vm: 'http://192.168.178.130:8080'
const forwardAddress = 'http://localhost:8080';

module.exports = {
  //获取商品左侧的商品类别
  /*'GET /lflweb/prd/shprdtype/%7B%7D': function (req, res) {
    var body;
    setTimeout(function () {
      body = {
        success: true,
        message: 'success',
        data: [
          {
            iconurl: "http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg",
            prdtype: "08",
            prdtypeintrduction: "这是套餐",
            prdtypename: "会员套餐"
          }, {
            iconurl: "http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg",
            prdtype: "07",
            prdtypeintrduction: "这是礼品",
            prdtypename: "礼品中心"
          }, {
            iconurl: "http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg",
            prdtype: "06",
            prdtypeintrduction: "这是特产",
            prdtypename: "延庆特产"
          }, {
            iconurl: "http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg",
            prdtype: "05",
            prdtypeintrduction: "这是茶饮",
            prdtypename: "茶饮酒水"
          }, {
            iconurl: "http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg",
            prdtype: "04",
            prdtypeintrduction: "这是蛋奶",
            prdtypename: "肉禽蛋奶"
          }, {
            iconurl: "http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg",
            prdtype: "02",
            prdtypeintrduction: "这是水果",
            prdtypename: "新鲜水果"
          }, {
            iconurl: "http://www.touxiang.cn/uploads/20130725/25-011254_691.jpg",
            prdtype: "01",
            prdtypeintrduction: "这是蔬菜",
            prdtypename: "时令蔬菜"
          }, {
            iconurl: "",
            prdtype: "",
            prdtypeintrduction: "",
            prdtypename: ""
          }]
      }
      res.json(body)
    }, 0)
  },*/
  //模拟地址详情接口
  'GET /lflweb/address/shoneaddress/*': function (req, res) {
    var body;
    setTimeout(function () {
      body = {
        success: true,
        msg: 'success',
        data: [{
          account: 'asdfasfafsa',
          phoneNumber: 'asdfafasdfas',
          district: [340000, 341500, 341522],
          addressDetail: 'asdggggggggggg',
          isdefault: true,
          radioValue: 0
        }]
      }
      res.json(body)
    }, 0)
  },
  //模拟修改用户名接口
  'GET /lflweb/account/UpdateUserName/*/*': function (req, res) {
    var body;
    setTimeout(function () {
      body = {
        success: true,
        msg: 'success'
      }
      res.json(body)
    }, 0)
  },
  //模拟修改手机号码接口
  'GET /lflweb/account/UpdatePhone/*/*': function (req, res) {
    var body;
    setTimeout(function () {
      body = {
        success: true,
        msg: 'success'
      }
      res.json(body)
    }, 0)
  },
  //模拟地址删除接口
  'GET /lflweb/address/deuseraddress/*': function (req, res) {
    var body;
    setTimeout(function () {
      body = {
        success: true,
        msg: 'success'
      }
      res.json(body)
    }, 0)
  },
  //礼品列表
  'GET /lflweb/prd/shprdbase': function (req, res) {
    setTimeout(function () {
      console.dir(req.url);
      var page = req.query.page;
      var body;
      if (parseInt(page) < 5) {
        var index = (page - 1) * 2;
        body = {
          success: true,
          msg: 'success',
          data: Array(2).join(' ').split(' ').map((v, i) => ({
            name: 'name' + (index + i),
            desc: 'desc' + (index + i),
            img: 'https://zos.alipayobjects.com/rmsportal/dKbkpPXKfvZzWCM.png',
            prdid: index + 8
          }))
        }
      } else {
        body = {
          success: true,
          msg: 'success',
          data: []
        }
      }
      res.json(body)
    }, 500)
  },
  //模拟订单提交接口
  /*'POST /lflweb/ord/seordbase': function (req, res) {
    const userItem = req.body
    console.log('*******' + JSON.stringify(req.body) + '********' + JSON.stringify(req.headers));
    var body;
    setTimeout(function () {
      body = {
        success: true,
        msg: 'OR201703013573366575',
        data: 'null'
      }
      res.json(body)
    }, 0)
  },*/
  //模拟图片上传接口
  'POST /lflweb/saveImage': function (req, res) {
    const imageList = req.body;
    let body;
    setTimeout(function () {
      body = {
        success: true,
        msg: 'good message!',
        data: ['asdfasdfadsf','asdfadsfadfasf']
      }
      res.json(body)
    },0)
  },
  //模拟微信支付接口
  'GET /lflweb/wx/GetPreOrderID/*/*': function (req, res) {
    const userItem = req.body
    console.log('*******' + JSON.stringify(req.body) + '********' + JSON.stringify(req.headers));
    var body;
    setTimeout(function () {
      body = {
        success: true,
        msg: 'OR201703013573366575',
        data: {
          params1: '14123424',
          params2: '14123424',
          params3: '14123424',
          params4: '14123424',
          params5: '14123424',
        }
      }
      res.json(body)
    }, 0)
  },
  //-------------------------------------------------------------------------------------
  //通过以下的配置进行跨域调试，可直接调用非同一域下的接口
  //demo: 该（'GET /lflweb/prd/shprdtype/%7B%7D'）请求，会被转发到：http://192.168.100.105:8080/lflweb/prd/shprdtype/%7B%7D下
  //1.商品类别获取接口
  'GET /lflweb/prd/shprdtype/%7B%7D': forwardAddress,
  //2.订单提交接口
  'POST /lflweb/ord/seordbase': forwardAddress,
  //3.订单列表分页接口
  'GET /lflweb/ord/shordbase/*/*/*': forwardAddress,
  //4.商品列表分页接口
  'GET /lflweb/prd/shprdbase/*/*': forwardAddress,
  //5.购物车保存接口
  'POST /lflweb/ord/ordershop/save': forwardAddress,
  //6.购物车数据获取接口
  'GET /lflweb/ord/ordershop/*': forwardAddress,
  //7.模拟地址列表接口
  'GET /lflweb/address/shuseraddress/*': forwardAddress,
  //8.模拟用户登录获取用户信息
  'GET /lflweb/WXuser/getUserDate': forwardAddress,
  //9.商品详情接口
  'GET /lflweb/prd/shprdbasedetail/*/*': forwardAddress,
  //10.预支付订单号获取接口
  'GET /lflweb/wx/GetPreOrderID/*/*': forwardAddress,
  //11.地址列表接口
  'GET /lflweb/address/shuseraddress/*': forwardAddress,
  //12.订单详情接口
  'GET /lflweb/ord/shordsundetails/*': forwardAddress,
  //13.地址详情接口
  'GET /lflweb/address/shoneaddress/*': forwardAddress,
  //14.地址保存接口
  'POST /lflweb/address/seuseraddress': forwardAddress,
  //15.订单确认接口
  'GET /lflweb/ord/upordbase/*/*': forwardAddress,
  //16.修改用户名接口
  'GET /lflweb/account/UpdateUserName/*/*': forwardAddress,
  //修改手机号码接口
  'GET /lflweb/account/UpdatePhone/*/*': forwardAddress,
  //17.修改手机号码接口
  'GET /lflweb/account/UpdatePhone/*/*': forwardAddress,
  //18.优惠券获取接口
  'GET /lflweb/act/mycoupon/*': forwardAddress,
  //19.可用优惠券获取接口
  'GET /lflweb/act/ordercoupon/*': forwardAddress,
  
  //模拟店铺注册
  'POST /lflweb/shop/saveShopRegister': forwardAddress,
  //模拟店铺信息维护
  'POST /lflweb/shop/saveShopBase': forwardAddress,
  //店铺商品列表分页接口
  'GET /lflweb/shop/getShopPrd/*/*/*': forwardAddress,
  //店铺订单列表分页接口
  'GET /lflweb/shop/getShopOrder/*/*/*': forwardAddress,
  //模拟查看当前登录用户是否已入驻
  'GET /lflweb/shop/getShopYesOrNo/*': forwardAddress,
  //模拟修改店铺名接口
  'POST /lflweb/shop/UpdateShopName': forwardAddress,
  //模拟修改店铺描述接口
  'POST /lflweb/shop/UpdateShopDesc': forwardAddress,
  //模拟修改店铺退货地址接口
  'POST /lflweb/shop/UpdateReturnAddress/*/*': forwardAddress,
  //模拟店铺订单发货接口
  'GET /lflweb/shop/upOrdSend/*/*': forwardAddress,
  //模拟店铺订单配送完成接口
  'GET /lflweb/shop/upOrdSendOk/*/*': forwardAddress,
  //模拟店铺订单同意取消接口
  'GET /lflweb/shop/upOrdCancel/*/*': forwardAddress,
  //模拟店铺商品发布
  'POST /lflweb/shop/saveShopPrd': forwardAddress,
  //模拟店铺商品发布
  'POST /lflweb/shop/updateShopPrd': forwardAddress,
  //模拟查询用户礼品卡分页
  'GET /lflweb/order/CardList/*/*/*': forwardAddress,
   //模拟查询礼品卡配送记录
  'GET /lflweb/order/cardSendList/*': forwardAddress,

  //20.支付宝接口
  'GET /lflweb/zfb/zfbpay/*': forwardAddress,
  //21.获取可绑定的优惠券
  'GET /lflweb/act/coupon/*': forwardAddress,
  //22.用户绑定优惠券
  'POST /lflweb/act/savecoupon': forwardAddress,
  //23.积分详情
  'GET /lflweb/act/integral/*': forwardAddress,
  //24.商品品牌列表
  'GET /lflweb/prd/shprdbrand/*': forwardAddress,
  //25.订单评价提交
  'POST /lflweb/ord/SaveComment': forwardAddress,
  //26.订单取消接口
  'POST /lflweb/ord/reordbase': forwardAddress,
  //27.商品查询接口
  'GET /lflweb/prd/shprdbasedetail/*': forwardAddress,
  //28.商品评价列表
  'GET /lflweb/ord/SearchPrdComment/*': forwardAddress,
  //29.首页商品活动数据
  'GET /lflweb/prd/searchtypeselect': forwardAddress,
  //30.首页轮播图片
  'GET /lflweb/mar/adv': forwardAddress,
  //31.首页大类列表
  'GET /lflweb/mar/bigtype': forwardAddress,
  //32.预售商品获取接口
  'GET /lflweb/prd/searchpreprd/*': forwardAddress,
  //33.测试文件上传接口
  'POST /lflweb/springUpload/*': forwardAddress,
  //34.微信jsSDK配置参数接口
  'POST /lflweb/wxjs/getjsjdk': forwardAddress,
  //35.图片上传接口，兼容多图片上传，返回图片上传地址
  'POST /lflweb/shop/ShopPrdUpload': forwardAddress
};
