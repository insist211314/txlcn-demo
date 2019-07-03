package org.txlcn.demo.servicea;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: MG01896
 * @date: 2019/3/22 10:40
 * @E-mail: lishangjiang@huijintech.com.cn
 * @version: 1.0.0
 * @describe: TODO
 */
@Component("springBeanUtil")
public class SpringBeanUtil implements ApplicationContextAware, BeanFactoryAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public  void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public static <T> T getBean(String name){
        return (T)applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public <T> Map<String, T> getBeanMap(Class<T> clazz){
        return getApplicationContext().getBeansOfType(clazz);
    }

    public static <T> List<T> getBeans(Class<T> clazz){
        List<T> result = new ArrayList();
        Map<String, T> beanMap = applicationContext.getBeansOfType(clazz);
        if(beanMap==null) return result;
        result.addAll(beanMap.values());
        return result;
    }


    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }


    public ConfigurableListableBeanFactory getBeanFactory(){
        return this.beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
        }
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    /**
     * 非spring对象进行@Autowriter注入
     * @param t
     */
    public <T> T autowireBean(T t){
        AutowireCapableBeanFactory beanFactory =applicationContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(t);
        return t;
    }
}

