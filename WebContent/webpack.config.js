const webpack = require('atool-build/lib/webpack');
const pxtorem = require('postcss-pxtorem');
const path = require("path");

module.exports = function (webpackConfig, env) {

  //const jsFileName = args.hash ? '[name]-[chunkhash].js' : '[name].js';
  //const cssFileName = args.hash ? '[name]-[chunkhash].css' : '[name].css';
  //const commonName = args.hash ? 'common-[chunkhash].js' : 'common.js';

  webpackConfig.babel.plugins.push('transform-runtime');
  //这里会进行按需加载
  webpackConfig.babel.plugins.push(['import', {
    libraryName: 'antd-mobile',
    style: 'css'
  }]);
  // Support hmr 在开发环境下可调用redux调试工具
  if (env === 'development') {
    webpackConfig.devtool = '#eval';
    webpackConfig.babel.plugins.push('dva-hmr');
  } else {
    webpackConfig.babel.plugins.push('dev-expression');
  }

  // Don't extract common.js and common.css
  webpackConfig.plugins = webpackConfig.plugins.filter(function (plugin) {
    return !(plugin instanceof webpack.optimize.CommonsChunkPlugin);
  });

  // Support CSS Modules
  // Parse all less files as css module.
  webpackConfig.module.loaders.forEach(function (loader, index) {
    if (typeof loader.test === 'function' && loader.test.toString().indexOf('\\.less$') > -1) {
      loader.include = /node_modules/;
      loader.test = /\.less$/;
    }
    if (loader.test.toString() === '/\\.module\\.less$/') {
      loader.exclude = /node_modules/;
      loader.test = /\.less$/;
    }
    if (typeof loader.test === 'function' && loader.test.toString().indexOf('\\.css$') > -1) {
      loader.include = /node_modules/;
      loader.test = /\.css$/;
    }
    if (loader.test.toString() === '/\\.module\\.css$/') {
      loader.exclude = /node_modules/;
      loader.test = /\.css$/;
    }

  });
  //高清适配方案
  webpackConfig.postcss.push(pxtorem({
    rootValue: 100,
    propWhiteList: [],
  }));
  //指定打包后的代码存放路径
  webpackConfig.output.path = path.join(__dirname, './');
  webpackConfig.output.filename = '[name].js';
  webpackConfig.output.chunkFilename = '[name].js';
  //webpackConfig.output.filename = jsFileName;
  //webpackConfig.output.chunkFilename = jsFileName;
  return webpackConfig;
};
