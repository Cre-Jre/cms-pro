package com.cms.portal.freemarker.directive;

import com.cms.contex.utils.UtilsDirective;
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
        List<CmsFriendLinkDto> list = cmsFriendLinkService.getList(new CmsFriendLinkDto());
        environment.setVariable("result", UtilsDirective.DEFAULT_OBJECT_WRAPPER.wrap(list));
        templateDirectiveBody.render(environment.getOut());
    }
}
