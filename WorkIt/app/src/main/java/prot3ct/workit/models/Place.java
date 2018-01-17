package prot3ct.workit.models;

import java.io.Serializable;

import prot3ct.workit.models.base.PlaceContract;

public class Place implements PlaceContract, Serializable {
    private String country;
    private String city;
    private String address;

    public Place(String country, String city, String address) {
        setAddress(address);
        setCountry(country);
        setCity(city);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
