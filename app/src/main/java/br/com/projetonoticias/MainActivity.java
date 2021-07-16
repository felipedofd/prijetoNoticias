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


        JsonDownloadTask thread = new JsonDownloadTask(this);
        thread.setNewsLoader(this);
        thread.execute("https://newsapi.org/v2/everything?apiKey=ae68700d7dad43d0bc90bda8e85caa12&q=Counter Strike&language=pt");
    }


    @Override
    public void onResult(List<Article> articlesResponses) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new ArticleAdapter(articlesResponses));
    }
}

