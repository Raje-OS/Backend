package raje.com.rajebackend.library.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {

    private double lat;
    private double lng;
    private String direccion;

    protected Location() {}

    public Location(double lat, double lng, String direccion) {
        this.lat = lat;
        this.lng = lng;
        this.direccion = direccion;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getDireccion() {
        return direccion;
    }
}
