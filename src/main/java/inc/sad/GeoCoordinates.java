package inc.sad;

public class GeoCoordinates {
    private Double latitude;
    private Double longitude;

    public GeoCoordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
