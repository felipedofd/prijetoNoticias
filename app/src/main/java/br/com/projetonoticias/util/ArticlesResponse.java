package br.com.projetonoticias.util;

import android.widget.ScrollView;

import java.util.List;

public class ArticlesResponse {

    String status;
    String totalResults;
    List<Article> articles;

    public ArticlesResponse(String status, String totalResults, List <Article> articles){
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;

    }
}
