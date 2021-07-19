package br.com.projetonoticias;

import br.com.projetonoticias.util.ArticlesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {

    public static final String BASE_URL = "https://newsapi.org/v2/";


    @GET("everything")
    public Call<ArticlesResponse> buscarArtigos(@Query("apiKey") String apiKey, @Query("q") String q, @Query("language") String lenguage);

}
