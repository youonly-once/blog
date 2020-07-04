//无限滚动反翻页 首页
jQuery.ias({
    history: false,
    container : '.content',//容器：顾名思义，就是放一条条信息的html元素。
    item: '.excerpt',//承载一条条信息的html标签,确保他们是放在container里面的。
    pagination: '.pagination',//做分页的时候，我们都会在页面上显示分页字符串，用的此技术时也应该有此功能，并赋值在页面上。该技术会自动将其隐藏。分页时会用的下一页的页 码。
    next: '.next-page a#slnext',
    trigger: '查看更多',
    loader: '<div class="pagination-loading"><img src="../images/loading.gif" /></div>',
    triggerPageThreshold: 5,
    onRenderComplete: function() {
        $('.excerpt .thumb').lazyload({
            placeholder: '../images/occupying.png',
            threshold: 400
        });
        $('.excerpt img').attr('draggable','false');
        $('.excerpt a').attr('draggable','false');
    },
    onPageChange: function (pageNum, pageUrl, scrollOffset) {
    },
    //加载之前判断函数
    beforePageChange:function(curScrOffset, nextPageUrl){

        // console.log(totalPage);
        let totalPage=parseInt($("#totalPage").html().trim());
        let currPage=parseInt($("#currPage").html().trim());
        if (currPage<totalPage){
            return true;
        }
        return false;
    }
});