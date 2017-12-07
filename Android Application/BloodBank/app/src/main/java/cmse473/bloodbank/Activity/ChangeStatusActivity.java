package cmse473.bloodbank.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cmse473.bloodbank.ApplicationClass;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.APIService;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeStatusActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private Button btn;
    private Spinner spn;

    private APIService _APIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status);

        btn = findViewById(R.id.btnOK);
        spn = findViewById(R.id.spnDonor);

        _APIService = ApiUtils.getAPIService();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);


        ArrayAdapter<CharSequence> spnAdapter = ArrayAdapter.createFromResource(this, R.array.Donors, android.R.layout.simple_spinner_item);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn.setAdapter(spnAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                map.put("email", ApplicationClass.getUser().getEmail());
                map.put("password", ApplicationClass.getUser().getPassword());
                map.put("donor", spn.getSelectedItem().toString());

                change(map);
            }
        });
    }

    /**
     * API Processes
     * **/

    private void change(Map<String, String> map){
        dialog.show();
        _APIService.changeStatus(map).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Status changed!", Toast.LENGTH_SHORT).show();
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
