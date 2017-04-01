Ext.define('rcp.uploadpanel', {
  extend : 'Ext.form.Panel',
  width : 500,
  height : '100%',
  frame : true,
  // title: '文件上传',
  items :
    [{
      xtype : 'textfield',
      fieldLabel : '样品编号',
      id : 'finfo',
      name : 'finfo'
    },{
      xtype : 'filefield',
      id : 'fiupload',
      emptyText : '请点击右边按钮选择文件！',
      fieldLabel : '选择文件',
      name : 'file',
      buttonText : '浏览文件',
      buttonConfig : {
        iconCls : 'upload-icon'
      }
    }],
  buttons :
    [{
      text : '保存文件',
      handler : function() {
        var fiform = this.up('form').getForm();
        if (fiform.isValid()) {
          fiform.submit({
            type : 'ajax',
            url : 'UpLoadMyFile.do',
            method : "POST",
            waitMsg : ' 正在上传，请稍候...',
            success : function(form, action) {
              Ext.Msg.alert('Success', '文件上传成功！');
              console.log(action);
            },
            failure : function(form, action) {
              Ext.Msg.alert("Failure", "文件上传失败");
            }
          });
        }
      }
    },{
      text : '重新上传',
      handler : function() {
        this.up('form').getForm().reset();
      }
    }]
});
