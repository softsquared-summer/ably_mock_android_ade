package com.jinwoo.ably.src.login;

import com.jinwoo.ably.src.login.interfaces.LogInActivityView;
import com.jinwoo.ably.src.login.interfaces.LogInRetrofitInterface;
import com.jinwoo.ably.src.login.models.LogInResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class LogInService {

    private final LogInActivityView logInActivityView;

    LogInService(LogInActivityView logInActivityView) {
        this.logInActivityView = logInActivityView;
    }

    void getLogIn(RequestBody requestBody) {

        final LogInRetrofitInterface logInRetrofitInterface = getRetrofit().create(LogInRetrofitInterface.class);

        logInRetrofitInterface.getLogIn(requestBody).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                final LogInResponse logInResponse = response.body();

                if (logInResponse == null) {
                    logInActivityView.validateFailure(null);
                    return;
                }

                logInActivityView.validateSuccess(logInResponse);
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                logInActivityView.validateFailure(null);
            }
        });
    }

}
