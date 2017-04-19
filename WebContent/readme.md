### 项目简介

**项目前端结构：**

```
|-- eshop
|-- js
|-- lflweb
|-- META-INF
|-- mock
|-- node_modules
|-- public
|-- server
|-- src
|   |-- assets
|   |-- components          //自定义组件
|   |-- demojson            //模拟用测试数据，生产环境有效
|   |-- img                 //页面图片存放地址
|   |-- models              //前端数据模型
|   |-- page                //前端页面
|   |-- routes
|   |-- services            //前端接口调用文件
|   |-- tests
|   |-- utils               //前端自定义工具模块
|   |-- index.html
|   |-- index.js
|   |-- index.less
|   |-- router.js
|-- WEB-INF
|-- .editorconfig
|-- .eslintignore
|-- .eslintrc
|-- package.json
|-- pay.htm
|-- proxy.config.js         //dora配置文件
|-- readme.md 
|-- webpack.config.js
```

- dist: 最终生成的文件，最终生产环境部署所用的```.css|.html|.js```文件都生成在这里；
- mock: 一个用于测试npm包；
- node_modules: 根据```package.json```文件下载的安装包；
- server：如果后端在用的是```node```进行开发，则对应的后端的代码放在这里；
- src: 前端源文件存放的位置；
  - assets: 前端静态文件存放的位置；
  - components: 前端react通用组件存放的位置；
  - demoJson: 前后端分离开发时，可以用json文件来模拟接口进行开发；
  - img: 用于存放页面图片，图片的存放请先按照图片调用的页面新建文件夹，然后将图片添加到对应的文件夹中。
  - models: ```dav```独有的数据模型对象；
  - page: 前端页面存放的位置；
  - routes: ```dva```项目原来存放页面的位置；
  - service： 前端通过```fetch```请求获取数据的文件；
  - tests: 
  - utils: 前端中常用的工具方法存放的地方；目前集成了fetch请求工具、价格计算确保计算精度的Number工具、格式化日期格式的DateFormat工具。

**项目开发及打包：**

  - ```npm i```命令用于安装项目所依赖的包，该命令需在WebContent目录下执行。
  - ```npm start```命令用于日常开发，在webstorm中打开项目后只需要运行一次该命令即可，需注意该命令是在WebContent目录下运行的。

  > attention: 关于dora插件的使用，dora插件可用于模拟服务端接口，在前后端分离的开发模式下，有利于前端开发工作的展开，具体关于dora的使用方法，详见proxy.config.js文件，如果后端的接口为开发完成，可以一下方法进行接口模拟：
  > ```javascript
  > 'GET /lflweb/address/shoneaddress/*': function (req, res) {
  >   var body;
  >   setTimeout(function () {
  >     body = {
  >       success: true,
  >       msg: 'success',
  >       data: [{
  >         account: 'asdfasfafsa',
  >         phoneNumber: 'asdfafasdfas',
  >         district: [340000, 341500, 341522],
  >         addressDetail: 'asdggggggggggg',
  >         isdefault: true,
  >         radioValue: 0
  >       }]
  >     }
  >     res.json(body)
  >   }, 0)
  > }
  > ```
  > 如果后台接口已开发完成，则可以通过以下的配置，实现跨域调试
  > ```javascript
  > const forwardAddress = 'http://192.168.100.105:8080';
> 
  > module.exports = {
    > //地址保存接口
  > 'POST /lflweb/address/seuseraddress': forwardAddress
> };
>   ```
>在开发调试的过程中为了模拟微信登录，需要在本地设置cookie，你需要在>->src->services->userAction.js中添加如下代码(注意提交的时候要将这段代码注释)：
>```javascript
>    var openId = "omEi307kdfaKm-wJ3YvFOjP3vJ0Y";
>		document.cookie = "openId=" + openId;
>```

  - ```npm run build```该命令用于最后的代码打包，打包后的代码将保存到WebContent根目录下，其主要生成的文件为 index.html、index.css、index.js、1.js、2.js.................
  
  
