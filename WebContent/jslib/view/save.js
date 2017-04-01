Ext.define('rcp.save', {
  extend : 'rcp.base',
  
  constructor : function(config) {
    // var me = this;
    
    Ext.apply(config, {
      Name : 'wq',
      Test : 'shjdbsjhdjshbdhsbdj'
    
    });
    
    this.callParent(arguments);
    
  },
  
  OnBeforeLoad : function() {
    var me = this;
    
    me.Age = '24';
    me.editControls =
      [tools.FormText('事件标题', 'alerttitle', 'alerttitle', 50, '20%', false, 2),{
        xtype : 'button',
        text : 'My Button',
        listeners : {
          click : function() {
            me.save();
          }
        }
      }];
  },
  
  save : function() {
    var me = this;
    me.p.form.submit({
      url : 'testtest3.do'
    });
  }

})

// Ext.create('rcp.save', {
// Name : 'rcp',
// Age : '22'
// });

