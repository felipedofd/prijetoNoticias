package br.com.projetonoticias;

import java.util.List;

import br.com.projetonoticias.util.Article;

public interface NewsResultListener {
     void onResult(List<Article> articlesResponses);
}
