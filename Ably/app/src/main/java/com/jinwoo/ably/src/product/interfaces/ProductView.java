package com.jinwoo.ably.src.product.interfaces;

import com.jinwoo.ably.src.product.models.ProductResponse;

public interface ProductView {
    public void validateSuccess(ProductResponse response);
    public void validateFailure(String message);
}
