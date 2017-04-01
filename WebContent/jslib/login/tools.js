Ext.namespace('tools');

var MenuHtml = ""; // 右侧自定义按钮html串
flag = ''; // 点击菜单传过来的id,用去区分各tab的ID
name = ''; // 要加载的页面的名称

Ext.Loader.setConfig({
  enabled : true,
  paths : {// '类名前缀':'所在路径'
    'rcp' : 'jslib/component/upload'
  },
});
Ext.require(
  ['rcp.AccordionWindow','rcp.uploadpanel']);// 通过匹配会自动加载'jslib/js/AccordionWindow.js'

/*
 * 用途:上传控件 author:rcp date:2016/8/25
 */
tools.uploadwindow = function() {
  
  var windowupload = Ext.create("rcp.AccordionWindow", {
    title : '文件上传'
  });
  
  windowupload.show();
  
},

/*
 * 用途:主菜单面板 author:rcp date:2016/8/25
 */
tools.treepanel = function() {
  Ext.Loader.setConfig({
    enabled : true,
    paths : {
      'rcp' : 'jslib/login'
    },
  });
  Ext.require(
    ['rcp.treepanel']);
  
  var treepanel = Ext.create("rcp.treepanel", {
    title : 'tree panel'
  });
  
  return treepanel;
  
},

/*
 * 用途:执行ajax获取的js页面,相当于将那个对象调入内存中 author:rcp date:2016/8/25
 */
tools.LoadJsSuccess = function(response) {
  var prefix = 'rcp.';
  try {
    eval(response.responseText);
    Ext.create(prefix + name, {
      mid : flag
    });
  } catch (ex) {
    alert(ex.toString());
  }
  
};

/*
 * 用途:菜单按钮单击事件 author:rcp date:2016/8/25
 */
tools.OnMenuClick = function(data) {
  flag = data.id;
  name = data.jsname;
  
  Ext.Ajax.request({
    url : data.url,
    async : false,
    method : 'POST',
    success : tools.LoadJsSuccess,// 这里相当于将ajax获取的页面调入内存中
    scope : tools.Create
  // 回调函数用于创建刚才调入内存的对象(没有起作用，暂时不知道原因)
  });
};

/*
 * 用途:右侧按钮html author:rcp date:2016/8/25
 */
tools.GetMenu = function() {
  
  Ext.Ajax.request({
    url : 'jslib/JsonStore/Menu.js',
    async : false,
    method : 'POST',
    success : function(response) {
      data = eval(response.responseText);
      for (var i = 0; i < data.length; i++) {
        MenuHtml += "<button onclick=rrcp('" + data[i].name + "')>" + data[i].title + "</button></br>";
      }
    }
  });
};

/*
 * 用途:创建jsonstore author:rcp date:2016/8/25
 */
tools.GetJsonStore = function(url, fields) {
  var toolsurl = url || "jslib/JsonStore/gird.js";
  var myfields = fields;
  
  return Ext.create('Ext.data.JsonStore', {
    id : 'jsonstore' + flag,
    autoLoad : true,
    pageSize : 20,
    proxy : {
      type : "ajax",
      url : toolsurl,
      reader : {
        root : "rows"
      }
    },
    fields : myfields
  });
  
};

/*
 * 用途:创建文本框 author:rcp date:2016/8/30
 */
tools.FormText = function(label, name, id, max, anchor, blank, tab, vtype, labelwidth) {
  var lw = 80;
  
  if (labelwidth)
    lw = labelwidth;
  
  var ab = Ext.create('Ext.form.TextField', {
    fieldLabel : label,
    name : name,
    id : id,
    maxLength : max,
    maxLengthText : label + '长度不能超过' + max + '个字符！',
    selectOnFocus : false,
    labelWidth : lw,
    blankText : label + '不能为空！',
    allowBlank : blank,
    anchor : anchor,
    vtype : vtype,
    tabIndex : tab
  });
  
  return ab;
};

/*
 * 用途:获取单击行的数据 author:rcp date:2016/8/30
 */
tools.OnGridLoad = function(grid, msg) {
  var selModel = grid.getSelectionModel();
  if (selModel.hasSelection()) {
    var selected = selModel.getSelection();
    
    return selected[0].data;
  } else {
    if (msg && !Ext.isEmpty(msg))
      tools.alert(msg);
    else
      tools.alert('请选择需要修改的数据！');
    return null;
  }
};
