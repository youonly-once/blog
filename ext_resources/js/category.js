$(function () {
        let currCategoryId = $("#currCategoryId").attr("data-currCategoryId");
        let curr=$(".title .more a[data-id='"+currCategoryId+"']");
        modi(curr);

     /*  $(".title .more a").click(function () {
               modi($(this));
        });*/
});
function modi(curr){
        //获取当前显示的文章类型


        let a = $(".title .more a");
        a.css("color","#3399CC");
        a.css("background-color",null);
        //修改选中的样式
        curr.css("color","white");
        curr.css("background-color","#3399CC");
}