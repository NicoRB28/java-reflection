package com.company.creationClass;

class MyClass{
    public MyClass(){
        System.out.println("Class created");
    }
}

public class GettingClassObject {
    public static void main(String[] args) throws ClassNotFoundException {

        //forName()
        Class<?> clss1 = Class.forName("java.lang.String");
        /**
         * si creo dos instancias de CLass de una misma clase,
         * ambas instancias de Class apuntan a la misma refe-
         * rencia.
         */
        Class<?> clss2 = Class.forName("java.lang.String");

        System.out.println("apuntan a la misma referencia: " + (clss2 == clss1) );

        //ClassName.class
        Class<?> clss3 = int.class;
        Class<?> clss4 = String.class;

        //obj.getClass()
        MyClass m = new MyClass();
        Class<?> clss5 = m.getClass();

        //para obtener la super clase de una Class:
        Class<?> super1 = clss1.getSuperclass();

        //para obtener las interfaces:
        Class<?>[] interfaces = clss1.getInterfaces();

        System.out.println(clss1.getName());
    }
}
