package com.jinwoo.ably.src.product.fragments.options;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jinwoo.ably.R;

public class OptionsFragment extends BottomSheetDialogFragment {

    private OptionsListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drawer_options, container, false);
    }

    public interface OptionsListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (OptionsListener) context;
    }
}
