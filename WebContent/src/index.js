import './index.html'
import './index.less'
//import './page/styles/Evaluation.less'
import dva from 'dva';
//import { useRouterHistory } from 'dva/router';

import { browserHistory } from 'dva/router';
//import { createHashHistory } from 'history';

import { createHashHistory } from 'history';
//import createLogger from 'redux-logger';
//import createLoading from 'dva-loading';
//打印出redux的日志，详见：https://github.com/evgenyrodionov/redux-logger/blob/master/example/src/index.js
const opts = {
  level: {
    prevState: () => `info`,
    error: () => `error`,
    nextState: () => `info`,
  },
  duration: true,
  actionTransformer: (action) => ({
    ...action,
    type: String(action.type),
  }),
  colors: {
    prevState: () => `#FFEB3B`,
    nextState: () => `#4CAF50`,
  },
  diff: true
}

// 1. Initialize
const app = dva(/*{
  history: useRouterHistory(createHashHistory)({ queryKey: false })
  这里面会有一些可配置的属性
   history,
   initialState,
   onError,
   onAction,
   onChange,
   onReducer,
   onEffect,
   onHmr,
   extraReducers,
   extraEnhancers,
   });
}*/
  {
    //initialState: {myName: 'xujingchen'},
    onError(e) {
      //message.error(e.message,3)
      console.log(`这里监听到了报错！${JSON.stringify(e)}`);
    }
    //onAction: createLogger(opts),
  },
  //createLoading()
);

// 2. Plugins
//app.use({});

// 3. Model:注意数据模型的名称不能与页面的名称相同,注意名字不要起的太长：http://stackoverflow.com/questions/36131934/module-not-found-error-cannot-resolve-file-or-directory
//app.model(require('./models/men'));
app.model(require('./models/user'));
app.model(require('./models/per'));
app.model(require('./models/cla'));
app.model(require('./models/glist'));
app.model(require('./models/payMent'));
app.model(require('./models/cart'));
app.model(require('./models/brand'));
app.model(require('./models/gooddm'));
app.model(require('./models/orderList'));
app.model(require('./models/orderevaluationModel'));
app.model(require('./models/detail'));
app.model(require('./models/orderConfirmModel'));
app.model(require('./models/address'));
app.model(require('./models/index'));
app.model(require('./models/search'));
app.model(require('./models/itergration'));
//app.model(require('./models/test1'));
//app.model(require('./models/list'));
app.model(require('./models/tabBar'));
app.model(require('./models/giftCard'));
app.model(require('./models/giftCardList'));
app.model(require('./models/couponList'));
app.model(require('./models/shop'));
app.model(require('./models/shopPrd'));
app.model(require('./models/shopPrdList'));
app.model(require('./models/shopOrder'));
app.model(require('./models/shopDetail'));
app.model(require('./models/sOrderDetail'));
app.model(require('./models/scan'));


// 4. Router
app.router(require('./router'));

// 5. Start
//启动应用加应用挂在到ID为'root'的div中
app.start('#root');
