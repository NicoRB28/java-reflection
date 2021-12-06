package com.company.fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

public class FieldInfo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Entity e = new Entity(10, "id");
        Class<?> clss = e.getClass();

        Field[] fields = clss.getFields();//retorna todos los campos PUBLICOS de una clase
        Arrays.asList(fields).forEach(field -> System.out.println(field.getName()));
        System.out.println("---------------------");
        Field[] allTheFields = clss.getDeclaredFields();// retorna TODOS los campos de una clase
        Stream.of(allTheFields).forEach(field -> System.out.println(field.getName()));

        /**
         * esto lleva a que existen dos tipos de campos, Declarados y No-Declarados,
         * los No-Declarados son los elementos publicos y de la superclase, y los
         * Declarados son todos los elementos presentes en la clase.
         *
         */
        System.out.println("------------");
        //si quiero uno puntual:
        Field typeField = clss.getField("type");
        System.out.println(typeField.getName());
        typeField.set(e, "rollNo.");//puedo cambiarle el valor pasando la entidad y el valor a asignar en el campo
        System.out.println(e.getType());
        Field valField = clss.getDeclaredField("val");
        System.out.println(valField.getName());
        //valField.setInt(e, Integer.parseInt("999"));//antes de poder setear un valor en un campo privado
        //hay que hacerlo accesible
        valField.setAccessible(true);
        valField.set(e,999);
        System.out.println(e.getVal());
    }
}
