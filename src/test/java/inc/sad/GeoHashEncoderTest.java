package inc.sad;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class GeoHashEncoderTest {

    @Test
    void encodeGeoHash() {
        int precision = 5;
        double latitude = 11.6532;
        double longitude = 1.76342;
        String expected = "s4162";
        String geoHash = GeoHashEncoder.encodeGeoHash(latitude, longitude, precision);
        assertEquals(expected, geoHash);
    }

    @Test
    void encodeGeoHashLatIsNeg() {
        int precision = -1;
        double latitude = 11.6532;
        double longitude = 1.76342;
        String expected = "";
        String geoHash = GeoHashEncoder.encodeGeoHash(latitude, longitude, precision);
        assertEquals(expected, geoHash);
    }
}