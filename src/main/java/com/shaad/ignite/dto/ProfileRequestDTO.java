package com.shaad.ignite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class ProfileRequestDTO {
    @JsonProperty("total")
    private final Integer total;

    @JsonProperty("results")
    private final Collection<ProfileDTO> results;

    public ProfileRequestDTO(Collection<ProfileDTO> results) {
        this.total = results.size();
        this.results = results;
    }

    public Integer getTotal() {
        return total;
    }

    public Collection<ProfileDTO> getResults() {
        return results;
    }
}
