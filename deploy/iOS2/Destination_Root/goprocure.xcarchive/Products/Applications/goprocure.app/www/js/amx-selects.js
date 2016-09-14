(function(){function a(a){var b=[];a.visitChildren(new adf.mf.api.amx.VisitContext,function(a,c){if(!c.isReadyToRender())return adf.mf.api.amx.VisitResult.REJECT;if(c.getTag().getNsPrefixedName()==adf.mf.api.amx.AmxTag.NAMESPACE_AMX+":selectItem")b.push({label:c.getAttribute("label"),value:c.getAttribute("value")});else if(c.getTag().getNsPrefixedName()==adf.mf.api.amx.AmxTag.NAMESPACE_AMX+":selectItems"){var d;if(adf.mf.environment.profile.dtMode){d=[];for(var e=1;4>e;e++){var g=adf.mf.resource.getInfoString("AMXInfoBundle",
"amx_selectManyCheckbox_ITEM_LABEL",e);g||(g="Item "+e);d.push({value:e,label:g})}}else d=c.getAttribute("value");e=adf.mf.internal.util.is_array(d);if(null!=d)for(var h in d){var k=d[h];e?void 0===k.label&&"row"===k[".type"]&&null!=k.bindings?(k=k.bindings,g=null==k.label?null:k.label.inputValue,k=null==k.label?null:k.label.inputValue):(g=k.label,k=k.value):g=h;null!=g&&""!=g&&"."!=g.charAt(0)&&b.push({label:g,value:k})}}return adf.mf.api.amx.VisitResult.ACCEPT});return b}function b(a){a=document.getElementById(a.data);
null!=a&&a.classList.add("amx-focus")}function c(a){a=document.getElementById(a.data);null!=a&&a.classList.remove("amx-focus")}var d=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"selectBooleanCheckbox");d.prototype.getInputValueAttribute=function(){return"value"};d.prototype.render=function(a,b){var c=amx.createField(a),d=c.fieldRoot,e=adf.mf.api.amx.isValueTrue(a.getAttribute("value")),g=c.isDisable;e?d.classList.add("on"):d.classList.add("off");var h=document.createElement("div");
h.setAttribute("id",b+"::checkbox");h.className="checkbox";h.setAttribute("role","checkbox");e?h.setAttribute("aria-checked","true"):h.setAttribute("aria-checked","false");1==(adf.mf.api.amx.isValueTrue(a.getAttribute("showRequired"))||adf.mf.api.amx.isValueTrue(a.getAttribute("required")))&&h.setAttribute("aria-required","true");var k=adf.mf.api.amx.isValueTrue(a.getAttribute("simple")),m=a.getAttribute("label"),e=b+"::txt";h.setAttribute("aria-labelledby",(null!=m&&0<m.length&&!k?b+"::lbl":e)+" "+
e);k=document.createElement("div");k.className="img-check";h.appendChild(k);c.fieldValue.appendChild(h);a.getAttribute("text")&&(k=document.createElement("div"),k.setAttribute("id",e),k.className="checkbox-text",k.textContent=a.getAttribute("text"),c.fieldValue.appendChild(k));g&&(d.classList.add("amx-disabled"),h.setAttribute("aria-disabled","true"));c.isReadOnly&&h.setAttribute("aria-readonly","true");c.isReadOnly||g||adf.mf.api.amx.addBubbleEventListener(c.fieldValue,"tap",this._handleTap,a);adf.mf.api.amx.applyRequiredMarker(a,
c);return d};d.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-selectBooleanCheckbox.js"};d.prototype._handleTap=function(a){if(adf.mf.api.amx.acceptEvent()){var b=a.data,c=b.getId(),c=document.getElementById(c),d=c.classList.contains("on"),e=!d;b.setAttributeResolvedValue("value",e);var g=new amx.ValueChangeEvent(!e,e);adf.mf.api.amx.processAmxEvent(b,"valueChange","value",e,g);d?(c.classList.add("off"),c.classList.remove("on")):(c.classList.add("on"),c.classList.remove("off"));
a.stopPropagation()}};d.prototype.attributeChangeResult=function(a,b,c){switch(b){case "label":return b=c.getOldValue("label"),c=a.getAttribute("label"),!adf.mf.api.amx.isValueTrue(a.getAttribute("simple"))&&null!=b&&0<b.length&&null!=c&&0<c.length?adf.mf.api.amx.AmxNodeChangeResult.REFRESH:adf.mf.api.amx.AmxNodeChangeResult.RERENDER;case "text":return b=c.getOldValue("text"),a=a.getAttribute("text"),null!=b&&null!=a?adf.mf.api.amx.AmxNodeChangeResult.REFRESH:adf.mf.api.amx.AmxNodeChangeResult.RERENDER;
case "value":return adf.mf.api.amx.AmxNodeChangeResult.REFRESH;default:return d.superclass.attributeChangeResult.call(this,a,b,c)}};d.prototype.refresh=function(a,b,c){var e=a.getId();b.hasChanged("label")&&(document.getElementById(e+"::lbl").textContent=a.getAttribute("label"));if(b.hasChanged("value")){var g=adf.mf.api.amx.isValueTrue(a.getAttribute("value")),h=document.getElementById(e),k=document.getElementById(e+"::checkbox");g?(h.classList.add("on"),h.classList.remove("off")):(h.classList.add("off"),
h.classList.remove("on"));g?k.setAttribute("aria-checked","true"):k.setAttribute("aria-checked","false")}b.hasChanged("text")&&(document.getElementById(e+"::txt").textContent=a.getAttribute("text"));d.superclass.refresh.call(this,a,b,c)};var e=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"selectBooleanSwitch");e.prototype.getInputValueAttribute=function(){return"value"};e.prototype.render=function(a,b){var c=amx.createField(a),d=c.fieldRoot,g=adf.mf.api.amx.isValueTrue(a.getAttribute("value"));
g?d.classList.add("on"):d.classList.add("off");c.isDisable&&d.classList.add("amx-disabled");var h=a.getAttribute("onLabel")||"ON",k=a.getAttribute("offLabel")||"OFF",m=document.createElement("div");m.setAttribute("id",b+"__switch");if(c.isReadOnly)m.className="readOnlyLabel",m.textContent=g?amx.getTextValue(h):amx.getTextValue(k),c.fieldValue.appendChild(m);else{m.className="switch";c.fieldValue.appendChild(m);var A=document.createElement("label");A.setAttribute("aria-hidden","true");A.className=
"label-on";A.textContent=amx.getTextValue(h);m.appendChild(A);h=document.createElement("label");h.setAttribute("aria-hidden","true");h.className="label-off";h.textContent=amx.getTextValue(k);m.appendChild(h);k=document.createElement("div");k.className="switch-button";k.setAttribute("role","checkbox");h=a.getId()+"::lbl";k.setAttribute("aria-labelledby",h);g?k.setAttribute("aria-checked","true"):k.setAttribute("aria-checked","false");c.isDisable&&k.setAttribute("aria-disabled","true");1==(adf.mf.api.amx.isValueTrue(a.getAttribute("showRequired"))||
adf.mf.api.amx.isValueTrue(a.getAttribute("required")))&&k.setAttribute("aria-required","true");m.appendChild(k);if(!c.isDisable){var B=function(b){if(adf.mf.api.amx.acceptEvent()){var c=!g;a.setAttributeResolvedValue("value",c);var e=new amx.ValueChangeEvent(!c,c);adf.mf.api.amx.processAmxEvent(a,"valueChange","value",c,e);(g=!g)?(d.classList.add("on"),d.classList.remove("off")):(d.classList.add("off"),d.classList.remove("on"));b&&b.stopPropagation()}};adf.mf.api.amx.addBubbleEventListener(m,"tap",
B);var C=!1;adf.mf.api.amx.addDragListener(m,{start:function(a,b){},drag:function(a,b){if(!C){var c=e._buildSwipeExtra(m,a,b);c&&(c=c.swipeType,"rtl"==document.documentElement.dir?"swipeLeft"==c?c="swipeEnd":"swipeRight"==c&&(c="swipeStart"):"swipeLeft"==c?c="swipeStart":"swipeRight"==c&&(c="swipeEnd"),g&&"swipeStart"==c||!g&&"swipeEnd"==c)&&(C=!0,m.removeAttribute("data-swipeDone"),B())}},end:function(a,b){C=!1;m.removeAttribute("data-swipeDone")},threshold:5})}}adf.mf.api.amx.applyRequiredMarker(a,
c);return d};e.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-selectBooleanSwitch.js"};e._buildSwipeExtra=function(a,b,c){b=null;if("true"!=a.getAttribute("data-swipeDone")&&c){var d=c.pageX-c.startPageX,e=c.pageY-c.startPageY,g=Math.abs(d),e=Math.abs(e);g>=e&&5<g&&(c=Math.abs(c.originalAngle),30>=c||150<=c)&&(b={},b.swipeType=-1<d?"swipeRight":"swipeLeft",a.setAttribute("data-swipeDone","true"))}return b};var g=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,
"selectOneButton");g.prototype.getInputValueAttribute=function(){return"value"};g.prototype.render=function(b){var c=amx.createField(b),d=c.fieldRoot,e;c.isReadOnly?(e=document.createElement("div"),e.className="readOnlyLabel",e.setAttribute("aria-readOnly","true")):(e=document.createElement("div"),e.className="selectItemsContainer");c.fieldValue.appendChild(e);"vertical"===b.getAttribute("layout")&&d.classList.add("vertical");e.setAttribute("role","radiogroup");e.setAttribute("aria-multiselectable",
"false");(adf.mf.api.amx.isValueTrue(b.getAttribute("showRequired"))||adf.mf.api.amx.isValueTrue(b.getAttribute("required")))&&e.setAttribute("aria-required","true");var g=b.getId()+"::lbl";e.setAttribute("aria-labelledby",g);c.isDisable&&(e.setAttribute("aria-disabled","true"),d.classList.add("amx-disabled"));c.isDisable||adf.mf.api.amx.addBubbleEventListener(e,"tap",function(a){if(adf.mf.api.amx.acceptEvent()&&!c.isReadOnly){for(var d=a.target;null!=d&&-1==d.className.indexOf("amx-selectOneButton")&&
-1==d.className.indexOf("amx-selectItem");)d=d.parentNode;if(-1!=d.className.indexOf("amx-selectItem")){var g=null,h=e.getElementsByClassName("amx-selected");0<h.length&&(h=h[0],g=adf.mf.internal.amx._getNonPrimitiveElementData(h,"labelValue").value,h.classList.remove("amx-selected"),h.setAttribute("aria-checked","false"));d.classList.add("amx-selected");d.setAttribute("aria-checked","false");d=adf.mf.internal.amx._getNonPrimitiveElementData(d,"labelValue").value;b.setAttributeResolvedValue("value",
d);g=new amx.ValueChangeEvent(g,d);adf.mf.api.amx.processAmxEvent(b,"valueChange","value",d,g);a.stopPropagation()}}});for(var g=a(b),h=g.length,k=Math.floor(100/h),m=100-k*h,A=0;A<h;++A){var B=g[A];if(c.isReadOnly)b.getAttribute("value")==B.value&&(e.textContent=B.label);else{var C=document.createElement("div");C.className="amx-selectItem";C.textContent=B.label;adf.mf.internal.amx._setNonPrimitiveElementData(C,"labelValue",B);"vertical"!==b.getAttribute("layout")&&(1<=m--?C.style.width=k+1+"%":C.style.width=
k+"%");C.setAttribute("role","radio");e.appendChild(C);b.getAttribute("value")==B.value?(C.classList.add("amx-selected"),C.setAttribute("aria-checked","true")):C.setAttribute("aria-checked","false")}}adf.mf.api.amx.applyRequiredMarker(b,c);return d};g.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-selectOneButton.js"};g=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"selectOneRadio");g.prototype.getInputValueAttribute=function(){return"value"};g.prototype.render=
function(b){var c=amx.createField(b),d=c.fieldRoot,e=document.createElement("div");e.className="selectItemsContainer";c.isReadOnly&&(e=document.createElement("div"),e.className="readOnlyLabel",e.setAttribute("aria-readonly","true"));c.fieldValue.appendChild(e);e.setAttribute("role","radiogroup");var g=b.getId()+"::lbl";e.setAttribute("aria-labelledby",g);c.isReadOnly&&e.setAttribute("aria-readOnly","true");(g=adf.mf.api.amx.isValueTrue(b.getAttribute("showRequired"))||adf.mf.api.amx.isValueTrue(b.getAttribute("required")))&&
e.setAttribute("aria-required","true");c.isDisable&&(e.setAttribute("aria-disabled","true"),d.classList.add("amx-disabled"));c.isDisable||c.isReadOnly||adf.mf.api.amx.addBubbleEventListener(e,"tap",function(a){if(adf.mf.api.amx.acceptEvent()&&!c.isReadOnly){for(var d=a.target;null!=d&&-1==d.className.indexOf("amx-selectOneRadio")&&-1==d.className.indexOf("amx-selectItem");)d=d.parentNode;if(-1!=d.className.indexOf("amx-selectItem")){var g=null,h=e.getElementsByClassName("amx-selected");0<h.length&&
(h=h[0],g=adf.mf.internal.amx._getNonPrimitiveElementData(h,"labelValue").value,h.classList.remove("amx-selected"),d.setAttribute("aria-checked","false"));d.classList.add("amx-selected");d.setAttribute("aria-checked","true");d=adf.mf.internal.amx._getNonPrimitiveElementData(d,"labelValue").value;b.setAttributeResolvedValue("value",d);g=new amx.ValueChangeEvent(g,d);adf.mf.api.amx.processAmxEvent(b,"valueChange","value",d,g);a.stopPropagation()}}});var h=a(b),k;for(k in h){var m=h[k];if(c.isReadOnly)b.getAttribute("value")==
m.value&&(e.textContent=m.label);else{var A=document.createElement("div");A.className="amx-selectItem";g&&A.setAttribute("aria-required","true");var B=document.createElement("div");B.className="radio";A.appendChild(B);var C=document.createElement("div");C.className="checkmark";B.appendChild(C);B.appendChild(document.createTextNode(m.label));adf.mf.internal.amx._setNonPrimitiveElementData(A,"labelValue",m);e.appendChild(A);A.setAttribute("role","radio");b.getAttribute("value")==m.value?(A.classList.add("amx-selected"),
A.setAttribute("aria-checked","true")):A.setAttribute("aria-checked",!1)}}adf.mf.api.amx.applyRequiredMarker(b,c);return d};g.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-selectOneRadio.js"};g=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"selectManyCheckbox");g.prototype.getInputValueAttribute=function(){return"value"};g.prototype.render=function(b,c){var d=amx.createField(b),e=d.fieldRoot,g=document.createElement("div"),h=c+"::lbl";g.className="selectItemsContainer";
g.setAttribute("role","group");g.setAttribute("aria-labelledby",h);d.isReadOnly&&(g=document.createElement("div"),g.className="readOnlyLabel");d.fieldValue.appendChild(g);d.isDisable&&e.classList.add("amx-disabled");d.isDisable||adf.mf.api.amx.addBubbleEventListener(g,"tap",function(a){if(adf.mf.api.amx.acceptEvent()&&!d.isReadOnly){for(var c=a.target;null!=c&&-1==c.className.indexOf("amx-selectManyCheckbox")&&-1==c.className.indexOf("amx-selectItem");)c=c.parentNode;if(-1!=c.className.indexOf("amx-selectItem")){for(var e=
[],h=g.getElementsByClassName("amx-selected"),k=h.length,m=0;m<k;m++){var t=h[m],t=adf.mf.internal.amx._getNonPrimitiveElementData(t,"labelValue").value;e.push(t)}h=!c.classList.contains("amx-selected");adf.mf.internal.amx.addOrRemoveCSSClassName(h,c,"amx-selected");c=[];h=g.getElementsByClassName("amx-selected");k=h.length;for(m=0;m<k;m++)t=h[m],t=adf.mf.internal.amx._getNonPrimitiveElementData(t,"labelValue").value,c.push(t);b.setAttributeResolvedValue("value",c);e=new amx.ValueChangeEvent(e,c);
adf.mf.api.amx.processAmxEvent(b,"valueChange","value",c,e);a.stopPropagation()}}});var h=a(b),k;for(k in h){var m=h[k],A=b.getAttribute("value");adf.mf.internal.util.is_array(A)||(A=Array(A));if(d.isReadOnly)0<A.indexOf(m.value)&&g.appendChild(document.createTextNode(", "+m.label)),0==A.indexOf(m.value)&&g.appendChild(document.createTextNode(m.label));else{var B=document.createElement("div");B.className="amx-selectItem";var C=document.createElement("div");C.className="checkbox";C.setAttribute("role",
"checkbox");(A=-1<A.indexOf(m.value))?C.setAttribute("aria-checked","true"):C.setAttribute("aria-checked","false");1==(adf.mf.api.amx.isValueTrue(b.getAttribute("showRequired"))||adf.mf.api.amx.isValueTrue(b.getAttribute("required")))&&C.setAttribute("aria-required","true");d.isDisable&&C.setAttribute("aria-disabled","true");var D=b.getId()+":"+h.indexOf(m)+"::txt";C.setAttribute("aria-labelledby",D);B.appendChild(C);var F=document.createElement("div");F.className="img-check";C.appendChild(F);C=document.createElement("div");
C.setAttribute("id",D);C.className="checkbox-text";B.appendChild(C);C.textContent=m.label;adf.mf.internal.amx._setNonPrimitiveElementData(B,"labelValue",m);g.appendChild(B);A&&B.classList.add("amx-selected")}}adf.mf.api.amx.applyRequiredMarker(b,d);return e};g.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-selectManyCheckbox.js"};g=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"selectOneChoice");g.prototype.getInputValueAttribute=function(){return"value"};
g.prototype.render=function(d,e){var g=amx.createField(d),h=g.fieldRoot,k,m=adf.mf.api.amx.isValueTrue(d.getAttribute("showRequired"))||adf.mf.api.amx.isValueTrue(d.getAttribute("required")),y=adf.mf.api.amx.isValueTrue(d.getAttribute("disabled")),z=adf.mf.api.amx.isValueTrue(d.getAttribute("readOnly")),A=a(d),y=y||0==A.length;k=z?document.createElement("div"):document.createElement("select");k.className="selectItemsContainer";k.setAttribute("role","listbox");k.setAttribute("aria-multiselectable",
"false");k.setAttribute("aria-labelledby",e+"::lbl");z&&k.setAttribute("aria-readOnly","true");m&&k.setAttribute("aria-required","true");y&&(k.setAttribute("aria-disabled","true"),k.setAttribute("disabled","true"));g.fieldValue.appendChild(k);adf.mf.internal.amx.registerFocus(k);adf.mf.internal.amx.registerBlur(k);m=adf.mf.internal.amx.agent.type;"gecko"!=m&&"trident"!=m&&adf.mf.api.amx.addBubbleEventListener(k,"tap",function(a){a.stopPropagation()});adf.mf.api.amx.addBubbleEventListener(k,"change",
function(a){if(adf.mf.api.amx.acceptEvent()&&!g.isReadOnly&&!y){a=adf.mf.internal.amx._getNonPrimitiveElementData(this.options[this.selectedIndex],"labelValue");var b=a.value,c=adf.mf.internal.amx._getNonPrimitiveElementData(h,"_oldValue");null==c&&(c=void 0);d.setAttributeResolvedValue("value",b);c=new amx.ValueChangeEvent(c,b);adf.mf.api.amx.processAmxEvent(d,"valueChange","value",b,c);adf.mf.internal.amx._setNonPrimitiveElementData(h,"_oldValue",a.value)}});1!=z&&0==y&&(adf.mf.api.amx.addBubbleEventListener(k,
"focus",b,e),adf.mf.api.amx.addBubbleEventListener(k,"blur",c,e));for(var B in A)z=A[B],g.isReadOnly?d.getAttribute("value")==z.value&&(k.textContent=z.label):(m=document.createElement("option"),m.value=z.value,m.className="amx-selectItem",m.textContent=z.label,adf.mf.internal.amx._setNonPrimitiveElementData(m,"labelValue",z),k.appendChild(m),m.setAttribute("role","option"),d.getAttribute("value")==z.value?(m.setAttribute("selected",!0),m.setAttribute("aria-selected",!0),adf.mf.internal.amx._setNonPrimitiveElementData(h,
"_oldValue",z.value)):m.setAttribute("aria-selected",!1));adf.mf.api.amx.isValueFalse(d.getAttribute("isReadOnly"))&&(A=k.selectedIndex,0<A&&(k=adf.mf.internal.amx._getNonPrimitiveElementData(k.options[A],"labelValue"),adf.mf.internal.amx._setNonPrimitiveElementData(h,"_oldValue",k.value)));adf.mf.api.amx.applyRequiredMarker(d,g);return h};g.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-selectOneChoice.js"};var h=!1;if(!adf.mf.environment.profile.dtMode&&("dev"==adf._bootstrapMode||
"hosted"==adf._bootstrapMode)){var k=adf.mf.api.getQueryString(),g=adf.mf.api.getQueryStringParamValue(k,"amx_skin_folder_override"),k=adf.mf.api.getQueryStringParamValue(k,"amx_skin_override"),m=adf.mf.internal.amx.agent.type;if(null!=g&&-1!=g.indexOf("android"))h=!0;else if(null!=k&&-1!=k.indexOf("android"))h=!0;else if("gecko"==m||"trident"==m||"webkit"==m)h=!0}g=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"selectManyChoice");g.prototype.getInputValueAttribute=function(){return"value"};
g.prototype.render=function(d,e){var g=adf.mf.api.amx.isValueTrue(d.getAttribute("showRequired"))||adf.mf.api.amx.isValueTrue(d.getAttribute("required"));if("Android"==adf.mf.internal.amx.agent.type||h){var k=amx.createField(d),m=k.fieldRoot,x=document.createElement("div"),y=document.createElement("span");this._updateText(y,d.getAttribute("value"),d);x.appendChild(y);adf.mf.internal.amx._setNonPrimitiveElementData(x,"value",d.getAttribute("value"));adf.mf.api.amx.applyRequiredMarker(d,k);g&&x.setAttribute("aria-required",
"true");var z=adf.mf.api.amx.isValueTrue(d.getAttribute("disabled")),A=a(d);0==z&&0==A.length&&(z=!0,m.setAttribute("class",m.getAttribute("class")+" amx-disabled"));z&&m.setAttribute("aria-disabled","true");g=d.getAttribute("readOnly");if(adf.mf.api.amx.isValueTrue(g))x.setAttribute("class","amx-selectManyChoice-root-readOnly"),y.setAttribute("class","amx-selectManyChoice-text-readOnly");else{x.setAttribute("class","amx-selectManyChoice-root");y.setAttribute("class","amx-selectManyChoice-text");
g=document.createElement("div");g.setAttribute("class","amx-selectManyChoice-iconWrapper");y=document.createElement("div");y.setAttribute("class","amx-selectManyChoice-iconStyle");x.appendChild(g);g.appendChild(y);var B=function(a,b,c){for(var d in b){var e=b[d],g=document.createElement("div");g.setAttribute("class","amx-selectManyChoice-picker-item");var h=document.createElement("div");h.textContent=e.label;h.setAttribute("class","amx-selectManyChoice-picker-item-centered-label");var k=document.createElement("div");
k.setAttribute("class","amx-selectManyChoice-picker-item-checkmark");null!=c&&-1!=c.indexOf(e.value)&&k.classList.add("checked");g.appendChild(h);g.appendChild(k);adf.mf.internal.amx._setNonPrimitiveElementData(g,"itemValue",e.value);adf.mf.api.amx.addBubbleEventListener(g,"tap",function(){var a=this.children[1];a.classList.contains("checked")?a.classList.remove("checked"):a.classList.add("checked")});a.appendChild(g)}},C=function(){var a=document.createElement("div");a.setAttribute("class","amx-selectManyChoice-picker-modalOverlay amx-purge-on-nav");
a.id="amx-selectManyChoice-picker-modalOverlay";var b=document.createElement("div");b.setAttribute("class","amx-selectManyChoice-picker-wrapper amx-purge-on-nav");b.id="amx-selectManyChoice-picker-wrapper";var c=document.createElement("div");c.setAttribute("class","amx-selectManyChoice-picker-label");c.textContent=d.getAttribute("label");b.appendChild(c);var e=document.createElement("div");e.setAttribute("class","amx-selectManyChoice-picker-inner-container");c=adf.mf.internal.amx._getNonPrimitiveElementData(x,
"value");B(e,A,c);b.appendChild(e);c=document.createElement("div");c.setAttribute("class","amx-selectManyChoice-picker-button-set");c.textContent=adf.mf.resource.getInfoString("AMXInfoBundle","amx_selectManyChoice_LABEL_BUTTON_SET");adf.mf.api.amx.addBubbleEventListener(c,"tap",function(){event.preventDefault();event.stopPropagation();for(var c=e.children,g=[],h=0;h<c.length;++h){var k=c[h];k.children[1].classList.contains("checked")&&(k=adf.mf.internal.amx._getNonPrimitiveElementData(k,"itemValue"),
g.push(k))}c=adf.mf.internal.amx._getNonPrimitiveElementData(x,"value");d.setAttributeResolvedValue("value",g);c=new amx.ValueChangeEvent(c,g);adf.mf.api.amx.removeDomNode(a);adf.mf.api.amx.removeDomNode(b);adf.mf.api.amx.processAmxEvent(d,"valueChange","value",g,c);adf.mf.internal.amx._setNonPrimitiveElementData(x,"value",g)});b.appendChild(c);c=document.createElement("div");c.setAttribute("class","amx-selectManyChoice-picker-button-cancel");c.textContent=adf.mf.resource.getInfoString("AMXInfoBundle",
"amx_selectManyChoice_LABEL_BUTTON_CANCEL");var g=function(){event.preventDefault();event.stopPropagation();adf.mf.api.amx.removeDomNode(a);adf.mf.api.amx.removeDomNode(b)};adf.mf.api.amx.addBubbleEventListener(c,"tap",g);b.appendChild(c);adf.mf.api.amx.addBubbleEventListener(a,"tap",g);c={};c.overlay=a;c.picker=b;return c};adf.mf.api.amx.addBubbleEventListener(x,"tap",function(){if(adf.mf.api.amx.acceptEvent()&&0==z){var a=C();document.body.appendChild(a.overlay);document.body.appendChild(a.picker);
event.stopPropagation()}})}k.fieldValue.appendChild(x);return m}var k=amx.createField(d),m=k.fieldRoot,y=adf.mf.api.amx.isValueTrue(d.getAttribute("readOnly")),z=adf.mf.api.amx.isValueTrue(d.getAttribute("disabled")),A=a(d),z=z||0==A.length,D=this._createSelectItemsContainer(y);D.setAttribute("role","listbox");D.setAttribute("aria-multiselectable","true");D.setAttribute("aria-labelledby",e+"::lbl");y&&D.setAttribute("aria-readonly","true");g&&D.setAttribute("aria-required","true");z&&(D.setAttribute("aria-disabled",
"true"),D.setAttribute("disabled","true"));k.fieldValue.appendChild(D);adf.mf.api.amx.addBubbleEventListener(D,"tap",function(a){a.stopPropagation()});adf.mf.internal.amx.registerFocus(D);adf.mf.internal.amx.registerBlur(D);1!=y&&0==z&&(adf.mf.api.amx.addBubbleEventListener(D,"blur",this._handleBlur,{amxNode:d}),adf.mf.api.amx.addBubbleEventListener(D,"focus",b,e),adf.mf.api.amx.addBubbleEventListener(D,"blur",c,e));g=d.getAttribute("value");adf.mf.internal.util.is_array(g)||(g=null==g?[]:Array(g));
y?this._createReadOnlyDom(g,D,A):this._createEditableDom(g,D,A);adf.mf.api.amx.applyRequiredMarker(d,k);return m};g.prototype.destroy=function(a,b){var c=document.getElementById("amx-selectManyChoice-picker-modalOverlay");adf.mf.api.amx.removeDomNode(c);c=document.getElementById("amx-selectManyChoice-picker-wrapper");adf.mf.api.amx.removeDomNode(c)};g.prototype.__getTestJavaScriptURI=function(a){return"js/testing/amx-selectManyChoice.js"};g.prototype._updateText=function(b,c,d){if("undefined"===typeof c||
null==c||0==0<c.length)b.textContent="";else if(1==c.length){d=a(d);for(var e in d){var g=d[e];if(c[0]===g.value){b.textContent=g.label;break}}}else b.textContent=adf.mf.resource.getInfoString("AMXInfoBundle","amx_selectManyChoice_LABEL_SELECTED_ITEM_COUNT",c.length)};g.prototype._createReadOnlyDom=function(a,b,c){var d=!0,e;for(e in c){var g=c[e];-1!=a.indexOf(g.value)&&(d?(d=!1,g=g.label):g=", "+g.label,b.appendChild(document.createTextNode(g)))}};g.prototype._createEditableDom=function(a,b,c){for(var d in c){var e=
c[d],g=0<=a.indexOf(e.value),h=document.createElement("option");h.value=e.value;h.className="amx-selectItem";h.textContent=e.label;b.appendChild(h);h.setAttribute("role","option");g?(h.setAttribute("selected",!0),h.setAttribute("aria-selected",!0)):h.setAttribute("aria-selected",!1)}};g.prototype._handleBlur=function(a){a=a.data.amxNode;if(adf.mf.api.amx.acceptEvent()){for(var b=[],c=0,d=this.options.length;c<d;++c){var e=this.options[c];e.selected&&b.push(e.getAttribute("value"))}c=a.getAttribute("value");
a.setAttributeResolvedValue("value",b);c=new amx.ValueChangeEvent(c,b);adf.mf.api.amx.processAmxEvent(a,"valueChange","value",b,c)}};g.prototype._createSelectItemsContainer=function(a){a?(a=document.createElement("div"),a.className="selectItemsContainer"):(a=document.createElement("select"),a.className="selectItemsContainer",a.setAttribute("multiple","multiple"));return a};adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"selectItem").prototype.render=function(a){var b=document.createElement("label");
b.setAttribute("for",a.getAttribute("value"));b.textContent=a.getAttribute("label");return b}})();