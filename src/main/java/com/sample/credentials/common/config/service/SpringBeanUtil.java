package com.sample.credentials.common.config.service;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringBeanUtil  implements ApplicationContextAware {


    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }

    /**
     * Accesseur de l'attribut bean.
     * @param beanClass
     * @param <T>
     * @return
     */
    public static<T> T getBean(Class<T> beanClass){
        return context.getBean(beanClass);
    }
}
