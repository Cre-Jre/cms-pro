<#--通用form模板-->
<#macro form>
    <form class="layui-form" method="post">
        <#nested>
    </form>
</#macro>

<#--通用form中的每一项 一行item-->
<#macro item class="">
    <div class="layui-form-item ${class}">
        <#nested>
    </div>
</#macro>

<#--    item中左右两边的inline-->
<#macro inline required=false full=false label="" className="" inlineStyle="">
    <div class="layui-inline ${className!} ${full?string("cms-inline-100","cms-inline-50")}" <#if inlineStyle!="">style="${inlineStyle!}"</#if> >
        <label class="layui-form-label layui-col-md6 <#if required>cms-label-required</#if>" style="width:197px;">${label}</label>
        <div class="layui-input-block layui-col-md6 cms-inline-block">
            <#nested>
        </div>
    </div>
</#macro>

<#--提交按钮-->
<#macro submit>
    <div class="layui-form-item">
        <div class="layui-inline cms-inline-50">
            <label class="layui-form-label layui-col-md6" style="width:197px;"></label>
            <div class="layui-input-block layui-col-md6 cms-inline-block">

            </div>
        </div>
        <div class="layui-inline cms-inline-50">
            <label class="layui-form-label layui-col-md6" style="width:197px;"></label>
            <div class="layui-input-block layui-col-md6 cms-inline-block">
                <p class="cms-flex-end cms-pt50">
                    <button class="layui-btn layui-btn-sm layui-btn-primary">重置</button>
                    <button class="layui-btn layui-btn-sm layui-btn-normal" type="button" lay-submit lay-filter="go">提交</button>
                </p>
            </div>
        </div>
    </div>
</#macro>

<#--单选框    -->
<#macro radio list name value="" itemLabel="" itemValue=""  filter="" enum=false>
    <#if list?is_sequence>
        <#if enum==false>
            <#if itemLabel!="" && itemValue!="">
                <#list list as item>
                    <input type="radio" name="${name}" title="${item[itemLabel]}" lay-filter="${filter}" <#if value=="${item[itemValue]}">checked</#if> value="${item[itemValue]}">
                </#list>
            <#else>

            </#if>
        <#else>
            <#list list as item>
                <input type="radio" name="${name}" title="${item.label}" lay-filter="${filter}" <#if value=="${item.getOrdinal()}">checked</#if> value="${item.getOrdinal()}">
            </#list>
        </#if>
    <#else>

    </#if>
</#macro>

<#--封装表头查询-->
<#macro headSearch>
    <#nested>
    <button class="layui-btn" lay-submit lay-filter="searchSubmit">查询</button>
</#macro>

<#--
select下拉  支持list和枚举
name select标签的name属性
list 数据

-->
<#macro select name list enum=false showDefaultOption=false itemLabel="" itemValue="" defaultOptionLabel="" value="" filter="">
    <select name="${name}" <#if filter!="">lay-filter="${filter}"</#if> >
        <#if showDefaultOption==true>
            <option value><#if defaultOptionLabel!="">${defaultOptionLabel}<#else>请选择</#if></option>
        </#if>
        <#if enum==false>
            <#list list as item>
                <#if itemLabel!="" && itemValue!="">

                <#else>
                    <option value="${item}" <#if value=="${item}">selected="selected"</#if>>${item}</option>
                </#if>
            </#list>
        <#else>
            <#list list as item>
                <option value="${item.getOrdinal()}" <#if value=="${item.getOrdinal()}">selected="selected"</#if> >${item.label}</option>
            </#list>
        </#if>
    </select>
</#macro>
<#--上传图片-->
<#macro imageUpload name src="" hasMask=false className="imageUpload" cssStyle="">
    <div class="imageUploadBox" <#if cssStyle!="">style="${cssStyle}"</#if>>
        <div class="imageUploadWrapper">
            <i class="iconfont iconplus imageUploadIcon"></i>
            <img src="${src}" alt="" class="${className}" width="100%" height="100%">
            <input name="${name}" type="hidden" class="submitInput">
            <div class="imageMask <#if hasMask==true>imageOpenMask</#if>">
                <div class="imageText"
                     style="width:100%;height:100px;color:#fff;font-size:12px;text-align:center;line-height:100px;">
                    点击查看大图
                </div>
                <span class="imageUpdate"
                      style="text-align:center;line-height:30px;color:#fff;z-index:10;background:#00aeff;display:inline-block;width:50%;height:30px;position:absolute;bottom:0;left:0;">修改</span>
                <span class="imageDelete"
                      style="text-align:center;line-height:30px;color:#fff;z-index:10;background:#ff7653;display:inline-block;width:50%;height:30px;position:absolute;bottom:0;right:0;">删除</span>
            </div>
        </div>
    </div>
</#macro>
<#--内容等模型页面使用    -->
<#macro layoutForm>
    <form class="layui-form layout-form-flex"  id="layoutForm" method="post" style="margin:15px 50px 15px 0;">
        <#nested>
    </form>
</#macro>
<#--内容等模型页面使用   项 -->
<#macro layoutItem label="" flex="50" required=false className="" style="">
    <div class="form-item flex-item-${flex} ${className}" style="${style}">
        <label class="form-item-label <#if required>label-required</#if>">${label}</label>
        <div class="form-item-content">
            <#nested>
        </div>
    </div>
</#macro>
<#macro layoutSubmit>
    <div class="form-item flex-item-50">
        <label class="form-item-label"></label>
        <div class="form-item-content">
            <#nested>
        </div>
    </div>
    <div class="form-item flex-item-50">
        <label class="form-item-label"></label>
        <div class="form-item-content">
            <p class="flex-align-right pt50">
                <button class="layui-btn layui-btn-sm layui-btn-primary" type="reset">重置</button>
                <button class="layui-btn layui-btn-sm layui-btn-normal" type="button" lay-submit lay-filter="go">
                    提交
                </button>
            </p>
        </div>
    </div>
</#macro>