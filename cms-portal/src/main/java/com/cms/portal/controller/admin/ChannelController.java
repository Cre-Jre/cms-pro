package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsChannelService;
import com.cms.service.api.CmsModelItemService;
import com.cms.service.api.CmsModelService;
import com.cms.service.dto.CmsChannelDto;
import com.cms.service.dto.CmsModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("channel")
public class ChannelController {

    @Autowired
    private CmsModelService cmsModelService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsModelItemService cmsModelItemService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("channel","index");
    }

    @GetMapping("add.do")
    public String toAdd(Integer modelId, Model model){
        CmsModelDto cmsModelDto = cmsModelService.getById(modelId);
        model.addAttribute("modelId",modelId);
        model.addAttribute("modelName",cmsModelDto.getName());
        model.addAttribute("modelItem",cmsModelItemService.getByModelIdAndChannelModel(modelId,true));
        return UtilsTemplate.adminTemplate("channel","add");
    }

    @PostMapping("add.do")
    @ResponseBody
    public Result<String> doAdd(CmsChannelDto cmsChannelDto){
        cmsChannelService.save(cmsChannelDto);
        return Result.success();
    }

    @PostMapping("tree.do")
    @ResponseBody
    public Result doTree(){
        return Result.success(new ArrayList<>());
    }

}
