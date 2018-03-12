package com.housecloud.housecloud.activitys_principales;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.housecloud.housecloud.R;
import com.housecloud.housecloud.fragments.ChatsFragment;
import com.housecloud.housecloud.fragments.FriendsFragment;
import com.housecloud.housecloud.fragments.RequestsFragment;

public class MessagesActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private SectionsPagerAdapter msectionsPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        mViewPager = findViewById(R.id.main_tab_pager);
        msectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(msectionsPagerAdapter);

        mTabLayout = findViewById(R.id.message_toolbar);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    RequestsFragment requestsFragment = new RequestsFragment();
                    return requestsFragment;
                case 1:
                    ChatsFragment chatsFragment = new ChatsFragment();
                    return chatsFragment;
                case 2:
                    FriendsFragment friendsFragment = new FriendsFragment();
                    return friendsFragment;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Request";
                case 1:
                    return "Chat";
                case 2:
                    return "Friends";
                default:
                    return null;
            }
        }
    }
}
