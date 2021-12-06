package com.company.custom;

public class Box<@NonEmpty T> {

    @NonEmpty
    int size;
    @NonEmpty
    T type;

    public Box(int size, T type){
        this.size = size;
        this.type = type;
    }

    class NestedBox extends Box<T> {
        NestedBox(int size, @NonEmpty T type){
            super(size,type);
        }
    }
}
