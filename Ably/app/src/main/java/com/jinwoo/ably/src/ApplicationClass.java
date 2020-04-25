package com.jinwoo.ably.src;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.jinwoo.ably.config.XAccessTokenInterceptor;
import com.jinwoo.ably.src.data.Category;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    // Server URL
    public static String BASE_URL = "http://apis.newvement.com/";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences key
    public static String TAG = "ABLY_APP";

    // JWT Token value
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    // Date format
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Retrofit instance
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initializing SharedPreferences
        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // Automatically sending JWT with the request
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}