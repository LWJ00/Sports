package com.hzuapp.team.sports.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.hzuapp.team.sports.R;
import com.hzuapp.team.sports.base.BaseActivity;
import com.hzuapp.team.sports.database.TabDb;

public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tabHost=(FragmentTabHost)super.findViewById(android.R.id.tabhost);
        tabHost.setup(this,super.getSupportFragmentManager(),R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();





    }

    private void initTab() {
        String tabs[]=TabDb.getTabsTxt();
        for(int i=0;i<tabs.length;i++){
            TabHost.TabSpec tabSpec=tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabDb.getFragments()[i],null);//添加Tab页面
            tabHost.setTag(i);
        }
    }

    private View getTabView(int idx) {
        View view= LayoutInflater.from(this).inflate(R.layout.footer_tabs,null);
        ((TextView)view.findViewById(R.id.tvTab)).setText(TabDb.getTabsTxt()[idx]);
        if (idx==0){
            ((TextView)view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.colorPrimary));
            ((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabImgLight()[idx]);

        }
        else {
            ((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabImg()[idx]);
        }
        return view;
    }

    @Override
    //Tab点击事件
    public void onTabChanged(String tabId){
        updateTab();
    }

    //更新Tab页面
    private void updateTab(){
        TabWidget tabWidget=tabHost.getTabWidget();
        for(int i=0;i<tabWidget.getChildCount();i++){
            View view=tabWidget.getChildAt(i);
            ImageView iv=(ImageView)view.findViewById(R.id.ivImg);
            if(i==tabHost.getCurrentTab()){
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.GREEN);
                iv.setImageResource(TabDb.getTabImgLight()[i]);
            }else{
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.foot_tet_gray));
                iv.setImageResource(TabDb.getTabImg()[i]);
            }
        }
    }

}
