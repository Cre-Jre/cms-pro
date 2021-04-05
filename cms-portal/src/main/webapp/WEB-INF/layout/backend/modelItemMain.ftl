<#macro layout title=''>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="${basePath}/admin/layui/css/layui.css" media="all">
        <script src="${basePath}/common/js/jq.js"></script>
        <script src="${basePath}/admin/layui/layui.js" charset="utf-8"></script>
        <link rel="stylesheet" href="${basePath}/admin/css/admin.css" media="all">
        <link rel="stylesheet" href="//at.alicdn.com/t/font_1060794_yaw1vj40wh9.css">
        <script type="application/javascript">
            let ADMIN_PATH="${adminPath!''}", BASE_PATH = "${basePath!''}",OPERATION_URL = "${operationUrl!''}";
        </script>
        <script src="${basePath}/admin/js/core.js"></script>
    </head>
    <body>
    <div>
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <div class="layui-card">
                        <div class="layui-card-header">
                            <h5>${title}</h5>
                        </div>
                    <div class="layui-card-body">
                        <div class="layui-row" style="margin:20px 0;">
                            <#nested>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</#macro>
