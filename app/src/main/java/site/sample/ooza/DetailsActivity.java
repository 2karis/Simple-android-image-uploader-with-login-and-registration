package site.sample.ooza;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by simple on 05/02/2017.
 */

public class DetailsActivity extends AppCompatActivity implements AsyncListener, ImageListener{
    private ProgressBar spinner;
    TextView title, username, price, created_at;
    Button profilebtn;
    ImageView imageView;
    String rUser_id;
    String SessId="None";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        SessId = getIntent().getStringExtra("Session_id");

        String content_id = getIntent().getStringExtra("content_id");
        String SessId = getIntent().getStringExtra("Session_id");

        String url = "http://misdirect1.000webhostapp.com/detail.php";
        String parameters ="content_id="+content_id;

        BackgroundTask backgroundTask = new BackgroundTask(DetailsActivity.this, (AsyncListener) DetailsActivity.this);
        backgroundTask.execute( parameters, url);
        spinner = (ProgressBar)findViewById(R.id.dProgressBar1);
        spinner.setVisibility(View.VISIBLE);
        title = (TextView) findViewById(R.id.dTitle);
        username = (TextView) findViewById(R.id.dUsername);
        price = (TextView) findViewById(R.id.dPrice);
        created_at = (TextView) findViewById(R.id.dCreated_at);
        profilebtn = (Button) findViewById(R.id.user_profile);
        imageView = (ImageView)findViewById(R.id.contentImage);
    }
    @Override
    public void render(String response) {

        spinner.setVisibility(View.GONE);
        try {
            JSONObject detail = new JSONObject(response);
            String rTitle = detail.getString("title");
            rUser_id = detail.getString("user_id");
            String rPrice = detail.getString("price");
            String rCreated_at = detail.getString("created_at");
            String rUsername = detail.getString("username");
            String rImg =  detail.getString("img");
            title.setText(rTitle);
            username.setText(rUsername);
            price.setText(rPrice);
            created_at.setText(rCreated_at);
            profilebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent profile_intent = new Intent(getApplicationContext(), UserActivity.class);
                    profile_intent.putExtra("user_id", rUser_id);
                    profile_intent.putExtra("Session_id", SessId);

                    startActivity(profile_intent);
                }
            });

            new DownloadImage(rImg, DetailsActivity.this).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setimage(Bitmap response) {
        imageView.setImageBitmap(response);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.menu_upload:
                if(SessId=="None"){
                    Toast.makeText(getApplicationContext(),"You Need To Log In To Do That!", Toast.LENGTH_LONG).show();
                }else {
                    Intent upload_intent = new Intent(this, UploadActivity.class);
                    upload_intent.putExtra("Session_id", SessId);
                    startActivity(upload_intent);
                    return true;
                }
            case R.id.menu_profile:
                if(SessId=="None"){
                    Toast.makeText(getApplicationContext(),"You Need To Log In To Do That!", Toast.LENGTH_LONG).show();
                }else {
                    Intent profile_intent = new Intent(this, UserActivity.class);
                    profile_intent.putExtra("user_id", SessId);
                    profile_intent.putExtra("Session_id", SessId);

                    startActivity(profile_intent);
                }
                return true;
            case R.id.menu_logout:
                Intent login_intent = new Intent(this, LoginActivity.class);
                startActivity(login_intent);
                Toast.makeText(getApplicationContext(), "logged out", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
