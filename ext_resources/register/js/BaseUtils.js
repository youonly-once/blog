function addfavorite() {
	var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd'
			: 'CTRL';
	if (document.all) {
		window.external.addFavorite('http://www.xinnet.com',
				'新网  一站式网络营销平台 简单更高效');
	} else if (window.sidebar) {
		window.sidebar.addPanel('新网  一站式网络营销平台 简单更高效', 'http://www.xinnet.com',
				"");
	} else {
		alert('对不起您的浏览器不支持此操作！请您使用菜单栏或是按Ctrl+D收藏本站。');
	}
}

// JavaScript Document

function initPop(ctx) {

	var a1 = "<div id='popIframe' class='popIframe' border='0' src='"
			+ ctx
			+ "/views/common/popiframe.html'></div>"
			+ "<div id='popDiv' class='mydiv' style='display:none;'> "
			+ "<div class='pop_bg'> <a style='float:right;'class='pop_close' href='javascript:closeDiv()'>&nbsp;</a></div>"
			+ "<div class='qzzp_tdjl' style='padding-left:20px;'>"
			+ "	<div class='content'>"
			+ "		  	<p   id='contentM'>恭喜您，您的操作已成功。</p>  "
			+ "	</div>"
			+ "</div>"
			+ "</div>"
			+ "<div id='popSmallDiv' class='mydivSmall' style='display:none;'>"
			+ "<div class='pop_bg'> <a style='float:right;'class='pop_close' href='javascript:closeDiv()'>&nbsp;</a></div>"
			+ "<div class='qzzp_tdjl' style='padding-left:20px;'>"
			+ "	<div class='content'>"
			+ "		  	<p   id='contentD'>恭喜您，您的操作已成功。</p>  "
			+ "	</div>"
			+ "</div>"
			+ "</div>"
			+ "<div id='popSmileDiv' class='mydiv' style='display:none;'> "
			+ "<div class='pop_bg'> <a style='float:right;'class='pop_close' href='javascript:closeDiv()'>&nbsp;</a></div>"
			+ "<div class='qzzp_tdjl' style='padding-left:20px;'>"
			+ "	<div class='content bgsmile'>"
			+ "		       <p class='t'><strong>温馨提示！</strong></p>"
			+ "    		<p  id='contentS'>恭喜您，您的操作已成功。</p>"
			+ "	</div>"
			+ "</div>"
			+ "</div>"

			+ "<div id='popCryDiv' class='mydiv' style='display:none;'> "
			+ "<div class='pop_bg'> <a style='float:right;'class='pop_close' href='javascript:closeDiv()'>&nbsp;</a></div>"
			+ "<div class='qzzp_tdjl' style='padding-left:20px;'>"
			+ "	<div class='content bgcry'>"
			+ "		       <p class='t'><strong>温馨提示！</strong></p>"
			+ "    		<p  id='contentC'>对不起，您的操作出现错误，请稍后重试。</p>"
			+ "	</div>"
			+ "</div>"
			+ "</div>"

			+ "<div id='popSmileDivFouce' class='mydiv' style='display:none;'> "

			+ "</div>"

			+ "<div id='bg' class='bg' style='display:none;'></div>"

			+ " <div id='popDivload' class='mydivload' style='display:none; filter:alpha(opacity=80);-moz-opacity:0.80;'> "

			+ "<div class='loaddiv'>"
			+ "<div class='loadswf'>"
			+ '<p align="center"> <img align="absmiddle" style="margin-right: 20px" src="../images/loading.gif"></p>'
			/*+ "<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0' width='32' height='32'>"
			+ "              <param name='movie' value='"
			+ ctx
			+ "/views/common/images/loading1.swf'>"
			+ "               <param name='quality' value='high' >"
			+ "                 <param name='wmode' value='transparent'>"
			+ "                    <embed src='"
			+ ctx
			+ "/views/common/images/loading1.swf' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='32' height='32' wmode='transparent' ></embed>"
			+ "                  </object>" */
			+ "         </div>"
			+ "          <div class='loadfont'></div>"

			+ "</div>"

			+ "</div>" + "</div>"

			+ "<div id='bg' class='bg' style='display:none;'></div>";

	document.write(a1);
}
function showSmallDiv(msg) {
	if (msg !== '') {
		$('#contentD').html(msg);
	}
	document.getElementById('popSmallDiv').style.display = 'block';

	document.getElementById('popIframe').style.display = 'block';

	document.getElementById('bg').style.display = 'block';

}

function showDiv(msg) {
	if (msg !== '') {
		$('#contentM').html(msg);
	}
	document.getElementById('popDiv').style.display = 'block';

	document.getElementById('popIframe').style.display = 'block';

	document.getElementById('bg').style.display = 'block';

}

function showSmileDiv(msg) {
	if (msg !== '') {
		$('#contentS').html(msg);
	}
	document.getElementById('popSmileDiv').style.display = 'block';

	document.getElementById('popIframe').style.display = 'block';

	document.getElementById('bg').style.display = 'block';

}

function onFouceTo(ids) {
	document.getElementById('popDiv').style.display = 'none';
	document.getElementById('popSmallDiv').style.display = 'none';
	document.getElementById('popSmileDivFouce').style.display = 'none';
	document.getElementById('popSmileDiv').style.display = 'none';
	document.getElementById('popCryDiv').style.display = 'none';

	document.getElementById('bg').style.display = 'none';

	document.getElementById('popIframe').style.display = 'none';
	$('#' + ids).focus();
	// $('#'+ids).css("background-color","#FFFFCC");
}
// 有笑脸，有确认按钮，点击确认关闭弹出层
function showSmileDiv3(msg) {
	document.getElementById('popSmileDivFouce').style.display = 'block';
	document.getElementById('popIframe').style.display = 'block';
	document.getElementById('bg').style.display = 'block';
	$('#popSmileDivFouce')
			.html(
					"<div class='pop_bg'></div><div class='qzzp_tdjl' style='padding-left:20px;'><div class='content bgsmile'><p class='t'><strong>温馨提示！</strong></p><p id='contentFouce'>"
							+ msg
							+ "</p><p class='btncen' style='padding-left:65px; height:150px; text-align:left; padding-top:45px;'><a class='btn4' href='javascript:closeDiv()'>确认</a></p></div></div>");
}

