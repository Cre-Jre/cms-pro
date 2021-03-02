package com.cms.contex.interceptor;

import com.cms.core.foundation.BaseEntity;
import org.apache.ibatis.javassist.bytecode.analysis.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * mybatis插件
 */
@Intercepts(
        @Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class})
)
public class BaseInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        switch (sqlCommandType){
            case INSERT:
                insert(invocation.getArgs()[1]);
                break;
            case UPDATE:
                update(invocation.getArgs()[1]);
                break;
            default:
                break;
        }
        //继续向下执行
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor){
            return Plugin.wrap(target,this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 如果传递实体的话可以传递时间
     * @param obj
     */
    private void insert(Object obj){
        if (obj instanceof BaseEntity){
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setCreateTime(LocalDateTime.now());
        }
    }

    /**
     * 修改时间
     */
    private void update(Object obj){
        if (obj instanceof BaseEntity){
            BaseEntity baseEntity = (BaseEntity) obj;
            baseEntity.setUpdateTime(LocalDateTime.now());
        }
    }
}
