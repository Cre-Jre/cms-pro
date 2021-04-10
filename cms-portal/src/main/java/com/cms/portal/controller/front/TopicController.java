package com.cms.portal.controller.front;

import com.cms.contex.utils.UtilsFront;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.CmsTopicService;
import com.cms.service.dto.CmsSiteDto;
import com.cms.service.dto.CmsTopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("topic")
public class TopicController {

    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsTopicService cmsTopicService;

    @GetMapping("index*.jspx")
    public String toIndex(ModelMap map){
        UtilsFront.assignPageInfo(map);
        return UtilsTemplate.frontTemplate("topic","index");
    }

    @GetMapping("{id}.jspx")
    public String toTopic(@PathVariable Integer id, Model model){
        CmsTopicDto cmsTopicDto = cmsTopicService.getById(id);
        CmsSiteDto cmsSiteDto = cmsSiteService.get();
        model.addAttribute("site",cmsSiteDto);
        model.addAttribute("topic",cmsTopicDto);
        return UtilsTemplate.frontTemplate("topic","topic");
    }

}
