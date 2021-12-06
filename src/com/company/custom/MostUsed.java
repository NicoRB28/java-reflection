package com.company.custom;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE,ElementType.METHOD})//indica a donde se puede utilizar la anotacion
@Retention(RetentionPolicy.RUNTIME)
public @interface MostUsed {
    String value() default "java";
}
