package com.example.testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
    @JsonProperty("Cur_ID")
    public Integer id;
    @JsonProperty("Cur_Abbreviation")
    public String abbreviation;

    public Currency() {

    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getAbbreviation(){
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }

}
