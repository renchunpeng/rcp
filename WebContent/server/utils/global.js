
global.ARGS = process.argv.splice(2);
global.NEWID = require('./idGenerator').newUuid;
global.moment = require('moment');
global.moment.locale('zh-cn');

global.caught = function (func, next) {
	var _next = next;
	if (typeof _next == 'function') {
		_next = function (err) {
			ERR(err);
		}
	}
	return function (err, data) {
		if (err) {
			_next(err);
		}
		else {
			func.apply(null, [].slice.call(arguments, 1));
		}
	};
};

global.put = function (res, next, p) {
	var result = res.result;
	if (p) {
		result = parserify(p, res.result);
	}
	return caught(result, next);
};
global.puts = function (res, next, p) {
	var result = function (data) {
		res.result({
			list: data
		});
	},
		parse = result;
	if (p) {
		parse = parserify(p, result);
	}
	return caught(parse, next);
};

global.nodify = function(promise, callback, userData) {
	return promise
		.then(function(data) {
			callback(null, userData || data);
		})
		.catch(function(err) {
			callback(err, null);
		})
};

global.clear = function(obj) {
	for (var i in obj) {
		if (obj.hasOwnProperty(i) &&
			(
				obj[i] == null ||
				typeof obj[i] == 'undefined' ||
				obj[i] === ''
			)
		) {
			delete obj[i];
		}
	}
	return obj;
};

global.LOG = function(title, content, type) {
	console.log('------------ [%s] %s ---------------', type || '', title);
	console.log(content);
	console.log('------------ [%s] %s [end] %s', type || '', title, new Date().toLocaleString());
	console.log('#');
	console.log('#');
};
global.ERR = function(error) {
	console.error('XxXxXxXxXxXx [%s] %s XxXxXxXxXxXx', error.number || 99999, error.message);
	console.error(error);
	console.error('XxXxXxXxXxXx [%s] XxXxXxXxXxXx - %s', error.number || 99999, new Date().toLocaleString());
	console.log('#');
	console.log('#');
};

global.errors = require('./errors');


function parserify (parser, callback, options, parserTables) {
	var _callback = callback,
		_options = _.extendOwn({
			withError: false,
			callbackNodify: true
		}, options),
		parse;
	var cb = function (err, payload, func) {
		_options.callbackNodify ? func(err, payload) : func(payload);
	};
	if (typeof parser == 'string') {
		parse = createMapping(parserTables[parser]);
	}
	return _options.withError ?
		function(err, data) {
			parse ?
				parse(data, function(result) {
					cb(err, result, _callback);
				}) :
				cb(err, data, _callback);
		} : function(data) {
		parse ?
			parse(data, function(result) {
				cb(null, result, _callback);
			}) :
			cb(null, data, _callback);
	};
}

function createMapping (table) {
	return function(_data, _resolve) {
		var dataArray = _data;
		if (!(dataArray instanceof Array)) {
			dataArray = [ dataArray ];
		}
		var solution = dataArray.map(function(d) {
			var result = {};
			for (var i in table) {
				if (table.hasOwnProperty(i)) {
					var ti = table[i];
					if (typeof ti.parse == 'function' && ti.field) {
						var _fields = ti.field.split(','),
							_params = _fields.map(function (_field) {
								return d[_field.trim()];
							});
						result[i] = ti.parse.apply(null, _params);
					}
					else {
						result[i] = d[ti];
					}
				}
			}
			return result;
		});
		if (!(_data instanceof Array)) {
			solution = solution[0];
		}
		_resolve(solution);
	}
}

