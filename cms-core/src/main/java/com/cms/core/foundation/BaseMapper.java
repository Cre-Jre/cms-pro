package com.cms.core.foundation;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<ENTITY extends BaseEntity<PK>,PK extends Serializable> {

    /**
     * 添加
     * @param entity
     */
    void save(ENTITY entity);

    /**
     * 批量插入
     * @param list
     */
    void batchInsert(List<ENTITY> list);

    /**
     * 根据id进行删除
     * @param id    主键
     */
    void deleteById(PK id);

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

    /**
     * 查询所有
     * @return    list
     */
    List<ENTITY> selectAll();

    /**
     * 分页查询
     * @return
     */
    List<ENTITY> selectBySearchProvider(SearchProvider searchProvider);
}
