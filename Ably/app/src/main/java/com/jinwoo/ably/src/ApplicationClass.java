package com.jinwoo.ably.src;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.jinwoo.ably.config.XAccessTokenInterceptor;
import com.jinwoo.ably.src.main.data.Category;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    // Server URL
    public static String BASE_URL = "http://3.34.80.36/";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences key
    public static String TAG = "ABLY_APP";

    // JWT Token value
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    // Date format
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Retrofit instance
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initializing SharedPreferences
        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }

    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // Automatically sending JWT with the request
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    // Category list
    public static ArrayList<Category> getCategories() {

        ArrayList<Category> Categories = new ArrayList<>();
        Category upper = new Category("상의");
        upper.child.add("전체");
        upper.child.add("티셔츠");
        upper.child.add("슬리브리스");
        upper.child.add("블라우스/셔츠");
        upper.child.add("니트");
        upper.child.add("맨투맨/후드");
        upper.child.add("베스트");
        Categories.add(upper);
        Category outer = new Category("아우터");
        outer.child.add("전체");
        outer.child.add("가디건");
        outer.child.add("자켓");
        outer.child.add("점퍼");
        outer.child.add("코트");
        outer.child.add("패딩");
        Categories.add(outer);
        Category onePiece = new Category("원피스");
        onePiece.child.add("전체");
        onePiece.child.add("미니원피스");
        onePiece.child.add("롱원피스");
        onePiece.child.add("투피스");
        Categories.add(onePiece);
        Category pants = new Category("팬츠");
        pants.child.add("전체");
        pants.child.add("롱팬츠");
        pants.child.add("숏팬츠");
        pants.child.add("슬랙스");
        pants.child.add("레깅스");
        pants.child.add("점프수트");
        Categories.add(pants);
        Category skirt = new Category("스커트");
        skirt.child.add("전체");
        skirt.child.add("미니/미디 스커트");
        skirt.child.add("롱스커트");
        Categories.add(skirt);
        Category bag = new Category("가방");
        bag.child.add("전체");
        bag.child.add("크로스백");
        bag.child.add("숄더백");
        bag.child.add("토트백");
        bag.child.add("클러치");
        bag.child.add("에코백");
        bag.child.add("백팩");
        Categories.add(bag);
        Category shoes = new Category("신발");
        shoes.child.add("전체");
        shoes.child.add("플랫/로퍼");
        shoes.child.add("힐");
        shoes.child.add("스니커즈");
        shoes.child.add("샌들");
        shoes.child.add("슬리퍼/쪼리");
        shoes.child.add("워커/부츠");
        Categories.add(shoes);
        Category props = new Category("패션소품");
        props.child.add("전체");
        props.child.add("모자/헤어");
        props.child.add("양말/스타킹");
        props.child.add("시계");
        props.child.add("머플러");
        props.child.add("폰 악세사리");
        props.child.add("기타");
        Categories.add(props);
        Category jewelry = new Category("주얼리");
        jewelry.child.add("전체");
        jewelry.child.add("귀걸이");
        jewelry.child.add("목걸이");
        jewelry.child.add("반지");
        jewelry.child.add("팔찌/발찌");
        Categories.add(jewelry);
        Category underwear = new Category("언더웨어");
        underwear.child.add("전체");
        underwear.child.add("브라팬티");
        underwear.child.add("보정");
        underwear.child.add("이너");
        underwear.child.add("홈웨어");
        Categories.add(underwear);
        Category beachwear = new Category("비치웨어");
        beachwear.child.add("전체");
        beachwear.child.add("비키니");
        beachwear.child.add("래쉬가드");
        beachwear.child.add("악세사리");
        Categories.add(beachwear);

        return Categories;
    }
}