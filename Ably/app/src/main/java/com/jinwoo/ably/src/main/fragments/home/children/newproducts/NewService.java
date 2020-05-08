package com.jinwoo.ably.src.main.fragments.home.children.newproducts;

import com.jinwoo.ably.src.main.fragments.home.children.newproducts.interfaces.NewFragmentView;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.interfaces.NewRetrofitInterface;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.BestProductsResponse;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.NewProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class NewService {

    final NewFragmentView newFragmentView;

    public NewService(NewFragmentView newFragmentView) {
        this.newFragmentView = newFragmentView;
    }

    public void getBestProducts() {
        final NewRetrofitInterface retrofitInterface = getRetrofit().create(NewRetrofitInterface.class);

        retrofitInterface.getBestProducts().enqueue(new Callback<BestProductsResponse>() {
            @Override
            public void onResponse(Call<BestProductsResponse> call, Response<BestProductsResponse> response) {
                BestProductsResponse bestProductsResponse = response.body();
                if (bestProductsResponse == null) {
                    newFragmentView.validateFailure(null);
                    return;
                }

                newFragmentView.validateBestProductsSuccess(bestProductsResponse);
            }

            @Override
            public void onFailure(Call<BestProductsResponse> call, Throwable t) {
                newFragmentView.validateFailure(null);
            }
        });
    }

    public void getNewProducts(int page) {
        final NewRetrofitInterface retrofitInterface = getRetrofit().create(NewRetrofitInterface.class);

        retrofitInterface.getNewProducts(page).enqueue(new Callback<NewProductsResponse>() {
            @Override
            public void onResponse(Call<NewProductsResponse> call, Response<NewProductsResponse> response) {
                NewProductsResponse newProductsResponse = response.body();
                if (newProductsResponse == null) {
                    newFragmentView.validateFailure(null);
                    return;
                }

                newFragmentView.validateNewProductsSuccess(newProductsResponse);
            }

            @Override
            public void onFailure(Call<NewProductsResponse> call, Throwable t) {
                newFragmentView.validateFailure(null);
            }
        });
    }

}
