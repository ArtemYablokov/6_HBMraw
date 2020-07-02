package com.yabloko.models;



public class Document {

    Integer id;
    Integer age;
    User owner;

    public Document() {
    }
    public Document(Integer id, Integer age, User owner) {
        this.id = id;
        this.age = age;
        this.owner = owner;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
