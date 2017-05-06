package site.sample.ooza;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements AsyncListener{
    EditText etUsername, etPassword;
    String username, password;
    String SessId="None";
    String url = "https://misdirect1.000webhostapp.com/login.php";
    AlertDialog.Builder builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.create);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

    }

    public void login(View view)
    {
        Toast.makeText(getApplicationContext(),"logging in.. ", Toast.LENGTH_LONG).show();

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        String parameters ="&username=" + username + "&password=" + password;
        BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this, LoginActivity.this);
        backgroundTask.execute(parameters, url);
    }

    @Override
    public void render(String response) {
        //log in spinner
        try {
            JSONObject profile = new JSONObject(response);
            JSONObject user = profile.getJSONObject("user");
            String rUser = user.getString("id");
            Intent Main_intent = new Intent(this, MainActivity.class);
            Main_intent.putExtra("Session_id", rUser);

            startActivity(Main_intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
        //LoginActivity.this.startActivity(MainIntent);
    }

}
