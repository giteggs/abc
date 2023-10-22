package com.abc.ecommerce.bo;

public class TrackingRequest {

    private String from_country;
    private String to_country;
    private String from_postcode;
    private String to_postcode;
    private String weight;
    private String length;
    private String width;
    private String height;
    private int group_id;

    public String getFrom_country() {
        return from_country;
    }

    public void setFrom_country(String from_country) {
        this.from_country = from_country;
    }

    public String getTo_country() {
        return to_country;
    }

    public void setTo_country(String to_country) {
        this.to_country = to_country;
    }

    public String getFrom_postcode() {
        return from_postcode;
    }

    public void setFrom_postcode(String from_postcode) {
        this.from_postcode = from_postcode;
    }

    public String getTo_postcode() {
        return to_postcode;
    }

    public void setTo_postcode(String to_postcode) {
        this.to_postcode = to_postcode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
