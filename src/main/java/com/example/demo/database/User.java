package com.example.demo.database;

import javax.persistence.*;

@Entity /*something for use from table - ORL*/
@Table(name = "userForJava")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Override
    public String toString() {
        return name;
    }

    public String writeAll() {
        return "[ id-" + id + ",name-" + name + " ]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
