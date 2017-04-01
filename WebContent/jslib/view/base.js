Ext.define('rcp.base', {
  
  Name : '',
  Age : '',
  editControls : [],
  
  constructor : function(config) {
    var me = this;

    Ext.apply(me, config);

    me.OnBeforeLoad();
    
    me.OnFormLoad();
  },
  
  OnBeforeLoad : function() {
  },
  
  OnFormLoad : function() {
    var me = this;
    
    me.p = Ext.create('Ext.form.Panel', {
      region : 'center',
      title : 'title',// 标题
      collapsible : true,// 右上角上的那个收缩按钮，设为false则不显示
      width : '100%',
      height : '100%',
      items : me.editControls,
      html : "名字：" + this.Name + " 年龄：" + this.Age + "</br>" + this.Test,
      listeners : {
        'add' : {
          fn : me.add,
          scope : me
        }
      }
    
    // panel主体中的内容，可以执行html代码
    });
    
    Ext.getCmp("tpanel" + me.mid).add(me.p);
  },
  
  Save : function() {
    var me = this;
    alert(1);
  },
  
  add : function() {
    console.log(this);
  }

})