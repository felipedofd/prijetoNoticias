package br.com.projetonoticias;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import androidx.annotation.Nullable;

import br.com.projetonoticias.util.JsonDownloadTask;


public class MainActivity extends AppCompatActivity {

    private String apiKey = "ae68700d7dad43d0bc90bda8e85caa12";




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("FELIPE", "onCreate");

        new JsonDownloadTask(this)
                .execute("https://newsapi.org/v2/everything?apiKey=ae68700d7dad43d0bc90bda8e85caa12&q=Counter Strike&language=pt");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("FELIPE", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("FELIPE", "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("FELIPE", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("FELIPE", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("FELIPE", "onDestroy");
    }


}

