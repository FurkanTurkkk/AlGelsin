package com.AlGelsin.order_service.model;

public enum Statu {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private String value;

    Statu(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
