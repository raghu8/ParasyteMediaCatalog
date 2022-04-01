package com.parasyte.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MediaSearchResults {
    public int page;
    public ArrayList<MediaSearchResult> results;
    public int total_pages;
    public int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MediaSearchResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<MediaSearchResult> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
