(function(){var a=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"view");a.prototype.render=function(b,c){for(var d=document.createElement("div"),k=b.renderDescendants(),m=0,n=k.length;m<n;++m)d.appendChild(k[m]);m=1E4;k=null;n=b.__getTagInstances(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"loadingIndicatorBehavior");if(null!=n){var t=n.length;if(0<t&&(n=n[t-1],t=parseInt(n.getAttribute("failSafeDuration"),10),isNaN(t)||(m=t),n=n.getAttribute("failSafeClientHandler"),null!=n&&""!=
n))try{k=new Function("return "+n+"()")}catch(u){adf.mf.log.logInfoResource("AMXInfoBundle",adf.mf.log.level.SEVERE,"amx.view.render","MSG_INVALID_TAG_ATTRIBUTE_VALUE","loadingIndicatorBehavior","failSafeClientHandler",u,n)}}adf.mf.internal.amx._failSafeDuration=m;adf.mf.internal.amx._failSafeClientHandler=k;k=b.__getTagInstances(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"navigationDragBehavior");n=[];m=0;for(t=k.length;m<t;++m)n.push(k[m]);0<n.length&&adf.mf.api.amx.addDragListener(d,{start:a._handleDragStart,
drag:a._handleDrag,end:a._handleDragEnd,threshold:5},{navigationDragBehaviors:n,viewNodeId:c});adf.mf.internal.api.initializePageLevelSystemActionOverrides();k=b.__getTagInstances(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"systemActionBehavior");m=0;for(n=k.length;m<n;++m){var t=k[m],v=t.getAttribute("type");"back"==v&&adf.mf.internal.api.registerSystemActionBehavior(v,a._systemActionBack,t)}return d};a.prototype.__experimentalCLAssociation=function(b,c,d,k){var m=!1;switch(d){case "showpagecomplete":case "mafviewvisible":case "mafviewhidden":case "amxnavigatestart":case "amxnavigateend":m=
!0}a.superclass.__experimentalCLAssociation.call(this,b,c,d,k,m)};a.prototype.findPopup=function(a,b){for(var c=a.getChildren(),d=0,m=c.length;d<m;++d){var n=c[d];if(n.getTag().getAttribute("id")==b)return n}return null};a._systemActionBack=function(a){var b=!1;if(adf.mf.api.amx.acceptEvent()){var c=a.getAttribute("action",!1);void 0!==c&&(b=!0);var d=function(){if(null!=c)try{adf.mf.api.amx.doNavigation(c)}catch(a){adf.mf.api.amx.addMessage("severe",a,null,null)}};a=a.getAttribute("actionListener",
!1);if(null!=a){var m=new amx.ActionEvent,n=[],t=[];n.push(m);t.push(m[".type"]);adf.mf.api.amx.invokeEl(a,n,null,t,d,d)}else d()}return b};a._handleDragStart=function(a,b){if(adf.mf.api.amx.acceptEvent()){for(var c=a.data.navigationDragBehaviors,d=!1,m=!1,n=0,t=c.length;n<t;++n){var u=c[n],v=u.getAttribute("direction");d||"back"!=v||adf.mf.api.amx.isValueTrue(u.getAttribute("disabled"))?m||"forward"!=v||adf.mf.api.amx.isValueTrue(u.getAttribute("disabled"))||(m=!0,a.data.forwardDragBehavior=u):(d=
!0,a.data.backDragBehavior=u);if(d&&m)break}a.data.hasBackDragBehavior=d;a.data.hasForwardDragBehavior=m}};a._handleDrag=function(b,c){if(adf.mf.api.amx.acceptEvent()){var d=Math.abs(c.originalAngle);if((30>=d||150<=d)&&c.requestDragLock(this,!0,!1))if(b.preventDefault(),b.stopPropagation(),c.preventDefault=!0,c.stopPropagation=!0,d=b.data.startPageX,null==d)b.data.startPageX=c.pageX;else{var k=c.pageX-d,m=b.data.dragDirectionIsBack,d=b.data.historyIndicator,n="rtl"==document.documentElement.dir;
if(null==m)0!=k&&(d=document.getElementById("historyIndicator"),null!=d&&adf.mf.api.amx.removeDomNode(d),m=n?0>k:0<k,!m||b.data.hasBackDragBehavior)&&(m||b.data.hasForwardDragBehavior)&&(b.data.dragDirectionIsBack=m,d=document.createElement("div"),d.id="historyIndicator",d.className=m?"amx-view-historyIndicatorBack":"amx-view-historyIndicatorForward",document.getElementById("bodyPageViews").appendChild(d),b.data.historyIndicator=d);else{var t=b.data.indicatorWidth;null==t&&(t=d.offsetWidth,b.data.indicatorWidth=
t);var u=!m;n&&(u=m);k=Math.max(0,Math.min(t,u?-k:k)/t);d.style.opacity=.3+.7*k;m=t*k;u?d.style.right=m-t+"px":d.style.left=m-t+"px";b.data.limitReached=1==k}}}else b.data.limitReached=!1,a._concludeHistoryIndicator(b,c)};a._handleDragEnd=function(b,c){a._concludeHistoryIndicator(b,c)};a._concludeHistoryIndicator=function(a,b){var c=!0===a.data.limitReached,d=!0===a.data.dragDirectionIsBack,m=a.data.historyIndicator;delete a.data.hasBackDragBehavior;delete a.data.hasForwardDragBehavior;delete a.data.startPageX;
delete a.data.historyIndicator;delete a.data.dragDirectionIsBack;delete a.data.limitReached;a.preventDefault();a.stopPropagation();b.preventDefault=!0;b.stopPropagation=!0;if(null!=m){adf.shared.impl.animationUtils._setTransition(m,"all 200ms linear");if(c){var n=document.getElementById(a.data.viewNodeId);if(n){var t=null;(t=d?a.data.backDragBehavior:a.data.forwardDragBehavior)&&adf.mf.api.amx.validate(n,function(){if(adf.mf.api.amx.acceptEvent()){var a=t.getAttribute("action",!1);if(null!=a)try{adf.mf.api.amx.doNavigation(a)}catch(b){adf.mf.api.amx.addMessage("severe",
b,null,null)}}})}}setTimeout(function(){if(null!=m){adf.mf.api.amx.addBubbleEventListener(m,adf.mf.internal.amx.agent.getTransitionEndEventName(),function(){adf.mf.api.amx.removeDomNode(this)});if(!c){var a=m.classList.contains("amx-view-historyIndicatorBack");"rtl"==document.documentElement.dir?a?m.style.right=-m.offsetWidth+"px":m.style.left=-m.offsetWidth+"px":a?m.style.left=-m.offsetWidth+"px":m.style.right=-m.offsetWidth+"px"}m.style.opacity=0}},1)}};adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,
"spacer").prototype.render=function(a){var b=amx.getTextValue(a.getAttribute("width")),c=amx.getTextValue(a.getAttribute("height"));if((a=!amx.isNodeRendered(a))||null==b||0>=b.length)return a||null==c||0>c.length?document.createElement("span"):0!=c.length?(a=document.createElement("div"),a.style.marginTop=c+"px",a):document.createElement("div");if(null==c||0>=c.length)c=0;a=document.createElement("div");var d=a.style;d.display="inline-block";d.marginTop=c+"px";d.marginRight=b+"px";return a};adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,
"verbatim").prototype.render=function(a){var b=document.createElement("div");a=a.getTag().getTextContent();b.innerHTML=a;return b};adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"goLink").prototype.render=function(a){var b;if(adf.mf.api.amx.isValueTrue(a.getAttribute("disabled"))){b=document.createElement("span");b.setAttribute("aria-disabled","true");var c=a.getAttribute("text");if(null!=c){var d=document.createElement("label");d.appendChild(document.createTextNode(c));d.setAttribute("role",
"presentation");d.setAttribute("aria-hidden","true");b.setAttribute("aria-label",c);b.appendChild(d)}}else b=document.createElement("a"),b.setAttribute("href",amx.getTextValue(a.getAttribute("url"))),b.appendChild(document.createTextNode(amx.getTextValue(a.getAttribute("text")))),c="mousedown",amx.hasTouch()&&(c="touchstart"),adf.mf.api.amx.addBubbleEventListener(b,c,function(a){a.stopPropagation()}),adf.mf.api.amx.addBubbleEventListener(b,"click",function(a){a.stopPropagation()});b.setAttribute("role",
"link");c=a.getAttribute("shortDesc");null!=c&&b.setAttribute("title",c);a=a.renderDescendants();c=0;for(d=a.length;c<d;++c)b.appendChild(a[c]);return b};var b=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"outputText");b.prototype.attributeChangeResult=function(a,c,d){switch(c){case "value":return adf.mf.api.amx.AmxNodeChangeResult.REFRESH;default:return b.superclass.attributeChangeResult.call(this,a,c,d)}};b.prototype.render=function(a){var b=document.createElement("span"),
c=a.getAttribute("styleClass");null!=c&&/\bamx-text-sectiontitle\b/.test(c)&&b.setAttribute("role","heading");a=this._getDisplayValue(a,b);b.appendChild(document.createTextNode(a));return b};b.prototype.refresh=function(a,c,d){if(c.hasChanged("value")){var k=a.getId(),k=document.getElementById(k),m=this._getDisplayValue(a,k);k.textContent=m}b.superclass.refresh.call(this,a,c,d)};b.prototype._getDisplayValue=function(a,b){var c=amx.getTextValue(a.getAttribute("value")),d=parseInt(a.getAttribute("truncateAt"));
!isNaN(d)&&0<d&&"undefined"!=typeof a.getAttribute("value")&&(15>d&&(d=15),b.setAttribute("amx-data-value",c),d<c.length&&(c=c.substring(0,d-3)+"..."),b.classList.add("amx-outputText-truncateAt"));return c};adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"outputHtml").prototype.render=function(a,b){var c=document.createElement("div");if("none"==a.getAttribute("security"))c.innerHTML=amx.getTextValue(a.getAttribute("value"));else{var d=amx.getTextValue(a.getAttribute("value"));
c.innerHTML=html_sanitize(d,null,function(a){return b+"_"+a})}return c};var c=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"inputText");c.prototype.getInputValueAttribute=function(){return"value"};c.prototype.attributeChangeResult=function(a,b,d){if(adf.mf.api.amx.isValueTrue(a.getAttribute("readOnly")))return adf.mf.api.amx.AmxNodeChangeResult.RERENDER;var k=!1;if("maximumLength"==b||"value"==b)if(1==a._dirty)k=!0;else{var m=document.getElementById(a.getId()+"__inputElement");
null!=m&&document.activeElement==m&&"Android"==adf.mf.internal.amx.agent.type&&(k=!0)}switch(b){case "maximumLength":return k?adf.mf.api.amx.AmxNodeChangeResult.RERENDER:adf.mf.api.amx.AmxNodeChangeResult.REFRESH;case "value":return k&&a.getAttribute("value")!=d.getOldValue("value")?adf.mf.api.amx.AmxNodeChangeResult.RERENDER:adf.mf.api.amx.AmxNodeChangeResult.REFRESH;case "label":return adf.mf.api.amx.AmxNodeChangeResult.REFRESH;default:return c.superclass.attributeChangeResult.call(this,a,b,d)}};
c.prototype.refresh=function(a,b,d){var k=b.hasChanged("maximumLength"),m=b.hasChanged("value"),n=b.hasChanged("label"),t=a.getId(),u=document.getElementById(t+"__inputElement");if(m||k)this._setValue(a,u),c._updateEmptyMarker(t);k=adf.mf.api.amx.isValueTrue(a.getAttribute("simple"));n&&!k&&this._setLabel(a,u);c.superclass.refresh.call(this,a,b,d)};c.prototype.render=function(a,b){var c=b+"__inputElement",d=amx.createField(a,c);d.fieldLabel.setAttribute("id",a.getId()+"__fieldLabel");var m;a._oldValue=
null;a._dirty=!1;var n="div",t=a.getAttribute("name"),u=amx.getTextValue(a.getAttribute("keyboardDismiss"));"go"==u?n="form":"search"==u&&(n="form",t="search");var v=document.createElement(n);v.className="wrap";d.fieldValue.appendChild(v);var w=adf.mf.api.amx.isValueTrue(a.getAttribute("showRequired"))||adf.mf.api.amx.isValueTrue(a.getAttribute("required"));if(adf.mf.api.amx.isValueTrue(a.getAttribute("readOnly")))m=document.createElement("div"),m.className="readOnlyLabel",m.setAttribute("aria-readonly",
"true"),m.appendChild(document.createTextNode(amx.getTextValue(a.getAttribute("value")))),v.appendChild(m);else{var x=a.getAttribute("rows");if(x&&1<parseInt(x,10))m=document.createElement("textarea"),m.setAttribute("rows",x),m.setAttribute("aria-multiline","true");else if(adf.mf.api.amx.isValueTrue(a.getAttribute("secret")))m=document.createElement("input"),m.setAttribute("type","password");else{x=a.getAttribute("inputType");switch(x){case "number":case "email":case "url":case "tel":break;default:x=
"text"}m=document.createElement("input");m.setAttribute("type",x)}m.setAttribute("id",c);m.className="amx-inputText-content";m.setAttribute("placeholder",amx.getTextValue(a.getAttribute("hintText")));c=amx.getTextValue(a.getAttribute("autoCapitalize"));""==c&&"auto"==c||m.setAttribute("autocapitalize",c);c=amx.getTextValue(a.getAttribute("autoCorrect"));""==c&&"auto"==c||m.setAttribute("autocorrect",c);v.appendChild(m);this._registerTextInputHandlers(m,a);c="mousedown";amx.hasTouch()&&(c="touchstart");
adf.mf.api.amx.addBubbleEventListener(m,c,function(a){a.stopPropagation()});this._setValue(a,m);adf.mf.internal.amx.registerBlur(m,function(){m.parentNode.classList.remove("amx-wrap-active");if(0!=a._dirty){a._dirty=!1;var b=m.value,c=a.getAttribute("maximumLength");0<c&&(b=b.substring(0,c),m.value=b);c=a.getConverter();if(null!=c){var d=c.getAsObject(b);if(""===d&&""!==b)return;b=c.getAsString(d);a.getTag().isAttributeElBound("value")||(m.value=b)}a.setAttributeResolvedValue("value",b);a._oldValue!==
b?(c=new amx.ValueChangeEvent(a._oldValue,b),adf.mf.api.amx.processAmxEvent(a,"valueChange","value",b,c)):adf.mf.api.amx.processAmxEvent(a,"valueChange","value",b)}});"go"==u?d.fieldRoot.className+=" amx-inputText-go":"search"==u&&(d.fieldRoot.className+=" amx-inputText-search",u=document.createElement("a"),u.id=b+"__clear",c=adf.mf.resource.getInfoString("AMXInfoBundle","amx_inputText_LABEL_CLEAR_BUTTON"),u.setAttribute("aria-label",c),u.className="amx-inputText-clear",d.fieldRoot.className=""==
m.value?d.fieldRoot.className+" amx-inputText-empty":d.fieldRoot.className+" amx-inputText-nonEmpty",v.appendChild(u),adf.mf.api.amx.addBubbleEventListener(u,amx.hasTouch()?"touchstart":"mousedown",this._handleClear,a));var y=this;adf.mf.internal.amx.registerFocus(m,function(){m.parentNode.classList.add("amx-wrap-active");y._setOldValue(a,m)})}"form"==n&&adf.mf.api.amx.addBubbleEventListener(v,"submit",function(){m.blur();return!1});null!=t&&m.setAttribute("name",t);m.setAttribute("role","textbox");
adf.mf.api.amx.isValueTrue(a.getAttribute("simple"))?(n=amx.getTextValue(a.getAttribute("label")),m.setAttribute("aria-label",n)):m.setAttribute("aria-labelledby",b+"::lbl");w&&m.setAttribute("aria-required","true");adf.mf.api.amx.isValueTrue(a.getAttribute("disabled"))&&(m.setAttribute("disabled",!0),m.setAttribute("aria-disabled","true"));adf.mf.api.amx.applyRequiredMarker(a,d);return d.fieldRoot};c.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-inputText.js"};c.prototype._registerTextInputHandlers=
function(a,b){var c=this,d="Android"==adf.mf.internal.amx.agent.type,m=d&&"Chrome"==adf.mf.internal.amx.agent.subtype,n=function(a){return c._handleTextInput(a,b,m)};adf.mf.api.amx.addBubbleEventListener(a,"keypress",n);adf.mf.api.amx.addBubbleEventListener(a,"keyup",n);adf.mf.api.amx.addBubbleEventListener(a,"textInput",n);d&&adf.mf.api.amx.addBubbleEventListener(a,"input",function(a){return b._dirty=!0})};c.prototype._handleTextInput=function(a,b,d){var k=b.getId(),m=document.getElementById(k+"__inputElement");
null!=document.getElementById(k+"__clear")&&(27==a.charCode&&(m.value=""),c._updateEmptyMarker(k));var n=b.getAttribute("maximumLength");if(0>=n)return b._dirty=!0;k=m.value;if("textInput"==a.type)if(a.oldData)k="",a=a.oldData;else if(d&&a.data)k="",a=a.data;else return b._dirty=!0;else d?(a=k,k=""):a=String.fromCharCode(a.charCode);d=n-k.length;return a.length>d?(0<d&&(b._dirty=!0,m.value=k+a.substring(0,d)),!1):b._dirty=!0};c.prototype._handleClear=function(a){a.preventDefault();a.stopPropagation();
a=a.data;var b=a.getId(),d=document.getElementById(b+"__inputElement");d.focus();d.value="";a._dirty=!0;document.getElementById(b+"__clear");c._updateEmptyMarker(b)};c.prototype._setValue=function(a,b){if(b){a._dirty=!1;var c=a.getAttribute("value");null!==c&&""!==c?(c=amx.getTextValue(c),b.value=c):b.value=null;this._setOldValue(a,b)}};c.prototype._setOldValue=function(a,b){if(void 0===b||null===b)b=document.getElementById(a.getId()+"__inputElement");b&&(a._oldValue=b.value)};c.prototype._setLabel=
function(a,b){var c=amx.getTextValue(a.getAttribute("label")),d=document.getElementById(a.getId()+"__fieldLabel");d&&(d.removeChild(d.childNodes[0]),d.appendChild(document.createTextNode(c)));b&&adf.mf.api.amx.isValueTrue(a.getAttribute("simple"))&&b.setAttribute("aria-label",c)};c._updateEmptyMarker=function(a){if(null!=document.getElementById(a+"__clear")){var b=document.getElementById(a+"__inputElement");null!=b&&(a=document.getElementById(a),null!=a&&(a.className=""==b.value?a.className.replace(/amx-inputText-(nonE|e)?mpty/,
"amx-inputText-empty"):a.className.replace(/amx-inputText-(nonE|e)?mpty/,"amx-inputText-nonEmpty")))}};var d=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"image");d.prototype.render=function(a){var b=document.createElement("img"),c=amx.getTextValue(a.getAttribute("source")),d=adf.mf.api.amx.buildRelativePath(c);d!=c&&a.setAttributeResolvedValue("_originalPath",c);b.setAttribute("src",d);adf.mf.api.amx.addBubbleEventListener(b,"error",this._handleError,a);a=a.getAttribute("shortDesc");
null==a||""==a?(b.setAttribute("aria-hidden","true"),b.setAttribute("alt","")):b.setAttribute("alt",a);return b};d.prototype._handleError=function(a){var b=a.target;a=a.data;var c=a.getAttribute("_originalPath");null!=c?(a.setAttributeResolvedValue("_originalPath",null),b.setAttribute("src","file://"+c)):(adf.mf.api.amx.removeBubbleEventListener(b,"error"),b.setAttribute("data-original-src",b.getAttribute("src")),b.setAttribute("src","data:image/gif;base64,R0lGODlhAQABAPAAAP+A/wAAACH5BAUAAAAALAAAAAABAAEAQAICRAEAOw\x3d\x3d"),
b.classList.add("amx-image-error"))};adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"commandLink").prototype.render=function(a){var b=document.createElement("a");b.setAttribute("role","link");adf.mf.api.amx.addBubbleEventListener(b,"click",function(a){a.stopPropagation();a.preventDefault()});adf.mf.api.amx.isValueTrue(a.getAttribute("disabled"))?(b.className="amx-disabled",b.setAttribute("aria-disabled","true")):adf.mf.api.amx.isValueTrue(a.getAttribute("readOnly"))?(b.className=
"amx-readOnly",b.setAttribute("aria-readonly","true")):b.setAttribute("href","#");adf.mf.api.amx.addBubbleEventListener(b,"tap",function(c){b.focus();c.preventDefault();c.stopPropagation();b.focus();adf.mf.api.amx.isValueTrue(a.getAttribute("disabled"))||adf.mf.api.amx.isValueTrue(a.getAttribute("readOnly"))||adf.mf.api.amx.validate(b,function(){if(adf.mf.api.amx.acceptEvent()){var b=new amx.ActionEvent;adf.mf.api.amx.processAmxEvent(a,"action",void 0,void 0,b,function(){var b=a.getAttributeExpression("action",
!0);null!=b&&adf.mf.api.amx.doNavigation(b)})}})});var c=a.getAttribute("text");if(null!=c){var d=document.createElement("label");d.appendChild(document.createTextNode(c));d.setAttribute("role","presentation");d.setAttribute("aria-hidden","true");b.setAttribute("aria-label",c);b.appendChild(d)}c=a.getAttribute("shortDesc");null!=c&&b.setAttribute("title",c);adf.mf.api.amx.enableAmxEvent(a,b,"swipe");adf.mf.api.amx.enableAmxEvent(a,b,"tapHold");for(var c=a.renderDescendants(),d=0,m=c.length;d<m;++d)b.appendChild(c[d]);
return b};d=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"commandButton");d.prototype.render=function(a){var b=document.createElement("div");b.setAttribute("tabindex","0");var c=document.createElement("label");c.className="amx-commandButton-label";c.appendChild(document.createTextNode(amx.getTextValue(a.getAttribute("text"))));b.appendChild(c);b.setAttribute("role","button");"__back"==a.getAttributeExpression("action",!0)&&(a=a.getAttribute("styleClass"),null!=a&&/\bamx-commandButton-normal\b/.test(a)||
b.classList.add("amx-commandButton-back"));return b};d.prototype.init=function(a,b){if(b.getAttribute("icon")){if(-1<b.getAttribute("icon").indexOf(".")){var c=document.createElement("img");c.className="amx-commandButton-icon";c.setAttribute("src",adf.mf.api.amx.buildRelativePath(b.getAttribute("icon")));a.insertBefore(c,a.firstChild)}"trailing"==b.getAttribute("iconPosition")?a.classList.add("amx-iconPosition-trailing"):a.classList.add("amx-iconPosition-leading")}for(var c=a.childNodes,d=c.length,
m=null,n=0;n<d;n++){var t=c[n];if(t.classList.contains("amx-commandButton-label")){m=t;break}}c=null!=m?m.textContent:"";d=b.getAttribute("shortDesc");null!=d&&a.setAttribute("aria-label",d);adf.mf.api.amx.isValueTrue(b.getAttribute("disabled"))&&(a.setAttribute("aria-disabled","true"),a.classList.add("amx-disabled"));""==c&&a.classList.add("amx-label-no-text");adf.mf.api.amx.addBubbleEventListener(a,"tap",function(c){a.focus();c.preventDefault();c.stopPropagation();adf.mf.api.amx.isValueTrue(b.getAttribute("disabled"))||
adf.mf.api.amx.validate(a,function(){if(adf.mf.api.amx.acceptEvent()){var a=new amx.ActionEvent;adf.mf.api.amx.processAmxEvent(b,"action",void 0,void 0,a,function(){var a=b.getAttributeExpression("action",!0,!0);null!=a&&adf.mf.api.amx.doNavigation(a)})}})});c=document.createElement("div");c.className="amx-extendedTarget";a.appendChild(c);adf.mf.api.amx.isValueTrue(b.getAttribute("disabled"))||(c="mousedown",d="mouseup",amx.hasTouch()&&(c="touchstart",d="touchend"),adf.mf.api.amx.addBubbleEventListener(a,
c,function(b){a.classList.add("amx-selected");a.setAttribute("aria-pressed","true")}),adf.mf.api.amx.addBubbleEventListener(a,d,function(b){a.classList.remove("amx-selected");a.setAttribute("aria-pressed","false")}),adf.mf.api.amx.addBubbleEventListener(a,"mouseout",function(){a.classList.remove("amx-selected");a.setAttribute("aria-pressed","false")}))};d.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-commandButton.js"};(function(){amx.createField=function(a,b){var c={},d=document.createElement("div");
d.className="field";c.fieldRoot=d;c.isReadOnly=adf.mf.api.amx.isValueTrue(a.getAttribute("readOnly"));c.isDisable=adf.mf.api.amx.isValueTrue(a.getAttribute("disabled"));var m=document.createElement("div");m.className="field-label";c.fieldLabel=m;d.appendChild(m);if(adf.mf.api.amx.isValueTrue(a.getAttribute("simple")))d.classList.add("amx-simple");else{var n=document.createElement("label"),t=a.getId()+"::lbl";n.setAttribute("id",t);null!=b&&n.setAttribute("for",b);n.appendChild(document.createTextNode(amx.getTextValue(a.getAttribute("label"))));
m.appendChild(n)}m=document.createElement("div");m.className="field-value";c.fieldValue=m;d.appendChild(m);return c}})(jQuery)})();