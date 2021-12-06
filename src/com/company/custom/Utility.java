package com.company.custom;

@MostUsed
public class Utility {

    void doStuff(){
        System.out.println("Doing something");
    }

    @MostUsed("Courses")
    void doStuff(String arg){
        System.out.println("Doing " + arg);
    }

    void doStuff(int arg){
        System.out.println("Doing number: " + arg);
    }
}

class SubUtility extends Utility {
    //hereda la anotacion @MostUsed porque esta marcada con @Inherited
}