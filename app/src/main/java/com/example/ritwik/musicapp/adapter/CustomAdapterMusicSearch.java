package com.example.ritwik.musicapp.adapter;

import android.content.Context;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ritwik.musicapp.HelperClass;
import com.example.ritwik.musicapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;


public class CustomAdapterMusicSearch extends ArrayAdapter<HelperClass> {

    Context context;

    public CustomAdapterMusicSearch(Context context, int resourceId,
                                        List<HelperClass> items) {
        super(context, resourceId, items);
        this.context = context;
        Log.v("Length",items.size()+"");
    }

    private class ViewHolder {
        TextView trackName;
        TextView artistName;
        TextView genre;
        TextView duration;
        TextView price;
        ImageView artAlbumImage;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        HelperClass rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_search, null);
            holder = new ViewHolder();
            holder.price = (TextView)convertView.findViewById(R.id.price);
            holder.trackName = (TextView) convertView.findViewById(R.id.trackName);
            holder.artistName = (TextView) convertView.findViewById(R.id.artistName);
            holder.artAlbumImage = (ImageView) convertView.findViewById(R.id.imageView);
            holder.duration = (TextView) convertView.findViewById(R.id.duration);
            holder.genre = (TextView) convertView.findViewById(R.id.genre);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.trackName.setText(rowItem.getTrackName());
        holder.price.setText(rowItem.getPrice());
        holder.artistName.setText(rowItem.getArtistName());
        holder.duration.setText(rowItem.getDuration());
        holder.genre.setText(rowItem.getGenre());
        if(rowItem.getArtWorkImage()!=null)
        {
            if(!rowItem.getArtWorkImage().equals(""))
            {
                try {
                    final Uri uriTrack = Uri.parse(rowItem.getArtWorkImage());
                    Picasso.with(context).load(uriTrack).fit().into(holder.artAlbumImage,new Callback() {@
                            Override
                    public void onSuccess() {
                    }@
                            Override
                    public void onError() {

                    }
                    });
                }catch (Exception e){
                }
            }
        }
        return convertView;
    }




}