//有笑脸，有确认按钮及X号，点击确认或X号关闭弹出层
function showSmileDiv4(msg) {
	document.getElementById('popSmileDivFouce').style.display = 'block';
	document.getElementById('popIframe').style.display = 'block';
	document.getElementById('bg').style.display = 'block';
	$('#popSmileDivFouce')
			.html(
					"<div class='pop_bg'><a style='float:right;'class='pop_close' href='javascript:closeDiv()'>&nbsp;</a></div><div class='qzzp_tdjl' style='padding-left:20px;'><div class='content bgsmile'><p class='t'><strong>温馨提示！</strong></p><p id='contentFouce'>"
							+ msg
							+ "</p><p class='btncen' style='padding-left:65px; height:150px; text-align:left; padding-top:45px;'><a class='btn4' href='javascript:closeDiv()'>确认</a></p></div></div>");
}
function showCryDiv(msg) {
	if (msg !== '') {
		$('#contentC').html(msg);
	}
	document.getElementById('popCryDiv').style.display = 'block';

	document.getElementById('popIframe').style.display = 'block';

	document.getElementById('bg').style.display = 'block';

}
// 有哭脸，有确认按钮，点确认按钮后设置焦点到指定的元素
function showCryDivFoce(msg, id) {
	document.getElementById('popSmileDivFouce').style.display = 'block';
	document.getElementById('popIframe').style.display = 'block';
	document.getElementById('bg').style.display = 'block';
	$('#popSmileDivFouce')
			.html(
					"<div class='pop_bg'><a style='float:right;'class='pop_close' href='javascript:closeDiv()'>&nbsp;</a></div><div class='qzzp_tdjl' style='padding-left:20px;'><div class='content bgcry'><p class='t'><strong>温馨提示！</strong></p><p id='contentFouce'>"
							+ msg
							+ "</p><p class='btncen' style='padding-left:65px; height:150px; text-align:left; padding-top:45px;'><a class='btn4' onclick='onFouceTo(\""
							+ id + "\")'>确认</a></p></div></div>");
}

function closeDiv() {

	document.getElementById('popDiv').style.display = 'none';
	document.getElementById('popSmallDiv').style.display = 'none';
	document.getElementById('popSmileDivFouce').style.display = 'none';
	document.getElementById('popSmileDiv').style.display = 'none';
	document.getElementById('popCryDiv').style.display = 'none';

	document.getElementById('bg').style.display = 'none';

	document.getElementById('popIframe').style.display = 'none';

}

function disabledMouseWheel() {
	if (document.addEventListener) {
		document.addEventListener('DOMMouseScroll', scrollFunc, false);
	}// W3C
	window.onmousewheel = document.onmousewheel = scrollFunc;// IE/Opera/Chrome
}
function scrollFunc(evt) {
	var bg = document.getElementById("bg");
	if (bg == null || bg.style.display == "block") {
		evt = evt || window.event;
		if (evt.preventDefault) {
			// Firefox
			evt.preventDefault();
			evt.stopPropagation();
		} else {
			// IE
			evt.cancelBubble = true;
			evt.returnValue = false;
		}
		return false;
	}
}

// JavaScript Document

function showDivLoadIng(msg) {
	if (msg !== '') {
		$('.loadfont').html(msg);
	}
	document.getElementById('popDivload').style.display = 'block';
	document.getElementById('popIframe').style.display = 'block';
	document.getElementById('bg').style.display = 'block';
}

function closeDivLoadIng() {
	$('.loadfont').html('');
	document.getElementById('popDivload').style.display = 'none';
	document.getElementById('bg').style.display = 'none';
	document.getElementById('popIframe').style.display = 'none';
}
//求字节数
var lenReg = function(str){ 
    return str.replace(/[^\x00-\xFF]/g,'**').length; 
}; 
//按字节截取字符串
function cutString(orignal,count){ 
    // 原始字符不为null，也不是空字符串 
	   //alert("你好");
	   
      // 将原始字符串转换为utf-8编码格式 
     // orignal = new String(orignal.getBytes(), "utf-8"); 
      // 要截取的字节数大于0，且小于原始字符串的字节数 
      if (count >0 && count<lenReg(orignal)) { 
		 
        var buff =[]; 
        var c; 
         for (var i = 0; i < count; i++) { 
			        
                c = orignal.charAt(i); 
				   
                buff.push(c);
				  
                if (lenReg(c)>1) { 
                  // 遇到中文汉字，截取字节总数减1 
                  --count; 
                } 
         } 
			
         return buff.toString(); 
      } 
   
    return orignal; 
} 

/**
 * 功能描述：通用ajax提交数据方法，无需表单（文件不行），需要自己实现回调函数callback,有一个参数，可以接受返回，function callback(result)
 * @param url 提交地址，绝对路径
 * @param params 参数数组，结构如：var params  = {userName:'111',password:'2222'};
 * @param callback
 */
function clickButton(url,params,callback){$.post(url, params, callback);
    }

function validUsername(username){
	var str = /^[a-zA-Z0-9_\u4e00-\u9fa5]{3,50}$/i;
	return str.test(username);
}
function validPassword(password){
	
	var str = /^[a-zA-Z0-9]{1,16}$/i;
	return str.test(password);
}


