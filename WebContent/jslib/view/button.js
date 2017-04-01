Ext.define('rcp.button', {
  extend : 'rcp.base',
  
  constructor : function(config) {
    var me = this;
    
    me.callParent(arguments);
  },
  
  OnBeforeLoad : function() {
    var me = this;
    
    me.editControls =
      [{
        id:'bb',
        xtype : 'button',
        text : 'My Button',
        listeners : {
          click : function() {
            me.Save();
          }
        }
      }]
  }
});

Ext.create('rcp.button', {
  Name : 'rcp',
  Age : '24'
});