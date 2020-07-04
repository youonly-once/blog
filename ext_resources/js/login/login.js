function login(u,p,rem,auto) {
    // document.getElementById("hotArticles").innerHTML ="";
    $.ajax({
        type: 'post', //可选get
        url: '/user/login.action', //这里是接收数据的程序
        data:"userName="+u+"&password="+p+"&remName="+rem+"&autoLogin="+auto,
        dataType: 'text', //服务器返回的数据类型 可选XML ,Json jsonp script html text等
        success: function(msg) {
            //这里是ajax提交成功后，程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！
            if(msg=="success"){
                //登录成功
                $("#loginMsg").html("登录成功，跳转中");
                //跳转首页
                window.location.replace("/");
            }else{
                console.log(msg);
                $("#loginMsg").html(msg)
            }
            $("#loginModalUserPwd").val(null)

        },
        error: function() {
            $("#loginModalUserPwd").val(null);
            $("#loginMsg").html("登录失败")
        }
    })
}
$(document).ready(function () {
    //cookie中文乱码 服务器会编码，前端 JS解码
    var v=$("#loginModalUserName");
    v.val( decodeURI(v.val()));

    $("#login").click(function () {
        $("#loginMsg").html("");
        let u=$("#loginModalUserNmae").val();
        let p=$("#loginModalUserPwd").val();
        let rem=$("#loginModalRemName").prop("checked");
        let auto=$("#loginModalAuto").prop("checked");
        if(!check(u,p)){
            login(u,p,rem,auto);
        }

    })
});
function check(u,p) {
    return (u.trim()=="" || p.trim()=="")
}