package com.jinwoo.ably.src.main.fragments.home.children.newproducts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.adapters.ProductRecyclerAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.adapters.ProductSlideAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.data.Product;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.interfaces.NewFragmentView;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.BestProductsResponse;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.NewProductsResponse;
import java.util.ArrayList;

public class FragmentNew extends Fragment implements NewFragmentView {

    private ProgressDialog mProgressDialog;
    private ImageView mTop;
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private ProductSlideAdapter productSlideAdapter;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private ArrayList<Product> mBestProducts, mNewProducts;
    private Context context;
    private NewService mNewService;
    private NestedScrollView mScrollView;
    private int mPage;

    public FragmentNew() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_main_home_new, container, false);
        mapWidgets(view);
        context = getActivity();
        mNewService = new NewService(this);

        mTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        // Best products list setting
        tryGetBestProducts();

        // Second product list setting
        mPage = 1;
        tryGetNewProducts(mPage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    boolean isAtLast = !mScrollView.canScrollVertically(1);
                    if (isAtLast) {
                        tryGetNewProducts(++mPage);
                    }
                }
            });
        }

        return view;
    }

    private void mapWidgets(View view) {
        mTop            = view.findViewById(R.id.new_iv_banner);
        mViewPager      = view.findViewById(R.id.new_body1);
        mRecyclerView   = view.findViewById(R.id.new_body2);
        mScrollView     = view.findViewById(R.id.new_scrollview);
    }

    private void tryGetBestProducts() {
        showProgressDialog();
        mNewService.getBestProducts();
    }

    private void tryGetNewProducts(int page) {
        showProgressDialog();
        mNewService.getNewProducts(page);
    }

    @Override
    public void validateBestProductsSuccess(BestProductsResponse response) {
        hideProgressDialog();
        if (response.getCode() == 100) {
            mBestProducts = new ArrayList<>();
            ArrayList<BestProductsResponse.Result> products = response.getResult();

            for (int i = 0; i < products.size(); i++) {
                int productIdx =        products.get(i).getProductIdx();
                String thumbnailUrl =   products.get(i).getThumbnailUrl();
                String discountRatio =  products.get(i).getDiscountRatio();
                String displayedPrice = products.get(i).getDisplayedPrice();
                int marketIdx =         products.get(i).getMarketIdx();
                String marketName =     products.get(i).getMarketName();
                String productName =    products.get(i).getProductName();
                String purchaseCnt =    products.get(i).getPurchaseCnt();
                String isMyHeart =      products.get(i).getIsMyHeart();
                String isHotDeal =      "N";
                String isNew =          products.get(i).getIsNew();

                Product product = new Product(productIdx, thumbnailUrl, discountRatio, displayedPrice,marketIdx, marketName, productName, purchaseCnt, isMyHeart, isHotDeal, isNew);
                mBestProducts.add(product);
            }

            productSlideAdapter = new ProductSlideAdapter(mBestProducts, context);
            mViewPager.setAdapter(productSlideAdapter);
        }
    }

    @Override
    public void validateNewProductsSuccess(NewProductsResponse response) {
        hideProgressDialog();
        if (response.getCode() == 100) {
            mNewProducts = new ArrayList<>();
            ArrayList<NewProductsResponse.Result> products = response.getResult();

            for (int i = 0; i < products.size(); i++) {
                int productIdx =            products.get(i).getProductIdx();
                String thumbnailUrl =       products.get(i).getThumbnailUrl();
                String discountRatio =      products.get(i).getDiscountRatio();
                String displayedPrice =     products.get(i).getDisplayedPrice();
                int marketIdx =             products.get(i).getMarketIdx();
                String marketName =         products.get(i).getMarketName();
                String productName =        products.get(i).getProductName();
                String purchaseCnt =        "";
                String isMyHeart =          products.get(i).getIsMyHeart();
                String isHotDeal =          "N";
                String isNew =              products.get(i).getIsNew();

                Product product = new Product(productIdx, thumbnailUrl, discountRatio, displayedPrice, marketIdx, marketName, productName, purchaseCnt, isMyHeart, isHotDeal, isNew);
                mNewProducts.add(product);
            }

            productRecyclerAdapter = new ProductRecyclerAdapter(mNewProducts, context);
            mRecyclerView.setHasFixedSize(false);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            mRecyclerView.setAdapter(productRecyclerAdapter);
        }
    }

    @Override
    public void validateFailure(String message) {}

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
