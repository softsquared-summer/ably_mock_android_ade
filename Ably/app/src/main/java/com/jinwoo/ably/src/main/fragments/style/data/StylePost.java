package com.jinwoo.ably.src.main.fragments.style.data;

public class StylePost {

    private int image;
    private String uploader;
    private String title;

    public StylePost(int image, String uploader, String title) {
        this.image = image;
        this.uploader = uploader;
        this.title = title;
    }

    public int getImage() { return image; }
    public String getUploader() {
        return uploader;
    }
    public String getTitle() {
        return title;
    }
}
