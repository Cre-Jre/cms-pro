package com.cms.portal.freemarker.directive;

import com.cms.contex.utils.UtilsDirective;
import com.cms.core.foundation.SearchProvider;
import com.cms.service.api.CmsContentService;
import com.cms.service.api.CmsSiteService;
import com.cms.service.dto.CmsContentDto;
import com.cms.service.dto.CmsSiteDto;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContentListDirective implements TemplateDirectiveModel {
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsContentService cmsContentService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        SearchProvider.Inner inner = new SearchProvider.Inner(UtilsDirective.getInt("count", map), UtilsDirective.getInt("topicId", map));
        List<CmsContentDto> list = cmsContentService.getListByDirective(inner);
        CmsSiteDto cmsSiteDto = cmsSiteService.get();

        List<CmsContentDto> collect = list.stream().map(x -> {
            String format = x.getCreateTime().format(DateTimeFormatter.ofPattern("/yyyyMMdd/"));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/html/").append(x.getCmsChannel().getPath()).append(format).append(x.getId()).append(".html");
            x.setUrl(stringBuilder.toString());
            return x;
        }).collect(Collectors.toList());
        environment.setVariable("result",UtilsDirective.DEFAULT_OBJECT_WRAPPER.wrap(collect));
        templateDirectiveBody.render(environment.getOut());
    }
}
