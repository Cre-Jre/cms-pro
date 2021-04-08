/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	config.uiColor = '#ffffff';
    config.toolbar = 'Basic';
    config.skin = 'bootstrapck';
    config.language = 'zh-cn';
    config.extraPlugins = 'uploadOss';
    config.image_previewText = '';
    config.removeDialogTabs = 'image:advanced;image:Link';//隐藏超链接与高级选项
    config.filebrowserImageUploadUrl = ADMIN_PATH + "/upload/file.do";//上传图片的地址
};
