package com.devon.demo.pojointercept2;

import com.devon.demo.pojointercept.Audit;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnnotationInvocationHandler implements InvocationHandler {

    private final Handler customAnnotation;

    public AnnotationInvocationHandler(Handler customAnnotation) {
        this.customAnnotation = customAnnotation;
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        //trying to call the method on the custom annotation, if it exists
        /*Method methodOnCustom = getMatchingMethodOnGivenAnnotation(method);
        if (methodOnCustom != null) {
            return  methodOnCustom.invoke(customAnnotation, args);
        } else {
            //otherwise getting the default value of the reference annotation method
            Object defaultValue = method.getDefaultValue();
            if (defaultValue != null) {
                return defaultValue;
            }
            throw new UnsupportedOperationException(
                    "The method \""
                            + method.getName()
                            + "\" does not exist in the custom annotation, and there is no default value for"
                            + " it in the reference annotation, please implement this method in your custom annotation.");
        }*/
        Method realMethod = customAnnotation.getClass().getMethod(
                method.getName(),
                method.getParameterTypes());
        if ("handle".equals(method.getName())) {
            System.out.println("handle is called");
        }

        DiwenAnnotation1 diwenAnnotation1 = realMethod.getAnnotation(DiwenAnnotation1.class);

        if (diwenAnnotation1 != null) {
            System.out.println("Found annotation");
            System.out.println("value from anootation is: "+diwenAnnotation1.testValue());
        }
        return method.invoke(customAnnotation, args);
    }

    private Method getMatchingMethodOnGivenAnnotation(Method method) {
        try {
            Method customMethod = customAnnotation.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
            if (customMethod.getReturnType().isAssignableFrom(method.getReturnType())) {
                return customMethod;
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}