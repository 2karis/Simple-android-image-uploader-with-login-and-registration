package site.sample.ooza;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserActivity extends AppCompatActivity implements AsyncListener {

    private ProgressBar spinner;
    ArrayList<HashMap<String, Object>> contentList;
    private GridView gridView;
    private SimpleAdapter gridAdapter;
    TextView usrtext, emailtxt, contenttxt;
    String rEmail,SessId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String user_id = getIntent().getStringExtra("user_id");
        SessId = getIntent().getStringExtra("Session_id");

        setContentView(R.layout.activity_user);
        String url = "http://misdirect1.000webhostapp.com/profile.php";
        String parameters = "user_id=" + user_id;
        gridView = (GridView) findViewById(R.id.usergrid);
        usrtext = (TextView) findViewById(R.id.usrName);
        emailtxt = (TextView) findViewById(R.id.usrEmail);
        contenttxt = (TextView) findViewById(R.id.usrContent);

        contentList = new ArrayList<>();
        BackgroundTask backgroundTask = new BackgroundTask(UserActivity.this, UserActivity.this);
        backgroundTask.execute(parameters, url);
        spinner = (ProgressBar) findViewById(R.id.progressProfile);
        spinner.setVisibility(View.VISIBLE);

    }

    @Override
    public void render(String response) {
        spinner.setVisibility(View.GONE);
        try {
            JSONObject profile = new JSONObject(response);
            JSONObject user = profile.getJSONObject("user");
            String rUser = user.getString("username");
            rEmail = user.getString("email");
            String count = "Uploads: "+user.getString("count");
            usrtext.setText(rUser);
            emailtxt.setText(rEmail);
            contenttxt.setText(count);
            JSONArray contents = profile.getJSONArray("content");
            for (int i = 0; i < contents.length(); i++) {
                JSONObject c = contents.getJSONObject(i);
                String title = c.getString("title");
                String contentid = c.getString("id");
                String price = c.getString("price");
                //String img = "http://misdirect1.000webhostapp.com/images/" + c.getString("img");
                HashMap<String, Object> content = new HashMap<>();
                content.put("title", title);

                content.put("id", contentid);
                contentList.add(content);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gridAdapter = new SimpleAdapter(UserActivity.this, contentList,
                R.layout.list_layout, new String[]{"title", "id"},
                new int[]{ R.id.itemTitle, R.id.itemid});

        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String mainChapterNumber = selectedItem.split("\\,", 2)[0];
                String data = mainChapterNumber.substring(mainChapterNumber.indexOf("=")+1);

                Intent intent = new Intent(UserActivity.this, DetailsActivity.class);
                intent.putExtra("content_id", data);
                intent.putExtra("user_id", SessId);
                startActivity(intent);


            }
        });


    }

    public void MessageUser(View v) {
        String[] addresses = {rEmail};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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

    @Override
    protected void onRestart() {
        super.onRestart();
        String user_id = getIntent().getStringExtra("user_id");
        SessId = getIntent().getStringExtra("Session_id");

        setContentView(R.layout.activity_user);
        String url = "http://misdirect1.000webhostapp.com/profile.php";
        String parameters = "user_id=" + user_id;
        gridView = (GridView) findViewById(R.id.usergrid);
        usrtext = (TextView) findViewById(R.id.usrName);
        emailtxt = (TextView) findViewById(R.id.usrEmail);
        contenttxt = (TextView) findViewById(R.id.usrContent);

        contentList = new ArrayList<>();
        BackgroundTask backgroundTask = new BackgroundTask(UserActivity.this, UserActivity.this);
        backgroundTask.execute(parameters, url);
        spinner = (ProgressBar) findViewById(R.id.progressProfile);
        spinner.setVisibility(View.VISIBLE);
    }
}