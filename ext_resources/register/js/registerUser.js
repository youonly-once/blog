var captcha = false;
$(function(){
	if($.browser.msie && parseFloat($.browser.version)<=parseFloat("8.0")) { 
		window.location.href ="http://www.xinnet.com/views/common/loweriebroser.jsp";
    }
	
	// slide验证插件使用
	/*$("#slide_box").slideToUnlock({
		text:"滚动滑块验证",   //文本设置
		successFunc:function(){    //拖动成功后的回调函数
			$(".slide_box .slide-to-unlock-progress").text("验证成功");  //成功后的文字显示
			$("#captcha").val(getCode());
            captcha = true;
            $("#captcha_hint").hide();
            checkCaptcha();
		}
	});*/
	
	if(!$.cookie('finger')){
		new Fingerprint2({
			userDefinedFonts: ["Nimbus Mono", "Junicode", "Presto"]
		}).get(function(result, components) {
			$.cookie('finger', result,{path:'/'});
		});
	}
	
	$(document).keypress(function(e) {  
	    // 回车键事件  
	   if(e.which === 13) {
		   register();
	   }  
	});
	
	/*$.ajax({
		url : jsCtx + '/user/userlogin.do?method=captcha',
		type : "POST",
		dataType : "text",
		data : {},
		success : function(data) {
			initUA(data);
		}
	});*/

   	
	//邮件提示插件
    $("#userNameEmail").mailAutoComplete();
	$("#userName_hint").hide();
	$("#nickName_hint").hide();
	$("#userNameEmail_hint").hide();

	$("#password_hint").hide();

	$("#captcha_hint").hide();

	$("#userName").blur(function(){
		if($("#userName").val()==""){
			$(this).attr('placeholder','请输入用户名');
			$(this).attr('style','border-color: #9c9c9c;');
		}else{
			checkUsername();
		}

	});
	$("#userName").focus(function(){
		defaultHint("userName",true);
	});
	$("#userName").keyup(function(){
		if($("#userName").val()=="请输入用户名"){
			$(this).attr('placeholder','');
			$(this).attr('style','border-color: #9c9c9c;');
		}
		checkUsername();
	});
	$("#nickName").blur(function(){
		if($("#nickName").val()==""){
			$(this).attr('placeholder','请输入昵称');
			$(this).attr('style','border-color: #9c9c9c;');
		}else{
			checkNickName();
		}

	});
	$("#nickName").focus(function(){
		defaultHint("nickName",true);
	});
	$("#nickName").keyup(function(){
		if($("#nickName").val()=="请输入昵称"){
			$(this).attr('placeholder','');
			$(this).attr('style','border-color: #9c9c9c;');
		}
		checkNickName();
	});

	$("#userNameEmail").blur(function(){
		if($("#userNameEmail").val()==""){
			$(this).attr('placeholder','请输入邮箱地址');
			$(this).attr('style','border-color: #dfdcdc;');
		}else{
			checkEmailFormat();
		}
	});
	$("#userNameEmail").focus(function(){
		defaultHint("userNameEmail",true);
	});
	$("#userNameEmail").keyup(function(){
		if($("#userNameEmail").val()=="请输入邮箱地址"){
			$(this).attr('placeholder','');
			$(this).attr('style','border-color: #9c9c9c;');
		}
		checkEmailFormat();
		//resetCaptcha();
	});
	
	$("#password").blur(function(){
		if($("#password").val()==""){
			$(this).attr('placeholder','请设置登录密码');
			$(this).attr('style','border-color: #dfdcdc;');
		}else{
			checkPassword();
		}
	});
	$("#password").focus(function(){
		defaultHint("password",true);
	});
	$("#password").keyup(function(){
		if($("#password").val()=="请设置登录密码"){
			$(this).attr('placeholder','');
			$(this).attr('style','border-color: #9c9c9c;');
		}
		checkPassword();
	});


	//刷新验证码
	$("#captchaImg").click(function () {
		$(this).css("background-image","url(/user/Captcha.action?x="+Math.random()+")");
	});
	$("#password").blur(function(){
		checkCaptcha();
	});
	$("#captcha").keyup(function () {
		checkCaptcha();
	});
	$("#captcha").focus(function () {
		defaultHint("captcha",true);
		$("#captchaImg").css("background-image","url(/user/Captcha.action?x="+Math.random()+")");
	});
	var pwdeye = $('#pwdeye');
	var pwdshow = $("#password");
	
	pwdeye.off('click').on('click',function(){
		if(pwdeye.hasClass('pwdeye_invisible')){
			pwdeye.removeClass('pwdeye_invisible').addClass('pwdeye_visible');//密码可见
			pwdshow.prop('type','text');
		}else{
			pwdeye.removeClass('pwdeye_visible').addClass('pwdeye_invisible');//密码不可见
			pwdshow.prop('type','password');
		};
	});

});

