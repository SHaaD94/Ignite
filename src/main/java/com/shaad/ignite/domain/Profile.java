package com.shaad.ignite.domain;

import java.util.Date;

public class Profile {
    private final String name;
    private final String email;
    private final Date activationDate;

    public Profile(String name, String email, Date activationDate) {
        this.name = name;
        this.email = email;
        this.activationDate = activationDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getActivationDate() {
        return activationDate;
    }
}
