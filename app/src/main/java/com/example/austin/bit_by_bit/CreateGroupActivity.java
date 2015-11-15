package com.example.austin.bit_by_bit;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CreateGroupActivity extends AppCompatActivity {

    private Menu menu;
    private CreateGroupListFragment groupList;

    public String[] strs = {
            "Austin",
            "Matt",
            "Joe",
            "Bob",
            "Priscilla",
            "Michael",
            "Ellen",
            "Paul",
            "Victor",
            "Patricia",
            "Enrique",
            "Scott",
            "Katherine"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.frame);

        groupList =  new CreateGroupListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.replace_frame, groupList).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_group, menu);

        this.menu = menu;

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
        groupList.people.clear();
        for (String str : strs) {
            if (Math.random() > Math.random()) {
                groupList.people.add(str);
            }
        }
        groupList.adapter.notifyDataSetChanged();
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
}
