package com.company.constructors;

import com.company.fields.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class ConstructorReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clss = Class.forName("com.company.fields.Entity");

        Constructor<?>[] constructors = clss.getConstructors();
        Stream.of(constructors).forEach(System.out::println);

        System.out.println("----");

        Constructor<?>[] declaredConstructors = clss.getDeclaredConstructors();
        Stream.of(declaredConstructors).forEach(System.out::println);

        Constructor<?> publicCtr = clss.getConstructor(int.class,String.class);
        Entity e = (Entity) publicCtr.newInstance(1,"studentId");
        System.out.println(e);

        Constructor<?> privCtr = clss.getDeclaredConstructor();
        privCtr.setAccessible(true);
        Entity e2 = (Entity) privCtr.newInstance();
        System.out.println(e2);
    }
}
