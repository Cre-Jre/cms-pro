package com.cms.service.strategy;

import com.cms.service.dto.CmsTaskDto;

import java.util.HashMap;
import java.util.Map;

public class TaskWeekCronExpressionStrategy implements TaskCronExpressionStrategy{

    private static final Map<Integer, String> WEEK_MAP = new HashMap<Integer, String>() {{
        put(1, "MONDAY");
        put(2, "TUESDAY");
        put(3, "WEDNESDAY");
        put(4, "THURSDAY");
        put(5, "FRIDAY");
        put(6, "SATURDAY");
        put(7, "SUNDAY");
    }};

    @Override
    public String buildCronExpress(CmsTaskDto cmsTask) {
        return "0 "+cmsTask.getMinute()+" "+cmsTask.getHour()+" ? * "+WEEK_MAP.get(cmsTask.getDayOfWeek());
    }
}
