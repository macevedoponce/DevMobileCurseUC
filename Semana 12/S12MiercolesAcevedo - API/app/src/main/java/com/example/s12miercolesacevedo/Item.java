package com.example.s12miercolesacevedo;

public class Item {
    private String imageUrlPreview,largeImageURL, tags,user,type;
    private int likes;
    public Item() {
    }

    public Item(String imageUrlPreview, String largeImageURL, String tags, String user, String type, int likes) {
        this.imageUrlPreview = imageUrlPreview;
        this.largeImageURL = largeImageURL;
        this.tags = tags;
        this.user = user;
        this.type = type;
        this.likes = likes;
    }

    public String getImageUrlPreview() {
        return imageUrlPreview;
    }

    public void setImageUrlPreview(String imageUrlPreview) {
        this.imageUrlPreview = imageUrlPreview;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
