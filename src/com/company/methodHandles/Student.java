package com.company.methodHandles;

public class Student {
    private static int numOfStudents;
    private String name;
    private String course;

    public Student(){
        numOfStudents++;
    }

    public Student(String name, String course){
        numOfStudents++;
        this.name = name;
        this.course = course;
    }

    public static int getNumOfStudents() {
        return numOfStudents;
    }

    public static void setNumOfStudents(int numOfStudents) {
        Student.numOfStudents = numOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("invoked");
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
