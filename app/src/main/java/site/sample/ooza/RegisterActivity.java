package site.sample.ooza;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements AsyncListener{
    EditText etEmail, etUsername, etPassword;
    String method, email, username, password;
    String url = "https://misdirect1.000webhostapp.com/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.email);
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
    }
    public void register(View view){
        Toast.makeText(getApplicationContext(),"Rgistering user.. ", Toast.LENGTH_LONG).show();

        email = etEmail.getText().toString();
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        String parameters ="email="+ email +"&username=" + username + "&password=" + password;
        BackgroundTask backgroundTask = new BackgroundTask(RegisterActivity.this,RegisterActivity.this);
        backgroundTask.execute( parameters, url);


    }

    @Override
    public void render(String response) {
        //registering spinner
        finish();
    }

}