/*****刷新验证码********/
function resetCaptcha(){
	$("#captchaImg").css("background-image","url(/user/Captcha.action?x="+Math.random()+")");
	if($("#captcha_position").is(":hidden")){ // 如果已发送验证码，再次修改需要重置滑块验证
		$("#captcha_position").show();
		$("#verifyCode_position").hide();
		disabledRegisterButton();
	}
	captcha = false;
	//eObj.refresh();
	/*$("#slide_box").slideToUnlock({
		text:"滚动滑块验证",   //文本设置
		successFunc:function(){    //拖动成功后的回调函数
			$(".slide_box .slide-to-unlock-progress").text("验证成功");  //成功后的文字显示
			$("#captcha").val(getCode());
            captcha = true;
            $("#captcha_hint").hide();
            checkCaptcha();
		}
	});*/
//	initVerify();
}
function checkIsRegister() {
	if (checkForm()){
		activateRegisterButton();
	}
}
/**
 * 判断验证码是否正确
 */
function checkCaptcha(){
	var captcha =$("#captcha").val();

	if(checkForm()){

		if (captcha.length!=4){
			errorHint("captcha","","验证码位数不正确");
			return false;
		}
	var correctFlag=false;
		$.ajax({
			url:  jsCtx+'/user/registerCheck.action?method=captcha',
			type : "POST",
			dataType : "text",
			data : "captcha="+captcha,
			success : function(data) {
				//验证码校验成功 注册按钮可用
				if(data=="success"){
					correctFlag = true;
					//$("#captchaImg").css("background-image","url(images/check_dui.png)");
					defaultHint("captcha",true);
					//激活注册按钮
					activateRegisterButton();


				}else{
					correctFlag = false;
					errorHint("captcha","","验证码错误");
				}
			}
		});
		return correctFlag;
	}else{
		/*setTimeout(function(){
			resetCaptcha();
		}, 1000);*/
		return false;
	}
}

function checkForm(){

	if(!checkUsername()){
		return false;
	}
	if(!checkNickName()){
		return false;
	}
	if(!checkPassword()){
		return false;
	}
	if(!checkEmail()){
		return false;
	}
/*    if(!checkCaptcha()){
		return false;
	}*/
	return true;
}

/*****激活注册按钮********/
function activateRegisterButton(){
	$("#register_href").removeClass("register_opac");
	$("#register_href").on("click",function(){register();});
}

/*****禁用注册按钮********/
function disabledRegisterButton(){
	$("#register_href").addClass("register_opac");
	$("#register_href").off('click');
}
/**
 * 
 * @param elementId 元素id 命名规则 元素Id_error_txt错误信息;元素Id_hint 限制规则
 * @param summaryMsg 概略错误提示内容 
 * @param detailMsg 详细错误提示内容
 */
function errorHint(elementId,summaryMsg,detailMsg){
	disabledRegisterButton();
	//alert(elementId)
/*	if (elementId!="captcha"){
		$("#captcha").val("");
	}*/

	$("#"+elementId+"_error_txt").html(summaryMsg);
	$("#"+elementId).addClass("register_error");
	if(detailMsg!="" && detailMsg!=null){
		$("#"+elementId+"_hint").show();
		$("#"+elementId+"_hint").html("<span></span>"+detailMsg);
	}
}
/******恢复错误提示为默认状态*******/
function defaultHint(elementId,isHint){
	$("#"+elementId+"_error_txt").html("");
	$("#"+elementId).removeClass("register_error");
	if(isHint){
		$("#"+elementId+"_hint").hide();
	}
}
/**
 * 验证用户名
 * @returns {Boolean}
 */
function checkUsername(){
	var userName=$("#userName").val();

	if(userName!=""&&userName!=null&&userName!="请输入用户名"){
		var correctFlag = false;
		//查询用户名是否已被注册
		$.ajax({
			url:jsCtx+'/user/registerCheck.action?method=userName',
			type:'post',
			dataType:"text",
			async:false,
			data:"userName="+userName,
			success:function(data){
				if(data=="success"){
					correctFlag = true;
					defaultHint("userName",true);
				}else{
					correctFlag = false;
					errorHint("userName","","用户名已被注册");
				}
			}
		});
	}else{
		correctFlag = false;
		errorHint("userName","","请输入用户名");
	}
	return correctFlag;
}

/**
 * 验证昵称
 * @returns {boolean}
 */
