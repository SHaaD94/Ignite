package com.shaad.ignite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileDTO {
    @JsonProperty("ctn")
    private final Long ctn;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("activateDate")
    private final String activateDate;

    public ProfileDTO(Long ctn, String name, String email, String activateDate) {
        this.ctn = ctn;
        this.name = name;
        this.email = email;
        this.activateDate = activateDate;
    }

    public Long getCtn() {
        return ctn;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getActivateDate() {
        return activateDate;
    }
}
