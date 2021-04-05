package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsServletContext;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsContentListenerService;
import com.cms.service.api.CmsSiteService;
import com.cms.service.dto.CmsContentDto;
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
    @Autowired
    private CmsContentListenerService cmsContentListenerService;

    @GetMapping("index.do")
    public String toIndex(Model model) {
        CmsContentDto cmsContentDto = new CmsContentDto();
        cmsContentDto.setId(1);
        cmsContentDto.setTitle("中秋佳节");
        cmsContentDto.setContent("每逢中秋佳节，我都会和家人一起吃团圆饭；每逢中秋之夜，我都会仰望天空，欣赏良辰美景");
        cmsContentListenerService.add(cmsContentDto);

        cmsContentDto.setId(2);
        cmsContentDto.setTitle("我爱中秋节");
        cmsContentDto.setContent("秋高气爽，我们又迎来了一年一度的中秋节，我爱中秋节，爱它月饼的金黄饱满，还爱它的花好月圆");
        cmsContentListenerService.add(cmsContentDto);

        cmsContentDto.setId(3);
        cmsContentDto.setTitle("走进中秋");
        cmsContentDto.setContent("带着这份释然，我走进了中秋，走进了团聚，走进了温馨和诗意");
        cmsContentListenerService.add(cmsContentDto);

        cmsContentDto.setId(4);
        cmsContentDto.setTitle("中秋之夜");
        cmsContentDto.setContent("中秋之夜，举杯邀月，阖家团圆，温馨绵绵，四世同堂，共享天伦。各色各样的月饼琳琅满目、异彩纷呈，不同的色泽，不同的口感，各人尽享其中，自是陶然自乐");
        cmsContentListenerService.add(cmsContentDto);

        cmsContentDto.setId(5);
        cmsContentDto.setTitle("中秋节有感");
        cmsContentDto.setContent("今又一年中秋，就在前几日，两株树上的香桂又幽雅地绽放了，那金黄的花瓣爬满了一枝枝树桠");
        cmsContentListenerService.add(cmsContentDto);

        cmsContentDto.setId(6);
        cmsContentDto.setTitle("月照中秋，相思如故");
        cmsContentDto.setContent("无论时光走得多远，每当有月的夜晚，你便披着月华的一泽清辉，走来到我心里;而每当秋风起时，那没有月的暗夜，便挽起惆怅的牵挂，在下一个满月的渡口，端坐光阴的莲花，静待你的轮回");
        cmsContentListenerService.add(cmsContentDto);


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
