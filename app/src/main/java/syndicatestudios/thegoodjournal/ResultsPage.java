package syndicatestudios.thegoodjournal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsPage extends AppCompatActivity {
TextView name,datetext,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        String[] columns={"id","date","answer1","answer2","answer3","answer4","answer5","answer6",
                "answer7","answer8","answer9","answer10","answer11","answer12","answer13",
                "answer14","answer15","answer16","answer17","answer18","name"};
        name=(TextView)findViewById(R.id.nameofday);
        datetext=(TextView)findViewById(R.id.date);
        a1=(TextView)findViewById(R.id.a1);
        a2=(TextView)findViewById(R.id.a2);
        a3=(TextView)findViewById(R.id.a3);
        a4=(TextView)findViewById(R.id.a4);
        a5=(TextView)findViewById(R.id.a5);
        a6=(TextView)findViewById(R.id.a6);
        a7=(TextView)findViewById(R.id.a7);
        a8=(TextView)findViewById(R.id.a8);
        a9=(TextView)findViewById(R.id.a9);
        a10=(TextView)findViewById(R.id.a10);
        a11=(TextView)findViewById(R.id.a11);
        a12=(TextView)findViewById(R.id.a12);
        a13=(TextView)findViewById(R.id.a13);
        a14=(TextView)findViewById(R.id.a14);
        a15=(TextView)findViewById(R.id.a15);
        a16=(TextView)findViewById(R.id.a16);
        a17=(TextView)findViewById(R.id.a17);
        a18=(TextView)findViewById(R.id.a18);

        Intent intent=getIntent();
        String x=intent.getStringExtra("posi");
        DatebaseHelper dbh=new DatebaseHelper(this);
        SQLiteDatabase sld=dbh.getWritableDatabase();
        Cursor cs=sld.query("JOURNALs",columns,"id=?",new String[]{x},null,null,null);
        cs.moveToFirst();
        datetext.setText(cs.getString(1));
        a1.setText(cs.getString(2));
        a2.setText(cs.getString(3));
        a3.setText(cs.getString(4));
        a4.setText(cs.getString(5));
        a5.setText(cs.getString(6));
        a6.setText(cs.getString(7));
        a7.setText(cs.getString(8));
        a8.setText(cs.getString(9));
        a9.setText(cs.getString(10));
        a10.setText(cs.getString(11));
        a11.setText(cs.getString(12));
        a12.setText(cs.getString(13));
        a13.setText(cs.getString(14));
        a14.setText(cs.getString(15));
        a15.setText(cs.getString(16));
        a16.setText(cs.getString(17));
        a17.setText(cs.getString(18));
        a18.setText(cs.getString(19));
        name.setText(cs.getString(20));
        sld.close();
    }
}
