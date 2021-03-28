package com.cms.portal.freemarker.directive;

import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.dto.CmsFriendLinkDto;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class CmsFriendLinkDirective implements TemplateDirectiveModel {

    @Autowired
    private CmsFriendLinkService cmsFriendLinkService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        System.out.println(templateModels.length);
        List<CmsFriendLinkDto> list = cmsFriendLinkService.getList(new CmsFriendLinkDto());
        templateModels[0] = new SimpleSequence(list,null);
        templateDirectiveBody.render(environment.getOut());
    }
}