// 首页广告滚动效果结束
function showShopcartId(){
	var countShopCartNumber = 0;
	$.ajax({
	  url:jsCtx+"/shopCartAction.do",
	  type:'post',
	  dataType:'json',
	  data:"method=getShopCartForPayment",
	  beforeSend:function(){
		  $("#div1Id").empty();
			$("#shopcartVoTableId").empty();
			$("#div1Id").html("<span style='color:#ccc;'><img src="+jsCtx+"'../images/loading.gif' align='absmiddle' style='margin-right:20px'>正在加载数据中.....</span>");
		},
	  success:function(data){
		 $("#div1Id").empty();
		$("#shopcartVoTableId").empty();
        var $table = $("<div class='buyList'><table id='shopcartVoTableId'>");
        var $tr1 = $("<tr> <th>产品名称</th> <th>总价</th> </tr>");
        $("#shopcartVoTableId").append($tr1);
        var shopCartList = eval(data.shopCartVoVector);
        var total = 0.00;  
        for(var m in shopCartList){
        	var n = shopCartList[m];
        	var str1 = "<tr><td><a href=\"javascript:deleteShopCartTop('"+n.uuid+"')\"><img src='"+jsCtx+"/views/common/images/close4.gif' alt='' /></a>&nbsp;&nbsp;<a href='#'>"+n.productName+"</a>&nbsp;&nbsp;<span>(<strong class='fontRed2'>"+n.purchaseAmount+"</strong>)</span></td><td>"+n.purchaseSum+"</td></tr>";
        	var $tr2 = $(str1);
          	
        	$("#shopcartVoTableId").append($tr2);
            total = total + n.purchaseSum;
            countShopCartNumber +=1;
        }
        
        $("#shopcartVoTableId").append("</table>");
        $("#shopcartVoDivId").append($table);
        
        var $div1 = $("<div id='div1Id' class='tj'>");
        var $p1 =$("<p class='jg'>金额总计：<span class='fontRed2'>￥"+total+"</span></p><p class='btn'><a href='"+jsCtx+"/online/shopcart.do?method=toShopCartList' class='btn2'>去结算</a></p>");
        var $p2 =$("</div></div>");				
        $("#div1Id").empty();
        $("#div1Id").append($div1);
        $("#div1Id").append($p1);
        $("#div1Id").append($p2);
	  }
		
	});
}
function queryNum(){
	$.ajax({
		url:jsCtx+"/online/shopcart.do",
		type:'post',
		dataType:'json',
		data:"method=queryNum",
		success: function(result){
			if(result.error != "error"){
				$("#countCartNumberId").empty();
				$("#countCartNumberId").html(result.num);
			}
		}
	});
}
function deleteShopCartTop(uuid){
	$.ajax({
		url:jsCtx+"/online/shopcart.do?method=deleteShopCartTop",
		type:'post',
		data:{uuid:uuid},
		beforeSend:function(){
			$("#div1Id").empty();
			$("#shopcartVoTableId").empty();
			$("#div1Id").html("<span style='color:#333;'><img src="+jsCtx+"'../images/loading.gif' align='absmiddle' style='margin-right:20px'>正在加载数据中.....</span>");
		},
		success: function(result){
			if(result == "error"){
				window.location.href = jsCtx+"/404.jsp";
			}else{
				showShopcartId();
				queryNum();
			}
		}
	});
}
// 页面加载完成后运行项
$(function() {
	refreshTop();
	//queryNum();
	

/*新头部内容*/
 $("#mainNav li .nav").hover(function(){
		$(this).addClass("hover").siblings('div').show();
	},function(){
		$(this).removeClass("hover").siblings('div').hide();
	});
    /*$(".subNav").hover(function(){
		$(this).show().siblings('a').addClass("hover");
	},
	function(){
		$(this).hide().siblings('a').removeClass("hover");
	});*/
/*购物车*/
	$("#buyBike").hover(function(){
		showShopcartId();
		$(this).siblings('.buyList').show();
	},function(){
		$("#buyList").hover(function(){
			$(this).show();
		},function(){
			$(this).slideUp();
		});
	});
	

/*新头部结束*/
	
	/*左侧菜单开始*/
	$('.sideMenue .sideSec,.sideMenue .diming').hide();
	$('.sideMenue .diming li.on').parents('.diming').show();
	$('.sideMenue .sideSec li.on').parents('.sideSec').show();
	$('.sideMenue .sideSec h6').click(function() {
	
		var _dis = $(this).next('.sideSec, .diming').css('display');
		$(this).parents('.sideSec').find('.diming').each(function() {
			if ($(this).css('display') == 'block') {
				$(this).hide();
			}
		});
		if (_dis == 'none') {
			$(this).next('.sideSec,.diming').show();
			$(this).children('a').removeClass('up');
		} else {
			$(this).next('.sideSec,.diming').hide();
			$(this).children('a').addClass('up');
		}
		return false;
	});
	
	var url = window.location.href;
	// alert(url.indexOf('/account/invoice.do?method=preApply')>-1);

	// 发票申请菜单
	if (url.indexOf('/account/invoice.do?method=preApply') > -1
			|| url.indexOf('/account/invoice.do?method=apply') > -1) {
		url = jsCtx + '/account/invoice.do?method=searchNoOpenRecords';
	}
	// 域名转移菜单
	if (url.indexOf('/domain/transferIn.do?method=listDomainCorpIn') > -1
			|| url.indexOf('/account/invoice.do?method=apply') > -1) {
		url = jsCtx + '/domain/transferOut.do?method=listDomainCorpOut';
	}
	// 域名管理——偿还期域名菜单
	if (url
			.indexOf('/domain/list.do?method=listDomainManage&serviceState=04&forward=deleteing&firstTime=yes&tab=con3') > -1
			|| url.indexOf('/account/invoice.do?method=apply') > -1) {
		url = jsCtx
				+ '/domain/list.do?method=listDomainManage&serviceState=03&forward=renewing&firstTime=yes&tab=con2';
	}
	// 域名管理——我的域名菜单
	if (url
			.indexOf('/domain/list.do?method=listDomainManage&serviceState=02&forward=inusing&firstTime=yes&tab=con1') > -1
			|| url.indexOf('/account/invoice.do?method=apply') > -1) {
		url = jsCtx
				+ '/domain/list.do?method=listDomainManage&serviceState=03&forward=renewing&firstTime=yes&tab=con2';
	}
	// 邮箱管理-->我的邮箱
	if (url.indexOf('/mail/list.do?method=searchList') > -1) {
		url = jsCtx + '/mail/list.do?method=searchList&searchMode=urgentNeed';
	}
	// 账户信息 -> 我的问题
	if (url.indexOf('/helpcenter/question.do?method=viewAsk') > -1
			|| url.indexOf('/helpcenter/question.do?method=reply') > -1
			|| url.indexOf('/helpcenter/question.do?method=closeAsk') > -1) {
		url = jsCtx + '/helpcenter/question.do?method=answerList';
	}
	// 账户信息 -> 我的消息
	if (url.indexOf('/helpcenter/opennotify.do?method=delNotify') > -1) {
		url = jsCtx + '/helpcenter/opennotify.do?method=list';
	}
	// 帮助中心 -> 资料下载
	if (url.indexOf('/helpcenter/xinDownload.do?method=downDetail') > -1) {
		url = jsCtx + '/helpcenter/xinDownload.do?method=list';
	}
	//虚机管理 -> 特惠
	if (url.indexOf('/vhost/upgrade.do?method=limitedTimeUpgrade1') > -1 
			|| url.indexOf('/vhost/upgrade.do?method=limitedTimeUpgrade2') > -1
			|| url.indexOf('/vhost/upgrade.do?method=limitedTimeUpgrade3') > -1) {
		url = jsCtx + '/vhost/manager.do?method=listVirhost';
	}
	$('.sideMenue .sideFir li').each(function(i) {

		if (url.indexOf($(this).children('a').attr('href')) > -1) {
			$(this).addClass('on');
		}
	});
	
	
	
	$("a[href=#]").attr("href", "javascript:");
	// function getNearParent(obj,filter){
	// alert(obj.parent(filter).length);
	// }
	// 首页-主机服务
	$('.host .adv').hover(function() {
		$(this).addClass('on');
		$(this).children('a.buy2').show();
	}, function() {
		$(this).removeClass('on');
		$(this).children('a.buy2').hide();
	})

	// 首页-企业邮箱
	$('.mail .adv').hover(function() {
		$(this).children('a.buy').show();
	}, function() {
		$(this).children('a.buy').hide();
	})

	// 首页-促销活动
	$('.sales .tit a').click(function() {
		$('.sales .con').hide();
		$('.sales .tit .on').removeClass('on');
		$(this).addClass('on');
		var ids = $(this).attr('id');
		$(ids).show();
		return false;
	}).css({
		"cursor" : "pointer"
	});

	
	// 域名服务-英文域名页签
	$('.namePrice .tit a').click(function() {
		$('.namePrice .con').hide();
		$('.namePrice .tit .on').removeClass('on');
		$(this).parent('li').addClass('on');
		var ids = $(this).attr('id');
		$(ids).show();
		return false;
	}).css({
		"cursor" : "pointer"
	});

	// 域名详细页-域名信息介绍
	$('.comMenu .tit a').click(function() {
		$('.comMenu > .con').hide();
		$('.comMenu .tit .on').removeClass('on');
		$(this).addClass('on');
		var ids = $(this).attr('id');
		$(ids).show();
		return false;
	}).css({
		"cursor" : "pointer"
	});
	$('.comMenu2 .tit a').click(function() {
		$('.comMenu2 .con').hide();
		$('.comMenu2 .tit .on').removeClass('on');
		$(this).parent('li').addClass('on');
		var ids = $(this).attr('id');
		$(ids).show();
		return false;
	}).css({
		"cursor" : "pointer"
	});
	// 套餐组合
	$('.bgbor .titmei a').click(function() {
		$('.bgbor .neirong').hide();
		$('.bgbor .titmei .on').removeClass('on');
		$(this).addClass('on');
		var ids = $(this).attr('id');
		$(ids).show();
		return false;
	}).css({
		"cursor" : "pointer"
	});

	// 域名服务-英文域名-checkbox
	$('input[name=pt2][type=checkbox]').click(function() {
		if (this.checked == true) {
			$('.tabList tr.xf').show();
		} else {
			$('.tabList tr.xf').hide();
		}

	});
	$('input[name=pt3][type=checkbox]').click(function() {
		if (this.checked == true) {
			$('.tabList tr.zr').show();
		} else {
			$('.tabList tr.zr').hide();
		}

	});
	$('input[name=db3][type=checkbox]').click(function() {
		if (this.checked == true) {
			$('.dbTab tr.zr2').show();
		} else {
			$('.dbTab tr.zr2').hide();
		}

	});
	$('input[name=db2][type=checkbox]').click(function() {
		if (this.checked == true) {
			$('.dbTab tr.xf2').show();
		} else {
			$('.dbTab tr.xf2').hide();
		}

	});

	// 域名服务-英文域名-详细页-查询
	$('.checkCom .opens').click(function() {
		$('.checkCom .ym .ymList').toggle();
		return false;
	});

	$('.checkCom .ym .ymList a').click(function() {
		var txt = $(this).text();
		$('.checkCom .ym span').text(txt);
		$('.checkCom .ym .ymList').hide();
		return false;
	});

	// 即时通信
	/*
	$(window).scroll(function() {
		var scrollDiv = $(".asks").offset().top;
		alert(scrollDiv);
		
		var offsetTop = scrollDiv + $(window).scrollTop() + "px";
		$(".asks").animate({
			top : offsetTop
		}, {
			duration : 600,
			queue : false
		});
	});
	*/
	// $('.im .buyAsk').click(function(){
	$('.buyAsk').click(function() {
		$('.askBox').show();
		return false;
	})
	$('.askBox .close').click(function() {
		$('.askBox').hide();
		return false;
	})

	$('.buyAsk2').click(function() {
		$('.askBox2').show();
		return false;
	});
	$('.askBox2 .close').click(function() {
		$('.askBox2').hide();
		return false;
	});

	$('.imBox').hover(function() {
		$('.im').show();
		$('.imBox .imgs').hide();
	}, function() {
		$('.im').hide();
		$('.imBox .imgs').show();
	})

	// 全选

	$("input[name=cheAll]").click(function() {
		if ($(this).attr("checked") == true) {
			$("input[name=che]").attr("checked", "checked");
		} else {
			$("input[name=che]").removeAttr("checked");
		}
	})

	// 域名查询结果-组合推荐

	$('.ass .tj1,.ass .tj2').hover(function() {
		$(this).children('.zhList').children('.bk').addClass('on');
		$(this).children('.zhList').next('.buy2').show();
	}, function() {
		$(this).children('.zhList').children('.bk').removeClass('on');
		$(this).children('.zhList').next('.buy2').hide();
	})

	$('.sele .open').click(function() {
		$(this).toggleClass('close');
		$(this).next('.seleList').toggle();
		return false;
	})

	$('.sele .seleList a').click(
			function() {
				var txt = $(this).text();
				$(this).parent('.seleList').parent('.sele').children('span')
						.text(txt);
				$(this).parent('.seleList').hide();
				$(this).parent('.seleList').parent('.sele').children('.open')
						.removeClass('close');
				return false;
			})

	// 域名管理页 左侧菜单
	$('.sideMenue .sideSec,.sideMenue .sideList').hide();
	$('.sideMenue .sideSec li.on').parents('.sideSec').show();
	$('.sideMenue .sideList li.on').parents('.sideList').show();
	$('.sideMenue .sideFir h4').click(function() {
		$(this).next('.sideSec,.sideList').toggle();
		$(this).children('a').toggleClass('up');
		return false;
	})
	// 联系我们 左侧菜单
	/*
	 * $('.sideMenue .sideSec,.sideMenue .diming').hide(); $('.sideMenue .diming
	 * li.on').parents('.diming').show(); $('.sideMenue .sideSec
	 * li.on').parents('.sideSec').show(); $('.sideMenue .sideSec
	 * h6').click(function(){ $(this).next('.sideSec,.diming').toggle();
	 * $(this).children('a').toggleClass('up'); return false; })
	 */
	

	// 域名频道-打包价格

	$('.dbList .tit2 a').click(function() {
		$(this).parent('.tit2').parent('.dbList').toggleClass('show');
		return false;
	})

	
	// 虚拟主机 加入购物车 按钮弹出层
	$('.addCart').click(function() {
		// $(this).next('.tipCen').show();
		$(this).parent().children('.tipCen').show();
		return false;
	})
	$('.tipCen .tit a,.tipCen .tit2 a').click(function() {
		var className = $(this).attr("class");
		if (className == "son") {
			$(this).parent().parent('.tipCen').hide();
		} else {
			// getNearParent($(this), '.tipCen');
			$(this).parents('.tipCen').hide();
		}
		return false;
	})

	$('.buyCen .tipCen .con .btns a.fontRed2').click(function() {
		$(this).parent('.btns').parent('.con').parent('.tipCen').hide();
		return false;
	})

	// select 隐藏 邮件定制

	$('.mailOrders .cenShow').click(function() {
		$('.seleNo').hide();
	})
	$('.mailOrders .tipCen .tit2 a,.mailOrders .tipCen .btns .close').click(
			function() {
				$('.seleNo').show();
			})

	// 购买页面 线下支付
	$('.lineDown .pt').click(function() {
		$(this).next('ul').toggle();
	})

	// 购物车 优惠码确认

	$('.wrong').hide();
	$('.yhOk').click(function() {
		$('.write').hide();
		$('.wrong').show();
		return false;
	})

	// 会员信息修改提示层

	$('.cenShow').click(function() {
		$(this).next('.tipCen').show();
		return false;
	})
	$('.tipCen .close').click(function() {
		$(this).parent('div').parent('div.con').parent('.tipCen').hide();
		return false;
	})

	// 域名管理权变更 接受提示层

	$('.accepts .acp2').hide();
	$('.accepts .none2').hide();
	$('.acp1,.none1').click(function() {
		$(this).next('.tipCen').show();
	})

	$('.tipOk .ok').click(function() {
		$(this).parents('.tipOk').hide();
		$(this).parents('.accepts').children('.none1').hide();
		$(this).parents('.accepts').children('.acp1').hide();
		$(this).parents('.accepts').children('.acp2').show();
		return false;
	})

	$('.tipNo .ok').click(function() {
		$(this).parents('.tipNo').hide();
		$(this).parents('.accepts').children('.none1').hide();
		$(this).parents('.accepts').children('.acp1').hide();
		$(this).parents('.accepts').children('.none2').show();
		return false;
	})

	// dns
	$('tr.dnsOther').hide();
	// $('tr.dnsarea').hide();
	$("input[type=radio][name=dnsse]").click(function() {
		if ($("input[type=radio][name=dnsse]").eq(0).attr("checked")) {
			$("tr.dnsOther").hide();
			$("tr.dnsXin").show();
			// $("tr.dnsarea").hide();
		} else {
			$("tr.dnsOther").show();
			$("tr.dnsXin").hide();
			// $("tr.dnsarea").hide();
		}
	});

	$('tr.dnsOther').hide();
	$('tr.dnsarea').hide();
	$("input[type=radio][name=domainSuffixType1]").click(
			function() {
				if ($("input[type=radio][name=domainSuffixType1]").eq(0).attr(
						"checked")) {
					$("tr.dnsOther").hide();
					$("tr.dnsXin").show();
					$("tr.dnsarea").hide();
				} else if ($("input[type=radio][name=domainSuffixType1]").eq(1)
						.attr("checked")) {
					$("tr.dnsOther").show();
					$("tr.dnsXin").hide();
					$("tr.dnsarea").hide();
				} else {
					$("tr.dnsOther").hide();
					$("tr.dnsXin").hide();
					$("tr.dnsarea").show();
					$("tr.dnsnew").show();
				}

			})

	

	// 域名注册 域名查询页签

	$('.menu3 .tt a').click(
			function() {
				$('.menu3 .ct').hide();
				$(this).parents('.tt').children('ul').children('li.on')
						.removeClass('on');
				$(this).parent('li').addClass('on');
				var ids = $(this).attr('id');
				$(this).parents('.menu3').children(ids).show();
				return false;
			}).css({
		"cursor" : "pointer"
	});

	// 智捷虚拟主机

	$('.zjList').mouseover(function() {
		$('.zjListOn').removeClass('zjListOn');
		$(this).addClass('zjListOn');
		$('.buy5').removeClass('buy5').addClass('buy5_0');
		$(this).find('.btns .buy5_0').removeClass('buy5_0').addClass('buy5');
	})
	// $('.zjList2').click(function(){
	// $('.zjListOn2').removeClass('zjListOn2');
	// $(this).addClass('zjListOn2');
	// })
	// 企业邮局

	$('.zjList2').mouseover(function() {
		$('.zjListOn2').removeClass('zjListOn2');
		$(this).addClass('zjListOn2');
		$('.buy5').removeClass('buy5').addClass('buy5_0');
		$(this).find('.btns .buy5_0').removeClass('buy5_0').addClass('buy5');
	})

	// tooltip
	// $('.tooltip').hover(function(){
	// $(this).parent().('a.buy2').show();
	// $('.tooltipcont').show();
	// $('.tooltipcont').css({"z-index":"999"});
	// },function(){
	// $(this).children('a.buy2').hide();
	// $('.tooltipcont').hide();
	// })

	$('.tooltip').mouseover(function() {
		$('.tooltipcont').show().mouseleave(function() {
			$(this).hide();
		});
	});

	
	
	window.onload = disabledMouseWheel;
	
	if(loginId == ''){
		 $('#tpassword').keydown(function(event) {
			 var keyCode = event.keyCode;
				if (keyCode == 13) {
					 var regk=/(^\s+)|(\s+$)/g;
					 var url = jsCtx+'/user/user.do?method=loginAdvance&loginType=top';
					 var un=$('#tuserName').attr('value');
					 var pw=$('#tpassword').attr('value');
				
					 if(un=='' || un=='用户名/会员ID'||regk.test(un)){
						 $('#tuserName').focus();
						 showCryDiv('请输入用户名');
					 }else if(pw=='' || pw== '密码密码密码'||regk.test(pw)){
						 $('#tpassword').focus();
						 showCryDiv('请输入密码');
					 }else if(un.length<3){
						 showCryDiv('用户名不能少于3位');
					 } 
					
					 else if(un.length>50){
						 showCryDiv('用户名不能多于50位');
					 } else if(pw.length>16){
						 showCryDiv('密码不能多于16位');
					 }
					 else if(!validUsername(un)){
						 showCryDiv('用户名只能由3-50位数字，字符，下划线或是中文组成');
					 }else if(!validPassword(pw)){
						 showCryDiv('密码只能由数字，字符组成,最少8位，最多16位');
					 }else{
						 var params = {
								 userName:un,
								 password:pw
						      };
						 
			
						 function callback(result){
							 if(result=='pwdError'){
								 showCryDiv('你输入的密码错误，请重新输入');
								 
							 }else if(result=='noUserError'){
								
								 showCryDiv('无此用户，请重新输入');
							 }else if(result=='serverError'){
								 showCryDiv('登陆失败，请稍后重试');
							 }  else{
								
								 if(result.length<50){
									 loginId=result;
								 }
								
								 var href = window.location.href;
								 if(href.indexOf('user.do?method=toLogin')>-1){
									 window.location.href=jsCtx+'/index.do?method=toIndex';
								 }else if(href.indexOf('index.do?method=toIndex')>-1){
									 var lb = $('#idLogin');
								  	 lb.attr('href','javascript://');
								  	 lb.addClass('loginBtnnew').removeClass('loginBtn');
								  	 refreshTop();
									 queryNum();	
									 
								 }else if(href.indexOf('user/user.do?method=loginAdvance')>-1){
									 window.location.href=jsCtx+'/index.do?method=toIndex';
								 }else
								 if(isLoginPage==true){
									 window.location.reload();
								 }else{
									 refreshTop();
									 queryNum();	
								 }
									 //window.location.reload();
								 
						 		 
						 	  	}
						 	  }
						 
						 clickButton(url,params,callback);
					 }
				}
		 });
		 $('#tuserName').keydown(function(event) {
			 var keyCode = event.keyCode;
				if (keyCode == 13) {
					 var regk=/(^\s+)|(\s+$)/g;
					 var url = jsCtx+'/user/user.do?method=loginAdvance&loginType=top';
					 var un=$('#tuserName').attr('value');
					 var pw=$('#tpassword').attr('value');
				
					 if(un=='' || un=='用户名/会员ID'||regk.test(un)){
						 $('#tuserName').focus();
						 showCryDiv('请输入用户名');
					 }else if(pw=='' || pw== '密码密码密码'||regk.test(pw)){
						 $('#tpassword').focus();
						 showCryDiv('请输入密码');
					 }else if(un.length<3){
						 showCryDiv('用户名不能少于3位');
					 } 
					
					 else if(un.length>50){
						 showCryDiv('用户名不能多于50位');
					 } else if(pw.length>16){
						 showCryDiv('密码不能多于16位');
					 }
					 else if(!validUsername(un)){
						 showCryDiv('用户名只能由3-50位数字，字符，下划线或是中文组成');
					 }else if(!validPassword(pw)){
						 showCryDiv('密码只能由数字，字符组成,最少8位，最多16位');
					 }else{
						 var params = {
								 userName:un,
								 password:pw
						      };
						 
			
						 function callback(result){
							 if(result=='pwdError'){
								 showCryDiv('你输入的密码错误，请重新输入');
								 
							 }else if(result=='noUserError'){
								
								 showCryDiv('无此用户，请重新输入');
							 }else if(result=='serverError'){
								 showCryDiv('登陆失败，请稍后重试');
							 }  else{
								
								 if(result.length<50){
									 loginId=result;
								 }
								
								 var href = window.location.href;
								 if(href.indexOf('user.do?method=toLogin')>-1){
									 window.location.href=jsCtx+'/index.do?method=toIndex';
								 }else if(href.indexOf('index.do?method=toIndex')>-1){
									 var lb = $('#idLogin');
								  	 lb.attr('href','javascript://');
								  	 lb.addClass('loginBtnnew').removeClass('loginBtn');
								  	 refreshTop();
									 queryNum();	
									 
								 }else if(href.indexOf('user/user.do?method=loginAdvance')>-1){
									 window.location.href=jsCtx+'/index.do?method=toIndex';
								 }else
								 if(isLoginPage==true){
									 window.location.reload();
								 }else{
									 refreshTop();
									 queryNum();	
								 }
									 //window.location.reload();
								 
						 		 
						 	  	}
						 	  }
						 
						 clickButton(url,params,callback);
					 }
				}
		 });
		
	}
	
	
	
});


