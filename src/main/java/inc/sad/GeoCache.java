package inc.sad;

import com.google.gson.Gson;
import com.streamsets.pipeline.api.ElDef;
import com.streamsets.pipeline.api.ElFunction;
import com.streamsets.pipeline.api.ElParam;
import redis.clients.jedis.Jedis;

@ElDef
public class GeoCache {
    private static Jedis redisConn = new Jedis("172.18.0.5", 6379);
    @ElFunction(
            prefix = "GEO",
            name = "geoCache"
    )
    public static String geoCache(
            @ElParam("address") String address
    ) {
        try {
            Gson gson = new Gson();

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
        GeoCoordinates pos = new GeoCoordinates(latitude, longitude);
        redisConn.append(address, gson.toJson(pos));
    }
}
