package com.example.austin.bit_by_bit;

import com.strongloop.android.loopback.Model;

import java.util.HashSet;

/**
 * Created by austin on 11/14/15.
 */
public class Group extends Model {

    private String title;
    private HashSet<User> members;

    public Group(String title) {
        this.title = title;
        this.members = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean addMember(User user) {
        if (!members.contains(user)) {
            members.add(user);
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeMember(User user) {
        if (members.contains(user)) {
            members.remove(user);
            return true;
        } else {
            return false;
        }
    }

}
