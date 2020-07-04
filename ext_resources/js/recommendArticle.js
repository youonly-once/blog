$(function () {
    $.ajax({
        type: 'post', //可选get
        url: "/article/recommend.action?articleId="+$("#articleid").val()+"&title="+$("#articleTitle").html(), //这里是接收数据的程序
        dataType: 'html', //服务器返回的数据类型 可选XML ,Json jsonp script html text等
        success: function(msg) {
            //这里是ajax提交成功后，程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！
            $("#recommendArticles").html(msg);
        },
        error: function() {
            console.log('加载推荐文章失败');
        }
    })
});