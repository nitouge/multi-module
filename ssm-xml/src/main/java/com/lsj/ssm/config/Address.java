package com.lsj.ssm.config;

public class Address {
    private String name;

    private final String city;

    private final String district;

    public Address(String city, String district) {
        System.out.println("Address constructor called");
        this.city = city;
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    @Override
    public String toString() {
        return "Address{name='" + name + ", city='" + city + "', district='" + district + "'}";
    }
}
