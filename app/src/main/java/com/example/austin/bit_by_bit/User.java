package com.example.austin.bit_by_bit;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.math.BigDecimal;

/**
 * Created by austin on 11/14/15.
 */
public class User extends com.strongloop.android.loopback.User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String toString() {
        return getUsername();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(username).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;

        User rhs = (User) obj;
        return new EqualsBuilder().append(username, rhs.username).isEquals();
    }
}
