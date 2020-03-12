package inc.sad;

import ch.hsr.geohash.GeoHash;
import com.streamsets.pipeline.api.ElDef;
import com.streamsets.pipeline.api.ElFunction;
import com.streamsets.pipeline.api.ElParam;

@ElDef
public class GeoHashEncoder {
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
