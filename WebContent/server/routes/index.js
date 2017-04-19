
var indexRouter = require('express').Router();

indexRouter.get('/index', function (req, res, next) {
  res.result({
    hello: 'world!'
  });
});


module.exports = indexRouter;
