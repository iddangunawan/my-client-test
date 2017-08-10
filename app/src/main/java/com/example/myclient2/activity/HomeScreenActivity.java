package com.example.myclient2.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.myclient2.R;
import com.example.myclient2.adapter.ViewPagerAdapter;
import com.example.myclient2.fragment.AddClientFragment;
import com.example.myclient2.fragment.DeleteClientFragment;
import com.example.myclient2.fragment.SearchClientFragment;
import com.example.myclient2.fragment.ViewClientFragment;

public class HomeScreenActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments
    AddClientFragment addClientFragment;
    ViewClientFragment viewClientFragment;
    SearchClientFragment searchClientFragment;
    DeleteClientFragment deleteClientFragment;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public  boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_add_client:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.action_view_client:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.action_search_client:
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.action_delete_client:
                            viewPager.setCurrentItem(3);
                            break;
                    }
                    return false;
                }
            });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public  void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*  //Disable ViewPager Swipe
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        */

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        addClientFragment = new AddClientFragment();
        viewClientFragment = new ViewClientFragment();
        searchClientFragment = new SearchClientFragment();
        deleteClientFragment = new DeleteClientFragment();

        adapter.addFragment(addClientFragment);
        adapter.addFragment(viewClientFragment);
        adapter.addFragment(searchClientFragment);
        adapter.addFragment(deleteClientFragment);

        viewPager.setAdapter(adapter);
    }
}
