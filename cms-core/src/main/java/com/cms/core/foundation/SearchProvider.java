package com.cms.core.foundation;

public class SearchProvider<T extends BaseEntity> {
    /**
     * 查询条件bean
     */
    private T criteria;
    /**
     * 排序字段
     */
    private String searchOrderBy;

    private SearchProvider.Inner inner;

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }

    /**
     * 静态方法  没有排序
     * @param criteria
     * @param <W>
     * @return
     */
    public static<W extends BaseEntity> SearchProvider of(W criteria){
        SearchProvider searchProvider = new SearchProvider<>();
        searchProvider.setCriteria(criteria);
        return searchProvider;
    }

    /**
     * 静态方法  自定义属性查询使用
     * @param criteria
     * @param inner
     * @param <W>
     * @return
     */
    public static<W extends BaseEntity> SearchProvider of(W criteria,SearchProvider.Inner inner){
        SearchProvider searchProvider = new SearchProvider<>();
        searchProvider.setCriteria(criteria);
        searchProvider.setInner(inner);
        return searchProvider;
    }

    /**
     * 静态方法 带有排序
     * @param criteria
     * @param orderBy
     * @param <W>
     * @return
     */
    public static<W extends BaseEntity> SearchProvider of(W criteria,String orderBy){
        SearchProvider searchProvider = new SearchProvider<>();
        searchProvider.setCriteria(criteria);
        searchProvider.setSearchOrderBy(orderBy);
        return searchProvider;
    }

    /**
     * 参数化内部类
     */
    public static class Inner{
        /**
         * 总条数
         */
        private Integer count;

        /**
         * 专题id
         * @param topicId
         */
        private Integer topicId;

        public Inner(Integer count) {
            this.count = count;
        }

        public Inner(Integer count,Integer topicId) {
            this.count = count;
            this.topicId = topicId;
        }


        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getTopicId() {
            return topicId;
        }

        public void setTopicId(Integer topicId) {
            this.topicId = topicId;
        }
    }

    public T getCriteria() {
        return criteria;
    }

    public void setCriteria(T criteria) {
        this.criteria = criteria;
    }

    public String getSearchOrderBy() {
        return searchOrderBy;
    }

    public void setSearchOrderBy(String searchOrderBy) {
        this.searchOrderBy = searchOrderBy;
    }
}
