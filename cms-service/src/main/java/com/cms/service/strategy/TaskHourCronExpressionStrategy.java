package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

public class TaskHourCronExpressionStrategy implements TaskCronExpressionStrategy{
    @Override
    public String buildCronExpress(CmsTaskDto cmsTask) {
        return "0 0 */"+cmsTask.getIntervalHour()+" * * ?";
    }
}
