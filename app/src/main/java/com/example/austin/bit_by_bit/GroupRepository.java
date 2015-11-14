package com.example.austin.bit_by_bit;

import com.strongloop.android.loopback.ModelRepository;

/**
 * Created by austin on 11/14/15.
 */
public class GroupRepository extends ModelRepository<Group> {
    public GroupRepository() {
        super ("group", Group.class);
    }
}
