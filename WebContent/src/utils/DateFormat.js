/**
 * this file was created by jcxu
 * description 该模块的作用主要为格式化时间格式
 */
//获取当前时间
function getTimeNow() {
    let timeStamp = (new Date()).valueOf();
    //console.log('此处显示的是获取的当前时间' + timeStamp);
    var date = new Date(timeStamp);
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    return Y + M + D + h + m + s;
};
//格式化时间
function formatDataNow(timestamp) {
    var _timestamp = timestamp.slice(9, 22);
    //console.log('此处显示的是时间戳字符串截取' + _timestamp);
    let b = parseInt(_timestamp, 10);
    var date = new Date(b);
    //console.log(date);
    var Y = date.getFullYear();
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    var h = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
    var m = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
    var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
    return Y + '-' + M + '-' + D + ' ' + h + ':' + m + ':' + s;
};
//针对于雀巢水项目，用于格式化后台取到的时间格式，时间格式为：new Date(1487843156000)
function formatDeliveryTime(timestamp) {
    let _timestamp = timestamp[0] == 'n'?timestamp.slice(9, 26):timestamp;
    //console.log('此处显示的是时间戳字符串截取' + _timestamp);
    let b = parseInt(_timestamp, 10);
    let date = new Date(b);
    //console.log(date);
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
    let h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
    let m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
    let s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
    //console.log('数据格式化的输出结果为：' + Y + M + D + ' ' + h + m + s);
    return Y + M + D + ' ' + h + m + s;
};

export { getTimeNow, formatDataNow, formatDeliveryTime }