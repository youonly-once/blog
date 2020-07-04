/*a中include b ，b 中 include c  b中不能动态刷新c，不知为何*/
loadHotArticles();
function loadHotArticles() {
    // document.getElementById("hotArticles").innerHTML ="";
    $.ajax({
        type: 'post', //可选get
        url: '/article/hot.action', //这里是接收数据的程序
        dataType: 'html', //服务器返回的数据类型 可选XML ,Json jsonp script html text等
        success: function(msg) {
            //这里是ajax提交成功后，程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！
            document.getElementById("hotArticles").innerHTML = msg;

        },
        error: function() {
            console.log('加载推荐文章失败');
        }
    })
}
