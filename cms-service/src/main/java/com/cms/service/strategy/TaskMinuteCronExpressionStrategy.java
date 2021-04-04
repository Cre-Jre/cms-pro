package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

public class TaskMinuteCronExpressionStrategy implements TaskCronExpressionStrategy{

    @Override
    public String buildCronExpress(CmsTaskDto cmsTask) {
        return "0 */"+cmsTask.getIntervalMinute()+" * * * ?";
    }
}
