package com.ttknpdev.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    @JsonInclude annotation contains  two values
    Include.NON_NULL: Indicates that only properties with not null values will be included in JSON.
    Include.NON_EMPTY: Indicates that only properties that are not empty will be included in JSON.
*/
/*
    @JsonIgnoreProperties(value = { // @JsonIgnoreProperties to use ignore some prop that you set like below
        "order" ,
        "owners"
    })
*/
/*
    @JsonIgnoreType means ignore class
*/
@Data
@NoArgsConstructor
// @JsonInclude(JsonInclude.Include.NON_EMPTY) if attribute has empty won't show values
@JsonInclude(JsonInclude.Include.NON_NULL) // if attribute has null won't show values
public class Car {
    /*
    if you need ignore some attribute
    can use
    @JsonIgnore above attribute
    */
    private Long order;
    private String modelName;
    private Double price;
    private Integer sales;
    private List<Owner> owners;
}
