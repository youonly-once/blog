
let searchStr=getUrlParam('searchStr')
let currPage=getUrlParam('currPage')
let pageNum=getUrlParam('pageNum')
	    if (currPage==''||currPage==null)
    {
       currPage=1
    }
	    if (pageNum==''||pageNum==null)
    {

        pageNum=5
    }
	//获取页数相关信息
 search(searchStr,currPage,pageNum)
var total = ($("#searchnum").val().trim()),//数据总条数
    pageNumber = 1,//当前页
    pageSize = ($("#searchpagenum").val()), //每页显示的条数
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
$(function(){

$('#search_form').submit(function () {
	$('#quotes').empty()
		//获取页数相关信息
searchStr=$('#searchStr').val()
 search(searchStr,currPage,pageNum)

       	// alert('你好')
        //按下回车/提交按钮后的操作
		$('.content').find("article").remove();
        //search($('#searchStr').val(),1,5);
      total = ($("#searchnum").val().trim()),//数据总条数
    pageNumber = 1,//当前页
    pageSize = ($("#searchpagenum").val()), //每页显示的条数
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
        //返回false
      return false;
    })

})
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
   
    $.ajax({
        type: 'post', //可选get
        url: '/article/search.action', //这里是接收数据的程序
        data: 'searchStr='+searchStr+'&currPage='+nextPage+'&pageNum='+pageNum,//传给后台的数据，多个参数用&连接
        dataType: 'json', //服务器返回的数据类型 可选XML ,Json jsonp script html text等
        success: function(data) {
            //alert(data.length)
            if (data==null)return
			//删除之前的内容
			$('.content').find("article").remove();
            $.each(data.articles,function(index, article){
                let myHtml='<article class="excerpt excerpt-1">' +
                    '<a class="focus" href="/article/detail/'+article.id+'.action" title=""> ' +
                    '<img class="thumb" data-original="/images/logo.png" src=/'+article.imagePath+' /> ' +
                    '</a>' +
                    '<header>' +
                    '<a class="cat" href="/article/detail/'+article.id+'.action">'+article.categoryName+'<i></i></a>' +
                    '<h2><a href="/article/detail/'+article.id+'.action" title="">'+article.title+'</a></h2>' +
                    '</header>' +
                    '<p class="meta">' +
                    '<time class="time"><i class="glyphicon glyphicon-time"></i>'+article.updateDate+'</time>' +
                    '<span class="views"><i class="glyphicon glyphicon-eye-open"></i> '+article.visitors+' 人围观</span>' +
                    '<a class="comment" href="/article/detail/'+article.id+'.action"><i class="glyphicon glyphicon-comment"></i> '+article.commNum+' 评论</a>' +
                    '</p>' +
                    '<a  href="/article/detail/'+article.id+'.action"><p class="note">'+article.description+'</p></a>' +
                    '</article>'
                //alert(myHtml)
                $(myHtml).insertBefore('#searchnum')
            })
            callback();
        },
        error: function() {
            console.log('对不起失败了');
        }
    })
}
//第一次搜索 获取搜索结果的数量，用于翻页计算
function search(searchStr,currPage,pageNum){
	//搜索字符串为空 返回
	if (searchStr==''||searchStr==null){
        return false
    }
	$('#searchStr').val(searchStr)
    //alert(searchStr)
    $.ajax({
        dataType:'json',
        async:false,
        url:'/article/search.action?searchStr='+searchStr+'&currPage='+currPage+'&pageNum='+pageNum,
        method:'get',
        success:function (data) {
            //alert(data.length)
            if (data==null)return
			$('#searchpagenum').val(5)
			$('#searchnum').val(data.totalNum)

        },
        error:function(data){

        }
    })
}
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURI(r[2]); return null; //返回参数值
}
