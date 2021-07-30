package br.com.projetonoticias;

import br.com.projetonoticias.util.ArticlesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends androidx.lifecycle.ViewModel {

    public String apiKey = "ae68700d7dad43d0bc90bda8e85caa12";
    public Retrofit retrofit;
    public ArticleService service;


    public void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ArticleService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ArticleService.class);
    }

    public void buscarNoticias(String perguntaUser, NewsResultListener listener) {
        service.buscarArtigos(apiKey, perguntaUser, "pt").enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                if (response.isSuccessful()) {
                    listener.onResult(response.body().getArticles());

                }
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {

            }
        });
    }
}