/* 登陆相关*/
function doLogin(){
	 var regk=/(^\s+)|(\s+$)/g;
	 var url = jsCtx+'/user/user.do?method=loginAdvance&loginType=top';
	 
	 var un= document.getElementById('tuserName').value;
	
	 var pw=document.getElementById('tpassword').value;
	 if(un=='' || un=='用户名/会员ID'||regk.test(un)){
		
		 document.getElementById('tuserName').focus();
		 showCryDiv('请输入用户名');
	 }else if(pw=='' || pw== '密码'||regk.test(pw)){
		 document.getElementById('tpassword_text').focus();
		 showCryDiv('请输入密码');
	 }else if(un.length<3){
		 showCryDiv('用户名不能少于3位');
	 } 
	
	 else if(un.length>50){
		 showCryDiv('用户名不能多于50位');
	 } else if(pw.length>16){
		 showCryDiv('密码不能多于16位');
	 }
	 else if(!validUsername(un)){
		 showCryDiv('用户名只能由3-50位数字，字符，下划线或是中文组成');
	 }else if(!validPassword(pw)){
		 showCryDiv('密码只能由数字，字符组成,最少8位，最多16位');
	 }else{
		 var params = {
				 userName:un,
				 password:pw
		      };
		 

		 function callback(result){
			 if(result=='pwdError'){
				 showCryDiv('你输入的密码错误，请重新输入');
				 
			 }else if(result=='noUserError'){
				
				 showCryDiv('无此用户，请重新输入');
			 }else if(result=='serverError'){
				 showCryDiv('登陆失败，请稍后重试');
			 }else{
				 if(result.length<50){
					 loginId=result;
				 }
				 
				 if(isLoginPage==true){
					 window.location.reload();
				 }else{
					 refreshTop();
					queryNum();	
				 }
				
				 
		 		 
		 	  	}
		 	  }
		 
		 clickButton(url,params,callback);
	 }
		 } 
