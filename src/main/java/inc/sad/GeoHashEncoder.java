package inc.sad;

import ch.hsr.geohash.GeoHash;
import com.streamsets.pipeline.api.ElDef;
import com.streamsets.pipeline.api.ElFunction;
import com.streamsets.pipeline.api.ElParam;

/**
 * Class for calculating geohash for gave coordinate with specified precision
 */
@ElDef
public class GeoHashEncoder {
    /**
     * Method for calculating geohash
     *
     * @param latitude
     * @param longitude
     * @param precision - Precision of geohash aka count of letters
     * @return
     */
    @ElFunction(
            prefix = "GEO",
            name = "encodeGeoHash"
    )
    public static String encodeGeoHash(
            @ElParam("latitude") double latitude,
            @ElParam("longitude") double longitude,
            @ElParam("precision") int precision
    ) {
        String hash = "";
        try {
            hash = GeoHash.geoHashStringWithCharacterPrecision(latitude, longitude, precision);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }
}
