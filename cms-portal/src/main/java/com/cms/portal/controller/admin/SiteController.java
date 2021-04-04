package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsServletContext;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("site")
public class SiteController {

    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private UtilsServletContext utilsServletContext;

    @GetMapping("index.do")
    public String toIndex(Model model) {
        model.addAttribute("data", cmsSiteService.get());
        model.addAttribute("staticWebSuffix", StaticWebSuffixEnum.values());
        model.addAttribute("tplIndex",utilsServletContext.getTplRelativePath("index","index"));
        return UtilsTemplate.adminTemplate("site", "index");
    }

    @PostMapping("edit.do")
    @ResponseBody
    @DoValid
    @DoLog(content = "修改站点配置")
    public Result<String> doEdit(@Valid CmsSiteDto cmsSiteDto, BindingResult result){
        cmsSiteService.update(cmsSiteDto);
        return Result.success();
    }

}
