package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsTemplate;
import com.cms.dao.enums.ModelItemDataTypeEnum;
import com.cms.dao.enums.ModelItemRequiredEnum;
import com.cms.dao.enums.ModelItemSingleEnum;
import com.cms.service.dto.CmsModelItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("modelItem")
public class ModelItemController {

    @GetMapping("index.do")
    public String toIndex(Integer modelId, Boolean channelModel, String modelName, Model model){
        model.addAttribute("modelId",modelId);
        model.addAttribute("modelName",modelName);
        model.addAttribute("channelModel",channelModel);
        model.addAttribute("defaultModelItem",defaultChannelModelItemList());
        return UtilsTemplate.adminTemplate("model","itemIndex");
    }


    /**
     * 获取默认栏目模型项
     * @return
     */
    private List<CmsModelItemDto> defaultChannelModelItemList(){
        return Arrays.asList(
                CmsModelItemDto.of("name", ModelItemDataTypeEnum.STRING,"栏目名称",
                        ModelItemSingleEnum.NO, ModelItemRequiredEnum.YES),
                CmsModelItemDto.of("path", ModelItemDataTypeEnum.STRING,"访问路径",
                        ModelItemSingleEnum.NO, ModelItemRequiredEnum.YES)
        );
    }
}
