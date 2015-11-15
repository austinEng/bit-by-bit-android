package com.example.austin.bit_by_bit;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

/**
 * Created by austin on 11/14/15.
 */


public class CreateGroupListFragment extends ListFragment {

    public ArrayList<String> people;
    public ArrayAdapter<String> adapter;

    public CreateGroupListFragment() {

    }

    public static CreateGroupListFragment newInstance() {
        CreateGroupListFragment fragment = new CreateGroupListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        people = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), R.layout.list_item_friend, R.id.Itemname, people);
        this.setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_create_group, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}