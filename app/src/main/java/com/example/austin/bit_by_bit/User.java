package com.example.austin.bit_by_bit;

import java.util.HashSet;
import com.strongloop.android.loopback.Model;

/**
 * Created by austin on 11/14/15.
 */
public class User extends Model {
    private String name;
    private HashSet<Group> groups;

    public User(String name) {
        this.name = name;
        this.groups = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeGroup(Group group) {
        if (groups.contains(group)) {
            groups.remove(group);
            return true;
        } else {
            return false;
        }
    }

}
