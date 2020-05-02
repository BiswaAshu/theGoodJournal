package syndicatestudios.thegoodjournal;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

/**
 * Created by Ashutosh on 07-07-2019.
 */

public class DatebaseHelper extends SQLiteOpenHelper {
    public static String MYDATABASENAME="theJournal";
    public static int DATABASEVERSION=1;
    Context context;
    public DatebaseHelper(Context context) {
        super(context, MYDATABASENAME, null, DATABASEVERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table JOURNALs(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR2(10),"+
                "answer1 VARCHAR2(100),answer2 VARCHAR2(100),answer3 VARCHAR2(100),answer4 VARCHAR2(100)," +
                "answer5 VARCHAR2(100),answer6 VARCHAR2(100),answer7 VARCHAR2(100),answer8 VARCHAR2(100)," +
                "answer9 VARCHAR2(100),answer10 VARCHAR2(100),answer11 VARCHAR2(100),answer12 VARCHAR2(100)," +
                "answer13 VARCHAR2(100),answer14 VARCHAR2(100),answer15 VARCHAR2(100),answer16 VARCHAR2(100)," +
                "answer17 VARCHAR2(100),answer18 VARCHAR2(100),name VARCHAR2(20));";
       try{
           db.execSQL(query);
       }catch (SQLException ee){
           Toast.makeText(context, ee.getMessage(), Toast.LENGTH_SHORT).show();
       }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS JOURNAL;";
        db.execSQL(query);
        onCreate(db);
        Toast.makeText(context, "Database Updated", Toast.LENGTH_SHORT).show();
    }
}
