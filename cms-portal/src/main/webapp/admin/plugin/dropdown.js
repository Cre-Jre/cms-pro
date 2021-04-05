(function ($, w) {
    function Dropdown(ele, data,callback) {
        this.init(ele, data, callback);
    }

    Dropdown.prototype = {
        construct: Dropdown,
        event: function (li, item, callback) {
            li.click(function () {
                callback(li,item);
            })
        },
        init: function (ele, data, callback) {
            if(!data || data.length<1){
                return
            }
            let drop = $("<div class='sui-dropdown'>");
            drop.hide();
            let ul = $('<ul class="sui-dropdown-menu">');
            let that = this;
            data.forEach(function (item) {
                if(item instanceof Object){
                    let li = $('<li class="sui-menu-list">' + item.name + '</li>');
                    that.event(li, item, callback);
                    ul.append(li);
                }
            });
            drop.append(ul);
            $(ele).after(drop);
            //这个地方很重要 在鼠标移动到菜单上的时候需要停止滑动
            drop.on("mouseenter",function(){
                drop.stop(true, true)
            });
            drop.on("mouseleave",function(){
                drop.slideUp()
            });
            $(ele).on("mouseover",function(){
                drop.slideDown().delay(800).fadeOut(400)
            });
            $(ele).on("mouseout",function(){
                drop.slideUp(800).delay(800).fadeOut(400)
            })
        }
    };
    w.dropdown = Dropdown;
})($, window);