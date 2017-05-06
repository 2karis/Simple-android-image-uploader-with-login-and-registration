package site.sample.ooza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.animation.PathInterpolator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by simple on 05/03/2017.
 */

public class DownloadImage extends AsyncTask<Void,Void,Bitmap>{
    ImageListener imageListener;
    String url = "http://misdirect1.000webhostapp.com/images/";
    public DownloadImage(String img, ImageListener imageListener){
        this.url +=img;
        this.imageListener = imageListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {

        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setConnectTimeout(1000*30);
            connection.setReadTimeout(1000*30);
            return BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageListener.setimage(bitmap);
    }
}
interface ImageListener {
    public void setimage(Bitmap response);
}