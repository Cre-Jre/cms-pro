package com.cms.core.foundation;

import java.io.Serializable;

public interface BaseService<DTO extends BaseDto<PK>,PK extends Serializable> {


    /**
     * 统一添加
     * @param dto
     */
    void save(DTO dto);

    /**
     * 通过主键删除
     * @param id  主键
     */
    void deleteById(PK id);

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

    /**
     * 分页查询
     * @param dto
     * @return
     */
    Page<DTO> getPage(DTO dto);
}
