var cookieSession = require('cookie-session');
var fs = require('fs'),
	join = require('path').join;
var ROUTES_PATH = './routes';

var indexRouter = require('express').Router();
indexRouter.get('/', function (req, res, next) {
	res.set('Content-Type', 'text/html');
	fs.readFile(join(__dirname, '../dist/index.html'), function(err, data) {
		if (err) {
			console.log("err: " + err);
			res.send("<html><head/><body>empty: " + __dirname + "</body></html>");
			return;
		}
		res.send(data);
	});
});

var AllRoutes = function(app) {

	app.use(function(req, res, next) {
		res.result = function(err, data) {
			var _err = err, _data = data;
			if (arguments.length == 1 && !(_err instanceof Error)) {
				_err = undefined;
				_data = err;
			}
			var _success = !(_err && _err.number);
			var jsonObject = {success: _success};
			if (_success) {
				jsonObject.error = _err && _err.number || 0;
				jsonObject.message = _err && _err.message || 'success';
			}
			else {
				jsonObject.error = _err.number;
				jsonObject.message = _err.message || 'undefined error!';
			}
			jsonObject.data = _data;
			res.json(jsonObject);
		};
		res.err = function(err) {
			res.result(err, null);
		};
		next();
	});

	app.set('trust proxy', 1);
	app.use(cookieSession({
		name: 'global-cookies-app',
		secret: 'lennon',
		maxAge: 2592000000
	}));

	app.use(indexRouter);

	if (fs.existsSync(join(__dirname, ROUTES_PATH)) && fs.existsSync(join(__dirname, ROUTES_PATH, 'index.js'))) {
		app.use(require(ROUTES_PATH));
	}

	app.use(function(req, res, next) {
		var err = new Error('Page Not Found');
		err.status = err.number = 404;
		next(err);
	});

	app.use(function(err, req, res, next) {
		err.originalUrl = req.originalUrl;
		err.query = req.query;
		err.ip = req.ip;
		ERR(err);
		res.status(
			err.number && err.number >= 10000 && 200 || (err.number || 500)
		);
		res.err(err);
	});

};
module.exports = AllRoutes;
