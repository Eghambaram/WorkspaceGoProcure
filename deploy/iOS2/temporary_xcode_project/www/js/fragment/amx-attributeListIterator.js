(function(){var a=adf.mf.api.amx.TypeHandler.register(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"attributeListIterator");a.prototype.createChildrenNodes=function(a){if(adf.mf.environment.profile.dtMode)return this._createDtChildren(a);this._initialize(a);var c=a.getAttribute("_listTagInstance");if(null!=c)for(var c=c.getChildren(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"attributeSet"),d=this._setupVarStatus(a,c),c=d.step,e=d.begin,d=d.end;0>c&&e>=d||0<c&&e<=d;e+=c)a.createStampedChildren(e,[null]);return!0};
a.prototype.visitChildren=function(a,c,d){var e=a.getAttribute("_listTagInstance");if(null==e)return!1;for(var g=a.getAttribute("_listMetaData"),e=e.getChildren(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"attributeSet"),h=this._setupVarStatus(a,e),k=h.step,m=h.begin,n=h.end,t=1;0>k&&m>=n||0<k&&m<=n;m+=k){var u=e[m];h.index=m;h.count=t++;h.last=m==n;u=this._setupContext(a,g,u,h);try{if(a.visitStampedChildren(m,null,null,c,d))return!0}finally{this._tearDownContext(a,u)}h.first=!1}return!1};a.prototype.isFlattenable=
function(a){return!0};a.prototype._setupContext=function(a,c,d,e){var g=c.setupContext(d),h=a.getAttribute("varStatus");null!=h&&adf.mf.api.pushVariable(h,e);e=a.getAttribute("_currentSetTagInstance");a.setAttributeResolvedValue("_currentSetTagInstance",d);return{metaDataSetupResult:g,lastSetTagInstance:e,metaData:c,varStatusVariableName:h}};a.prototype._tearDownContext=function(a,c){c.metaData.tearDownContext(c.metaDataSetupResult);null!=c.varStatusVariableName&&adf.mf.api.popVariable(c.varStatusVariableName);
a.setAttributeResolvedValue("_currentSetTagInstance",c.lastSetTagInstance)};a.prototype._createDtChildren=function(a){for(var c=0;3>c;++c)a.createStampedChildren(c,[null]);a.setState(adf.mf.api.amx.AmxNodeStates.ABLE_TO_RENDER);return!0};a.prototype._initialize=function(a){if(void 0===a.getAttribute("_listTagInstance")){var c=a.getAttribute("name"),d=this._getParentAmxNode(a),e=this._findAttributeListTagInstance(d,c);a.setAttributeResolvedValue("_listTagInstance",e);if(null!=e){d=("attributeListIterator"==
d.getTag().getName()?d.getAttribute("_listMetaData"):adf.mf.internal.amx.fragment.FragmentMetaData.getMetaData(d)).getAttributeListMetaData(c);if(null==d)throw Error(adf.mf.resource.getInfoString("AMXErrorBundle","ERROR_NO_ATTRIBUTE_LIST_DEFINED",c));a.setAttributeResolvedValue("_listMetaData",d)}}};a.prototype._getParentAmxNode=function(a){for(a=a.getParent();null!=a;a=a.getParent()){var c=a.getTag();if(c.getNamespace()==adf.mf.api.amx.AmxTag.NAMESPACE_AMX){if("fragmentDef"==c.getName())return a.getParent();
if("attributeListIterator"==c.getName())return a}}return null};a.prototype._findAttributeListTagInstance=function(a,c){if(null==a)throw Error(adf.mf.resource.getInfoString("AMXErrorBundle","ERROR_ATTRIBUTE_LIST_NOT_IN_FRAGMENT",c));var d=null;if("attributeListIterator"==a.getTag().getName()){var e=a.getAttribute("_currentSetTagInstance");if(null==e)throw Error(adf.mf.resource.getInfoString("AMXErrorBundle","ERROR_PARENT_ATTRIBUTE_LIST_NOT_ACTIVE"));d=e.getChildren(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,
"attributeList")}else d=a.__getTagInstances(adf.mf.api.amx.AmxTag.NAMESPACE_AMX,"attributeList");e=null;if(null!=d)for(var g=0,h=d.length;g<h;++g){var k=d[g];if(k.getAttribute("name")==c){e=k;break}}null!=e&&(d=e.getAttribute("ref"),null!=d&&(a=this._getParentAmxNode(e.getParentAmxNode()),e=this._findAttributeListTagInstance(a,d)));return e};a.prototype._setupVarStatus=function(a,c){var d=c.length,e=this._getIntAttributeWithDefault(a,"begin",0),g=this._getIntAttributeWithDefault(a,"end",d-1),h=this._getIntAttributeWithDefault(a,
"step",1);if(0==h)throw Error(adf.mf.resource.getInfoString("AMXErrorBundle","ERROR_ATTRIBUTE_LIST_STEP_0"));0<h&&g>d-1&&(g=d-1);0>e&&(e=0);1<h?g-=(g-e)%h:-1>h&&(e+=(g-e)%h);return{begin:e,end:g,first:!0,last:e==g,index:e,count:1,step:h}};a.prototype._getIntAttributeWithDefault=function(a,c,d){a=a.getAttribute(c);return void 0==a?d:parseInt(a,10)}})();