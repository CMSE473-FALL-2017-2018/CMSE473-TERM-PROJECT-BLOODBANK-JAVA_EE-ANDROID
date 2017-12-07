package cmse473.bloodbank.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cmse473.bloodbank.ApplicationClass;
import cmse473.bloodbank.Model.User;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.APIService;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private Button login;
    private EditText mail, password;
    private TextView register;

    private APIService _APIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = findViewById(R.id.btnLogin);
        mail = findViewById(R.id.etMail);
        password = findViewById(R.id.etPassword);
        register = findViewById(R.id.tvRegister);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        _APIService = ApiUtils.getAPIService();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getText().toString().trim();
                String pass = password.getText().toString().trim();

                HashMap<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("password", pass);

                login(map);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
            }
        });
    }

    /**
     * API Operations
     * **/

    private void login(HashMap<String, String> map){
        dialog.show();
        _APIService.login(map).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    ApplicationClass.setUser(response.body());
                    dialog.dismiss();
                    startActivity(new Intent(HomeActivity.this, PanelActivity.class));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Wrong E-mail/Password combination.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

