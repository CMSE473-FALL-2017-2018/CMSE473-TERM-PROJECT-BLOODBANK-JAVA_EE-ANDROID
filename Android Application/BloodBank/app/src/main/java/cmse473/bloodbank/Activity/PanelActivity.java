package cmse473.bloodbank.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cmse473.bloodbank.ApplicationClass;
import cmse473.bloodbank.R;

public class PanelActivity extends AppCompatActivity {

    private TextView header;
    private Button findDonor, findSeeker, changeStatus, seekBlood, settings, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        header = findViewById(R.id.tvHeader);
        findDonor = findViewById(R.id.btnFindDonor);
        findSeeker = findViewById(R.id.btnFindSeeker);
        changeStatus = findViewById(R.id.btnChangeStatus);
        seekBlood = findViewById(R.id.btnSeekBlood);
        settings = findViewById(R.id.btnSettings);
        logout = findViewById(R.id.btnLogout);

        header.setText("Welcome, " + ApplicationClass.getUser().getName());

        findDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanelActivity.this, FindDonorActivity.class));
            }
        });

        findSeeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanelActivity.this, FindSeekerActivity.class));

            }
        });

        changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanelActivity.this, ChangeStatusActivity.class));
            }
        });

        seekBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanelActivity.this, SeekBloodActivity.class));
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanelActivity.this, SettingsActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanelActivity.this, HomeActivity.class));
            }
        });
    }
}
