package com.jinwoo.ably.src.product.fragments.options;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.product.fragments.options.adapters.OptionAdapter;
import com.jinwoo.ably.src.product.fragments.options.adapters.SelectedOptionAdapter;
import com.jinwoo.ably.src.product.fragments.options.data.SelectedOption;
import com.jinwoo.ably.src.product.fragments.options.interfaces.OptionsView;
import com.jinwoo.ably.src.product.fragments.options.models.OptionsResponse;

import java.text.NumberFormat;
import java.util.ArrayList;

public class OptionsFragment extends BottomSheetDialogFragment implements OptionsView{

    private OptionsListener             mOptionsListener;
    private OptionsService              mOptionsService;
    private Spinner                     mSpinner1, mSpinner2;
    private ListView                    mSelectedOptions;
    private LinearLayout                mSummary;
    private TextView                    mTotalCount, mTotalCost;
    private Button                      mCart, mPurchase;
    private int                         mProductIdx, mDetailedProductIdx, mDetailedPrice, mStock;
    private String                      mFirstOption, mSecondOption, mSelectedFirstOption, mSelectedSecondOption;
    private ArrayList<String>           mFirstOptions, mSecondOptions;
    private ArrayList<Integer>          mStocks, mDetailedPrices;
    private ArrayList<SelectedOption>   mSelectedOptionList;
    private SelectedOptionAdapter       mSelectedOptionAdapter;

