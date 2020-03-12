package inc.sad;

import com.google.gson.Gson;
import com.streamsets.pipeline.api.ElDef;
import com.streamsets.pipeline.api.ElFunction;
import com.streamsets.pipeline.api.ElParam;
import redis.clients.jedis.Jedis;

/**
 * This is class for handling and caching geopoints of hotels
 */

@ElDef
public class GeoCache {
    /**
     * Method for resolving address of hotel
     * @param address - address of hotel
     * @return string of geopoints bordered by ";" if hotel already cached or empty string if not
     */
    @ElFunction(
            prefix = "GEO",
            name = "geoCache"
    )
    public static String geoCache(
            @ElParam("address") String address
    ) {
        try {
            Gson gson = new Gson();
            Jedis redisConn = new Jedis("172.18.0.2", 6379);

            String jsonPos = redisConn.get(address);
            if (!jsonPos.isEmpty()) {
                GeoCoordinates pos = gson.fromJson(jsonPos, GeoCoordinates.class);
                return String.format("%f;%f", pos.getLatitude(), pos.getLongitude());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Method for saving hotel geopoints in cache DB. Data stores as JSON
     * @param address - Address of hotel
     * @param latitude
     * @param longitude
     */
    @ElFunction(
            prefix = "GEO",
            name = "addGeoCache"
    )
    public static void addGeoCache(
            @ElParam("address") String address,
            @ElParam("latitude") double latitude,
            @ElParam("longitude") double longitude
    ) {
        Gson gson = new Gson();
        Jedis redisConn = new Jedis("localhost", 6379);
        GeoCoordinates pos = new GeoCoordinates(latitude, longitude);
        redisConn.append(address, gson.toJson(pos));
    }
}
