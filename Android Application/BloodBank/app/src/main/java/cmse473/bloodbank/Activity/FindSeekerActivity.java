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

import cmse473.bloodbank.Adapter.FindSeekerAdapter;
import cmse473.bloodbank.Model.Seek;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.APIService;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindSeekerActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private Button search;
    private EditText etCity, etType;

    private ArrayList<Seek> list;
    private ListView lv;
    private FindSeekerAdapter adapter;

    private APIService _APIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_seeker);

        etCity = findViewById(R.id.etSCity);
        etType = findViewById(R.id.etSType);
        search = findViewById(R.id.btnSSearch);

        list = new ArrayList<>();
        lv = findViewById(R.id.listSeek);
        adapter = new FindSeekerAdapter(FindSeekerActivity.this, list);
        lv.setAdapter(adapter);

        _APIService = ApiUtils.getAPIService();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        fetchAllSeeks();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                map.put("city", etCity.getText().toString().trim());
                map.put("type", etType.getText().toString().trim());
                searchSeeks(map);
            }
        });
    }

    /**
     * API Operations
     * **/

    private void fetchAllSeeks(){
        dialog.show();
        _APIService.fetchAllSeeks().enqueue(new Callback<List<Seek>>() {
            @Override
            public void onResponse(Call<List<Seek>> call, Response<List<Seek>> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    for(int i =0; i < response.body().size(); i++){
                        Seek seek = response.body().get(i);
                        list.add(seek);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Seek>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchSeeks(Map<String, String> map){
        dialog.show();
        _APIService.searchSeeker(map).enqueue(new Callback<List<Seek>>() {
            @Override
            public void onResponse(Call<List<Seek>> call, Response<List<Seek>> response) {
                if(response.isSuccessful()){
                    dialog.dismiss();
                    list.clear();
                    for(int i =0; i < response.body().size(); i++){
                        Seek seek = response.body().get(i);
                        list.add(seek);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Seek>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
