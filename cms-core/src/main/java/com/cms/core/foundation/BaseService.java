package com.cms.core.foundation;

import java.io.Serializable;

public interface BaseService<DTO extends BaseDto<PK>,PK extends Serializable> {
    /**
     * 统一添加
     * @param dto
     */
    void save(DTO dto);

    /**
     * 修改
     * @param dto
     */
    void update(DTO dto);

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    DTO getById(PK id);


}
