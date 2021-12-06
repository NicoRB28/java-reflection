package com.company.reflectionAndAnnotation;

import com.company.custom.MostUsed;
import com.company.custom.Utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReadingAnnotation {
    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clss = Class.forName("com.company.custom.Utility");
        Constructor<?> constructor = clss.getConstructor();
        Utility utility = (Utility) constructor.newInstance();

        Method[] methods = clss.getDeclaredMethods();
        for (Method method: methods) {
            method.setAccessible(true);
            if(method.isAnnotationPresent(MostUsed.class)){
                method.invoke(utility,"Scala");
                //para utilizar el default value:
                MostUsed annotation = method.getAnnotation(MostUsed.class);
                String value = annotation.value();
                method.invoke(utility,value);
            }
        }

    }
}
