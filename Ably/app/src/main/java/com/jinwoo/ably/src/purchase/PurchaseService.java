package com.jinwoo.ably.src.purchase;

import com.jinwoo.ably.src.purchase.interfaces.PurchaseRetrofitInterface;
import com.jinwoo.ably.src.purchase.interfaces.PurchaseView;
import com.jinwoo.ably.src.purchase.models.PurchaseResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class PurchaseService {

    private PurchaseView purchaseView;

    public PurchaseService(PurchaseView purchaseView) {
        this.purchaseView = purchaseView;
    }

    public void postOrder(RequestBody requestBody) {
        final PurchaseRetrofitInterface purchaseRetrofitInterface = getRetrofit().create(PurchaseRetrofitInterface.class);

        purchaseRetrofitInterface.postOrder(requestBody).enqueue(new Callback<PurchaseResponse>() {
            @Override
            public void onResponse(Call<PurchaseResponse> call, Response<PurchaseResponse> response) {
                PurchaseResponse body = response.body();
                if (body == null) {
                    purchaseView.validateOrderFailure(null);
                    return;
                }

                purchaseView.validateOrderSuccess(body);
            }

            @Override
            public void onFailure(Call<PurchaseResponse> call, Throwable t) {
                purchaseView.validateOrderFailure(null);
            }
        });
    }

}
