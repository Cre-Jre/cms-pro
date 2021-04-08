package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsModelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private CmsModelItemService cmsModelItemService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("content","index");
    }

    @GetMapping("add.do")
    public String toAdd(Integer modelId, Model model){
        model.addAttribute("modelId",modelId);
        model.addAttribute("modelItem",cmsModelItemService.getByModelIdAndChannelModel(modelId,false));
        return UtilsTemplate.adminTemplate("content","add");
    }



}
