package com.jinwoo.ably.src.main.fragments.style;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.fragments.style.adapter.StylePostAdapter;
import com.jinwoo.ably.src.main.fragments.style.data.StylePost;
import java.util.ArrayList;

public class FragmentStyle extends Fragment {

    private RecyclerView mStyleView;
    private StylePostAdapter mStylePostAdapter;
    private ArrayList<StylePost> mStylePostList;

    public FragmentStyle() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main_style, container, false);

        mStylePostList = new ArrayList<>();
        StylePost s1 = new StylePost(R.drawable.img_style_1, "뭐혜연", "학생st 뿜뿜하는 04년생's pick 봄 코디룩");
        StylePost s2 = new StylePost(R.drawable.img_style_2, "율럽Yulluv", "명품백과 어울리는 13개 코디 +10cm 커보이는 건 덤으로");
        StylePost s3 = new StylePost(R.drawable.img_style_3, "혜성 MoonBrand", "오늘 같은 날씨에 딱 어울리는 러블리 하이틴 룩");
        StylePost s4 = new StylePost(R.drawable.img_style_4, "승아네 seungahne", "실패할 수 없는 색조합 코디꿀팁 연남동 나들이룩에 찰-떡");
        StylePost s5 = new StylePost(R.drawable.img_style_5, "[MD's PICK]키작녀 마켓을 소개해드려요!", "키작녀코디 김소녀만 믿어봐(Feat.155cm 예뻐민");
        StylePost s6 = new StylePost(R.drawable.img_style_6, "DNCE 든세", "이보다 유니크한 블랙 & 화이트는 없어 <ITZY-WANNABE> 커버댄스");
        StylePost s7 = new StylePost(R.drawable.img_style_7, "ARANG Makeup 아랑", "봄 바람에 살랑살랑~ 천사표 롱 원피스");
        StylePost s8 = new StylePost(R.drawable.img_style_8, "윤짜미 YoonCharmi", "다이어트 필요없어 -5kg 빠져보이는 일주일 코디");
        mStylePostList.add(s1);
        mStylePostList.add(s2);
        mStylePostList.add(s3);
        mStylePostList.add(s4);
        mStylePostList.add(s5);
        mStylePostList.add(s6);
        mStylePostList.add(s7);
        mStylePostList.add(s8);

        mStyleView = view.findViewById(R.id.style_recycler);
        mStylePostAdapter = new StylePostAdapter(getContext(), mStylePostList);
        mStyleView.setHasFixedSize(false);
        mStyleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mStyleView.setAdapter(mStylePostAdapter);

        return view;
    }

}
