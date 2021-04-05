package com.cms.portal.freemarker.directive;

import com.cms.contex.utils.UtilsDirective;
import com.cms.service.api.CmsTopicService;
import com.cms.service.dto.CmsTopicDto;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

@Slf4j
public class CmsTopicDirective implements TemplateDirectiveModel {

    @Autowired
    private CmsTopicService cmsTopicService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        List<CmsTopicDto> list = cmsTopicService.getListByTag(UtilsDirective.getInt("count", map));
        environment.setVariable("result",UtilsDirective.DEFAULT_OBJECT_WRAPPER.wrap(list));
        templateDirectiveBody.render(environment.getOut());
    }
}
