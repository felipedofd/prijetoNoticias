package br.com.projetonoticias.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonDownloadTask extends AsyncTask<String, Void, List<Article>> {



    private final Context context;
    ProgressDialog dialog;
    private NewsLoader newsLoader;

    public JsonDownloadTask(Context context) {
        this.context = context;
    }
    //
    public void setNewsLoader (NewsLoader newsLoader) {
        this.newsLoader = newsLoader;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Carregando", "", true);

    }

    @Override
    protected List<Article> doInBackground(String... params) {
        String url = params[0];
        try {
            URL requestUrl = new URL(url);


            HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setReadTimeout(2000);
            urlConnection.setConnectTimeout(2000);
            urlConnection.setRequestProperty("User-Agent", "Bla");

            int responseCode = urlConnection.getResponseCode();
            if (responseCode > 400) {
                throw new IOException("Error na comunicação do servidor");
            }

            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());

            String jsonAsString = toString(in);
            List<Article> articles = getArticles(new JSONObject(jsonAsString));
            in.close();
            return articles;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Article> getArticles(JSONObject json) throws JSONException {
        List<Article> articles = new ArrayList<>();

        JSONArray articlesArray = json.getJSONArray("articles");
        for (int i = 0; i < articlesArray.length(); i++) {
            JSONObject article = articlesArray.getJSONObject(i);
            String author = article.getString("author");
            String title = article.getString("title");
            String description = article.getString("description");
            String url = article.getString("url");
            String urlToImage = article.getString("urlToImage");
            String publishedAt = article.getString("publishedAt");
            String content = article.getString("content");
            JSONObject source = article.getJSONObject("source");
            String id = source.getString("id");
            String name = source.getString("name");

            Source newSource = new Source(id, name);

            Article newArticle = new Article(newSource, author, title, description, url, urlToImage, publishedAt, content);
            articles.add(newArticle);


        }


        return articles;
    }

    @Override
    protected void onPostExecute(List<Article> articlesResponse) {
        super.onPostExecute(articlesResponse);

        dialog.dismiss();
        //listner
        if (newsLoader != null)
            newsLoader.onResult(articlesResponse);
    }

    private String toString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }
    public interface NewsLoader {
        void onResult(List<Article> articlesResponses);

    }
}
