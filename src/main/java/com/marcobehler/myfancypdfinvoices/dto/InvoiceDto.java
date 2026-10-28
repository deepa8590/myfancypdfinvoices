package com.marcobehler.myfancypdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//class reflecting the properties of the JSON you are sending in.As the user_id json property does not exist in Java, you need to configure Jackson accordingly with the @JsonProperty annotation.
public class InvoiceDto {
    @JsonProperty("user_id")
    private String userId;
    
    private Integer amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}