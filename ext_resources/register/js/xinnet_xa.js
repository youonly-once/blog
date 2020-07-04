if (navigator.cookieEnabled){
  var traker = new xaTraker();
  var userCode = getCookieData("userCode");
    var lastTime = 0;
   traker.trace();
   if(userCode !=""){
	   var regFlag = getCookieData("regFlag");
	   var flag = getCookieData("flag");
	   if(regFlag !="" && regFlag =="5"){
		   traker.traceReg();
	   }
	   if(flag == ""){
		   traker.loginIn();
	   }
   }
   
    window.onbeforeunload = function() {
		 lastTime = new Date().getTime();
	  traker.traceBase("V","");
	  };
   
}

function xaTraker(){
	  var xa_img = null;
	  var currentUrl	= window.location.href;
	  var itime = new Date().getTime();
	
	  this.traceBase=function(operationType,operationData){
		   var query = new Array();
	       setQueryArray(currentUrl,query,lastTime,itime);
		   query.push("operationType="+operationType);
		if(operationData!=""){
			query.push("operationData="+operationData);
		}
		doGet(query.join("&"));
	  }
	  
	  this.traceBaseByOnload=function(operationType,operationData){
		   var query = new Array();
		   setQueryArrayByonLoad(currentUrl,query,lastTime,itime);
		   query.push("operationType="+operationType);
		if(operationData!=""){
			query.push("operationData="+operationData);
		}
		doGet(query.join("&"));
	  }
	  
	  this.traceCount=function(domain,tenantID,operationType){
		  this.traceBase(operationType,"");
	  };
	  this.trace = function(){
		   isInit =  checkPage(currentUrl);
		  if(isInit === true)
			return;
		 this.traceBaseByOnload("L","");
		  
		  
		  
	  };
	   this.traceReg = function(){
		   this.traceBase("T",userCode);
		   addCookieData("regFlag",1,0);
	   };
	   
	  this.traceShare = function(domain,tenantID){
		   this.traceBase("S","");
	  };
	  this.fxBtn = function(domain,tenantID){
		  this.traceBase("A","");
	  };
	  this.fxClose = function(domain,tenantID) {
		  this.traceBase("P","");
	  };
	  this.loginIn = function (){
		  this.traceBase("O",userCode);
		   addCookieData("flag",1,0);
	  };
	 
}

function setQueryArrayByonLoad(currentUrl,query,lastTime,itime){
	
	var xa_preref = document.referrer;
	var pageNum = setPageno(currentUrl);
	var newVisitor=0;
	var uid = getCookieData("uid");
	if(uid === ""){
		uid = makeUID();
		newVisitor = 1;
		addCookieData("uid",uid,10000);
	   }

	var date = new Date();
	var sid = getCookieData("sId");
	if(sid === ""){
		sid = makeUID();
		addCookieData("sId",sid,0); //
			   }else{
			var loadTime = getCookieData("loadTime");
			 if(date.getTime()-loadTime>1200000){
				sid = makeUID();
				addCookieData("sId",sid,0); //
			}else {
			if(xa_preref!=null&&xa_preref!=''){
			  if(xa_preref.indexOf('xinnet.com')==-1){
					sid = makeUID();
					addCookieData("sId",sid,0); //
				}
			  }
				
			}
	   }
	addCookieData("loadTime",date.getTime(),0); //进入时间
	var metak = document.querySelector('meta[name="Keywords"]');
    
	if(metak != null && metak != ''){
		var mk = document.querySelector('meta[name="Keywords"]').getAttribute('content');
	   query.push("metaKeywords="+encodeURI(encodeURI(mk)));
	}
	query.push("domain=xinnet");   // liweichao
	query.push("tenantID=110");
	query.push("userId="+uid);
	query.push("prevUrl=" + escape(xa_preref));
	query.push("currentUrl="+currentUrl);
	query.push("jsSessionId="+sid);
	query.push("pageNumber="+pageNum);
	query.push("newVisitor="+newVisitor);
	if(lastTime>0){
		var vtime = lastTime - itime;
		if(vtime > 1800000){
						addCookieData("sId","",0);
                        vtime  = 1800000;
					}
	    query.push("vtime=" + vtime);
	}
}


