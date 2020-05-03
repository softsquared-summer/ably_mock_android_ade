package com.jinwoo.ably.src.product.fragments.options;

import com.jinwoo.ably.src.product.fragments.options.interfaces.OptionsRetrofitInterface;
import com.jinwoo.ably.src.product.fragments.options.interfaces.OptionsView;
import com.jinwoo.ably.src.product.fragments.options.models.OptionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class OptionsService {
    final OptionsView optionsView;

    public OptionsService(OptionsView optionsView) { this.optionsView = optionsView; }

    public void getOptions(int productIdx) {
        final OptionsRetrofitInterface optionsRetrofitInterface = getRetrofit().create(OptionsRetrofitInterface.class);

        optionsRetrofitInterface.getOptions(productIdx).enqueue(new Callback<OptionsResponse>() {
            @Override
            public void onResponse(Call<OptionsResponse> call, Response<OptionsResponse> response) {
                OptionsResponse optionsResponse = response.body();
                if (optionsResponse == null) {
                    optionsView.validateFailure(null);
                    return;
                }

                optionsView.validateSuccess(optionsResponse);
            }

            @Override
            public void onFailure(Call<OptionsResponse> call, Throwable t) {
                optionsView.validateFailure(null);
            }
        });
    }
}
