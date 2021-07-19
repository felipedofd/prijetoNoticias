package br.com.projetonoticias;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import br.com.projetonoticias.util.Article;
import br.com.projetonoticias.util.ArticleAdapter;
import br.com.projetonoticias.util.JsonDownloadTask;
import br.com.projetonoticias.util.ArticlesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements JsonDownloadTask.NewsLoader {

    SwipeRefreshLayout refreshLayout;
    private String apiKey = "ae68700d7dad43d0bc90bda8e85caa12";
    private RecyclerView recyclerView;
    private SearchView searchView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.lista);
        refreshLayout = findViewById(R.id.refreshLayout);
        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                buscarNoticias();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                buscarNoticias();
                refreshLayout.setRefreshing(false);
            }
        });


    }

    public void buscarNoticias() {
        JsonDownloadTask thread = new JsonDownloadTask(MainActivity.this);
        thread.setNewsLoader(MainActivity.this);
        CharSequence perguntaUser = searchView.getQuery();
        thread.execute("https://newsapi.org/v2/everything?apiKey=ae68700d7dad43d0bc90bda8e85caa12&q=" + perguntaUser + "&language=pt");
    }

    @Override
    public void onResult(List<Article> articlesResponses) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new ArticleAdapter(articlesResponses));
    }
}

