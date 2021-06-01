package com.lm.pub4.Entities;


import com.lm.pub4.Exceptions.InvalidArgumentsException;

public class User {

    String name;

    UserRole userRole;

//    List<String> topicsSubscribed;

    public enum UserRole {
        ADMIN,
        USER
    }

    public User (String name, String userRole) throws InvalidArgumentsException {
        this.name = name;
//        this.topicsSubscribed = new ArrayList<String>();

        if (userRole.equals("ADMIN"))
            this.userRole = UserRole.ADMIN;
        else if (userRole.equals("USER"))
            this.userRole = UserRole.USER;
        else
            throw new InvalidArgumentsException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

//    public void addTopic(String topicName) {
//        this.topicsSubscribed.add(topicName);
//    }

}
