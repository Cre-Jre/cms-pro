package com.cms.portal.freemarker.directive;

import com.cms.service.api.CmsTopicService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class CmsTopicDirective implements TemplateDirectiveModel {

    @Autowired
    private CmsTopicService cmsTopicService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
//        cmsTopicService.getListByTag()
        System.out.println(111);
    }
}
