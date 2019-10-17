package com.ad.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Topic {

    @NotNull
    private String id;
    private String name;
    private String description;
}
