package com.example.ritwik.musicapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ritwik.musicapp.adapter.CustomAdapterMusicSearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public List<String> trackNameList,artistNameList,genre,duration,price,artWorkPicString,artisticViewUrl,collectionViewUrl,collectionPrice,country;
    EditText searchForMusic;
    public static HelperClass selectedRowItem;
    List<HelperClass> rowItems=new ArrayList<>();
    CustomAdapterMusicSearch adapter;
    ListView listview;
    View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchForMusic = (EditText)findViewById(R.id.searchMusicEditText);
        selectedRowItem = new HelperClass("","","","","","","","","","");
        progressView=findViewById(R.id.search_progress);
        adapter = new CustomAdapterMusicSearch(MainActivity.this,
                R.layout.list_item_search, rowItems);
        listview = (ListView) findViewById(R.id.musicList);
        listview.setAdapter(adapter);
        searchForMusic.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if(trackNameList!=null)
                        trackNameList.clear();
                    if(artistNameList!=null)
                        artistNameList.clear();
                    if(genre!=null)
                        genre.clear();
                    if(duration!=null)
                        duration.clear();
                    if(price!=null)
                        price.clear();
                    if(artWorkPicString!=null)
                        artWorkPicString.clear();
                    if(artisticViewUrl!=null)
                        artisticViewUrl.clear();
                    if(collectionViewUrl!=null)
                        collectionViewUrl.clear();
                    if(collectionPrice!=null)
                        collectionPrice.clear();
                    if(country!=null)
                        country.clear();
                    String searchable = searchForMusic.getText().toString();
                    showProgress(true);
                    // Searching for the music
                    GetData getData = new GetData(searchable);
                    getData.execute();
                    return true;
                }
                return false;
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                //Sending data to other view
                Intent intent = new Intent(MainActivity.this, ShowDataforSelectedAlbum.class);
                intent.putExtra("Trackname", rowItems.get(i).getTrackName());
                intent.putExtra("artistName", rowItems.get(i).getArtistName());
                intent.putExtra("genre", rowItems.get(i).getGenre());
                intent.putExtra("duration", rowItems.get(i).getDuration());
                intent.putExtra("price", rowItems.get(i).getPrice());
                intent.putExtra("artistImage", rowItems.get(i).getArtWorkImage());
                intent.putExtra("artistUrl", rowItems.get(i).getArtistviewurl());
                intent.putExtra("collectionViewUrl", rowItems.get(i).getCollectionviewUrl());
                intent.putExtra("country", rowItems.get(i).getCountry());
                intent.putExtra("collectionPrice", rowItems.get(i).getCollectionPrice());
                startActivity(intent);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
    }


    public class GetData extends AsyncTask<String, Void, String> {

        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;
        URL url = null;
        Boolean postexec = false;
        String searchable;

        GetData(String searchable){
            this.searchable = searchable;
        }

        public void addToJSON(String response){
            try {
                JSONObject responseObject = new JSONObject(response);
                if(responseObject.has("results")){
                    JSONArray data = new JSONArray(responseObject.getString("results"));
                    if(data.length() > 0){
                        trackNameList = new ArrayList<String>(data.length());
                        artistNameList = new ArrayList<String>(data.length());
                        genre = new ArrayList<String>(data.length());
                        duration = new ArrayList<String>(data.length());
                        price = new ArrayList<String>(data.length());
                        artWorkPicString = new ArrayList<String>(data.length());
                        artisticViewUrl = new ArrayList<String>(data.length());
                        collectionViewUrl = new ArrayList<String>(data.length());
                        collectionPrice = new ArrayList<String>(data.length());
                        country = new ArrayList<String>(data.length());
                        JSONObject obj;
                        for (int i = 0; i < data.length(); i++){
                            obj = data.getJSONObject(i);
                            if(obj.has("trackName")){
                                trackNameList.add(obj.getString("trackName"));
                            }
                            if(obj.has("artistName")){
                                artistNameList.add(obj.getString("artistName"));
                            }
                            if(obj.has("primaryGenreName")){
                                genre.add(obj.getString("primaryGenreName"));
                            }
                            if(obj.has("trackTimeMillis")){
                                Date date = new Date(Long.parseLong(obj.getString("trackTimeMillis")));
                                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                                String dateFormatted = formatter.format(date);
                                duration.add(dateFormatted);
                            }
                            if(obj.has("trackPrice")){
                                price.add(obj.getString("trackPrice")+ " " + obj.getString("currency"));
                            }
                            if(obj.has("artworkUrl100")){
                                artWorkPicString.add(obj.getString("artworkUrl100"));
                            }
                            if(obj.has("artistViewUrl")){
                                artisticViewUrl.add(obj.getString("artistViewUrl"));
                            }
                            if(obj.has("collectionViewUrl")){
                                collectionViewUrl.add(obj.getString("collectionViewUrl"));
                            }
                            if(obj.has("collectionPrice")){
                                collectionPrice.add(obj.getString("collectionPrice")+ " " + obj.getString("currency"));
                            }
                            if(obj.has("country")){
                                country.add(obj.getString("country"));
                            }
                        }
                    }
                }
                rowItems.clear();
                for (int i = 0; i < trackNameList.size(); i++) {
                    HelperClass item = new HelperClass(trackNameList.get(i), artistNameList.get(i), genre.get(i),duration.get(i),price.get(i),artWorkPicString.get(i),artisticViewUrl.get(i),collectionViewUrl.get(i),collectionPrice.get(i),country.get(i));
                    rowItems.add(item);
                }
            }catch(Exception e){
                System.out.println(e);
            }

        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected String doInBackground(String... params) {

            try {

                url = new URL("https://itunes.apple.com/search?term="+searchable);
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb2 = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb2.append(line);
                }
                reader.close();
                addToJSON(sb2.toString());
                postexec = true;
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return null;}

        protected void onPostExecute(String result) {
            if(postexec){
                showProgress(false);
                postexec = false;
                adapter.notifyDataSetChanged();
            }
        }
    }
}
