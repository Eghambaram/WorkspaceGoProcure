(function(){function a(a,b){if(null==a)return b;var c=parseInt(a,10);return isNaN(c)?b:c}function b(){var a=arguments.length;2>a&&console.log("Not enough _ensureValidEnum arguments");for(var b=arguments[0],c=1;c<a;c++)if(b==arguments[c])return b;return arguments[1]}var c=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"panelPage");c.prototype._handleOverflowTap=function(a){window.AdfmfCallback.openMenu()};c.prototype._handleOverflowRefresh=function(a,b){null==b&&(b=document.getElementById(a.data+
"_header"));b&&(window.AdfmfCallback.hasOverflow()?b.classList.remove("amx-panelPage-overflowSuppressed"):b.classList.add("amx-panelPage-overflowSuppressed"))};c.prototype._handleContentOffsetTopChange=function(a){var b=c._lastTopContentOffset;if(null==a)adf.mf.log.AMX.logp(adf.mf.log.level.FINER,"panelPage","_handleContentOffsetTopChange","Unexpected parameter.");else{if(null==a.detail)b=adf.mf.api.topContentOffset,null==b&&(b=adf.mf.api.getQueryString(),b=parseInt(adf.mf.api.getQueryStringParamValue(b,
"topContentOffset","0"),10));else{var d=a.detail;isNaN(d)||(b=d)}void 0===b&&(b=0);b=Math.max(0,b);c._lastTopContentOffset=b;document.querySelector("#bodyPage").setAttribute("data-topContentOffset",b);var e=a.data;document.getElementById(e);a=document.getElementById(e+"_header");var d=c._getHeaderChildByClass(a,e,"amx-panelPage-header-appIcon"),g=c._getHeaderChildByClass(a,e,"amx-panelPage-header-overflowIcon"),h=c._getHeaderChildByClass(a,e,"amx-panelPage-facet-primary"),w=c._getHeaderChildByClass(a,
e,"amx-panelPage-facet-secondary"),x=c._getHeaderChildByClass(a,e,"amx-panelPage-facet-header"),e=document.getElementById(e+"_content");null==a?(c._applyStyle(a,"padding-top","0px"),a&&"block"==adf.mf.internal.amx.getComputedStyle(a).display&&(c._applyStyle(d,"margin-top","0px"),c._applyStyle(g,"margin-top","0px"),c._applyStyle(h,"margin-top","0px"),c._applyStyle(w,"margin-top","0px"),c._applyStyle(x,"margin-top","0px")),c._applyStyle(e,"padding-top",b+"px")):(c._applyStyle(a,"padding-top",b+"px"),
a&&"block"==adf.mf.internal.amx.getComputedStyle(a).display&&(c._applyStyle(d,"margin-top",b+"px"),c._applyStyle(g,"margin-top",b+"px"),c._applyStyle(h,"margin-top",b+"px"),c._applyStyle(w,"margin-top",b+"px"),c._applyStyle(x,"margin-top",b+"px")),c._applyStyle(e,"padding-top","0px"))}};c._getHeaderChildByClass=function(a,b,c){var d=null;null!=a&&(d=a.querySelector("#"+b+"_header \x3e ."+c));return d};c._applyStyle=function(a,b,c){null!=a&&(a.style[b]=c)};c.prototype.render=function(a,b){var c=document.createElement("div"),
d=a.getRenderedChildren("primary"),e=a.getRenderedChildren("header"),g=a.getRenderedChildren("secondary");if(d.length||e.length||g.length){var h=document.createElement("div");h.className="amx-panelPage-header";h.id=b+"_header";var x=document.createElement("section");x.className="amx-panelPage-header-appIcon";h.appendChild(x);if(e.length){x=document.createElement("div");x.className="amx-panelPage-facet-header amx-panelPage-bar";x.setAttribute("role","heading");h.appendChild(x);for(var y in e){var z=
e[y].render();z&&x.appendChild(z)}}x=!1;if(z=d.length){var A=document.createElement("section");A.className="amx-panelPage-facet-primary amx-panelPage-bar";h.appendChild(A);for(y in d){var B=d[y].render();null!=B&&(1==z&&B.classList.contains("amx-commandButton-back")&&(x=!0),A.appendChild(B))}}x&&h.classList.add("amx-panelPage-header-primaryIsBack");if(g.length)for(y in d=document.createElement("section"),d.className="amx-panelPage-facet-secondary amx-panelPage-bar",h.appendChild(d),g)(x=g[y].render())&&
d.appendChild(x);e.length&&null!=window.AdfmfCallback&&(y=document.createElement("section"),y.className="amx-panelPage-header-overflowIcon",y.setAttribute("tabindex","0"),y.setAttribute("role","button"),e=adf.mf.resource.getInfoString("AMXInfoBundle","amx_panelPage_LABEL_OVERFLOW_BUTTON"),y.setAttribute("aria-label",e),h.appendChild(y),adf.mf.api.amx.addBubbleEventListener(y,"tap",this._handleOverflowTap),adf.mf.api.amx.addBubbleEventListener(document,"mafviewvisible",this._handleOverflowRefresh,
b),adf.mf.api.amx.addBubbleEventListener(document,"mafoverflowrefresh",this._handleOverflowRefresh,b),this._handleOverflowRefresh(null,h));c.appendChild(h)}y=document.createElement("div");y.className="amx-panelPage-contentWrapper";h=document.createElement("div");h.id=b+"_content";h.className="amx-panelPage-content";adf.mf.api.amx.enableScrolling(h);y.appendChild(h);c.appendChild(y);e=a.renderDescendants();y=0;for(g=e.length;y<g;++y)h.appendChild(e[y]);h=a.getRenderedChildren("footer");if(h.length)for(y in g=
document.createElement("footer"),g.className="amx-panelPage-footer",c.appendChild(g),e=document.createElement("div"),e.className="amx-panelPage-facet-footer amx-panelPage-bar",g.appendChild(e),h)(g=h[y].render())&&e.appendChild(g);return c};c.prototype.postDisplay=function(a,b){var c=b.getId(),d=b.getClientState();if(null!=d){var e=document.getElementById(c+"_content");if(e){var g=d.scrollLeft;null!=g&&(e.scrollLeft=g);d=d.scrollTop;null!=d&&(e.scrollTop=d)}}this._handleContentOffsetTopChange({data:c});
adf.mf.api.amx.addBubbleEventListener(window,"mafcontentoffsettop",this._handleContentOffsetTopChange,c)};c.prototype.preDestroy=function(a,b){var c=document.getElementById(b.getId()+"_content");if(c){var d=c.scrollLeft,c=c.scrollTop;null==d&&null==c||b.setClientState({scrollLeft:d,scrollTop:c})}d=b.getId();adf.mf.api.amx.removeBubbleEventListener(document,"mafviewvisible",this._handleOverflowRefresh,d);adf.mf.api.amx.removeBubbleEventListener(document,"mafoverflowrefresh",this._handleOverflowRefresh,
d);adf.mf.api.amx.removeBubbleEventListener(window,"mafcontentoffsettop",this._handleContentOffsetTopChange,d)};var d=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"panelFormLayout");d.prototype.render=function(a){var b=document.createElement("div"),c=[];c.push("amx-panelFormLayout");b.setAttribute("role","form");var d=document.createElement("div");d.className="amx-panelFormLayout_body";b.appendChild(d);var e="amx-label-position-start";null!=a.getAttribute("labelPosition")&&
("end"===a.getAttribute("labelPosition")?e="amx-label-position-end":"center"===a.getAttribute("labelPosition")?e="amx-label-position-center":"topStart"===a.getAttribute("labelPosition")?e="amx-label-position-topStart":"topCenter"===a.getAttribute("labelPosition")?e="amx-label-position-topCenter":"topEnd"===a.getAttribute("labelPosition")&&(e="amx-label-position-topEnd"));c.push(e);e="amx-field-halign-end";null!=a.getAttribute("fieldHalign")&&("start"===a.getAttribute("fieldHalign")?e="amx-field-halign-start":
"center"===a.getAttribute("fieldHalign")&&(e="amx-field-halign-center"));c.push(e);var e=a.renderDescendants(),g=a.getAttribute("maxColumns");g||(g=Number.MAX_VALUE);var h=a.getAttribute("rows");h||(h=Number.MAX_VALUE);var x=a.getAttribute("showHorizontalDividers");"true"==x?c.push("amx-panelFormLayout_showHorizontalDividers"):"false"==x&&c.push("amx-panelFormLayout_hideHorizontalDividers");g=Math.max(Math.ceil(e.length/g),h);x=a.getAttribute("labelWidth");this._nonNegativeNumberRegExp.test(x)&&(x+=
"px");var y=a.getAttribute("fieldWidth");this._nonNegativeNumberRegExp.test(y)&&(y+="px");var z=null==x?!1:x==this._nonNegativePercentRegExp.exec(x),A=null==y?!1:y==this._nonNegativePercentRegExp.exec(y),B=!z&&(null==x?!1:x==this._nonNegativePxRegExp.exec(x)),C=!A&&(null==y?!1:y==this._nonNegativePxRegExp.exec(y)),h=a="";z&&A?(x=parseFloat(x),y=parseFloat(y),totalPercent=x+y,x=100*x/totalPercent+"%",y=100*y/totalPercent+"%",a=x,h=y):B||B&&C?a=x:C&&(h=y);x=0;z=y=null;b.className=c.join(" ");adf.mf.api.amx.enableScrolling(b,
!0);c=0;for(A=e.length;c<A;++c){if(!y){y=document.createElement("div");y.className="amx-panelFormLayout_column";z=document.createElement("div");z.className="amx-panelFormLayout_sizingTable";y.appendChild(z);B=document.createElement("div");B.className="amx-panelFormLayout_sizingRow";C=document.createElement("div");C.className="field-label";C.style.width=a;var D=document.createElement("div");D.className="field-value";D.style.width=h;B.appendChild(C);B.appendChild(D);z.appendChild(B)}d.appendChild(y);
z.appendChild(e[c]);x++;x>=g&&(y=null,x=0)}return b};d.prototype._nonNegativeNumberRegExp=new RegExp(/^[0-9]+[.]?[0-9]*$/);d.prototype._nonNegativePercentRegExp=new RegExp(/[0-9]+[.]?[0-9]*[%]/);d.prototype._nonNegativePxRegExp=new RegExp(/[0-9]+[.]?[0-9]*[p][x]/);adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"panelLabelAndMessage").prototype.render=function(a){var b=amx.createField(a),c=b.fieldRoot;c.setAttribute("aria-labelledby",a.getAttribute("label"));for(var d=b.fieldValue,
e=a.renderDescendants(),g=0,h=e.length;g<h;++g)d.appendChild(e[g]);adf.mf.api.amx.isValueTrue(a.getAttribute("showRequired"))&&d.setAttribute("aria-required","true");adf.mf.api.amx.applyRequiredMarker(a,b);return c};d=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"panelGroupLayout");d.prototype.render=function(a){var b,c=null;b=a.getAttribute("layout");var d=a.getAttribute("scrollPolicy"),e="horizontal"===b,g="wrap"===b,h=g?null:a.getAttribute("halign"),x=e?a.getAttribute("valign"):
null;e?(b=document.createElement("div"),b.className="amx-horizontal","scroll"==d?adf.mf.api.amx.enableScrolling(b):"none"!=d&&adf.mf.api.amx.enableScrolling(b,!0),d=document.createElement("table"),b.appendChild(d),c=d.insertRow(-1),"center"==h?d.setAttribute("align","center"):"end"==h&&(d=c.insertCell(-1),d.setAttribute("width","100%"))):g?(b=document.createElement("span"),b.className="amx-wrap","scroll"==d?adf.mf.api.amx.enableScrolling(b):"none"!=d&&adf.mf.api.amx.enableScrolling(b,!0)):(b=document.createElement("div"),
b.className="amx-vertical","scroll"==d?adf.mf.api.amx.enableScrolling(b):"none"!=d&&adf.mf.api.amx.enableScrolling(b,!0),"center"==h?b.setAttribute("align","center"):"end"==h&&("rtl"==document.documentElement.dir?b.setAttribute("align","left"):b.setAttribute("align","right")));a=a.renderDescendants();for(var h=0,y=a.length;h<y;++h){var z=a[h];e?(d=c.insertCell(-1),d.appendChild(z),"top"!=x&&"middle"!=x&&"bottom"!=x||d.setAttribute("valign",x)):g?b.appendChild(z):(d=document.createElement("div"),d.appendChild(z),
b.appendChild(d))}return b};d.prototype.postDisplay=function(a,b){if("scroll"==b.getAttribute("scrollPolicy")){var c=b.getClientState();if(null!=c){var d=c.scrollLeft;null!=d&&(a.scrollLeft=d);c=c.scrollTop;null!=c&&(a.scrollTop=c)}}};d.prototype.preDestroy=function(a,b){if("scroll"==b.getAttribute("scrollPolicy")){var c=a.scrollLeft,d=a.scrollTop;null==c&&null==d||b.setClientState({scrollLeft:c,scrollTop:d})}};var e=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"tableLayout");
e.prototype.render=function(c){var d=document.createElement("div");adf.mf.api.amx.enableScrolling(d,!0);var e=document.createElement("table");e.setAttribute("border",a(c.getAttribute("borderWidth"),0));e.setAttribute("cellPadding",a(c.getAttribute("cellPadding"),0));e.setAttribute("cellSpacing",a(c.getAttribute("cellSpacing"),0));var g=b(c.getAttribute("halign"),"start","center","end");"end"==g?"rtl"==document.documentElement.dir?e.setAttribute("align","left"):e.setAttribute("align","right"):"center"==
g&&e.setAttribute("align",g);"fixed"==b(c.getAttribute("layout"),"fixed","weighted")&&(e.style.tableLayout="fixed");g=c.getAttribute("shortDesc");null!=g&&(e.title=g);g=c.getAttribute("summary");e.summary=null==g?"":g;""==e.summary&&e.setAttribute("role","presentation");g=c.getAttribute("width");null!=g&&(e.width=g);d.appendChild(e);c=c.renderDescendants();for(var g=0,h=c.length;g<h;++g){var v=c[g];"TR"==v.tagName?e.appendChild(v):console.log("Illegal child found in tableLayout: "+v)}return d};e.prototype.attributeChangeResult=
function(a,b,c){switch(b){case "width":return adf.mf.api.amx.AmxNodeChangeResult.REFRESH;default:return e.superclass.attributeChangeResult.call(this,a,b,c)}};e.prototype.refresh=function(a,b,c){if(b.hasChanged("width")){var d=document.getElementById(a.getId()).firstChild,g=a.getAttribute("width");d.width=g}e.superclass.refresh.call(this,a,b,c)};adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"rowLayout").prototype.render=function(a){var b=document.createElement("tr");a=a.renderDescendants();
for(var c=0,d=a.length;c<d;++c){var e=a[c];"TD"==e.tagName?b.appendChild(e):"TH"==e.tagName?b.appendChild(e):console.log("Illegal child found in rowLayout: "+e)}return b};var g=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"cellFormat");g.prototype.render=function(c){var d;d=c.getAttribute("header");d="true"===d||!0===d?document.createElement("th"):document.createElement("td");var e=Math.max(1,a(c.getAttribute("columnSpan"),1));d.setAttribute("colspan",e);e=Math.max(1,a(c.getAttribute("rowSpan"),
1));d.setAttribute("rowspan",e);e=b(c.getAttribute("halign"),"start","center","end");"end"==e?"rtl"==document.documentElement.dir?d.setAttribute("align","left"):d.setAttribute("align","right"):"center"==e?d.setAttribute("align",e):"rtl"==document.documentElement.dir?d.setAttribute("align","right"):d.setAttribute("align","left");e=b(c.getAttribute("valign"),"middle","top","bottom");d.setAttribute("valign",e);e=c.getAttribute("shortDesc");null!=e&&(d.title=e);e=c.getAttribute("width");null!=e&&(d.width=
e);e=c.getAttribute("height");null!=e&&(d.height=e);c=c.renderDescendants();for(var e=0,g=c.length;e<g;++e)d.appendChild(c[e]);return d};g.prototype.attributeChangeResult=function(a,b,c){switch(b){case "width":case "height":return adf.mf.api.amx.AmxNodeChangeResult.REFRESH;default:return g.superclass.attributeChangeResult.call(this,a,b,c)}};g.prototype.refresh=function(a,b,c){if(b.hasChanged("width")){var d=document.getElementById(a.getId()),e=a.getAttribute("width");d.width=e}b.hasChanged("height")&&
(d=document.getElementById(a.getId()),e=a.getAttribute("height"),d.height=e);g.superclass.refresh.call(this,a,b,c)};var d=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"panelStretchLayout"),h={top:"top",bottom:"bottom",center:"center"};d.prototype.createChildrenNodes=function(a){for(var b in h)this._createChildrenNodes(a,b);return!0};d.prototype._createChildrenNodes=function(a,b){var c=a.getTag().getChildFacetTag(b);if(null!==c)for(var c=c.getChildrenUITags(),d=0,e=c.length;d<
e;++d)a.addChild(c[d].buildAmxNode(a,null),b)};d.prototype.render=function(a){try{var b=document.createElement("div");b.className="amx-fitParent";var c=document.createElement("div");c.className="amx-panelStretchLayout_top";var d=document.createElement("div"),e=document.createElement("div");e.className="amx-panelStretchLayout_center";var g=document.createElement("div");g.className="amx-panelStretchLayout_bottom";b.appendChild(c);b.appendChild(d);d.appendChild(e);b.appendChild(g);var w=this._getAreaToComponent(b),
x=a.getAttribute("scrollPolicy");"scroll"==x?adf.mf.api.amx.enableScrolling(w.center):"none"!=x&&adf.mf.api.amx.enableScrolling(w.center,!0);for(var y in h)this._render(a,w,y);return b}catch(z){console.error(z)}};d.prototype._render=function(a,b,c){a=a.getRenderedChildren(c);if(a.length){b=b[c];for(var d in a)(c=a[d].render())&&b.appendChild(c)}};d.prototype._getAreaToComponent=function(a){var b={},c;for(c in h){var d=a.getElementsByClassName("amx-panelStretchLayout_"+c);0<d.length&&(b[c]=d[0])}return b}})();