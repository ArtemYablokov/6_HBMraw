package com.yabloko.models;

import javax.persistence.*;

@Entity
@Table(name = "apple_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "moooodel")
    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Car() {
    }
    public Car(Integer id, String model, User owner) {
        this.id = id;
        this.model = model;
        this.owner = owner;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", owner=" + owner +
                '}';
    }
}
