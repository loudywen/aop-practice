package com.devon.demo.pojointercept2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class TestMain implements Handler {


    public static void main(String[] args) {
        //Annotation customAnnotation = TestMain.class.getAnnotation(DiwenAnnotation1.class);

        InvocationHandler handler = new AnnotationInvocationHandler(new TestMain());
        Handler handlerImpl = (Handler) Proxy.newProxyInstance(AnnotationInvocationHandler.class.getClassLoader(), new Class[]{Handler.class}, handler);


/*
        TestMain.testMethod();
*/
        handlerImpl.handle();
    }


    @DiwenAnnotation1(testValue = "hello")
    @Override
    public void handle() {
        System.out.println("In test handle");
    }
}
