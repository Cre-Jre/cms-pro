package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

public class TaskDayCronExpressionStrategy implements TaskCronExpressionStrategy{
    @Override
    public String buildCronExpress(CmsTaskDto cmsTask) {
        return "0 "+cmsTask.getMinute()+" "+cmsTask.getHour()+" * * ?";
    }
}
