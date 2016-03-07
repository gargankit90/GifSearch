package com.example.ankitgarg.gifsearch.model;

import io.realm.RealmObject;

/**
 * Created by ankitgarg on 3/5/16.
 */
public class SearchTerm extends RealmObject {
    private String searchTerm;

    public SearchTerm(){

    }

    public SearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
