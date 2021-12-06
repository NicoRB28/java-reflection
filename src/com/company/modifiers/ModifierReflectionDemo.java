package com.company.modifiers;

import com.company.fields.Entity;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierReflectionDemo {

    private static final int ALGO = 1 | 2 | 3;

    public static void main(String[] args) throws NoSuchMethodException {
        Entity e = new Entity(10, "id");
        Class<?> clss = e.getClass();
        int modifiersInt = clss.getModifiers();
        int i = modifiersInt & Modifier.PUBLIC;
        System.out.println(i);
        System.out.println(Modifier.isPublic(modifiersInt));
        System.out.println(Modifier.toString(modifiersInt));
        System.out.println("------------------");

        Method getValMet = clss.getMethod("getVal");
        int getValMetModifierInt = getValMet.getModifiers();
        int mod = getValMetModifierInt & Modifier.PUBLIC;// & da 1 si todos los bits son iguales, caso contrario da 0
        System.out.println(mod);
        System.out.println(Modifier.isPrivate(getValMetModifierInt));
        System.out.println(Modifier.toString(getValMetModifierInt));
    }
}
