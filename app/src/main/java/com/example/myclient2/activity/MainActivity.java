package com.example.myclient2.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myclient2.R;
import com.example.myclient2.fragment.AddClientFragment;
import com.example.myclient2.fragment.DeleteClientFragment;
import com.example.myclient2.fragment.SearchClientFragment;
import com.example.myclient2.fragment.ViewClientFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imgview_barcode;
    private CircleImageView imgview_profile_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        initComponentsNavHeader();
    }

    private void initComponentsNavHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, AddClientFragment.newInstance());
        transaction.commit();

        // first set all menu items to unchecked
        uncheckAllMenuItems(navigationView);

        // now set clicked menu item to checked
        navigationView.getMenu().getItem(0).setChecked(true);

        View headerView = navigationView.getHeaderView(0);
        imgview_barcode = (ImageView) headerView.findViewById(R.id.imgview_barcode);
        imgview_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Info");
            }
        });

        imgview_profile_photo = (CircleImageView) headerView.findViewById(R.id.imgview_profile_photo);
        imgview_profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Profile Photo");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment selectedFragment = null;
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_add_client:
                selectedFragment = AddClientFragment.newInstance();
                break;
            case R.id.nav_view_client:
                selectedFragment = ViewClientFragment.newInstance();
                break;
            case R.id.nav_search_client:
                selectedFragment = SearchClientFragment.newInstance();
                break;
            case R.id.nav_delete_client:
                selectedFragment = DeleteClientFragment.newInstance();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frame_layout);
        if (!selectedFragment.getClass().toString().equals(currentFragment.getTag())) {
            getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_layout, selectedFragment, selectedFragment.getClass().toString()) // add and tag the new fragment
                .commit();
        }

        // now set clicked menu item to checked
        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void uncheckAllMenuItems(NavigationView navigationView) {
        final Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                SubMenu subMenu = item.getSubMenu();
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    subMenuItem.setChecked(false);
                }
            } else {
                item.setChecked(false);
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
