package cmse473.bloodbank.RetrofitAPI;

/**
 * Created by Onur on 30.11.2017.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://10.0.2.2:8080/bloodbank/api/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
