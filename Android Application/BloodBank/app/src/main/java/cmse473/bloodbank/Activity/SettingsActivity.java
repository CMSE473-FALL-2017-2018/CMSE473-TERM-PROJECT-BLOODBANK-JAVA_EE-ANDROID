package cmse473.bloodbank.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cmse473.bloodbank.ApplicationClass;
import cmse473.bloodbank.Model.User;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    User user = ApplicationClass.getUser();

    private EditText mail, pass, newpass;
    private Button del, save;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mail = findViewById(R.id.etSetMail);
        pass = findViewById(R.id.etSetPass);
        newpass = findViewById(R.id.etSetNewPass);
        save = findViewById(R.id.btnSave);
        del = findViewById(R.id.delAccount);
        sp = findViewById(R.id.spnSetCity);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(cityAdapter);

        user.setCity(sp.getSelectedItem().toString());

        mail.setText(user.getEmail());
        mail.setEnabled(false);
        pass.setText(user.getPassword());
        pass.setEnabled(false);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(ApplicationClass.getUser().getEmail());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_pass= newpass.getText().toString().trim();
                if(new_pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill the new password or write current password" , Toast.LENGTH_LONG).show();
                }else{
                    user.setPassword(new_pass);
                    update(user);
                }
            }
        });
    }

    /**
     * API Operations
     * */

    private void delete(String mail){
        dialog.show();
        ApiUtils.getAPIService().deleteMe(mail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Your account deleted!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(User user){
        dialog.show();
        ApiUtils.getAPIService().update(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
