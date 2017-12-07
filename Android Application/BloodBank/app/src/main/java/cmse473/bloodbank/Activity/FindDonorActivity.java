package cmse473.bloodbank.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmse473.bloodbank.Adapter.FindDonorAdapter;
import cmse473.bloodbank.Model.User;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.APIService;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindDonorActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private Button search;
    private EditText etCity, etType;

    private ArrayList<User> list;
    private ListView lv;
    private FindDonorAdapter adapter;

    private APIService _APIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor);

        etCity = findViewById(R.id.etCity);
        etType = findViewById(R.id.etType);
        search = findViewById(R.id.btnSearch);

        list = new ArrayList<>();
        lv = findViewById(R.id.listDonor);
        adapter = new FindDonorAdapter(FindDonorActivity.this, list);
        lv.setAdapter(adapter);

        _APIService = ApiUtils.getAPIService();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        fetchAllDonors();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                map.put("city", etCity.getText().toString().trim());
                map.put("type", etType.getText().toString().trim());
                searchDonor(map);
            }
        });
    }

    /**
     * API Operations
     * **/

    private void fetchAllDonors(){
        dialog.show();
        _APIService.fetchAllDonors().enqueue(new Callback<List<User>>() {
         @Override
         public void onResponse(Call<List<User>> call, Response<List<User>> response) {
             dialog.dismiss();
             if(response.isSuccessful()){
                 for(int i =0; i < response.body().size(); i++){
                     User user = response.body().get(i);
                     list.add(user);
                 }
             }else{
                 Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
             }
         }

         @Override
         public void onFailure(Call<List<User>> call, Throwable t) {
             dialog.dismiss();
             Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
         }
     });

    }

    private void searchDonor(Map<String, String> map){
        dialog.show();
        _APIService.searchDonor(map).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    list.clear();
                    for(int i =0; i < response.body().size(); i++){
                        User user = response.body().get(i);
                        list.add(user);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
