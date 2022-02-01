package com.imtiaz.movieslist.Model;

import java.util.List;

public class MoviesInfoModel {

    List<MoviesInfoModelObject> titles = null;
    List<MoviesInfoModelObject> names = null;
    List<MoviesInfoModelObject> companies = null;

    public List<MoviesInfoModelObject> getTitles() {
        return titles;
    }

    public void setTitles(List<MoviesInfoModelObject> titles) {
        this.titles = titles;
    }

    public List<MoviesInfoModelObject> getNames() {
        return names;
    }

    public void setNames(List<MoviesInfoModelObject> names) {
        this.names = names;
    }

    public List<MoviesInfoModelObject> getCompanies() {
        return companies;
    }

    public void setCompanies(List<MoviesInfoModelObject> companies) {
        this.companies = companies;
    }
}
