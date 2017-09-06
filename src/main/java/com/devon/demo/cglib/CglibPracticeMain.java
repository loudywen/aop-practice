package com.devon.demo.cglib;

import com.devon.demo.pojointercept2.DiwenAnnotation1;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class CglibPracticeMain {

    private static SampleClass proxy = null;
    static {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if("test".equals(method.getName())) {
                    System.out.println("main triggered");
                    DiwenAnnotation1 diwenAnnotation1 = method.getAnnotation(DiwenAnnotation1.class);
                    if(diwenAnnotation1!=null){
                        System.out.println("Found annotation");
                    }
                    return "diwen";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
         proxy = (SampleClass) enhancer.create();
    }
    public static void main(String[] args) {
        System.out.println("in main");
        System.out.println(proxy.test(null));
    }

    public static void testAgain(){
        System.out.println("in test again");
    }
}
