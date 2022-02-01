package com.imtiaz.movieslist.Model;

import java.util.List;

public class SearchMoviesModel {

    List<SearchMoviesModelObject> titles = null;
    List<SearchMoviesModelObject> names = null;
    List<SearchMoviesModelObject> companies = null;

    public List<SearchMoviesModelObject> getTitles() {
        return titles;
    }

    public void setTitles(List<SearchMoviesModelObject> titles) {
        this.titles = titles;
    }

    public List<SearchMoviesModelObject> getNames() {
        return names;
    }

    public void setNames(List<SearchMoviesModelObject> names) {
        this.names = names;
    }

    public List<SearchMoviesModelObject> getCompanies() {
        return companies;
    }

    public void setCompanies(List<SearchMoviesModelObject> companies) {
        this.companies = companies;
    }
}
