package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("modelItem")
public class ModelItemController {

    @GetMapping("index.do")
    public String toIndex(Integer modelId, Boolean channelModel, String modelName, Model model){
        model.addAttribute("modelId",modelId);
        model.addAttribute("modelName",modelName);
        return UtilsTemplate.adminTemplate("model","itemIndex");
    }



}
