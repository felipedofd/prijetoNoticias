package br.com.projetonoticias.util;

import java.util.List;

import retrofit2.http.GET;

public class ArticlesResponse {

    private String status;
    private String totalResults;
    private List<Article> articles;

    public ArticlesResponse(String status, String totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;


    }

    public List<Article> getArticles() {
        return articles;
    }
}
