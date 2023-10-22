package com.abc.ecommerce.dto;

public class TrackingResponseDTO {

    private String error;

    private double price;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
