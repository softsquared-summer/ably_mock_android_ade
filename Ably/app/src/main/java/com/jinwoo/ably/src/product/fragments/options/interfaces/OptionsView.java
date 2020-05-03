package com.jinwoo.ably.src.product.fragments.options.interfaces;

import com.jinwoo.ably.src.product.fragments.options.models.OptionsResponse;

public interface OptionsView {
    public void validateSuccess(OptionsResponse response);
    public void validateFailure(String message);
}