//头部用户名密码特效
function focusUser(inputBox) {		 	
	if(inputBox.value == inputBox.defaultValue)
	{
	    inputBox.value = ""
        $(".userName").css("color","#000");
	}
}
function blurUser(inputBox) {
	if($('#userName').val().length>0){
		var agentCode = $('#userName').val();
	}
	if($('#userName').val().length==0){
		var agentCode = $('#userName1').val();
	}
	
	$.ajax({url:jsCtx+"/user/user.do?method=isDynamicCode",
		  type:'POST',
		  async: false,
		  cache: false,
		  dataType:'json',
		  data:{"agentCode":agentCode},
		  success:function(obj){		  
			  if(obj.isDynamic == 'NU'){
				   
				  $('#error1').removeClass('hideElement'); 
				  $('#error1').html('邮箱输入错误或该邮箱未验证。<a id=\'msg\' href=\'javascript:changeOldUserVerifyCode();\' class=\'email-a\'>验证邮箱？</a>');		
				  
				  $('#yanzhengCode').show();
				  $('#dynamicCode').hide();
				  $('#noCheckRand').val("");
			  }
			  if(obj.isDynamic == 'Y'){	
				  $('#error1').addClass('hideElement');
				  $('#error1').html('');
				  $('#checkOLCode').show();
				  $('#yanzhengCode').hide();
				  $('#dynamicCode').show();
				  $('#noCheckRand').val("Y");
			  }
			  if(obj.isDynamic == 'N'){
				  $('#error1').addClass('hideElement');
				  $('#error1').html('');
				  $('#yanzhengCode').show();
				  $('#dynamicCode').hide();
				  $('#noCheckRand').val("");
			  }			  
		  },
		  error:function(){
			  alert("异常");
		  }
	});	
	
	if(inputBox.value == "") {				 
	    inputBox.value = inputBox.defaultValue;
        $("#userName").css("color","#b5b5b5");
	}
}
function passwordTip(th){
	var passText = document.getElementById('tpassword_text');
	var pass = document.getElementById('tpassword');
	if(objIsNull(pass) || objIsNull(passText)){
		return;
	}
	if(th.id == 'tpassword'){
		if(th.value == '' || th.value.length == 0 ){
			passText.style.display='block';
			pass.style.display='none';
		}
	}else{
		passText.style.display='none';
		pass.style.display='block';
		pass.focus();
	}
}

