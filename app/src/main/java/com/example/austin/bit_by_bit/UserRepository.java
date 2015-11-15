package com.example.austin.bit_by_bit;

/**
 * Created by austin on 11/14/15.
 */
public class UserRepository extends com.strongloop.android.loopback.UserRepository<User> {
    public interface LoginCallback extends com.strongloop.android.loopback.UserRepository.LoginCallback<User> {

    }

    public UserRepository() {
        super("user", null, User.class);
    }
}