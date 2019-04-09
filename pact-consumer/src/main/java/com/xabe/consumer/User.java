package com.xabe.consumer;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class User {

    private final String firstName;
    private final String lastName;

    @JsonbCreator
    public User(
            @JsonbProperty("firstName") String firstName,
            @JsonbProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
