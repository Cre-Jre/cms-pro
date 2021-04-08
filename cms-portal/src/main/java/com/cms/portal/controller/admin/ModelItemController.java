package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.dao.enums.ModelItemDataTypeEnum;
import com.cms.dao.enums.ModelItemRequiredEnum;
import com.cms.dao.enums.ModelItemSingleEnum;
import com.cms.service.api.CmsModelItemService;
import com.cms.service.dto.CmsModelItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("modelItem")
public class ModelItemController {

    @Autowired
    private CmsModelItemService cmsModelItemService;

    @GetMapping("index.do")
    public String toIndex(Integer modelId, Boolean channelModel, String modelName, Model model){
        model.addAttribute("modelId",modelId);
        model.addAttribute("modelName",modelName);
        model.addAttribute("channelModel",channelModel);
        List<CmsModelItemDto> checkedModelItemList = cmsModelItemService.getByModelIdAndChannelModel(modelId, channelModel);
        List<String> checkedModelItem = checkedModelItemList.stream().map(CmsModelItemDto::getField).collect(Collectors.toList());
        model.addAttribute("defaultModelItem",defaultChannelModelItemList().stream().filter(x->!checkedModelItem.contains(x.getField())).collect(Collectors.toList()));
        model.addAttribute("checkedModelItem",checkedModelItemList);
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

    /**
     * 默认内容模型
     * @return
     */
    private List<CmsModelItemDto> defaultContentModelItemList(){
        return Arrays.asList(
                CmsModelItemDto.of("channelId", ModelItemDataTypeEnum.SELECT,"栏目",
                        ModelItemSingleEnum.NO, ModelItemRequiredEnum.YES),
                CmsModelItemDto.of("titleImg", ModelItemDataTypeEnum.SELECT,"标题图",
                        ModelItemSingleEnum.NO, ModelItemRequiredEnum.NO),
                CmsModelItemDto.of("description", ModelItemDataTypeEnum.SELECT,"摘要",
                        ModelItemSingleEnum.YES, ModelItemRequiredEnum.NO),
                CmsModelItemDto.of("title", ModelItemDataTypeEnum.SELECT,"标题",
                        ModelItemSingleEnum.YES, ModelItemRequiredEnum.YES),
                CmsModelItemDto.of("txt", ModelItemDataTypeEnum.SELECT,"内容",
                        ModelItemSingleEnum.YES, ModelItemRequiredEnum.NO)
        );
    }

    @PostMapping("defaultSetting.do")
    @ResponseBody
    public Result<String> doDefaultSetting(String[] defaultField,Integer modelId,Boolean channelModel){
        List<CmsModelItemDto> defaultModelItemList = defaultChannelModelItemList();
        List<String> selectModelItemList = Arrays.asList(defaultField);
        cmsModelItemService.batchInsert(defaultModelItemList.stream().filter(x->selectModelItemList.contains(x.getField())).peek(y->{
            y.setModelId(modelId);
            y.setChannelModel(channelModel);
        }).collect(Collectors.toList()));
        return Result.success();
    }
}
