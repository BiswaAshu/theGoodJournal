package syndicatestudios.thegoodjournal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Ashutosh on 08-07-2019.
 */

public class SearchAdapter extends BaseAdapter {
    String[] columns={"id","date","answer1","answer2","answer3","answer4","answer5","answer6",
            "answer7","answer8","answer9","answer10","answer11","answer12","answer13",
            "answer14","answer15","answer16","answer17","answer18","name"};
    String[] dates=new String[100];
    String[] names=new String[100];
    Context c;
    int length;
    DatebaseHelper dbh;
    SQLiteDatabase sld;
    LayoutInflater inflater;
    Cursor cs;
    public SearchAdapter(Context c){
        this.c=c;
        dbh = new DatebaseHelper(c);
        sld = dbh.getWritableDatabase();

        cs = sld.query("JOURNALs",columns,null,null,null,null,null);
        length=cs.getCount();
        inflater=LayoutInflater.from(c);
        read();
    }

    private void read() {
        int x=0;
        for(x=0;cs.moveToNext();x++){
            names[x]=cs.getString(20);
            dates[x]=cs.getString(1);
        }
    }

    @Override
    public int getCount() {
        return length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.singlesearch,null);
        TextView name=(TextView)convertView.findViewById(R.id.nameView);
        TextView date=(TextView)convertView.findViewById(R.id.dateView);
        name.setText(names[position]);
        date.setText(dates[position]);
        return convertView;
    }
}
