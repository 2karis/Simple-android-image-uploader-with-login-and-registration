package site.sample.ooza;

import android.graphics.Bitmap;

/**
 * Created by simple on 05/02/2017.
 */

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;
    private String title;
    private String price;
    private String id;

    public ImageItem(Bitmap image,String id, String title, String price) {
        super();
        this.image = image;
        this.title = title;
        this.price = price;
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}