/*
Assignment: In Class Assignment 04 Homework
File name: MainActivity.java
Full name:
Akhil Madhamshetty-801165622
Tarun thota-801164383
 */
package com.example.inclass06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "demo";
    String baseURL = "http://newsapi.org/v2/top-headlines";
    ImageView imageView;
    ImageView imageLeft;
    ImageView imageRight;
    Button button_select;
    TextView showCategoryText;
    TextView title;
    TextView publishedAt;
    TextView description;
    TextView pageNoText;
    ProgressBar progressBar;
    String[] categories = {"business","entertainment","general","health","science","sports","technology"};
    ArrayList<News> newsList = new ArrayList<>();
    int index;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageLeft = findViewById(R.id.imageLeft);
        imageRight = findViewById(R.id.imageRight);
        showCategoryText = findViewById(R.id.showCategoryText);
        title = findViewById(R.id.title);
        publishedAt = findViewById(R.id.publishedAt);
        description = findViewById(R.id.description);
        pageNoText = findViewById(R.id.pageNoText);
        button_select = findViewById(R.id.seelct_button);
        progressBar = findViewById(R.id.progressBar);

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose Category")
                        .setItems(categories, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String category = categories[which];
                                showCategoryText.setText(category);
                                new GetJSONData().execute(category);
                            }
                        });
                builder.create().show();
            }
        });

        imageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == newsList.size() - 1){
                    index = 0;
                    if(newsList.get(index).title == null || newsList.get(index).title.equals("")){
                        title.setText("No text Present");
                    }
                    else{
                        title.setText(newsList.get(index).title);
                    }
                    if(newsList.get(index).publishedAt == null || newsList.get(index).publishedAt.equals("")){
                        publishedAt.setText("Published At not available");
                    }
                    else{
                        publishedAt.setText(newsList.get(index).publishedAt);
                    }
                    if(newsList.get(index).imageURL == null || newsList.get(index).imageURL.equals("")){
                        imageView.setImageResource(R.mipmap.ic_launcher_round);
                    }
                    else{
                        Picasso.get().load(newsList.get(index).imageURL).into(imageView);
                    }
                    if(newsList.get(index).description == null || newsList.get(index).description.equals("")){
                        description.setText("No Description present");
                    }
                    else{
                        description.setText(newsList.get(index).description);
                    }

                    if(newsList.size() == 1) {
                        imageLeft.setVisibility(View.INVISIBLE);
                        imageRight.setVisibility(View.INVISIBLE);
                    }
                    pageNoText.setText( (index + 1) + " out of " + newsList.size());
                }
                else{
                    index = index + 1;
                    if(newsList.get(index).title == null || newsList.get(index).title.equals("")){
                        title.setText("No text Present");
                    }
                    else{
                        title.setText(newsList.get(index).title);
                    }
                    if(newsList.get(index).publishedAt == null || newsList.get(index).publishedAt.equals("")){
                        publishedAt.setText("Published At not available");
                    }
                    else{
                        publishedAt.setText(newsList.get(index).publishedAt);
                    }
                    if(newsList.get(index).imageURL == null || newsList.get(index).imageURL.equals("")){
                        imageView.setImageResource(R.mipmap.ic_launcher_round);
                    }
                    else{
                        Picasso.get().load(newsList.get(index).imageURL).into(imageView);
                    }
                    if(newsList.get(index).description == null || newsList.get(index).description.equals("")){
                        description.setText("No Description present");
                    }
                    else{
                        description.setText(newsList.get(index).description);
                    }

                    if(newsList.size() == 1) {
                        imageLeft.setVisibility(View.INVISIBLE);
                        imageRight.setVisibility(View.INVISIBLE);
                    }
                    pageNoText.setText( (index + 1) + " out of " + newsList.size());
                }
            }
        });

        imageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == 0 ){
                    index = newsList.size() - 1;
                    if(newsList.get(index).title == null || newsList.get(index).title.equals("")){
                        title.setText("No text Present");
                    }
                    else{
                        title.setText(newsList.get(index).title);
                    }
                    if(newsList.get(index).publishedAt == null || newsList.get(index).publishedAt.equals("")){
                        publishedAt.setText("Published At not available");
                    }
                    else{
                        publishedAt.setText(newsList.get(index).publishedAt);
                    }
                    if(newsList.get(index).imageURL == null || newsList.get(index).imageURL.equals("")){
                        imageView.setImageResource(R.mipmap.ic_launcher_round);
                    }
                    else{
                        Picasso.get().load(newsList.get(index).imageURL).into(imageView);
                    }
                    if(newsList.get(index).description == null || newsList.get(index).description.equals("")){
                        description.setText("No Description present");
                    }
                    else{
                        description.setText(newsList.get(index).description);
                    }

                    if(newsList.size() == 1) {
                        imageLeft.setVisibility(View.INVISIBLE);
                        imageRight.setVisibility(View.INVISIBLE);
                    }

                    pageNoText.setText( (index + 1) + " out of " + newsList.size());
                }
                else{
                    index = index - 1;
                    if(newsList.get(index).title == null || newsList.get(index).title.equals("")){
                        title.setText("No text Present");
                    }
                    else{
                        title.setText(newsList.get(index).title);
                    }
                    if(newsList.get(index).publishedAt == null || newsList.get(index).publishedAt.equals("")){
                        publishedAt.setText("Published At not available");
                    }
                    else{
                        publishedAt.setText(newsList.get(index).publishedAt);
                    }
                    if(newsList.get(index).imageURL == null || newsList.get(index).imageURL.equals("")){
                        imageView.setImageResource(R.mipmap.ic_launcher_round);
                    }
                    else{
                        Picasso.get().load(newsList.get(index).imageURL).into(imageView);
                    }
                    if(newsList.get(index).description == null || newsList.get(index).description.equals("")){
                        description.setText("No Description present");
                    }
                    else{
                        description.setText(newsList.get(index).description);
                    }

                    if(newsList.size() == 1) {
                        imageLeft.setVisibility(View.INVISIBLE);
                        imageRight.setVisibility(View.INVISIBLE);
                    }
                    pageNoText.setText( (index + 1) + " out of " + newsList.size());
                }
            }
        });



    }

    class GetJSONData extends AsyncTask<String, Void, ArrayList<News>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsList = new ArrayList<>();
            index = 0;
            pageNoText.setText("");

            progressBar.setVisibility(View.VISIBLE);
            title.setVisibility(View.INVISIBLE);
            publishedAt.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            description.setVisibility(View.INVISIBLE);
            imageLeft.setVisibility(View.INVISIBLE);
            imageRight.setVisibility(View.INVISIBLE);
            pageNoText.setVisibility(View.INVISIBLE);
        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            ArrayList<News> news = new ArrayList<>();
            String category = strings[0];
            try {
                String url = baseURL + "?"
                                + "apikey=" + getResources().getString(R.string.news_key)
                                + "&" + "category=" + URLEncoder.encode(category,"UTF-8");

                URL url1 = new URL(url);
                connection = (HttpURLConnection) url1.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF8");

                    JSONObject root = new JSONObject(json);
                    JSONArray articles = root.getJSONArray("articles");
                    for (int i=0;i<articles.length();i++) {
                        JSONObject articleJson = articles.getJSONObject(i);


                        News newsItem = new News();

                        newsItem.title = articleJson.getString("title");
                        newsItem.imageURL = articleJson.getString("urlToImage");
                        newsItem.description = articleJson.getString("description");
                        newsItem.publishedAt = articleJson.getString("publishedAt");

                        news.add(newsItem);
                    }
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);
            newsList = news;
            Log.d(TAG, "onPostExecute: " + newsList.size());
            if(newsList.size() == 0) {
                Toast.makeText(MainActivity.this,"No News Found",Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.INVISIBLE);
            title.setVisibility(View.VISIBLE);
            publishedAt.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            description.setVisibility(View.VISIBLE);
            imageLeft.setVisibility(View.VISIBLE);
            imageRight.setVisibility(View.VISIBLE);
            pageNoText.setVisibility(View.VISIBLE);
            index = 0;

            if(newsList.get(index).title == null || newsList.get(index).title.equals("")){
                title.setText("No text Present");
            }
            else{
                title.setText(newsList.get(index).title);
            }
            if(newsList.get(index).publishedAt == null || newsList.get(index).publishedAt.equals("")){
                publishedAt.setText("Published At not available");
            }
            else{
                publishedAt.setText(newsList.get(index).publishedAt);
            }
            if(newsList.get(index).imageURL == null || newsList.get(index).imageURL.equals("")){
                imageView.setImageResource(R.mipmap.ic_launcher_round);
            }
            else{
                Picasso.get().load(newsList.get(index).imageURL).into(imageView);
            }
            if(newsList.get(index).description == null || newsList.get(index).description.equals("")){
                description.setText("No Description present");
            }
            else{
                description.setText(newsList.get(index).description);
            }

            if(newsList.size() == 1) {
                imageLeft.setVisibility(View.INVISIBLE);
                imageRight.setVisibility(View.INVISIBLE);
            }
            pageNoText.setText( (index + 1) + " out of " + newsList.size());
        }
    }
}
