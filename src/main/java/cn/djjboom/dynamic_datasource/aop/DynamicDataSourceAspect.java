package cn.djjboom.dynamic_datasource.aop;

import cn.djjboom.dynamic_datasource.annotation.RoutingDataSource;
import cn.djjboom.dynamic_datasource.datasource.DatasourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 23:42
 * AOP组件
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    /**
     * 拦截使用了RoutingDataSource注解的service方法，
     * 在方法执行之前通过设置Threadlocal的值，设置当前线程使用的datasource
     * @param joinPoint
     */
    @Before("@annotation(cn.djjboom.dynamic_datasource.annotation.RoutingDataSource)")
    public void beforeMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();

        if (method.isAnnotationPresent(RoutingDataSource.class)){
            RoutingDataSource annotation = method.getAnnotation(RoutingDataSource.class);
            String datasource = annotation.value();
            DatasourceContextHolder.setDataSource(datasource);
        }
    }

    /**
     * 方法执行完成后，清除
     */
    @After("@annotation(cn.djjboom.dynamic_datasource.annotation.RoutingDataSource)")
    public void afterMethod(){
        DatasourceContextHolder.removeDataSource();
    }
}
