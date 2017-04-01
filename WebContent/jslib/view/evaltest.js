Ext.define('rcp.evaltest', {
  
  constructor : function(config) {
    var me = this;
    Ext.apply(me, config);

    var numberer = new Ext.grid.RowNumberer({
      id : flag + '_number',
      header : '序号',
      dataIndex : 'IndexNum',
      locked : true,
      width : 40,
      sortable : false
    });
    
    var fields =
      [{
        name : 'name',
        type : 'string'
      },{
        name : 'age',
        type : 'int'
      },{
        name : 'phone',
        type : 'string'
      },{
        name : 'zhiye',
        type : 'string',
        mapping : 'dd'
      }]

    var mystore = tools.GetJsonStore('jslib/JsonStore/gird.js', fields);
    
    var ebatchcloumns =
      [numberer,{
        text : '姓名',
        dataIndex : 'name'
      },{
        text : '年龄',
        dataIndex : 'age',
        xtype : 'numbercolumn',
        format : '0',
        editor : {
          xtype : "numberfield",
          decimalPrecision : 0,
          selectOnFocus : true
        }
      },{
        text : '电话',
        dataIndex : 'phone',
        editor : "textfield"
      },{
        text : '职业',
        dataIndex : 'zhiye',
        editor : "textfield"
      }]

    var me = this;
    me.ebatchdown = Ext.create('Ext.grid.Panel', {
      id : flag + 'ebatchdown',
      region : 'north',
      height : '100%',
      frame : false,
      border : false,
      margins : '0 2 2 0',
      padding : '0 0 0 0',
      loadMask : true,
      columnLines : true,
      auoload : true,
      title : '考题信息',
      autoScroll : true,
      viewConfig : {
        autoFill : true,
        stripeRows : true
      },
      columns : ebatchcloumns,
      store : mystore,
      listeners : {
        'itemdblclick' : {
          fn : me.Click,
          scope : me
        }
      }
    });
    
    // 分页处理
    if (true) {
      me.ebatchdown.insertDocked(0, Ext.create('Ext.PagingToolbar', {
        id : flag + 'ebatchdownwww',
        store : mystore,
        displayInfo : true,
        displayMsg : 'displatMsg',
        emptyMsg : 'emptyMsg',
        suspendLayout : true,
        dock : 'bottom'
      }));
    }
    
    Ext.getCmp("tpanel" + me.mid).add(me.ebatchdown);
  },
  
  Click : function() {
    var me = this;
    var record = tools.OnGridLoad(me.ebatchdown);
    alert(record.name);
    var el = Ext.fly(Ext.getCmp("tpanel" + me.mid).getEl()); 
    console.log(el);
  }

});

//Ext.create('rcp.evaltest');
