package com.devon.demo.bppandproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class MethodTImedPoxy implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        if (bean instanceof Cat || bean instanceof Dog) {
            ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setTarget(bean);
            proxyFactory.addAdvice(new CustomMethodInvoker());
            return (Object) proxyFactory.getProxy();
        } else {
            return bean;
        }
    }

    private class CustomMethodInvoker implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {

            Object result = null;
            if (methodInvocation.getMethod().getAnnotation(Timed.class) != null) {
                long start = System.currentTimeMillis();
                result = methodInvocation.proceed();
                long stop = System.currentTimeMillis();
                System.out.println(methodInvocation.getMethod().getName() + " : " + (stop - start) + "ms");
            } else {
                result = methodInvocation.proceed();
            }
            return result;
        }
    }
}
