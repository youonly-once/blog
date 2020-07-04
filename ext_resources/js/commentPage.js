var total = ($("#commentnum").val().trim()),//数据总条数
    pageNumber = 1,//当前页
    pageSize = ($("#commenpagenum").val()), //每页显示的条数
    edges = 2,//两侧显示的页码数 大于1
    playes = 5,//主页码区显示的页码数 大于3
    pages = Math.ceil(total / pageSize);//总页数
    if(pages>=5){
        playes=5;
    }else{
        playes=1
    }
    if (parseInt(total)>0){
        renderPageItem();//加载第一页
        allhtml(1,function () {

        });
    }

function renderPageItem() {
    $ul = $('<ul class="pagination"></ul>');
    var start = 1;
    var end = pages;
    if (playes % 2) {
        //playes是奇数
        start = pageNumber - Math.floor(playes / 2);
        end = pageNumber + Math.floor(playes / 2);
    } else {
        //playes是偶数
        start = pageNumber - (playes / 2 - 1);
        end = pageNumber + playes / 2;
    }

    if (start <= edges + 1) {
        start = 1;
        if (end < playes && playes<pages) {
            end = playes;
        }
    } else {
        for (var i = 1; i <= edges; i++) {
            $ul.append(renderItem(i));
        }
        $ul.append('<li><span>...</span></li>')
    }
    if (end < pages - edges) {
        for (var i = start; i <= end; i++) {
            $ul.append(renderItem(i));
        }
        $ul.append('<li><span>...</span></li>');
        for (var i = pages - edges + 1; i <= pages; i++) {
            $ul.append(renderItem(i));
        }
    } else {
        end = pages;
        if(start>pages-playes+1){
            start = pages-playes+1
        }
        for (var i = start; i <= end; i++) {
            $ul.append(renderItem(i));
        }
    }
    $ul.prepend(renderPrevItem());
    $ul.append(renderNextItem());
    $('#quotes').empty().append($ul);
}

function renderItem(i) {
    $item = $('<li><a href="#">' + i + '</a></li>');
    if (i == pageNumber) {
        $item.addClass('active');
    }
    $item.on('click', (function (num) {
        return function () {
            pageNumber = num;

            //点击页码处理 刷新评论
            allhtml(
                pageNumber,
                function() {

                });
            renderPageItem();
            //console.log("dddd:"+pageNumber)


        }
    })(i));

    return $item
}

function renderPrevItem() {
    $prev = $('<li><a href="#">&laquo;</a></li>');
    if (pageNumber == 1) {
        $prev.addClass('disabled');
    } else {
        $prev.on('click', function () {
            pageNumber = pageNumber - 1;

            allhtml(
                pageNumber,
               function() {
                   console.log("上一页评论")
                });
            renderPageItem();
            console.log("Prev:"+pageNumber);


        })
    }

    return $prev;
}

function renderNextItem() {
    $next = $('<li><a href="#">&raquo;</a></li>');
    if (pageNumber == pages) {
        $next.addClass('disabled');
    } else {
        $next.on('click', function () {
            pageNumber = pageNumber + 1;
            allhtml(
                pageNumber,
                function() {
                    console.log("下一页评论")
                });
            renderPageItem();
            console.log("next:"+pageNumber);
        })
    }

    return $next;
}
function allhtml(nextPage,callback) {
    document.getElementById("commentlist").innerHTML ="";
    $.ajax({
        type: 'post', //可选get
        url: '/article/comment.action?currPage='+nextPage, //这里是接收数据的程序
        data: 'articleId=' + $("#articleid").val().trim(),//传给后台的数据，多个参数用&连接
        dataType: 'html', //服务器返回的数据类型 可选XML ,Json jsonp script html text等
        success: function(msg) {
            //这里是ajax提交成功后，程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！
            document.getElementById("commentlist").innerHTML = msg;
            //  $("#nextPage").html(nextPage);
            //$("#duoduo").innerHTML = msg;
            //console.log("成功:"+msg);
            //回调更新页码
            callback();
        },
        error: function() {
            console.log('对不起失败了');
        }
    })
}
/*文章评论*/
$(function(){
    $("#comment-submit").click(function(){
        var commentContent = $("#comment-textarea");
        var commentButton = $("#comment-submit");
        var promptBox = $('.comment-prompt');
        var promptText = $('.comment-prompt-text');
        var articleid = $('.articleid').val();
        promptBox.fadeIn(400);
        if(commentContent.val() === ''){
            promptText.text('请留下您的评论');
            return false;
        }
        commentButton.attr('disabled',true);
        commentButton.addClass('disabled');
        promptText.text('正在提交...');
        $.ajax({
            type:"POST",
            url:"/article/addComment.action?article.id=" + articleid,
            data:"comment=" + replace_em(commentContent.val()),
            cache:false, //不缓存此页面
            success:function(data){
                //alert(data);
                if(data=="success"){
                    promptText.text('评论成功!');
                    promptText.css('color',"#3399CC");
                    $(".commentlist").fadeIn(600);
                    //刷新页码 需重新获取数据总数 计算变量 改天在做

                    //刷新评论界面
                    allhtml(1,function () {


                    });
                }
                else{
                    promptText.css('color',"red");
                    promptText.text('评论失败!');
                    $(".commentlist").fadeIn(3000);
                }
                commentContent.val(null);

                /*$(".commentlist").append();*/
                commentButton.attr('disabled',false);
                commentButton.removeClass('disabled');
            }
        });
        /*$(".commentlist").append(replace_em(commentContent.val()));*/
        promptBox.fadeOut(100);
        return false;
    });
});
//对文章内容进行替换
function replace_em(str){
    str = str.replace(/\</g,'&lt;');
    str = str.replace(/\>/g,'&gt;');
    str = str.replace(/\[em_([0-9]*)\]/g,'<img src="/images/arclist/$1.gif" border="0" />');
    return str;
}
