package cmse473.bloodbank.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cmse473.bloodbank.Adapter.HistoryAdapter;
import cmse473.bloodbank.ApplicationClass;
import cmse473.bloodbank.Model.Seek;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private ListView lv;
    public static HistoryAdapter adapter;
    private ArrayList<Seek> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait while loading...");
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        lv = findViewById(R.id.listHistory);
        list = new ArrayList<>();
        adapter = new HistoryAdapter(HistoryActivity.this, list);
        lv.setAdapter(adapter);

        history(ApplicationClass.getUser().getEmail());
    }

    /**
     * API Operations
     * **/

    private void history(String email){
        dialog.show();
        ApiUtils.getAPIService().history(email).enqueue(new Callback<List<Seek>>() {
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

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Seek>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(),  Toast.LENGTH_SHORT).show();
            }
        });
    }

    // delete process is in custom adapter and application class
}
