webpackJsonp([12],{30:function(e,t){"use strict";t.__esModule=!0,t["default"]=function(e,t){var n={};for(var a in e)t.indexOf(a)>=0||Object.prototype.hasOwnProperty.call(e,a)&&(n[a]=e[a]);return n}},53:function(e,t,n){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}Object.defineProperty(t,"__esModule",{value:!0}),t["default"]=void 0;var r=n(3),o=a(r),s=n(2),u=a(s),i=n(6),l=a(i),d=n(5),p=a(d),f=n(1),c=a(f),h=n(192),m=a(h),v=n(47),b=a(v),y=n(4),g=a(y),C=Object.assign||function(e){for(var t,n=1,a=arguments.length;n<a;n++){t=arguments[n];for(var r in t)Object.prototype.hasOwnProperty.call(t,r)&&(e[r]=t[r])}return e},x=function(e){function t(){return(0,u["default"])(this,t),(0,l["default"])(this,e.apply(this,arguments))}return(0,p["default"])(t,e),t.prototype.render=function(){var e,t=this.props,n=t.prefixCls,a=t.className,r=t.style,s=t.children,u=(0,g["default"])((e={},(0,o["default"])(e,a,!!a),(0,o["default"])(e,n+"-wrapper",!0),e)),i=c["default"].createElement("label",{className:u,style:r},c["default"].createElement(m["default"],C({},(0,b["default"])(this.props,["className","style"]))),s);return this.props.wrapLabel?i:c["default"].createElement(m["default"],C({},this.props))},t}(c["default"].Component);t["default"]=x,x.defaultProps={prefixCls:"am-checkbox",wrapLabel:!0},e.exports=t["default"]},114:function(e,t){"use strict";function n(e){var t={};return Object.keys(e).forEach(function(n){0===n.indexOf("data-")&&(t[n]=e[n])}),t}Object.defineProperty(t,"__esModule",{value:!0}),t["default"]=n,e.exports=t["default"]},117:[932,125],125:645,151:function(e,t,n){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}Object.defineProperty(t,"__esModule",{value:!0}),t["default"]=void 0;var r=n(3),o=a(r),s=n(2),u=a(s),i=n(6),l=a(i),d=n(5),p=a(d),f=n(1),c=a(f),h=n(4),m=a(h),v=n(53),b=a(v),y=n(114),g=a(y),C=n(47),x=a(C),P=Object.assign||function(e){for(var t,n=1,a=arguments.length;n<a;n++){t=arguments[n];for(var r in t)Object.prototype.hasOwnProperty.call(t,r)&&(e[r]=t[r])}return e},N=function(e){function t(){return(0,u["default"])(this,t),(0,l["default"])(this,e.apply(this,arguments))}return(0,p["default"])(t,e),t.prototype.render=function(){var e,t=this.props,n=t.prefixCls,a=t.style,r=t.className,s=(0,m["default"])((e={},(0,o["default"])(e,n+"-agree",!0),(0,o["default"])(e,r,r),e));return c["default"].createElement("div",P({},(0,g["default"])(this.props),{className:s,style:a}),c["default"].createElement(b["default"],P({},(0,x["default"])(this.props,["style"]),{className:n+"-agree-label"})))},t}(c["default"].Component);t["default"]=N,N.defaultProps={prefixCls:"am-checkbox"},e.exports=t["default"]},152:function(e,t,n){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}function r(){}Object.defineProperty(t,"__esModule",{value:!0}),t["default"]=void 0;var o=n(3),s=a(o),u=n(2),i=a(u),l=n(6),d=a(l),p=n(5),f=a(p),c=n(1),h=a(c),m=n(4),v=a(m),b=n(17),y=a(b),g=n(53),C=a(g),x=n(47),P=a(x),N=Object.assign||function(e){for(var t,n=1,a=arguments.length;n<a;n++){t=arguments[n];for(var r in t)Object.prototype.hasOwnProperty.call(t,r)&&(e[r]=t[r])}return e},w=y["default"].Item,E=function(e){function t(){return(0,i["default"])(this,t),(0,d["default"])(this,e.apply(this,arguments))}return(0,f["default"])(t,e),t.prototype.render=function(){var e,t=this,n=this.props,a=n.prefixCls,o=n.listPrefixCls,u=n.className,i=n.children,l=n.disabled,d=n.checkboxProps,p=void 0===d?{}:d,f=(0,v["default"])((e={},(0,s["default"])(e,a+"-item",!0),(0,s["default"])(e,a+"-item-disabled",l===!0),(0,s["default"])(e,u,u),e)),c=(0,P["default"])(this.props,["listPrefixCls","onChange","disabled","checkboxProps"]);l?delete c.onClick:c.onClick=c.onClick||r;var m={};return["name","defaultChecked","checked","onChange","disabled"].forEach(function(e){e in t.props&&(m[e]=t.props[e])}),h["default"].createElement(w,N({},c,{prefixCls:o,className:f,thumb:h["default"].createElement(C["default"],N({},p,m))}),i)},t}(h["default"].Component);t["default"]=E,E.defaultProps={prefixCls:"am-checkbox",listPrefixCls:"am-list"},e.exports=t["default"]},153:function(e,t,n){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}Object.defineProperty(t,"__esModule",{value:!0});var r=n(53),o=a(r),s=n(152),u=a(s),i=n(151),l=a(i);o["default"].CheckboxItem=u["default"],o["default"].AgreeItem=l["default"],t["default"]=o["default"],e.exports=t["default"]},487:function(e,t,n){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}function r(e){function t(e){console.log("-------------------------"+e)}function n(){location.href="???#/orderConfirm/"+s}var a=e.cart,r=(e.dispatch,e.user),o=a.shopCart,s=r.custid;c["default"].createElement(l["default"],{type:"plus-circle"}),c["default"].createElement(l["default"],{type:"minus-circle"});return c["default"].createElement("div",{style:{paddingBottom:"2rem"}},o.map(function(e,n){return c["default"].createElement(u["default"],{key:n,renderHeader:function(){return c["default"].createElement(b,{defaultChecked:!0,"data-seed":"logId",onChange:function(e){return console.log("checkbox",e)}},e.shopName)}},e.goodList.map(function(e,n){return c["default"].createElement(v,{key:n,defaultChecked:!0,onChange:function(){return t(e.goodId)}},c["default"].createElement("div",{style:{width:"100%",height:"2rem"}},c["default"].createElement("img",{style:{width:"2rem",height:"2rem","float":"left"},src:e.goodImg,alt:""}),c["default"].createElement("span",null,e.goodName),c["default"].createElement("br",null),c["default"].createElement("span",null,"\u5546\u54c1\u53c2\u6570"),c["default"].createElement("br",null),c["default"].createElement("span",{style:{"float":"right"}},"200yuan"),c["default"].createElement("br",null)))}))}),c["default"].createElement("div",{style:{width:"100%",lineHeight:"1rem",height:"1rem",backgroundColor:"white",position:"fixed",bottom:"1rem",borderTop:"1px solid #e6e6e6"}},c["default"].createElement("div",{style:{width:"2rem",height:"1rem","float":"left"}},c["default"].createElement(b,{defaultChecked:!0,"data-seed":"logId",onChange:function(e){return console.log("checkbox",e)}},"\u5168\u9009")),"\u603b\u8ba1200\u5143",c["default"].createElement("div",{onClick:n,style:{width:"2rem",height:"1rem",textAlign:"center",lineHeight:"1rem",color:"white",backgroundColor:"orange","float":"right"}},"\u53bb\u7ed3\u7b97\uff08\uff09")))}function o(e){var t=e.cart,n=e.user;return{cart:t,user:n}}Object.defineProperty(t,"__esModule",{value:!0});var s=(n(16),n(17)),u=a(s),i=(n(20),n(19)),l=a(i),d=(n(117),n(153)),p=a(d),f=n(1),c=a(f),h=n(13),m=n(747),v=(a(m),p["default"].CheckboxItem),b=p["default"].AgreeItem;r.PropTypes={},t["default"]=(0,h.connect)(o)(r),e.exports=t["default"]},746:function(e,t,n){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}Object.defineProperty(t,"__esModule",{value:!0});var r=n(30),o=a(r),s=n(2),u=a(s),i=n(6),l=a(i),d=n(5),p=a(d),f=n(1),c=a(f),h=n(128),m=a(h),v=function(e){function t(){return(0,u["default"])(this,t),(0,l["default"])(this,e.apply(this,arguments))}return(0,p["default"])(t,e),t.prototype.render=function(){var e=this.props,t=e.prefixCls,n=e.disabled,a=(0,o["default"])(e,["prefixCls","disabled"]);return c["default"].createElement(m["default"],{disabled:n,activeClassName:t+"-handler-active"},c["default"].createElement("span",a))},t}(f.Component);v.propTypes={prefixCls:f.PropTypes.string,disabled:f.PropTypes.bool},t["default"]=v,e.exports=t["default"]},747:function(e,t,n){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}function r(){}function o(e){e.preventDefault()}Object.defineProperty(t,"__esModule",{value:!0});var s=n(3),u=a(s),i=n(7),l=a(i),d=n(1),p=a(d),f=n(4),c=a(f),h=n(748),m=a(h),v=n(746),b=a(v),y=p["default"].createClass({displayName:"InputNumber",propTypes:{focusOnUpDown:d.PropTypes.bool,onChange:d.PropTypes.func,onKeyDown:d.PropTypes.func,onKeyUp:d.PropTypes.func,prefixCls:d.PropTypes.string,disabled:d.PropTypes.bool,onFocus:d.PropTypes.func,onBlur:d.PropTypes.func,readOnly:d.PropTypes.bool,max:d.PropTypes.number,min:d.PropTypes.number,step:d.PropTypes.oneOfType([d.PropTypes.number,d.PropTypes.string]),upHandler:d.PropTypes.node,downHandler:d.PropTypes.node},mixins:[m["default"]],getDefaultProps:function(){return{focusOnUpDown:!0,prefixCls:"rc-input-number"}},componentDidMount:function(){this.componentDidUpdate()},componentDidUpdate:function(){this.props.focusOnUpDown&&this.state.focused&&document.activeElement!==this.refs.input&&this.focus()},onKeyDown:function g(e){38===e.keyCode?this.up(e):40===e.keyCode&&this.down(e);var g=this.props.onKeyDown;if(g){for(var t=arguments.length,n=Array(t>1?t-1:0),a=1;a<t;a++)n[a-1]=arguments[a];g.apply(void 0,[e].concat(n))}},onKeyUp:function C(e){this.stop();var C=this.props.onKeyUp;if(C){for(var t=arguments.length,n=Array(t>1?t-1:0),a=1;a<t;a++)n[a-1]=arguments[a];C.apply(void 0,[e].concat(n))}},getValueFromEvent:function(e){return e.target.value},focus:function(){this.refs.input.focus()},render:function(){var e,t=(0,l["default"])({},this.props),n=t.prefixCls,a=t.disabled,s=t.readOnly,i=(0,c["default"])((e={},(0,u["default"])(e,n,!0),(0,u["default"])(e,t.className,!!t.className),(0,u["default"])(e,n+"-disabled",a),(0,u["default"])(e,n+"-focused",this.state.focused),e)),d="",f="",h=this.state.value;if(isNaN(h))d=n+"-handler-up-disabled",f=n+"-handler-down-disabled";else{var m=Number(h);m>=t.max&&(d=n+"-handler-up-disabled"),m<=t.min&&(f=n+"-handler-down-disabled")}var v=!t.readOnly&&!t.disabled,y=void 0;return y=this.state.focused?this.state.inputValue:this.toPrecisionAsStep(this.state.value),void 0===y&&(y=""),p["default"].createElement("div",{className:i,style:t.style},p["default"].createElement("div",{className:n+"-handler-wrap"},p["default"].createElement(b["default"],{ref:"up",disabled:!!d||a||s,prefixCls:n,unselectable:"unselectable",onTouchStart:v&&!d?this.up:r,onTouchEnd:this.stop,onMouseDown:v&&!d?this.up:r,onMouseUp:this.stop,onMouseLeave:this.stop,className:n+"-handler "+n+"-handler-up "+d},this.props.upHandler||p["default"].createElement("span",{unselectable:"unselectable",className:n+"-handler-up-inner",onClick:o})),p["default"].createElement(b["default"],{ref:"down",disabled:!!f||a||s,prefixCls:n,unselectable:"unselectable",onTouchStart:v&&!f?this.down:r,onTouchEnd:this.stop,onMouseDown:v&&!f?this.down:r,onMouseUp:this.stop,onMouseLeave:this.stop,className:n+"-handler "+n+"-handler-down "+f},this.props.downHandler||p["default"].createElement("span",{unselectable:"unselectable",className:n+"-handler-down-inner",onClick:o}))),p["default"].createElement("div",{className:n+"-input-wrap"},p["default"].createElement("input",{type:t.type,placeholder:t.placeholder,onClick:t.onClick,className:n+"-input",autoComplete:"off",onFocus:this.onFocus,onBlur:this.onBlur,onKeyDown:this.onKeyDown,onKeyUp:this.onKeyUp,autoFocus:t.autoFocus,readOnly:t.readOnly,disabled:t.disabled,max:t.max,min:t.min,name:t.name,onChange:this.onChange,ref:"input",value:y})))}});t["default"]=y,e.exports=t["default"]},748:function(e,t){"use strict";function n(){}Object.defineProperty(t,"__esModule",{value:!0});var a=200,r=600;t["default"]={getDefaultProps:function(){return{max:1/0,min:-(1/0),step:1,style:{},defaultValue:"",onChange:n,onKeyDown:n,onFocus:n,onBlur:n}},getInitialState:function(){var e=void 0,t=this.props;return e="value"in t?t.value:t.defaultValue,e=this.toNumber(e),{inputValue:this.toPrecisionAsStep(e),value:e,focused:t.autoFocus}},componentWillReceiveProps:function(e){if("value"in e){var t=this.toNumber(e.value);this.setState({inputValue:e.value?e.value.toString():e.value,value:t})}},componentWillUnmount:function(){this.stop()},onChange:function(e){var t=this.getValueFromEvent(e).trim();this.setState({inputValue:t}),this.props.onChange(this.toNumberWhenUserInput(t))},onFocus:function(){var e;this.setState({focused:!0}),(e=this.props).onFocus.apply(e,arguments)},onBlur:function(e){for(var t=this,n=arguments.length,a=Array(n>1?n-1:0),r=1;r<n;r++)a[r-1]=arguments[r];this.setState({focused:!1});var o=this.getCurrentValidValue(this.state.inputValue);this.setValue(o,function(){var n;(n=t.props).onBlur.apply(n,[e].concat(a))})},getCurrentValidValue:function(e){var t=e,n=this.props;return""===t?t="":this.isNotCompleteNumber(t)?t=this.state.value:(t=Number(t),t<n.min&&(t=n.min),t>n.max&&(t=n.max)),this.toNumber(t)},setValue:function(e,t){var n=this.isNotCompleteNumber(parseFloat(e,10))?void 0:parseFloat(e,10),a=""+n!==this.state.inputValue;"value"in this.props?this.setState({inputValue:this.toPrecisionAsStep(this.state.value)},t):this.setState({value:e,inputValue:this.toPrecisionAsStep(e)},t),a&&this.props.onChange(n)},getPrecision:function(e){var t=e.toString();if(t.indexOf("e-")>=0)return parseInt(t.slice(t.indexOf("e-")+1),10);var n=0;return t.indexOf(".")>=0&&(n=t.length-t.indexOf(".")-1),n},getMaxPrecision:function(e){var t=this.props.step,n=this.getPrecision(t);if(!e)return n;var a=this.getPrecision(e);return a>n?a:n},getPrecisionFactor:function(e){var t=this.getMaxPrecision(e);return Math.pow(10,t)},toPrecisionAsStep:function(e){if(this.isNotCompleteNumber(e)||""===e)return e;var t=Math.abs(this.getMaxPrecision(e));return t?Number(e).toFixed(t):e.toString()},isNotCompleteNumber:function(e){return isNaN(e)||""===e||e.toString().indexOf(".")===e.toString().length-1},toNumber:function(e){return this.isNotCompleteNumber(e)?e:Number(e)},toNumberWhenUserInput:function(e){return/\.0+$/.test(e)&&this.state.focused?e:this.toNumber(e)},upStep:function(e){var t=this.props,n=t.step,a=t.min,r=this.getPrecisionFactor(e),o=Math.abs(this.getMaxPrecision(e)),s=void 0;return s="number"==typeof e?((r*e+r*n)/r).toFixed(o):a===-(1/0)?n:a,this.toNumber(s)},downStep:function(e){var t=this.props,n=t.step,a=t.min,r=this.getPrecisionFactor(e),o=Math.abs(this.getMaxPrecision(e)),s=void 0;return s="number"==typeof e?((r*e-r*n)/r).toFixed(o):a===-(1/0)?-n:a,this.toNumber(s)},step:function(e,t){t&&t.preventDefault();var n=this.props;if(!n.disabled){var a=this.getCurrentValidValue(this.state.inputValue);if(!this.isNotCompleteNumber(a)){var r=this[e+"Step"](a);r>n.max||r<n.min||(this.setValue(r),this.setState({focused:!0}))}}},stop:function(){this.autoStepTimer&&clearTimeout(this.autoStepTimer)},down:function(e,t){var n=this;e.persist&&e.persist(),this.stop(),this.step("down",e),this.autoStepTimer=setTimeout(function(){n.down(e,!0)},t?a:r)},up:function(e,t){var n=this;e.persist&&e.persist(),this.stop(),this.step("up",e),this.autoStepTimer=setTimeout(function(){n.up(e,!0)},t?a:r)}},e.exports=t["default"]}});