window.TrNumberConverter=function(a,b,c,d,e,g,h,k,m,n,t,u){this._pattern=a;this._type=b;this._locale=c;this._messages=d;this._currencyCode=h;this._currencySymbol=k;this._maxFractionDigits=m;this._maxIntegerDigits=n;this._minFractionDigits=t;this._minIntegerDigits=u;this._integerOnly=void 0!==e?e:!1;this._groupingUsed=void 0!==g?g:!0;this._initNumberFormat(c);this._class="TrNumberConverter"};TrNumberConverter.prototype=new TrConverter;
TrNumberConverter.prototype.setCurrencyCode=function(a){this._currencyCode=a};TrNumberConverter.prototype.getCurrencyCode=function(){return this._currencyCode};TrNumberConverter.prototype.setCurrencySymbol=function(a){this._currencySymbol=a};TrNumberConverter.prototype.getCurrencySymbol=function(){return this._currencySymbol};TrNumberConverter.prototype.setMaxFractionDigits=function(a){this._maxFractionDigits=a};TrNumberConverter.prototype.getMaxFractionDigits=function(){return this._maxFractionDigits};
TrNumberConverter.prototype.setMaxIntegerDigits=function(a){this._maxIntegerDigits=a};TrNumberConverter.prototype.getMaxIntegerDigits=function(){return this._maxIntegerDigits};TrNumberConverter.prototype.setMinFractionDigits=function(a){this._minFractionDigits=a};TrNumberConverter.prototype.getMinFractionDigits=function(){return this._minFractionDigits};TrNumberConverter.prototype.setMinIntegerDigits=function(a){this._minIntegerDigits=a};TrNumberConverter.prototype.getMinIntegerDigits=function(){return this._minIntegerDigits};
TrNumberConverter.prototype.setGroupingUsed=function(a){this._groupingUsed=a};TrNumberConverter.prototype.isGroupingUsed=function(){return this._groupingUsed};TrNumberConverter.prototype.setIntegerOnly=function(a){this._integerOnly=a};TrNumberConverter.prototype.isIntegerOnly=function(){return this._integerOnly};
TrNumberConverter.prototype.getFormatHint=function(){return this._messages&&this._messages.hintPattern?TrMessageFactory.createCustomMessage(this._messages.hintPattern,this._pattern):this._pattern?TrMessageFactory.createMessage("org.apache.myfaces.trinidad.convert.NumberConverter.FORMAT_HINT",this._pattern):null};
TrNumberConverter.prototype.getAsString=function(a,b){if(this._isConvertible()){if("percent"==this._type||"currency"==this._type){var c=this._numberFormat.format(a);"currency"==this._type&&(this._currencyCode?c=c.replace(getLocaleSymbols().getCurrencyCode(),this._currencyCode):this._currencySymbol&&(c=c.replace(getLocaleSymbols().getCurrencySymbol(),this._currencySymbol)));return c}return"string"===typeof a?this._numberFormat.format(parseFloat(a)):this._numberFormat.format(parseFloat(a.toFixed(this._numberFormat.getMaximumFractionDigits())))}};
TrNumberConverter.prototype.getAsObject=function(a,b){a=TrFormatUtils.trim(a);if(this._isConvertible(a)){if(null==a)return null;"string"!=typeof a&&(a=""+a);if(0==a.length)return null;var c;if("percent"==this._type||"currency"==this._type){c=getLocaleSymbols(this._locale);var d=c.getGroupingSeparator();"\u00a0"==d&&(a=a.replace(RegExp("\\ ","g"),"\u00a0"));a=a.replace(new RegExp("\\"+d,"g"),"");c=c.getDecimalSeparator();a=a.replace(new RegExp("\\"+c,"g"),".");try{a=this._numberFormat.parse(a)+""}catch(e){try{a=
TrNumberFormat.getNumberInstance().parse(a)+""}catch(g){throw c=this._numberFormat.format(this._example),d="org.apache.myfaces.trinidad.convert.NumberConverter.CONVERT_"+this._type.toUpperCase(),c=this._messages&&this._messages[this._type]?_createCustomFacesMessage(TrMessageFactory.getSummaryString(d),this._messages[this._type],b,a,c):_createFacesMessage(d,b,a,c),new TrConverterException(c);}}a=a.replace(RegExp("\\.","g"),getLocaleSymbols().getDecimalSeparator())}c=_decimalParse(a,this._messages,
"org.apache.myfaces.trinidad.convert.NumberConverter",null,null,null,null,b,!this.isIntegerOnly());c=parseFloat(c.toFixed(this._numberFormat.getMaximumFractionDigits()));"percent"==this._type&&(c/=100);return c}};TrNumberConverter.prototype._isConvertible=function(a){return null!=this._pattern?!1:TrFormatUtils.isNumberConvertible(a)};
TrNumberConverter.prototype._initNumberFormat=function(a){"percent"==this._type?(this._example=.3423,this._numberFormat=TrNumberFormat.getPercentInstance(a)):"currency"==this._type?(this._example=10250,this._numberFormat=TrNumberFormat.getCurrencyInstance(a,this._currencyCode,this._currencySymbol)):"number"==this._type&&(this._numberFormat=TrNumberFormat.getNumberInstance(a));this._numberFormat.setGroupingUsed(this.isGroupingUsed());this._numberFormat.setMaximumFractionDigits(this.getMaxFractionDigits());
this._numberFormat.setMaximumIntegerDigits(this.getMaxIntegerDigits());this._numberFormat.setMinimumFractionDigits(this.getMinFractionDigits());this._numberFormat.setMinimumIntegerDigits(this.getMinIntegerDigits())};