package com.jinwoo.ably.src.main.fragments.home.children.newproducts.interfaces;

import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.BestProductsResponse;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.NewProductsResponse;

public interface NewFragmentView {
    void validateBestProductsSuccess(BestProductsResponse response);
    void validateNewProductsSuccess(NewProductsResponse response);
    void validateFailure(String message);
}
