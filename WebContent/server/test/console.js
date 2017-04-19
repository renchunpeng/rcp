var _ = require('underscore');

var a = {
	c: '11',
	d: function(){return 'x';}
};

var b = _.extendOwn(a, {
	d: function(){
		return 'y';
	}
});

console.log(('22'+'asd'.bold+'23').green.inverse);