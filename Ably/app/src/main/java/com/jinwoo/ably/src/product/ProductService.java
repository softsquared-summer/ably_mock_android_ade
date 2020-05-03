package com.jinwoo.ably.src.product;

import com.jinwoo.ably.src.product.interfaces.ProductRetrofitInterface;
import com.jinwoo.ably.src.product.interfaces.ProductView;
import com.jinwoo.ably.src.product.models.ProductResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class ProductService {

    final ProductView productView;

    public ProductService(ProductView productView) {
        this.productView = productView;
    }

    public void getProduct(int productIdx) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);

        productRetrofitInterface.getProduct(productIdx).enqueue(new Callback<ProductResponse> () {

            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse = response.body();
                if (productResponse == null) {
                    productView.validateFailure(null);
                }

                productView.validateSuccess(productResponse);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productView.validateFailure(null);
            }
        });
    }
}
