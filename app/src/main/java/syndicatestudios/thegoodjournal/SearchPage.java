package syndicatestudios.thegoodjournal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class SearchPage extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        SearchAdapter sa=new SearchAdapter(this);
        ListView lv=(ListView)findViewById(R.id.listView);
        lv.setAdapter(sa);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(this,ResultsPage.class);

        String[] columns={"id","date","answer1","answer2","answer3","answer4","answer5","answer6",
                "answer7","answer8","answer9","answer10","answer11","answer12","answer13",
                "answer14","answer15","answer16","answer17","answer18","name"};
        DatebaseHelper dbh=new DatebaseHelper(this);
        SQLiteDatabase sld=dbh.getWritableDatabase();
        Cursor cs=sld.query("JOURNALs",columns,null,null,null,null,null);
        i.putExtra("posi",""+(position+1));
        startActivity(i);

    }
}
