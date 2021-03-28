package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CmsRoleService cmsRoleService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("admin","index");
    }

    @GetMapping("add.do")
    public String toAdd(Model model){
        model.addAttribute("roles",cmsRoleService.getList());
        return UtilsTemplate.adminTemplate("admin","add");
    }

}
