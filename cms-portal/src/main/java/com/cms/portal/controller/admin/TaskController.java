package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsTemplate;
import com.cms.dao.enums.TaskExecutionCycleUnitEnum;
import com.cms.dao.enums.TaskExecutionTypeEnum;
import com.cms.dao.enums.TaskStaticTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("task")
public class TaskController {

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("task","index");
    }

    @GetMapping("add.do")
    public String toAdd(Model model){
        model.addAttribute("taskType", TaskStaticTypeEnum.values());
        model.addAttribute("taskExecutionType", TaskExecutionTypeEnum.values());
        model.addAttribute("taskExecutionCycle", TaskExecutionCycleUnitEnum.values());
        return UtilsTemplate.adminTemplate("task","add");
    }
}
