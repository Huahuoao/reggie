package com.itheima.pojo;

public class Animal {
    private String name;
    private boolean isGeted;
    private Integer age;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", isGeted=" + isGeted +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGeted() {
        return isGeted;
    }

    public void setGeted(boolean geted) {
        isGeted = geted;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
