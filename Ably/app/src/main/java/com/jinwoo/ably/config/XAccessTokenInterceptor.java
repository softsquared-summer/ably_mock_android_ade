package com.jinwoo.ably.config;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import static com.jinwoo.ably.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.jinwoo.ably.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);

        // If access token is stored in SharedPreferences, put it inside the header
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
        }

        // Let the request continue on
        return chain.proceed(builder.build());
    }
}
