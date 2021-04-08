package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsShiro;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsContentService;
import com.cms.service.api.CmsModelItemService;
import com.cms.service.dto.CmsContentDto;
import com.cms.service.dto.CmsUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private CmsContentService cmsContentService;
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

    @PostMapping("add.do")
    @ResponseBody
    public Result<String> doAdd(CmsContentDto cmsContentDto){
        CmsUserDto cmsUserDto = (CmsUserDto) UtilsShiro.getSubject().getPrincipal();
        cmsContentDto.setUserId(cmsUserDto.getId());
        cmsContentService.save(cmsContentDto);
        return Result.success();
    }
}
