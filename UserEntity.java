package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;
   String name;


   private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(String status) {

    }
}
