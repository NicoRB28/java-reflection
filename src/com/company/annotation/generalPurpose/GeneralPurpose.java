package com.company.annotation.generalPurpose;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GeneralPurpose extends Parent{

    @Override
    public void m1() {
        System.out.println("m1 child implementation");
    }

    public static void main(String[] args) {

        @SuppressWarnings("unused")
        int a = 10;

        //@SuppressWarnings({"rawtypes", "unused"})
        @SuppressWarnings("all")
        ArrayList alist = new ArrayList();

        //deprecated
        GeneralPurpose g = new GeneralPurpose();
        g.m2();

        MyFunctionalInterface inter = value -> System.out.println("get implementation");
        Stream.of(1,2,3,4).forEach(inter::get);

    }

}

@FunctionalInterface
interface MyFunctionalInterface{
    void get(int value);
}
