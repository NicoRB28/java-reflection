package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class MyClass{
    private MyClass(){
        System.out.println("MyClass object created!");
    }
}

public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //MyClass obj = new MyClass(); No es posible porque el ctr es privado pero:
        Class<?> clss = Class.forName("com.company.MyClass");//me permite obtener la clase
        Constructor<?> ctr = clss.getDeclaredConstructor();//de esta forma recuperamos un constructor y se le pueden pasar los
        //parametros que necesite el constructor (si es que los necesita)
        ctr.setAccessible(true);//cambiamos la accesibilidad del constructor
        MyClass myClass = (MyClass) ctr.newInstance();
    }
}