function checkNickName(){
	var nickName=$("#nickName").val();

	if(nickName!=""&&nickName!=null&&nickName!="请输入昵称"){
		var correctFlag = true;
//
		//查询昵称是否已被注册
		$.ajax({
			url:jsCtx+'/user/registerCheck.action?method=nickName',
			type:'post',
			dataType:"text",
			async:false,
			data:"nickName="+nickName,
			success:function(data){
				if(data=="success"){
					correctFlag = true;
					defaultHint("nickName",true);
				}else{
					correctFlag = false;
					errorHint("nickName","","昵称已被注册");
				}
			}
		});
	}else{
		correctFlag = false;
		errorHint("nickName","","请输入昵称");
	}
	return correctFlag;
}
/*********验证邮箱格式**********/
function checkEmailFormat(){
	var userNameEmail=$("#userNameEmail").val();
	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(userNameEmail==''||userNameEmail==null){
		errorHint("userNameEmail","","请输入邮箱");
		return false;
	}else if(userNameEmail.length>64){
		errorHint("userNameEmail","邮箱格式错误","邮箱长度不能超过64");
        return false;
	}else if (!reg.test(userNameEmail)) {
		errorHint("userNameEmail","邮箱格式错误","邮箱格式有误，请重新输入");
	    return false;
    }
	defaultHint("userNameEmail",true);
	checkEmail();
	return true;
}
/**
 * 验证邮箱
 * @returns {Boolean}
 */
function checkEmail(){
	var userNameEmail=$("#userNameEmail").val();

	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if(userNameEmail == ''){
		errorHint("userNameEmail","","请输入邮箱");
		return false;
	}else if(userNameEmail.length>64){
		errorHint("userNameEmail","邮箱格式错误","邮箱长度不能超过64");
        return false;
	}else if (!reg.test(userNameEmail)) {
		errorHint("userNameEmail","邮箱格式错误","邮箱格式有误，请重新输入");
	    return false;
    }
	var flag=false;
	$.ajax({
		url:jsCtx+'/user/registerCheck.action?method=mail',
		type:"POST",
		async:false,
		data:"mail="+userNameEmail,
		dataType:"text",
		/*contentType:"application/x-www-form-urlencoded; charset=utf-8",*/
		success:function(data){
			if(data=="success"){
		        flag=true;
		        defaultHint("userNameEmail",true);
		    }else{
				errorHint("userNameEmail",data,"该邮箱地址不可用");
		    }
		},
		error:function(){
			errorHint("userNameEmail","","系统繁忙");
		}
	});
	return flag;
}


function checkPassword(){
	var password=$("#password").val();
	var regex=new RegExp("^(((?=.*[0-9].*)(?=.*[a-z].*))|((?=.*[0-9].*)(?=.*[A-Z].*))|((?=.*[a-z].*)(?=.*[A-Z].*))|((?=.*[0-9].*)(?=.*[a-z].*)(?=.*[A-Z].*))).{8,64}$","g");
	if(password==''||password==null){
		errorHint("password","","密码不能为空");
		return false;
	}else if(password.length<8||password.length>16){
		errorHint("password","密码格式错误","密码为8~16个字符，区分大小写");
		return false;
	}else if(regex.test(password)==false){
		errorHint("password","密码格式错误","至少包含数字字母");
		return false;
	}
	defaultHint("password",true);
	return true;
}


/*********注册提交start*********************/

function register(){
	if(!checkForm()){
		return false;
	}
	$("#userRegisterForm").submit();
	/*var registerType=getCookie("registerType");
	$("#registerType").val(registerType);
	$("#password").val(encodepwd($("#password").val()));
	var userRegisterForm=$("#userRegisterForm").serialize();
	 $.ajax({
			url:jsCtx+'/userRegister/userRegister.do?method=myRegister',
			type:"post",
			data:userRegisterForm,
			async: false,
			cache: false,
			success:function(data){
				var results = data.split("--");
				if(results[0]=='success'){
					var urlHref= jsCtx+"/views/registernew/mobile_register_success.jsp";
					if(results.length == 3){
						if (results[2] == '1') {
							urlHref = results[1];
						}
					} else if (results.length == 4) {
						if (results[3] == '1') {
							urlHref = results[2];
						}
					}
					window.location.href = urlHref;
					return true;
				} else {
					$("#password").val("");
					
					if(results[0] == '902504'){
						errorHint("verifyCode","验证码错误","验证码过期或未发送，请重新获取数字验证码！");
					}else if(results[0] == '902400'){
						errorHint("verifyCode","验证码错误");
					}else if(results[0] == 'verify_code_is_error'){
						errorHint("verifyCode","非法访问","非法访问！");
					}else if(results[0] == 'illegalAccess'){
					}
					return false;
				}
			},error:function(){
				$("#password").val("");
				return;	
			}		
	});*/
}

