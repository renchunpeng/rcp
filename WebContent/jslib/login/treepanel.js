Ext.define('rcp.treepanel', {
			extend : 'Ext.tree.Panel',
			id : 'deptlist',
			rootVisible : false,
			region : 'west',
			title : 'tree panel',
			width : '20%',
			autoScroll : true,
			border : true,
			margins : '2 0 2 2',
			layout : 'fit',
			animate : false,
			/*
			 * root : { text : "根", expanded : true, children : [{ text : "叶子1",
			 * leaf : true }, { text : "叶子2", leaf : true }] children:data }
			 */

			store : Ext.create('Ext.data.TreeStore', {
						id : '1',
						proxy : {
							type : 'ajax',
							// url : 'testtest2.do'
							url : 'jslib/login/MainMenu.js'
						},
						fields : ['text', 'id', 'leaf', 'url', 'jsname']
					}),
			listeners : {
				'itemclick' : {
					fn : function(view, record) {
						rrcp(record.data);
					}
				}
			}

		});