function objIsNull(obj){
	if(null == obj || obj=='' || obj==undefined)
		return true;
	return false;
}


//刷新头部

function refreshTop(){
	if(loginId==''){
		
	$.post(jsCtx+'/user/user.do?method=getUserName', {}, function(result){
		if(result=='notLogin'){
			loginId='';
		}else{
			loginId=result;
		}
		
	});
	}
	
	if(loginId!=''){
		var len=lenReg(loginId);
		if(len>12){
    		loginId=this.cutString(loginId, 12)+"...";
    	}
		loginId=loginId.replace(/,/g,'');
		$("#userLogin").children().hide()
		$("#welcome").show()
		$("#gz").show()
		$("#regBtn").hide()
		$("#welcome2").show()
		$("#welcomeUser").html(loginId);
	}
	
}




//主机服务页签切换


$(function() {
	/*往内容页面里加上当前二级导航项的class*/
	if(document.getElementById('navSecNow') != null){
		//$('.nav .cur').hide();
		var num1 = parseInt($('#navSecNow .f').text());
		var num2 = parseInt($('#navSecNow .s').text());
		$('#mainNav li .nav').eq(num1).addClass('hover');
		//$('.nav .navFir ul .on .navSec li').eq(num2).addClass('on1');
	}

	$('#mainNav li .nav').hover(function(){
		//$('.nav .navFir ul .on .navSec').hide();									
		$('#mainNav li .nav').removeClass('hover');									
		$(this).addClass('hover');
		//$('.nav .navFir ul .on .navSec').show();
		//$('.nav .cur').hide();
	})

	/*结束*/
	$(".topEffect_tiele_m ul li a").click(function() {
		var current = $(this); 
		var aList = current.parents(".topEffect_tiele_m").find("a");//  $(".topEffect_tiele_m").find("a");
		var index = aList.index(current);
		aList.removeClass("on01");
		$(aList[index]).addClass("on01"); 
		var divList = current.parents(".topEffect_tiele_m").next().find(".topEffect_con_m");//$(".topEffect_box_m").find(".topEffect_con_m"); 
		divList.hide();
		$(divList[index]).show();
	});
})

				


