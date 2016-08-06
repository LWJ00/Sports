package com.hzuapp.team.sports.database;


import com.hzuapp.team.sports.R;
import com.hzuapp.team.sports.fragment.mainFrgment.DataGraphFragment;
import com.hzuapp.team.sports.fragment.mainFrgment.DetailsFragment;
import com.hzuapp.team.sports.fragment.mainFrgment.OwnerFragment;
import com.hzuapp.team.sports.fragment.mainFrgment.SportsFragment;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class TabDb {

    public static String[] getTabsTxt(){
        String[] tabs={"运动","数据图","详情","我的"};
        return tabs;
    }

    public static int[] getTabImg(){
        int[] ids={R.drawable.ic_tab_sport_normal,R.drawable.nav_cir_0,R.drawable.main_tab_social_unselected,R.drawable.ic_tab_me_normal};
        return ids;
    }
    public static int[] getTabImgLight(){
        int[] ids={R.drawable.ic_tab_sport_selected,R.drawable.nav_cir_1,R.drawable.main_tab_social_selected,R.drawable.ic_tab_me_selected};
        return ids;
    }

    public static Class[] getFragments(){
        Class[] clz={SportsFragment.class, DataGraphFragment.class, DetailsFragment.class, OwnerFragment.class};
        return clz;
    }
}
