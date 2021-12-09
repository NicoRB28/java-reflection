package com.company.methodHandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandlesDemo {
    public static void main(String[] args) throws Throwable {

        Lookup lookup = MethodHandles.lookup();
        Class<?> studentClass = lookup.findClass(Student.class.getName());

        Student s = new Student();
        s.setCourse("Courseeeee");
        MethodType methodType = MethodType.methodType(String.class);
        MethodHandle getCourse = lookup.findVirtual(Student.class, "getCourse", methodType);
        System.out.println(getCourse.invoke(s));

        MethodType constructorType = MethodType.methodType(void.class);//el tipo de retorno de un constructor es void.class
        //y no tiene mas parametros porque es el constructor vacio
        MethodHandle noArgConstructor = lookup.findConstructor(Student.class, constructorType);
        Student student = (Student) noArgConstructor.invoke();
        student.setName("Juan Carlos");
        student.setCourse("Fantasma");
        System.out.println(student);

        MethodType argConstructor = MethodType.methodType(void.class,String.class,String.class);
        MethodHandle constructorWithArgs = lookup.findConstructor(Student.class, argConstructor);
        Student student2 = (Student) constructorWithArgs.invoke("Juan", "Computer Science");
        System.out.println(student2);

        MethodType methodType1 = MethodType.methodType(void.class, String.class);
        MethodHandle setName = lookup.findVirtual(Student.class, "setName", methodType1);
        setName.invoke(student,"Horacio");
        System.out.println(student);

        MethodType methodType3 = MethodType.methodType(void.class, int.class);
        MethodHandle setNumberOfStudents = lookup.findStatic(Student.class, "setNumOfStudents", methodType3);
        setNumberOfStudents.invoke(2);
        System.out.println(Student.getNumOfStudents());

        /**
         * Para poder acceder a los atributos privados de una clase
         * se debe crear un lookup privado:
         */
        Lookup privateLookup = MethodHandles.privateLookupIn(studentClass, lookup);
        //String.class es el tipo de retorno:
        MethodHandle nameGetter = privateLookup.findGetter(studentClass, "name", String.class);
        //String.class es el tipo de parametro:
        MethodHandle nameSetter = privateLookup.findSetter(studentClass, "name", String.class);
        System.out.println(nameGetter.invoke(student));
        nameSetter.invoke(student,"Julio");
        System.out.println(nameGetter.invoke(student));

        /**
         * VarHandles para acceder a fields de una clase sean privados o publicos.
         */
        VarHandle courseField = privateLookup.findVarHandle(studentClass, "course", String.class);//String.class es el tipo del field.
        String courseValue = (String)courseField.get(s);
        System.out.println("course value: " + courseValue);
        courseField.set(s,"otro curso");
        System.out.println("course value: " + (String)courseField.get(s));


    }
}
