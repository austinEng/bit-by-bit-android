package com.example.austin.bit_by_bit;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.strongloop.android.loopback.AccessToken;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ObjectCallback;

import org.json.JSONException;

/**
 * Created by austin on 11/14/15.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    static RestAdapter restAdapter;
    static UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restAdapter = new RestAdapter(getApplicationContext(), "http://4e91b6d8.ngrok.io/api");
        userRepository = restAdapter.createRepository(UserRepository.class);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mDrawerList = (NavigationView) findViewById(R.id.nav_view);
        mDrawerList.setNavigationItemSelectedListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new MainFragment());
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
        if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        final MainActivity that = this;
        /*userRepository.loginUser("poop", "poop", new UserRepository.LoginCallback() {

            @Override
            public void onSuccess(AccessToken token, User currentUser) {
                Log.d("userid", token.getUserId().toString());
                Log.d("id", token.getId().toString());
                Log.d("str", token.toString());
                //System.out.println(token.getUserId() + ":" + currentUser.getId());
            }

            @Override
            public void onError(Throwable t) {

            }
        });*/

        String id = null;
        try {
            id = (String) LoginActivity.token.get("id");
        } catch (JSONException e) {
            e.printStackTrace();
            Intent intent = new Intent(that, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        userRepository.findById(id, new ObjectCallback<User>() {
            @Override
            public void onSuccess(User user) {
                if (user != null) {
                    Log.d("SUCCESS", "Logged In!");
                } else {
                    Intent intent = new Intent(that, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(Throwable t) {

            }
        });

        /*userRepository.findCurrentUser(new ObjectCallback<User>() {
            @Override
            public void onSuccess(User user) {
                if (user != null) {
                    // logged in
                } else {
                    // not logged in
                    Intent intent = new Intent(that, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                    //finish();
                }
            }

            @Override
            public void onError(Throwable t) {

            }
        });*/

        super.onResume();
        setTitle(R.string.app_name);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_friends) {
        } else if (id == R.id.nav_invites) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_settings) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