function setQueryArray(currentUrl,query,lastTime,itime){
	
	var xa_preref = document.referrer;
	var pageNum = setPageno(currentUrl);
	var newVisitor=0;
	var uid = getCookieData("uid");
	if(uid === ""){
		uid = makeUID();
		newVisitor = 1;
		addCookieData("uid",uid,10000);
	   }
	var sid = getCookieData("sId");
	if(sid === ""){
		sid = makeUID();
		addCookieData("sId",sid,0); //
	   }
	var metak = document.querySelector('meta[name="Keywords"]');
    
	if(metak != null && metak != ''){
		var mk = document.querySelector('meta[name="Keywords"]').getAttribute('content');
	   query.push("metaKeywords="+encodeURI(encodeURI(mk)));
	}
	query.push("domain=xinnet");   // liweichao
	query.push("tenantID=110");
	query.push("userId="+uid);
	query.push("prevUrl=" + escape(xa_preref));
	query.push("currentUrl="+currentUrl);
	query.push("jsSessionId="+sid);
	query.push("pageNumber="+pageNum);
	query.push("newVisitor="+newVisitor);
	if(lastTime>0){
		var vtime = lastTime - itime;
		if(vtime > 1800000){
						addCookieData("sId","",0);
                        vtime  = 1800000;
					}
	    query.push("vtime=" + vtime);
	}
}

function setPageno(currentUrl){
	var frontUrl = getCookieData("frontUrl");
   frontUrl = decodeURIComponent(frontUrl);
   addCookieData("_page",frontUrl,0);
   addCookieData("frontUrl",currentUrl,24);
	var pageno = getCookieData("pageno");
	if(pageno==""){
		pageno = 1;
	} else if(frontUrl != currentUrl){
		pageno = parseInt(pageno)+1;
	}
	 addCookieData("pageno",pageno,720);
	 return pageno;
}

function addCookieData(name,value,expired){
		var tmp = name + "=" + encodeURIComponent(value) + "; Path=/;domain=xinnet.com;";
		if (expired > 0) {
			var date = new Date();
			date.setTime(date.getTime() + expired * 3600000);
			tmp = tmp + " expires=" + date.toGMTString();
		}
		document.cookie = tmp;
	}

	function getCookieData(name) {
		var d = new RegExp("(^|;)[ ]*" + name + "[ ]*=[ ]*[^;]+", "i");
		if (document.cookie.match(d)) {
			var tmp = document.cookie.match(d)[0];
			var pos = tmp.indexOf("=");
	  		if (pos > 0){
	  			return tmp.substring(pos + 1).replace(/^\s+|\s+$/g, "");
	  		}
		}
		else{
			return "";
		}
	}

	function doGet(query) {
		var  xa_api = "http://analysis.xinnet.com/receive/track.do";
		
			
			query =query.replace("#","-");
			var send_img = document.createElement('img');
			send_img.src = xa_api + "?" + query;
            
		
	}
	
	
	/**
	 * Uid 绐乮d
	 *
	 * @return
	 */
	function makeUID() {
		var s = [];
		var hexDigits = "0123456789ABCDEF";
		for ( var i = 0; i < 32; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[12] = "4";
		s[16] = hexDigits.substr((s[16] & 0x3) | 0x8, 1);

		var uuid = s.join("");
		return uuid;
	}
	
	function checkPage(currentUrl){
		var _flag = false;
		var _page = getCookieData("_page");
		_page = decodeURIComponent(_page);
		if(_page == currentUrl){
			_flag = true;
		}
		return _flag;
	}