package com.example.testtask.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyRate {
    
    @JsonProperty("Cur_ID")
    public Integer id;
    @JsonProperty("Date")
    public Date date;
    @JsonProperty("Cur_OfficialRate")
    public Double rate;

    public CurrencyRate() {
       
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Double getRate(){
        return rate; 
    }

    public void setRate(Double rate){
        this.rate = rate;
    }

}
