import _ from 'underscore';


const A = ('zero^one^two^three^four^five^six^seven^eight^nine^ten' +
  '^eleven^twelve^thirteen^fourteen^fifteen^sixteen^seventeen^eighteen^nineteen^' +
  '^twenty^thirty^forty^fifty^sixty^seventy^eighty^ninety^hundred^' +
  '^thousand^million^billion^trillion^quadrillion^quintillion').split('^'),
  B = [];
for (var i = 0; i < 1000; i++) {
  B[i] = i < 20 ? A[i] :
    i < 100 ?
    A[19 + Math.floor(i / 10)] + (i % 10 == 0 ? "" : "-" + B[i % 10])
      :
    A[Math.floor(i / 100)] + " " + A[29] + (i % 100 == 0 ? "" : " and " + B[i % 100]);
}

const S = ['', '十', '百', '千', '万', '亿', '点', ''],
  R = ['', '拾', '佰', '仟', '万', '亿', '点', ''],
  X = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九'],
  Y = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];

const utils = {

  num2EN(num){
    var numInt = num.toString().split('.')[0].split(',').join(''),
      sec, text = '';
    for (var i = 0; i < Math.ceil(numInt.length / 3); i++) {
      sec = parseInt(
        numInt.substring(
          numInt.length - 3 * i - 3,
          numInt.length - 3 * i
        ),
        10
      );
      text = ( i == 0 && sec > 0 && sec < 100 && parseInt(numInt.substring(0, length - 3), 10) > 0 ? ' and ' : '' ) +
        ( sec == 0 && (i > 0 || numInt.toString(10) != 0) ? '' : B[sec]) +
        ( sec == 0 ? '' : ' ' + (typeof A[30 + i] == 'undefined' ? 'undefined' : A[30 + i]) ) +
        ( i == 0 || sec == 0 || (sec > 0 && text == '') ? '' : ', ' ) + text;
    }
    if (num.toString().split('.')[1]) {
      var numFloat = num.toString().split('.')[1].split(',').join('');
      for (i = 0; i < numFloat.length; i++) {
        sec = parseInt(numFloat.substring(i, i + 1), 10);
        text += ( i == 0 ? (text == '' ? A[0] : '') + ' point ' : '' ) +
          ( sec == 0 && i != 0 && !parseInt(numFloat.substring(i + 1), 10) > 0 ? '' : B[sec] + ' ' );
      }
    }
    return text;
  },

  num2CN(num, f){
    if (!/^\d*(\.\d*)?$/.test(num)) {
      return 'Number is wrong!';
    }
    var AA = f ? Y : X,
      BB = f ? R : S;
    var a = num.toString().replace(/(^0*)/g, '').split('.'),
      k = 0,
      re = '',
      ss = a[0];
    for (var i = ss.length - 1; i >= 0; i--) {
      switch (k) {
        case 0:
          re = BB[7] + re;
          break;
        case 4:
          if (!new RegExp('0{4}\\d{' + (ss.length - i - 1) + '}$').test(ss)) {
            re = BB[4] + re;
          }
          break;
        case 8:
          re = BB[5] + re;
          BB[7] = BB[5];
          k = 0;
          break;
      }
      if (k % 4 == 2 && ss[i + 2] != 0 && ss[i + 1] == 0) {
        re = AA[0] + re;
      }
      if (ss[i] != 0) {
        re = AA[ss[i]] + BB[k % 4] + re;
        k++;
      }
    }
    if (a.length > 1) {
      re += BB[6];
      for (var j = 0; j < a[1].length; j++) {
        re += AA[a[1][j]];
      }
    }
    return re;
  },

  isMine(me, prop, callback) {
    return function (payload) {
      if (me.props.id == payload[prop]) {
        callback(payload);
      }
    };
  },

  newId(pre) {
    pre = pre || '';
    var d = (new Date()).getTime().toString(),
      i = Math.floor(Math.random() * 10),
      l = d.length;
    if (l < 15) {
      var x = 15 - l;
      while (x-- > 0) d += '0';
    }
    return pre +
      Math.random().toString().slice(2, 8) +
      d.slice(0, i) +
      Math.random().toString().slice(5, 10) +
      d.slice(i) +
      Math.random().toString().slice(9, 14) + i;
  },

  deepClone(object) {
    var clone = _.clone(object);

    _.each(clone, function (value, key) {
      if (_.isFunction(value)) {
        clone[key] = value;
      }
      else if (_.isObject(value)) {
        clone[key] = _.deepClone(value);
      }
    });

    return clone;
  },

  getKey(reactId) {
    return reactId.slice(reactId.lastIndexOf('$') + 1);
  },

  keyMirror(obj) {
    var ret = {};
    if (obj instanceof Object && !Array.isArray(obj)) {
      for (var key in obj) {
        if (!obj.hasOwnProperty(key)) {
          continue;
        }
        ret[key] = key;
      }
      return ret;
    }
    else {
      console.log('keyMirror(...): Argument must be an object.');
    }
  },

  isWechatClient(){
    var ua = navigator.userAgent.toLowerCase();
    return ua.match(/MicroMessenger/i) == 'micromessenger';
  },

  parseQuery(queryString){
    var qs = queryString,
      result = {},
      i = qs.indexOf('?');
    if (i != -1) {
      qs = qs.slice(i + 1);
    }
    qs.split('&').forEach(function (q) {
      var kv = q.split('=');
      result[kv[0]] = kv[1];
    });
    return result;
  },

  createMarkup(content){
    return {__html: content};
  },

  htmlEscape(html) {
    return html.replace(/<[^>]*>/g, '');
  },

  getCurrentPath() {
    return location.hash.slice(1).split('?')[0];
  }

};

_.mixin(utils);

export default _;
