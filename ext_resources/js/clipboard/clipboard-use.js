/*页面载入完成后，创建复制按钮*/
!function (e, t, a) {
    /* code */
    var initCopyCode = function(){
        var copyHtml = '';
        copyHtml += '<button class="btn-copy" data-clipboard-snippet="" data-clipboard-target="#foo">';
        copyHtml += '<span>复制</span>';
        copyHtml += '</button>';
        // 因为使用的是谷歌代码插件样式，每个pre标签外再嵌套一层code
        $("pre").wrap("<code></code>");
      /* $("code pre").html("");*/
        $("code pre").before(copyHtml);
/*        new ClipboardJS('.btn', {
            container:  $("code pre")
        });*/

        new ClipboardJS('.btn-copy',{
            target: function(trigger) {

                return trigger.nextElementSibling;
            }
        });
    };
    initCopyCode();
    /*
    * 鼠标进入显示复制按钮
    * */
    $("code").on({
        mouseenter: function (e) {
            $(this).children("button").css("opacity",50);
        },
        mouseleave: function (e) {
            $(this).children("button").css("opacity",0);

        }
    })
 /*   $("code button").on({
        mouseenter: function (e) {
            $(this).prev().css("opacity",50);
        },
        mouseleave: function (e) {
            $(this).prev().css("opacity",0);

        }
    })*/
}(window, document);
