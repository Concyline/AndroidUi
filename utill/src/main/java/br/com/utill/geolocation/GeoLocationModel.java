package br.com.utill.geolocation;

public class GeoLocationModel {
    double lattitude;
    double longitude;
    String address;
    String city;

    public String getCity() {
        return  city != null ? city : "is null";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return  address != null ? address : "is null";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    ;
}