/*2012-12-31新增*/
$(function(){
	$(".server_menu_y").click(function(){
		var txt=$(this).text();
		var current = $(".topEffect_tiele_m a:contains('"+txt+"')");
		var aList = current.parents(".topEffect_tiele_m").find("a");;//$(".topEffect_tiele_m").find("a");
		var index = aList.index(current);
		aList.removeClass("on");
		$(aList[index]).addClass("on"); 
		var divList = current.parents(".topEffect_tiele_m").next().find(".topEffect_con_m");//$(".topEffect_box_m").find(".topEffect_con_m"); 
		divList.hide();
		$(divList[index]).show();
		return false;
	});
});



/*2013-02-06新增 by 白羽*/
function initNewPop(ctx) {

	var a2 ="<div id='popUp' class='popUp' style='display:none;'> "
			+ "<h3 class='h3'>系统提示</h3>"
			+ "<div class='popContent'>"
			+ "<p style='padding:20px 0 0;' id='contentNew'>"
			+ "	域名：www.${domainName}<br />商务名片设置成功!  "
			+ "</p>"
			+ "</div>"
			+ "<a href='javascript:closeNewDiv();' class='popClose' id='hrefNew'>确认</a>"
			+ "<a href='javascript:closeNewDiv();' class='popCloseX' >关闭</a>"
			+ "</div>"
			
			+ "<div id='bg' class='bg' style='display:none;'></div>";

	document.write(a2);
}
/*2013-02-06调用新版提示框 by 白羽    parms[domainName：操作的域名(可为空);msg：提示信息]*/
function showNewDiv(msg,domainName){
	
	var message='';
	if(domainName!=''&&domainName!=undefined){
		message+="域名：www."+domainName+"<br />";
	}
	if(msg!=''){
		message+=msg;
	}
	if (message != '') {
		$('#contentNew').html(message);
	}
	document.getElementById('popUp').style.display='block';
	document.getElementById('bg').style.display='block';
}
/*附属产品页遮罩效果，加入z-index=99999*/
function showNewDivNeedZindex(msg,domainName){
	document.getElementById("popUp").style.zIndex='99999';
	var message='';
	if(domainName!=''&&domainName!=undefined){
		message+="域名：www."+domainName+"<br />";
	}
	if(msg!=''){
		message+=msg;
	}
	if (message != '') {
		$('#contentNew').html(message);
	}
	document.getElementById('popUp').style.display='block';
	document.getElementById('bg').style.display='block';
}
/*确定按钮带URL跳转*/
function showNewDivNeedUrl(msg,url){
	var message='';
	if(url!=''&&url!=undefined){
		document.getElementById("hrefNew").href=url;
	}
	if(msg!=''){
		message+=msg;
	}
	if (message != '') {
		$('#contentNew').html(message);
	}
	document.getElementById('popUp').style.display='block';
	document.getElementById('bg').style.display='block';
}
function closeNewDiv(){
	document.getElementById('popUp').style.display='none';
	document.getElementById('bg').style.display='none';
}











