package com.hzuapp.team.sports.fragment.mainFrgment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hzuapp.team.sports.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    @Bind(R.id.roll_view_pager)
    RollPagerView rollViewPager;
    @Bind(R.id.ll_Compare)
    LinearLayout llCompare;
    @Bind(R.id.ll_History)
    LinearLayout llHistory;
    @Bind(R.id.ll_Attend)
    LinearLayout llAttend;
    @Bind(R.id.ll_Absence)
    LinearLayout llAbsence;


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);


        //设置播放时间间隔(2s)
        rollViewPager.setPlayDelay(2000);
        //设置透明度
        rollViewPager.setAnimationDurtion(500);
        //设置适配器
        rollViewPager.setAdapter(new TestNormalAdapter());

        /**
         * 设置指示器（顺序依次）
         * 自定义指示器图片
         * 设置圆点指示器颜色
         * 设置文字指示器
         * 隐藏指示器
         * */
        //rollViewPager.setHintView(new IconHintView(getContext(), R.drawable.point_press, R.drawable.point_normal));
        rollViewPager.setHintView(new ColorPointHintView(getContext(), Color.GREEN, Color.WHITE));
        //rollViewPager.setHintView(new TextHintView(this));
        //rollViewPager.setHintView(null);
        return view;
    }

    @OnClick({R.id.ll_Compare, R.id.ll_History, R.id.ll_Attend, R.id.ll_Absence})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_Compare:
                break;
            case R.id.ll_History:
                break;
            case R.id.ll_Attend:
                break;
            case R.id.ll_Absence:
                break;
        }
    }

    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.im01,
                R.drawable.im02,
                R.drawable.im03,
                R.drawable.im04,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
