var srcArray = new Array();
var index = 0;
CKEDITOR.dialog.add('uploadOssDialog', function (editor) {
    return {
        title: '图片上传七牛云',
        minWith: 400,
        minHeight: 200,
        //content是子标签
        contents: [
            {
                id: 'imageOss',
                label: '上传图片',
                //elements是每个子标签下面的ui元素，如表单元素等  自定义弹窗的内容，可以使用模板，也可自定义html及样式
                elements: [
                    {
                        //图片上传成功后的容器
                        type: 'html',
                        html: '<div id="fileOss"></div>',
                        //对应的样式
                        style: '',
                        onShow: function () {
                            //在每次弹窗打开的时候都会调用该方法
                        },
                        //点击确定按钮时，在onOK中调用commitContent，会依次触发element的commit方法
                        commit: function (editor) {  //点击确定按钮时，将图片src传入全局src中
                            src = $('.imgbox img').attr('src');
                        },
                        //点击确定按钮时，在onOK中滴啊用superContent，会依次触发element的setup方法
                        setup: function (editor) {

                        }
                    },
                    {
                        //图片上传成功后的容器
                        type: 'html',
                        html: '<a id="postFiles" href="javascript:void(0);" class="btn">开始上传</a>',
                        //对应的样式
                        style: 'display:none;',
                    },
                    {
                        //选择图片按钮
                        id: 'chooseImage',
                        type: 'html',
                        //plupload按钮
                        html: '<div id="container"><span id="selectFiles" href="javascript:void(0);" class="btn" style="display:block;text-align:center;line-height:34px;color:#fff;cursor:pointer;">选择文件</span></div>',
                        style: 'display:block;width:82px;height:34px;line-height:34px;cursor:pointer;font-size:14px;color:#fff;text-align:center;background-color:#2981db;border-radius:4px;',  //html的样式，直接作用于上面的a元素
                        //当该元素show的时候执行的方法
                        onShow: function () {
                            document.getElementById('fileOss').innerHTML = '';
                        },
                        onLoad: function () {
                            let input = document.createElement("input");
                            input.type = "file";
                            $("#container").click(function () {
                                input.click();
                            });
                            input.onchange = function () {
                                let file = input.files[0], form = new FormData();
                                form.append("file", file);
                                form.append("fileName", file.name);
                                core.http({url: ADMIN_PATH + "/upload/file.do", data: form, processData: false, contentType: false, autoComplete:false, load:false},function(res){
                                    console.log(res);
                                })
                            }
                        }
                    }
                ]
            }
        ],
        onShow: function () {
        },
        onOk: function () {
            //该方法会依次调用element数组中的commit方法，在这里我们不需要在element中做额外调用，所以不使用，如果需要的话可以开启使用
            //this.commitContent(editor);
            //点击确定时，把图片依次插入
            console.log(srcArray);
            for (x in srcArray) {
                var realImageSrc = "https://XXXXXXX/" + srcArray[x];
                var ele = CKEDITOR.dom.element.createFromHtml('<p style="padding:5px 0;"><img style="width:250px;height:250px" src="' + realImageSrc + '"/></p><br/>');
                editor.insertElement(ele);   //将element插入editor
            }
        },
        onCancel: function () {
        }
    }
});