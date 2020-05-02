package syndicatestudios.thegoodjournal;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Spalsh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Spalsh.this, OnBoardingScreen.class);
                startActivity(i);
                finish();
            }
        }, 3*1000); // wait for 5 seconds
    }
}
