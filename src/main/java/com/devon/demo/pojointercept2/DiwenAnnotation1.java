package com.devon.demo.pojointercept2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PACKAGE,ElementType.METHOD})
public @interface DiwenAnnotation1 {

String testValue() default  "default test value";

}
