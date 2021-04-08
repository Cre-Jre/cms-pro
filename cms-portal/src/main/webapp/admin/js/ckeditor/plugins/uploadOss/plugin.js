CKEDITOR.plugins.add('uploadOss', {
    icons: 'uploadOss',
    init: function (editor) {
        editor.addCommand('addUploadOss', new CKEDITOR.dialogCommand('uploadOssDialog', {
            // allowedContent: 'abbr[title,id]',
            // requiredContent: 'abbr'
        }));

        //添加插件按钮
        editor.ui.addButton('UploadOss', {
            label: '上传图片',
            command: 'addUploadOss',
            toolbar: 'insert'
        });
        //添加对话框，配置实现对话框逻辑的js文件
        CKEDITOR.dialog.add('uploadOssDialog', this.path + 'dialog/uploadOss.js');
    }
});