var flag=true;
$(document).ready(function () {

    //清空之前的
    $("span").val("");
    $("#valiimg").click(function () {
        $(this).attr("src","/Captcha?x="+Math.random())
    })
    $("input[name='username']").keyup(function () {

        checkEmpty($(this),"用户名不能为空");
    });
    $("input[name='password']").keyup(function () {
        checkEmpty($(this),"密码不能为空");

    });
    $("input[name='password2']").keyup(function () {
        checkEmpty($(this),"确认密码不能为空");
        if($(this).val()!="" && $("input[name='password']").val()!=$(this).val()){
            setTip($(this),"密码不匹配");
            flag=false;
        }
    });
    $("input[name='nickname']").keyup(function () {
        checkEmpty($(this),"昵称不能为空");
    });
    $("input[name='email']").keyup(function () {
        checkEmpty($(this),"邮箱地址不能为空");
        var reg=/^\w+@\w+(.\w+)+$/;
        if($(this).val()!="" && !reg.test($(this).val())){
            setTip($(this),"邮箱格式不正确");
            flag=false;
        }
    });
    $("input[name='valistr']").keyup(function () {
        checkEmpty($(this),"验证码不能为空");
    });
})


function checkForm() {
    flag=true;
    //清空之前的
    $("span").html("");
    // var flag=true;
    //非空校验
    var emailo=$("input[name='email']");
    var passwordo=$("input[name='password']");
    var password2o=$("input[name='password2']");
    var usernameo=$("input[name='username']");
    var nicknameo=$("input[name='nickname']");
    var valistro=$("input[name='valistr']");
    checkEmpty(usernameo,"用户名不能为空");
    checkEmpty(passwordo,"密码不能为空");
    checkEmpty(password2o,"确认密码不能为空");
    checkEmpty(emailo,"邮箱不能为空");
    checkEmpty(nicknameo,"昵称不能为空");
    checkEmpty(valistro,"验证码不能为空");
    //密码不一致校验
    if(password2o.val()!="" && passwordo.val()!=password2o.val()){
        setTip($("input[name='password2']"),"密码不匹配");
        flag=false;
    }
    var reg=/^\w+@\w+(.\w+)+$/;
    if(emailo.val()!="" && !reg.test(emailo.val())){
        setTip($("input[name='email']"),"邮箱格式不正确");
        flag=false;
    }
    $("#tip").val("");
    return flag;
}
function checkEmpty(o,tip){
    setTip(o,"");
    if(o.val().trim()== ""){
        setTip(o,tip);
        flag=false;
    }
}
function setTip(o,tip){
    o.nextAll("span").html(tip).css("color","red");
}
