package com.company;

import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getId() {
        return id.toString();
    }


    public void setName(String newName) {
        name = newName;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setId(UUID newId) {
        id = newId;
    }
}
