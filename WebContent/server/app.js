/**
 * Module dependencies.
 */

var express = require('express'),
	path = require('path'),
	logger = require('morgan'),
	bodyParser = require('body-parser'),
	cookieParser = require('cookie-parser'),
	compress = require('compression'),
	favicon = require('serve-favicon'),
	methodOverride = require('method-override'),
	errorHandler = require('errorhandler'),
	fs = require('fs');

var https = require('https'),
  http = require('http');

var app = express(),
  config = require('./config');

require('./utils/global');


app.set('port', ARGS[0] || config.httpPort || 60080);
app.set('https_port', config.httpsPort || 60443);

var logPath = path.join(__dirname, 'logs'),
	logFilePath = path.join(logPath, 'access.log');
try {
	fs.readdirSync(logPath);
}
catch (ex) {
	fs.mkdirSync(logPath);
}

app
	.use(compress())
	.use(favicon(path.join(__dirname, 'favicon.png')))
	.use(logger('combined', {
		stream: fs.createWriteStream(logFilePath, {flags: 'a'})
	}))
  .use(bodyParser.urlencoded({extended: false}))
  .use(bodyParser.json())
	.use(cookieParser())
	.use(methodOverride())
	.use(express.static(path.join(__dirname, '../dist')));


require("./all.routes")(app);


if (app.get('env') === 'development') {
	app.use(errorHandler());
}

config.httpOn && http.createServer(app).listen(app.get('port'), function () {
  console.log('Server listening on port ' + app.get('port'));
}) || console.log('http server is off...');

config.httpsOn && https.createServer({
  key: fs.readFileSync(config.httpsKeys.key),
  crt: fs.readFileSync(config.httpsKeys.crt)
}, app).listen(app.get('https_port'), function () {
  console.log('https server is listening on port ' + app.get('https_port'));
}) || console.log('https server is off...');


/*

 server {
 listen 443 ssl;
 server_name enneagram.youkezhong.com;
 location / {
 proxy_pass https://enneagram.youkezhong.com;
 }
 # Nginx Https Configuration
 ssl on;
 ssl_certificate /ocdata/iris/server/secure/keys/server.pem;
 ssl_certificate_key /ocdata/iris/server/secure/keys/server.key;
 ssl_session_timeout 5m;
 ssl_protocols SSLv3 TLSv1;
 ssl_ciphers HIGH:!ADH:!EXPORT56:RC4+RSA:+MEDIUM;
 ssl_prefer_server_ciphers on;
 }

*/

