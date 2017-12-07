package cmse473.bloodbank.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import cmse473.bloodbank.Model.User;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.APIService;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private EditText mail, pass, name;
    private Spinner city, type, donor, sex, year;
    private Button enroll;

    private APIService _APIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _APIService = ApiUtils.getAPIService();

        enroll = findViewById(R.id.btnEnroll);
        mail = findViewById(R.id.etMail);
        pass = findViewById(R.id.etPassword);
        name = findViewById(R.id.etName);
        city = findViewById(R.id.spCity);
        type = findViewById(R.id.spType);
        donor = findViewById(R.id.spDonation);
        sex = findViewById(R.id.spSex);
        year = findViewById(R.id.spYear);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.Types, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> donorAdapter = ArrayAdapter.createFromResource(this, R.array.Donors, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this, R.array.Sexes, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.Years, android.R.layout.simple_spinner_item);

        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        donorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        city.setAdapter(cityAdapter);
        type.setAdapter(typeAdapter);
        donor.setAdapter(donorAdapter);
        sex.setAdapter(sexAdapter);
        year.setAdapter(yearAdapter);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setBirthYear(Integer.parseInt(year.getSelectedItem().toString()));
                user.setSex(sex.getSelectedItem().toString());
                user.setDonor(donor.getSelectedItem().toString());
                user.setBloodType(type.getSelectedItem().toString());
                user.setCity(city.getSelectedItem().toString());
                user.setName(name.getText().toString().trim());
                user.setPassword(pass.getText().toString().trim());
                user.setEmail(mail.getText().toString().trim());

                register(user);
            }
        });
    }

    /**
     * API Operations
     * */
    private void register(User user){
        dialog.show();
        _APIService.register(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
