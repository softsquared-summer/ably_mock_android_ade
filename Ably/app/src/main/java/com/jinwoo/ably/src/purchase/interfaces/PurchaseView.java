package com.jinwoo.ably.src.purchase.interfaces;

import com.jinwoo.ably.src.purchase.models.PurchaseResponse;

public interface PurchaseView {
    public void validateOrderSuccess(PurchaseResponse response);
    public void validateOrderFailure(String message);
}
