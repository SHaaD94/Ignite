package com.shaad.ignite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ProfileDTO {
    @JsonProperty("ctn")
    private final long ctn;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("activationDate")
    private final Date activationDate;

    public ProfileDTO(long ctn, String name, String email, Date activationDate) {
        this.ctn = ctn;
        this.name = name;
        this.email = email;
        this.activationDate = activationDate;
    }

    public long getCtn() {
        return ctn;
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
