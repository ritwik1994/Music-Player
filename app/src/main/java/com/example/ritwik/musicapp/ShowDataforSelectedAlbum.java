package com.example.ritwik.musicapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ritwik.musicapp.adapter.CustomAdapterMusicSearch;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luther on 8/3/17.
 */

public class ShowDataforSelectedAlbum extends AppCompatActivity {

    TextView trackName,artistName,genre,duration,price,artistUrl,collectionUrl,country,collectionPrice;
    CircularImageView artistImage;
    String trackname,artsiname,Genre,Duration,Price,ArtistUrl,CollectionUrl,Country,CollectionPrice,Image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data_file);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            trackname = extras.getString("Trackname");
            artsiname=extras.getString("artistName");
            Genre=extras.getString("genre");
            Duration=extras.getString("duration");
            Price=extras.getString("price");
            ArtistUrl=extras.getString("artistUrl");
            CollectionUrl=extras.getString("collectionViewUrl");
            Country=extras.getString("country");
            CollectionPrice=extras.getString("collectionPrice");
            Image=extras.getString("artistImage");
        }
        //Showing the data in the view

        trackName = (TextView)findViewById(R.id.trackNameText);
        trackName.setText(trackname);
        artistName = (TextView)findViewById(R.id.artistNameText);
        artistName.setText(artsiname);
        genre = (TextView)findViewById(R.id.genreText);
        genre.setText(Genre);
        duration = (TextView)findViewById(R.id.durationText);
        duration.setText(Duration);
        price = (TextView)findViewById(R.id.priceText);
        price.setText(Price);
        collectionUrl = (TextView)findViewById(R.id.informationCollectionText);
        collectionUrl.setText(CollectionUrl);
        country = (TextView)findViewById(R.id.countryText);
        country.setText(Country);
        collectionPrice = (TextView)findViewById(R.id.collectionPriceText);
        collectionPrice.setText(CollectionPrice);
        artistUrl = (TextView)findViewById(R.id.inforMationArtistText);
        artistUrl.setText(ArtistUrl);
        artistImage = (CircularImageView)findViewById(R.id.fUser);
        artistUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Displaying url in webview
                Intent intent=new Intent(ShowDataforSelectedAlbum.this,ShowUrl.class);
                intent.setData(Uri.parse(ArtistUrl));
                startActivity(intent);
            }
        });
        collectionUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Displaying url in webview
                Intent intent=new Intent(ShowDataforSelectedAlbum.this,ShowUrl.class);
                intent.setData(Uri.parse(CollectionUrl));
                startActivity(intent);
            }
        });
        final Uri uriTrack = Uri.parse(Image);
        Picasso.with(ShowDataforSelectedAlbum.this).load(uriTrack).fit().into(artistImage,new Callback() {@
                Override
        public void onSuccess() {
        }@
                Override
        public void onError() {

        }
        });
    }

}