    public OptionsFragment(int productIdx) { mProductIdx = productIdx; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        mOptionsService = new OptionsService(this);
        mapWidgets(view);
        tryGetOptions(mProductIdx);

        // Selected options listview setting
        mSelectedOptions.setDividerHeight(16);
        mSelectedOptionList     = new ArrayList<>();
        mSelectedOptionAdapter  = new SelectedOptionAdapter(getContext(), mSelectedOptionList, mSummary, mTotalCount, mTotalCost, mCart,mPurchase);
        mSelectedOptions.setAdapter(mSelectedOptionAdapter);

        mPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedOptionList.isEmpty()) {
                    Toast.makeText(getContext(), "옵션을 선택해 주십시오", Toast.LENGTH_SHORT).show();
                    return;
                }
                mOptionsListener.onPurchaseClicked(mSelectedOptionList);
                dismiss();
            }
        });

        return view;
    }

    private void mapWidgets(View view) {
        mSpinner1           = view.findViewById(R.id.options_spinner1);
        mSpinner2           = view.findViewById(R.id.options_spinner2);
        mSelectedOptions    = view.findViewById(R.id.options_lv_selected_options);
        mSummary            = view.findViewById(R.id.options_summary);
        mTotalCount         = view.findViewById(R.id.options_tv_count);
        mTotalCost          = view.findViewById(R.id.options_tv_cost);
        mCart               = view.findViewById(R.id.options_btn_cart);
        mPurchase           = view.findViewById(R.id.options_btn_purchase);

        mSpinner2           .setVisibility(View.INVISIBLE);
        mSelectedOptions    .setVisibility(View.INVISIBLE);
        mSummary            .setVisibility(View.INVISIBLE);
    }

    private void tryGetOptions(int productIdx) { mOptionsService.getOptions(productIdx); }

    @Override
    public void validateSuccess(OptionsResponse response) {
        int code = response.getCode();

        if (code == 100) {
            ArrayList<OptionsResponse.Result> results = response.getResult();

            mStocks         = new ArrayList<>();
            mFirstOptions   = new ArrayList<>();
            mSecondOptions  = new ArrayList<>();
            mDetailedPrices = new ArrayList<>();

            mFirstOptions   .add("옵션1을 선택해주세요");
            mSecondOptions  .add("옵션2을 선택해주세요");

            for (int i = 0; i < results.size(); i++) {
                mDetailedProductIdx =   results.get(i).getDetailedProductIdx();
                mStock =                results.get(i).getStock();
                mFirstOption =          results.get(i).getFirstOption();
                mSecondOption =         results.get(i).getSecondOption();
                mDetailedPrice =        results.get(i).getDetailedPrice();

                mStocks                 .add(mStock);
                mFirstOptions           .add(mFirstOption);
                if (!mSecondOptions.contains(mSecondOption))
                    mSecondOptions      .add(mSecondOption);
                mDetailedPrices         .add(mDetailedPrice);

            }

            // First option spinner setting
            OptionAdapter firstOptionAdapter = new OptionAdapter(getContext(), mFirstOptions, null);
            mSpinner1.setAdapter(firstOptionAdapter);
            mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mSelectedFirstOption = mFirstOptions.get(position);
                    if (position != 0) {
                        mSpinner2.setVisibility(View.VISIBLE);
                        mSelectedOptions.setVisibility(View.INVISIBLE);
                        if (mSelectedOptionList.isEmpty()) mSummary.setVisibility(View.INVISIBLE);
                        else mSummary.setVisibility(View.VISIBLE);
                    }
                    else {
                        mSpinner2.setVisibility(View.INVISIBLE);
                        mSpinner2.setSelection(0);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    mSpinner2.setVisibility(View.INVISIBLE);
                    mSelectedOptions.setVisibility(View.VISIBLE);
                    if (mSelectedOptionList.isEmpty()) mSummary.setVisibility(View.INVISIBLE);
                    else mSummary.setVisibility(View.VISIBLE);
                }
            });

            // Second option spinner setting
            OptionAdapter secondOptionAdapter = new OptionAdapter(getContext(), mSecondOptions, mDetailedPrices);
            mSpinner2.setAdapter(secondOptionAdapter);
            mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mSelectedSecondOption = mSecondOptions.get(position);
                    if (position != 0) {
                        SelectedOption newSelectedOption = new SelectedOption(mSelectedFirstOption, mSelectedSecondOption, mDetailedPrice);
                        String newOption1 = mSelectedFirstOption;

                        for (int i = 0; i < mSelectedOptionList.size(); i++) {

                            String existingOption1  = mSelectedOptionList.get(i).getOption1();

                            if (existingOption1.equals(newOption1)) {
                                int currentCount = mSelectedOptionList.get(i).getCount();
                                mSelectedOptionList.get(i).setCount(currentCount + 1);
                                mSelectedOptionAdapter.notifyDataSetChanged();

                                mSpinner2       .setVisibility(View.INVISIBLE);
                                mSelectedOptions.setVisibility(View.VISIBLE);
                                mSummary        .setVisibility(View.VISIBLE);
                                setSummary();

                                mSpinner1.setSelection(0);
                                mSpinner2.setSelection(0);
                                return;
                            }
                        }

                        mSelectedOptionList.add(newSelectedOption);
                        mSelectedOptionAdapter.notifyDataSetChanged();

                        mSpinner2       .setVisibility(View.INVISIBLE);
                        mSelectedOptions.setVisibility(View.VISIBLE);
                        mSummary        .setVisibility(View.VISIBLE);
                        setSummary();

                        mSpinner1.setSelection(0);
                        mSpinner2.setSelection(0);
                        mSpinner1.setSelected(false);
                        mSpinner2.setSelected(false);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    mSpinner2       .setVisibility(View.VISIBLE);
                    mSelectedOptions.setVisibility(View.INVISIBLE);
                    if (mSelectedOptionList.isEmpty()) mSummary.setVisibility(View.INVISIBLE);
                    else mSummary.setVisibility(View.VISIBLE);
                }
            });

        }
        else {
            Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }

    void setSummary() {
        mTotalCount.setText(String.valueOf(mSelectedOptionAdapter.getTotalCount()));
        NumberFormat format = NumberFormat.getInstance();
        String displayedTotalCost = format.format(mSelectedOptionAdapter.getTotalCost()) + "원";
        mTotalCost.setText(displayedTotalCost);
    }

    @Override
    public void validateFailure(String message) {}

    public interface OptionsListener {
        void onPurchaseClicked(ArrayList<SelectedOption> selectedOptionList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mOptionsListener = (OptionsListener) context;
    }
}