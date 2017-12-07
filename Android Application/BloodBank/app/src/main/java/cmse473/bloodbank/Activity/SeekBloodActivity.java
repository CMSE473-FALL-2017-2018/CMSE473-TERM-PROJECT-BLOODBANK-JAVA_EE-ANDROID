package cmse473.bloodbank.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cmse473.bloodbank.ApplicationClass;
import cmse473.bloodbank.Model.Seek;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.APIService;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeekBloodActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private Spinner spnCity, spnType;
    private Button complete, history;

    private APIService _APIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_blood);

        spnCity = findViewById(R.id.spnSeekCity);
        spnType = findViewById(R.id.spnSeekType);
        complete = findViewById(R.id.btnComplete);
        history = findViewById(R.id.btnHistory);

        _APIService = ApiUtils.getAPIService();

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.Types, android.R.layout.simple_spinner_item);

        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        spnCity.setAdapter(cityAdapter);
        spnType.setAdapter(typeAdapter);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Seek seeker = new Seek();
               seeker.setName(ApplicationClass.getUser().getName());
               seeker.setMail(ApplicationClass.getUser().getEmail());
               seeker.setCity(spnCity.getSelectedItem().toString());
               seeker.setBloodType(spnType.getSelectedItem().toString());

                seek(seeker);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeekBloodActivity.this, HistoryActivity.class));
            }
        });

    }

    /**
     * API Processes
     * **/

    private void seek(Seek seek){
        dialog.show();
      _APIService.seek(seek).enqueue(new Callback<Void>() {
          @Override
          public void onResponse(Call<Void> call, Response<Void> response) {
              dialog.dismiss();
              if(response.isSuccessful()){
                  Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_SHORT).show();
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
