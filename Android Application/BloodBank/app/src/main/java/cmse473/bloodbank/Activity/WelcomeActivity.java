package cmse473.bloodbank.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cmse473.bloodbank.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000); // wait 3 seconds...
                    startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
