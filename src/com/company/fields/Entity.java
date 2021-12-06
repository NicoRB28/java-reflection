package com.company.fields;

public class Entity {
    private int val;
    public String type;

    public Entity(int val, String type) {
        this.val = val;
        this.type = type;
    }

    private Entity(){
        this(0,"id");
    }

    public int getVal() {
        return val;
    }

    private void setVal(int val) {
        this.val = val;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "val=" + val +
                ", type='" + type + '\'' +
                '}';
    }
}