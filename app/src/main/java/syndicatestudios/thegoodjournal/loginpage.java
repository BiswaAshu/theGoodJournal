package syndicatestudios.thegoodjournal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class loginpage extends AppCompatActivity implements View.OnClickListener {
        TextView showtime;
        EditText nameedit;
        Button pick_time,saved;
        Spinner dd,mm,yy;
        String[] date=new String[32];
        String[] month;
        String[] year=new String[27];
        int mHour,mMinute,hour,min;
        SharedPreferences sha;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_loginpage);
            showtime=(TextView)findViewById(R.id.showTime);
            nameedit=(EditText)findViewById(R.id.editText);
            pick_time=(Button)findViewById(R.id.button);
            saved=(Button)findViewById(R.id.button3);
            saved.setOnClickListener(this);
            yy=(Spinner)findViewById(R.id.spinner);
            dd=(Spinner)findViewById(R.id.spinner2);
            mm=(Spinner)findViewById(R.id.spinner3);
            pick_time.setOnClickListener(this);
            date[0]="Date";
            for(int x=1;x<=31;x++){
                date[x]=x+"";
            }
            month=new String[]{"month","January","February","March","April","May","June","July","August","September","October","November","December"};
            year[0]="year";
            int y=1;
            for(int x=1990;x<=2015;x++){
                year[y]=x+"";
                y++;
            }
            ArrayAdapter ar=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,date);
            dd.setAdapter(ar);
            ArrayAdapter aw=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,month);
            mm.setAdapter(aw);
            ArrayAdapter aq=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,year);
            yy.setAdapter(aq);
            //calender
            Calendar c=Calendar.getInstance();
            mHour=c.get(Calendar.HOUR_OF_DAY);
            mMinute=c.get(Calendar.MINUTE);
            showtime.setText(mHour+":"+mMinute);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                TimePickerDialog tp=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour=hourOfDay;
                        min=minute;
                        showtime.setText(hourOfDay+":"+minute);
                    }
                },mHour,mMinute,true);
                tp.show();
            }
            if(v.getId()==R.id.button3){
                if(!(nameedit.getText().toString().isEmpty()||mm.getSelectedItem().toString().equals("month")||dd.getSelectedItem().toString().equals("Date")||yy.getSelectedItem().toString().equals("year"))){
                    sha = getSharedPreferences("dataset",MODE_PRIVATE);
                    SharedPreferences.Editor edit=sha.edit();
                    edit.putString("name",nameedit.getText().toString());
                    // edit.putInt("hour",hour);
                    // edit.putInt("minute",min);
                    String dates=dd.getSelectedItem().toString();
                    edit.putInt("date",Integer.parseInt(dates));
                    edit.putInt("month",mm.getSelectedItemPosition());
                    edit.commit();
                    //seting alarm to open app daily at set time
                    setAlarm();
                    setBirthdayNoti(Integer.parseInt(dates),mm.getSelectedItemPosition());
                    Intent i=new Intent(this,WorkingActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(this, "Fill it Up All Information", Toast.LENGTH_SHORT).show();
            }
        }

    private void setBirthdayNoti(int d,int m) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.DATE,d);
        c.set(Calendar.MONTH,m);

        c.set(Calendar.HOUR_OF_DAY,6);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Intent intent = new Intent(this, BirthdayNotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0,intent, 0);
        AlarmManager am1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am1.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pendingIntent);
    }

    private void setAlarm() {
            Calendar cal=Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY,hour);
            cal.set(cal.MINUTE,min-1);
            cal.set(Calendar.SECOND,60);
            Intent intent=new Intent(this,AppTriggerReciever.class);
            PendingIntent pi=PendingIntent.getBroadcast(this,1001,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am=(AlarmManager)this.getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
        }

}
