package syndicatestudios.thegoodjournal;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class WorkingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView yv;
    SharedPreferences sh;
    public static FragmentManager man;
    public static Q1 q;
    FragmentTransaction trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        getSupportActionBar().setTitle("the Good Journal");
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);

        yv = v.findViewById(R.id.nav_header_textView);
        sh = getSharedPreferences("dataset", MODE_PRIVATE);
        man = getSupportFragmentManager();
        trans = man.beginTransaction();

        q = new Q1();
        trans.add(R.id.innerlay, q);
        trans.commit();
        yv.setText("Hey "+sh.getString("name", "alpha"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder al = new AlertDialog.Builder(this);
            al.setMessage("Sure to leave?");
            al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            al.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            al.show();
            // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.working, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_search) {
            String[] columns={"id","date","answer1","answer2","answer3","answer4","answer5","answer6",
                    "answer7","answer8","answer9","answer10","answer11","answer12","answer13",
                    "answer14","answer15","answer16","answer17","answer18","name"};
            DatebaseHelper dbh=new DatebaseHelper(this);
            SQLiteDatabase sld=dbh.getWritableDatabase();
            Cursor cs=sld.query("JOURNALs",columns,null,null,null,null,null);
            if(cs.getCount()!=0)
                startActivity(new Intent(this,SearchPage.class));
            else
                Toasty.error(WorkingActivity.this, "No data found..", Toast.LENGTH_SHORT, true).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_details) {
            Intent i = new Intent(this, loginpage.class);
            startActivity(i);
        } else if (id == R.id.nav_sleeptime) {
            Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog tp = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Calendar cal=Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY,hourOfDay);
                    cal.set(cal.MINUTE,minute-1);
                    cal.set(Calendar.SECOND,60);
                    Intent intent=new Intent(WorkingActivity.this,AppTriggerReciever.class);
                    PendingIntent pi=PendingIntent.getBroadcast(WorkingActivity.this,1001,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager am=(AlarmManager)WorkingActivity.this.getSystemService(ALARM_SERVICE);
                    am.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
                    Toasty.info(WorkingActivity.this, "Wake time changed to "+hourOfDay+":"+minute, Toast.LENGTH_SHORT, true).show();
                }
            }, mHour, mMinute, true);
            tp.show();

        } else if (id == R.id.nav_info) {
            AlertDialog.Builder al = new AlertDialog.Builder(this);
            al.setTitle("How things work..");
            al.setMessage("The Good Journal is a digital diary which helps you record the answers to basic question which you should ask youself but some how fail to do so. " +
                    "We believe that writing the goods and bads of the day really can change the feel of tomorrow.\n" +
                    "So the Good Journal pops every night just before you sleep and does the job. Moreover we will wish you your Birthday too. \nHope you enjoy and have a nice day.");
            al.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            al.show();
        } else if (id == R.id.nav_about) {
            AlertDialog.Builder al = new AlertDialog.Builder(this);
            al.setTitle("How things work..");
            al.setMessage("Started as my first Android project which I look forward to improvise in the forth coming days before releasing to play store.");
            al.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            al.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
