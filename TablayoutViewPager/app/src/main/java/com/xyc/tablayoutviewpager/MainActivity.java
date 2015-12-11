package com.xyc.tablayoutviewpager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//MODE_FIXED不滑动  MODE_SCROLLABLE可滑动

        MyPagerAdapter adapter = new MyPagerAdapter(this, fragments);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setOffscreenPageLimit(1);
        fragments.add(DemoLazyFragment.newInstance("第一页"));
        fragments.add(DemoLazyFragment.newInstance("第二页"));
        fragments.add(DemoLazyFragment.newInstance("第三页"));
        fragments.add(DemoLazyFragment.newInstance("第四页"));

//        fragments.add(DemoFragment.newInstance("第一页"));
//        fragments.add(DemoFragment.newInstance("第二页"));
//        fragments.add(DemoFragment.newInstance("第三页"));
//        fragments.add(DemoFragment.newInstance("第四页"));

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);

    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fralists;
        private String tabTitles[] = new String[]{"页 1", "页 2", "页 3", "页 4"};


        public MyPagerAdapter(FragmentActivity aty, List<Fragment> viewList) {
            super(aty.getSupportFragmentManager());
            this.fralists = viewList;
        }


        @Override
        public Fragment getItem(int i) {
            return fralists.get(i);
        }

        @Override
        public int getCount() {
            return fralists.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tab_tv);
            tv.setText(tabTitles[position]);
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
