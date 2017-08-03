package com.example.ritwik.musicapp;


public class HelperClass {
    public String trackName;
    public String artistName;
    public String genre;
    public String duration;
    public String price;
    public String artWorkImage;
    public String artistviewurl;
    public String collectionviewUrl;
    public String collectionPrice;
    public String country;

    public HelperClass(String trackName,String artistName,String genre,String duration,String price,String artWorkImage,String artistviewurl,String collectionviewUrl,String collectionPrice,String country) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
        this.price = price;
        this.artWorkImage = artWorkImage;
        this.artistviewurl = artistviewurl;
        this.collectionviewUrl = collectionviewUrl;
        this.collectionPrice = collectionPrice;
        this.country = country;
    }
    public String getTrackName() {
        return trackName;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getArtWorkImage() {
        return artWorkImage;
    }
    public void setArtWorkImage(String artWorkImage) {
        this.artWorkImage = artWorkImage;
    }
    public String getArtistviewurl() {
        return artistviewurl;
    }
    public void setArtistviewurl(String artistviewurl) {
        this.artistviewurl = artistviewurl;
    }
    public String getCollectionviewUrl() {
        return collectionviewUrl;
    }
    public void setCollectionviewUrl(String collectionviewUrl) {
        this.collectionviewUrl = collectionviewUrl;
    }
    public String getCollectionPrice() {
        return collectionPrice;
    }
    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}

