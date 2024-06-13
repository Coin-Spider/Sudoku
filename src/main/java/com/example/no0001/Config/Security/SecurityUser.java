package com.example.no0001.Config.Security;

import com.example.no0001.Domain.Ser.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private User user;
    public SecurityUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUserName(),user.getUserName(), authorities);
        this.user=user;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user=user;
    }
}
