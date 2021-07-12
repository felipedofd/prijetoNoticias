package br.com.projetonoticias.util;

public class Article {

    Source source;
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    String content;

    public Article(Source source, String author, String title, String description, String url, String urlToImage, String publishedAt, String content){
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;




    }
}

