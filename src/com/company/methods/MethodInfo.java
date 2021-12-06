package com.company.methods;

import com.company.fields.Entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class MethodInfo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Entity entity = new Entity(10, "id");
        Class<?> clss1 = entity.getClass();
        Method[] methods = clss1.getMethods(); //trae todos los metodos publicos y de la super clase
        Stream.of(methods).forEach(method -> System.out.println(method.getName()));
        System.out.println("-----------------------");
        Method[] methods2 = clss1.getDeclaredMethods();//solo los metodos que estan en la clase ya sean privados o publicos.
        Stream.of(methods2).forEach(m -> System.out.println(m.getName()));

        Method setValueMethod = clss1.getDeclaredMethod("setVal", int.class);
        setValueMethod.setAccessible(true);//si el metodo es privado antes de utilizarlo debo setear su accesibilidad
        setValueMethod.invoke(entity, 98989);

        Method method2 = clss1.getMethod("getVal",null);
        System.out.println(method2.invoke(entity,null));
    }
}
