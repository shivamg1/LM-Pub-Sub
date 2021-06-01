package com.lm.pub4.Entities;

import java.util.ArrayList;
import java.util.List;

public class Topic {

    private String name;

    private List<User> users;

    public Topic(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public void addUser (User user) {
        users.add(user);
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }
}
