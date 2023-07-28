package com.ttknpdev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = { // @JsonIgnoreProperties to use ignore some prop that you set like below
        "age" ,
        "level"
        // when u ignore any values u add it's just not added to attribute
})
public class Owner {
    private String fullname;
    private Short age;
    private Character level;
    private String comment;

}
