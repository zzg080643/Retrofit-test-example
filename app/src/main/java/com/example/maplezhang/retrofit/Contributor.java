package com.example.maplezhang.retrofit;

/**
 * Created by maplezhang on 16/12/20.
 */

public  class Contributor {

    public final String login;

    public String getContributions() {
        return contributions;
    }

    public String getLogin() {
        return login;
    }

    public final String contributions;

    public Contributor(String login, String contributions) {

        this.login = login;
        this.contributions = contributions;
    }
}