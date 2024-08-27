package com.ensas.librarymanagementsystem.configuration;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class LoggedUser implements Serializable, HttpSessionBindingListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private String username;
    private ActiveUserStore activeUserStore;


    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getUsername())) {
            users.add(user.getUsername());
        }
    }
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(user.getUsername())) {
            users.remove(user.getUsername());
        }
    }
}
