package br.com.projetonoticias;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import br.com.projetonoticias.databinding.ActivityMainBinding;
import br.com.projetonoticias.util.Article;
import br.com.projetonoticias.util.ArticleAdapter;
import br.com.projetonoticias.util.ArticlesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements NewsResultListener {


    private ActivityMainBinding binding;
    private NewsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.init();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.buscarNoticias(query,MainActivity.this);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CharSequence perguntaUser = binding.searchView.getQuery();
                viewModel.buscarNoticias(perguntaUser.toString(),MainActivity.this);
                binding.refreshLayout.setRefreshing(false);
            }
        });


    }


    @Override
    public void onResult(List<Article> articlesResponses) {
        binding.lista.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.lista.setAdapter(new ArticleAdapter(articlesResponses));
    }
}

