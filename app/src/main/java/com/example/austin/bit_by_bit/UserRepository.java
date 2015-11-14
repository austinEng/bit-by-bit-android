package com.example.austin.bit_by_bit;

import com.strongloop.android.loopback.ModelRepository;

/**
 * Created by austin on 11/14/15.
 */
public class UserRepository extends ModelRepository<User> {
    public UserRepository() {
        super("user", User.class);
    }
}
