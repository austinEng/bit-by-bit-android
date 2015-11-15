package com.example.austin.bit_by_bit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by austin on 11/14/15.
 */
public class UserMultiSelectAdapter extends ArrayAdapter<User> {
    private Activity activity;
    private ArrayList<User> lUser;
    private HashSet<User> selected;
    private static LayoutInflater inflater = null;

    public UserMultiSelectAdapter(Activity activity, int textViewResourceId, ArrayList<User> _lUser) {
        super(activity, textViewResourceId, _lUser);
        selected = new HashSet<>();
        try {
            this.activity = activity;
            this.lUser = _lUser;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    /*public int getCount() {
        return lUser.size();
    }

    public User getItem(User position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }*/

    public class ViewHolder {
        public TextView username;
        public ImageView icon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.list_item_friend, null);
                holder = new ViewHolder();

                holder.username = (TextView) vi.findViewById(R.id.username);
                holder.icon = (ImageView) vi.findViewById(R.id.icon);
                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }

            User u = getItem(position);

            holder.username.setText(u.getUsername());

            if (selected.contains(u)) {
                holder.icon.setImageResource(R.drawable.ic_check);
                //holder.icon.setBackgroundColor(getContext().getColor(R.color.colorAccent));
            } else {
                holder.icon.setImageResource(R.drawable.ic_person);
            }


        } catch (Exception e) {

        }
        return vi;
    }

    public boolean toggle(User user) {
        if (selected.contains(user)) {
            return selected.remove(user);
        } else {
            return selected.add(user);
        }
    }

    public boolean toggle(User user, boolean bool) {
        if (bool) {
            bool = selected.add(user);
        } else {
            bool = selected.remove(user);
        }
        return bool;
    }
}
