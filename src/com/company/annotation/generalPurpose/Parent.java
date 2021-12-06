package com.company.annotation.generalPurpose;

public class Parent {
    public void m1(){
        System.out.println("m1 parent implementation");
    }
    @Deprecated(since = "2")
    public void m2(){
        System.out.println("m2 parent implementation");
    }
}
