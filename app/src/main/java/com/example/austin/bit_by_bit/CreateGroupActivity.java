package com.example.austin.bit_by_bit;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.wefika.flowlayout.FlowLayout;

import java.util.LinkedHashSet;
import java.util.List;

public class CreateGroupActivity extends AppCompatActivity {

    private CreateGroupListFragment groupList;
    private FlowLayout confirmed;
    private LinkedHashSet<User> confirmedPeople;
    ListView listView;

    private RestAdapter restAdapter;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.create_group_frame);

        groupList =  new CreateGroupListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.replace_frame, groupList).commit();

        confirmedPeople = new LinkedHashSet<>();
        confirmed = (FlowLayout) findViewById(R.id.confirmedList);

        restAdapter = new RestAdapter(getApplicationContext(), "http://4e91b6d8.ngrok.io/api");
        userRepository = restAdapter.createRepository(UserRepository.class);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        listView = groupList.getListView();
        final CreateGroupActivity that  = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User u = (User) listView.getItemAtPosition(position);
                Log.d("DEBUG", u.toString());

                if (!confirmedPeople.contains(u)) {
                    confirmedPeople.add(u);
                    groupList.adapter.toggle(u, true);
                } else {
                    confirmedPeople.remove(u);
                    groupList.adapter.toggle(u, false);
                }

                groupList.adapter.notifyDataSetChanged();

                refreshConfirmed();
            }
        });
        loadUsers("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_group, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                loadUsers(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                loadUsers(query);
                return true;
            }

        });

        return true;
    }

    private void loadUsers(String query) {
        userRepository.findAll(new ListCallback<User>() {

            @Override
            public void onSuccess(List<User> users) {
                Log.d("COUNT", String.valueOf(users.size()));
                for (User u : users) {
                    Log.d("USERS", u.getUsername());
                }

                groupList.people.clear();
                //groupList.adapter.clear();
                for (User user : users) {
                    groupList.people.add(user);
                    /*if (confirmedPeople.contains(user)) {
                        groupList.adapter.toggle(user, true);
                    }*/
                }
                groupList.adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.create_btn:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("Create Group");
    }


    private void refreshConfirmed() {
        confirmed.removeAllViews();
        for (User person : confirmedPeople) {
            Button b = new Button(this);
            b.setTextSize(10);
            b.setText(person.toString());
            FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
            confirmed.addView(b, lp);
        }
    }

}
