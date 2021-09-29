package com.example.hcp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hcp.fragments.AssessmentDashboard;
import com.example.hcp.fragments.Dashboard_patient_pending_treatment;
import com.example.hcp.fragments.SampleDashboard;
import com.example.hcp.fragments.VaccinationDashboard;
import com.example.hcp.fragments.sample_status_Dashboard;
import com.example.hcp.fragments.vitalDashboard;
import com.google.android.material.navigation.NavigationView;
import com.example.hcp.R;
import com.example.hcp.fragments.DashboardFragment;
import com.example.hcp.utils.Constants;
import com.example.hcp.utils.SharedPref;
import com.pixplicity.easyprefs.library.Prefs;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECTED_ITEM_ID = "SELECTED_ITEM_ID";
    private final Handler mDrawerHandler = new Handler();

    private int mPrevSelectedId;
    private NavigationView mNavigationView;
    private int mSelectedId;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    FragmentManager fragmentManager;
    public static MainActivity mainActivity;

    //172.16.8.13:4236:4236
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);

        mainActivity = this;

        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);


        mTitle.setText(R.string.app_name);

        fragmentManager = MainActivity.this.getSupportFragmentManager();

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mNavigationView = findViewById(R.id.navigation_view);
        assert mNavigationView != null;
        mNavigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                super.onDrawerSlide(drawerView, 0);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


//        String[] a = new SharedPref(MainActivity.main).GetUserName().split("@");
//        String name = a[0];

        View headerView = mNavigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.navUsername);

        String username = Prefs.getString(Constants.USERNAME, "");

        navUsername.setText(username);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        mSelectedId = mNavigationView.getMenu().getItem(prefs.getInt("default_view", 0)).getItemId();

        mSelectedId = savedInstanceState == null ? mSelectedId : savedInstanceState.getInt(SELECTED_ITEM_ID);

        mPrevSelectedId = mSelectedId;

        mNavigationView.getMenu().findItem(mSelectedId).setChecked(true);

        if (savedInstanceState == null) {
            mDrawerHandler.removeCallbacksAndMessages(null);

            mDrawerHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigate(mSelectedId);
                }
            }, 250);

            boolean openDrawer = prefs.getBoolean("open_drawer", false);

            if (openDrawer)
                mDrawerLayout.openDrawer(GravityCompat.END);
            else
                mDrawerLayout.closeDrawers();
        }
    }

    private void navigate(final int itemId) {
        final View elevation = findViewById(R.id.elevation);
        Fragment navFragment = null;
        switch (itemId) {
            case R.id.nav_1:
                mPrevSelectedId = itemId;
                navFragment = new DashboardFragment();
                break;
//            case R.id.nav_2:
//                mPrevSelectedId = itemId;
//
//                navFragment = new RegisterFamilyFragment();
//                break;nav_assessment
            case R.id.nav_4:
                mPrevSelectedId = itemId;

                navFragment = new vitalDashboard();
                break;
            case R.id.nav_assessment:
                mPrevSelectedId = itemId;
                navFragment = new AssessmentDashboard();
                break;

            case R.id.nav_sample:
                mPrevSelectedId = itemId;
                navFragment = new SampleDashboard();
                break;
            case R.id.nav_pending:
                mPrevSelectedId = itemId;
                navFragment = new Dashboard_patient_pending_treatment();
                break;

            case R.id.nav_vaccination:
                mPrevSelectedId = itemId;
                navFragment = new VaccinationDashboard();
                break;

            case R.id.sample_status:
                mPrevSelectedId = itemId;
                navFragment = new sample_status_Dashboard();
                break;

            case R.id.nav_3:
                logout();
                break;
        }

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(4));

        if (navFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            try {
                transaction.replace(R.id.content_frame, navFragment, "asasa").addToBackStack(null).commit();

                if (elevation != null) {
                    params.topMargin = navFragment instanceof DashboardFragment ? dp(0) : 0;

                    Animation a = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            elevation.setLayoutParams(params);
                        }
                    };
                    a.setDuration(150);
                    elevation.startAnimation(a);
                }
            } catch (IllegalStateException ignored) {
            }
        }
    }

    public int dp(float value) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;

        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        mDrawerHandler.removeCallbacksAndMessages(null);
        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(mSelectedId);
            }
        }, 250);
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        try {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (v instanceof EditText) {
                    Rect outRect = new Rect();
                    v.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        v.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }

        }catch (Exception e ){

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//         Check which request we're responding to
        if (requestCode == 100) {
            // Make sure the request was successful
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

//        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onPause() {
        super.onPause();
    }


    void logout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {
                            new SharedPref(getApplicationContext()).SaveCredentials(null, null, null, null, null, null, null, null, null, null,null);
                            new SharedPref(getApplicationContext()).Logout();
                            Prefs.edit().putBoolean(Constants.isDataLoaded,false).apply();
                            Prefs.edit().putString(Constants.USERNAME,"").apply();
                            Prefs.edit().putString(Constants.PASSWORD,"").apply();

                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }catch (Exception e){

                        }
                    }})
                .setNegativeButton(android.R.string.no, null).show();



    }

    public void setSelection(int i) {
        mNavigationView.setCheckedItem(i);
    }

    public void switchFragment(int itemId) {
        mSelectedId = mNavigationView.getMenu().getItem(itemId).getItemId();
        mNavigationView.getMenu().findItem(mSelectedId).setChecked(true);
        mDrawerHandler.removeCallbacksAndMessages(null);
        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(mSelectedId);
            }
        }, 250);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }  else if (fragmentManager.getBackStackEntryCount() <= 1) {
//            Fragment DFragment = new DashboardFragment();
//
//            Bundle args = new Bundle();
//
//
//            if (DFragment != null) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//                DFragment.setArguments(args);
//                try {
//                    transaction.replace(R.id.content_frame, DFragment, "Main").commit();
//                } catch (IllegalStateException ignored) {
//                }
//            }

            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();



        }
        else if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
        else {

        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}