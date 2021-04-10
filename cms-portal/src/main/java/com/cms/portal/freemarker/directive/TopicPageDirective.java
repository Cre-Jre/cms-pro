package com.cms.portal.freemarker.directive;

import com.cms.contex.utils.UtilsDirective;
import com.cms.core.foundation.Page;
import com.cms.service.api.CmsTopicService;
import com.cms.service.dto.CmsTopicDto;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

public class TopicPageDirective implements TemplateDirectiveModel {

    @Autowired
    private CmsTopicService cmsTopicService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Page<CmsTopicDto> page = cmsTopicService.getPage(new CmsTopicDto());
        environment.setVariable("result", UtilsDirective.DEFAULT_OBJECT_WRAPPER.wrap(page.getContent()));
        environment.setVariable("totalPage", UtilsDirective.DEFAULT_OBJECT_WRAPPER.wrap(page.getPages()));
        templateDirectiveBody.render(environment.getOut());
    }
}
