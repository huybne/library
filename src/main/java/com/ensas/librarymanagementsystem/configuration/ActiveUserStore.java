package com.ensas.librarymanagementsystem.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class ActiveUserStore {
    public List<String> users;

    public ActiveUserStore() {
        users = new ArrayList<>();
    }

}