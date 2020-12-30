package com.cms.core.foundation;

import java.io.Serializable;

public interface BaseMapper<ENTITY extends BaseEntity<PK>,PK extends Serializable> {
    /**
     * 添加
     * @param entity
     */
    void save(ENTITY entity);

    /**
     * 修改
     * @param entity
     */
    void update(ENTITY entity);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    ENTITY selectById(PK id);


}
