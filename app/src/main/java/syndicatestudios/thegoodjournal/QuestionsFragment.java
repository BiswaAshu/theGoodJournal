package syndicatestudios.thegoodjournal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

/**
 * Created by Ashutosh on 06-07-2019.
 */

public class QuestionsFragment extends Fragment implements View.OnClickListener {
    Button pos1,pos2,pos3,pos4,pos5,pos6,pos7;
    Button neg1,neg2,neg3,neg4,neg5,neg6,neg7;
    Button save;
    ImageButton speech;
    EditText an1,an2,an3,an4,an5,an6,an7,an8,an9,an10,an11,name;
    String[] answers;
    DatebaseHelper dbh;
    public QuestionsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.questionslayout,container,false);

        pos1=(Button)v.findViewById(R.id.pos1); pos1.setOnClickListener(this);
        pos2=(Button)v.findViewById(R.id.pos2); pos2.setOnClickListener(this);
        pos3=(Button)v.findViewById(R.id.pos3); pos3.setOnClickListener(this);
        pos4=(Button)v.findViewById(R.id.pos4); pos4.setOnClickListener(this);
        pos5=(Button)v.findViewById(R.id.pos5); pos5.setOnClickListener(this);
        pos6=(Button)v.findViewById(R.id.pos6); pos6.setOnClickListener(this);
        pos7=(Button)v.findViewById(R.id.pos7); pos7.setOnClickListener(this);

        neg1=(Button)v.findViewById(R.id.neg1); neg1.setOnClickListener(this);
        neg2=(Button)v.findViewById(R.id.neg2); neg2.setOnClickListener(this);
        neg3=(Button)v.findViewById(R.id.neg3); neg3.setOnClickListener(this);
        neg4=(Button)v.findViewById(R.id.neg4); neg4.setOnClickListener(this);
        neg5=(Button)v.findViewById(R.id.neg5); neg5.setOnClickListener(this);
        neg6=(Button)v.findViewById(R.id.neg6); neg6.setOnClickListener(this);
        neg7=(Button)v.findViewById(R.id.neg7); neg7.setOnClickListener(this);

        speech=(ImageButton)v.findViewById(R.id.voice_click);   speech.setOnClickListener(this);
        save=(Button)v.findViewById(R.id.save); save.setOnClickListener(this);

        an1=(EditText)v.findViewById(R.id.ans1);
        an2=(EditText)v.findViewById(R.id.ans2);
        an3=(EditText)v.findViewById(R.id.ans3);
        an4=(EditText)v.findViewById(R.id.ans4);
        an5=(EditText)v.findViewById(R.id.ans5);
        an6=(EditText)v.findViewById(R.id.ans6);
        an7=(EditText)v.findViewById(R.id.ans7);
        an8=(EditText)v.findViewById(R.id.ans8);
        an9=(EditText)v.findViewById(R.id.ans9);
        an10=(EditText)v.findViewById(R.id.ans10);
        an11=(EditText)v.findViewById(R.id.ans11);
        name=(EditText)v.findViewById(R.id.name);

        dbh=new DatebaseHelper(getContext());
        answers=new String[18];
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                ArrayList<String> ar = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if(!ar.get(0).isEmpty())
                    an11.setText(ar.get(0));
                //Toast.makeText(this, ar.get(0), Toast.LENGTH_LONG).show();
        }
    }
    public void getSpeechInput(View v) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);
        try {
            startActivityForResult(intent, 10);
        } catch (Exception e) {
            //Toast.makeText(this, "your device doesn't supported", Toast.LENGTH_LONG).show();
        }
    }

    public void getData(){
        answers[0]=an1.getText().toString();
        answers[1]=an2.getText().toString();
        answers[2]=an3.getText().toString();
        answers[4]=an4.getText().toString();
        answers[5]=an5.getText().toString();
        answers[9]=an6.getText().toString();
        answers[10]=an7.getText().toString();
        answers[12]=an8.getText().toString();
        answers[14]=an9.getText().toString();
        answers[16]=an10.getText().toString();
        answers[17]=an11.getText().toString();
    }

    public void insertData(){
        Calendar c=Calendar.getInstance();
        SQLiteDatabase sld=dbh.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("date",c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR)+"");
        cv.put("answer1",answers[0]);
        cv.put("answer2",answers[1]);
        cv.put("answer3",answers[2]);
        cv.put("answer4",answers[3]);
        cv.put("answer5",answers[4]);
        cv.put("answer6",answers[5]);
        cv.put("answer7",answers[6]);
        cv.put("answer8",answers[7]);
        cv.put("answer9",answers[8]);
        cv.put("answer10",answers[9]);
        cv.put("answer11",answers[10]);
        cv.put("answer12",answers[11]);
        cv.put("answer13",answers[12]);
        cv.put("answer14",answers[13]);
        cv.put("answer15",answers[14]);
        cv.put("answer16",answers[15]);
        cv.put("answer17",answers[16]);
        cv.put("answer18",answers[17]);
        cv.put("name",name.getText().toString());
        long id=sld.insert("JOURNALs",null,cv);
        sld.close();
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.pos1){answers[3]="Someone was Happy because of me.";
            pos1.setEnabled(false);neg1.setEnabled(true); }
        else if(v.getId()==R.id.neg1){answers[3]="I failed to make someone happy.";
            pos1.setEnabled(true);neg1.setEnabled(false);}

        if(v.getId()==R.id.pos2){answers[6]="Tried to be polite to everyone.";
            pos2.setEnabled(false);neg2.setEnabled(true);}
        else if(v.getId()==R.id.neg2){answers[6]="I could'nt be polite to everyone";
            pos2.setEnabled(false);neg2.setEnabled(true);}

        if(v.getId()==R.id.pos3){answers[7]="Helped someone in need.";
            pos3.setEnabled(false);neg3.setEnabled(true);}
        else if(v.getId()==R.id.neg3){answers[7]="Helped no one.";
            pos3.setEnabled(true);neg3.setEnabled(false);}

        if(v.getId()==R.id.pos4){answers[8]="Prayed.";
            pos4.setEnabled(false);neg4.setEnabled(true);}
        else if(v.getId()==R.id.neg4){answers[8]="Did not pray.";
            pos4.setEnabled(true);neg4.setEnabled(false);}

        if(v.getId()==R.id.pos5){answers[11]="Improved and learnt something new to be better than yesterday.";
            pos5.setEnabled(false);neg5.setEnabled(true);}
        else if(v.getId()==R.id.neg5){answers[11]="Did not learn anything.";
            pos5.setEnabled(true);neg5.setEnabled(false);}

        if(v.getId()==R.id.pos6){answers[13]="Wasted food, which someone else could have had.";
            pos6.setEnabled(false);neg6.setEnabled(true);}
        else if(v.getId()==R.id.neg6){answers[13]="Did not waste a grain.";
            pos6.setEnabled(true);neg6.setEnabled(false);}

        if(v.getId()==R.id.pos7){answers[15]="I accept the fault that I misbehaved Someone";
            pos7.setEnabled(false);neg7.setEnabled(true);}
        else if(v.getId()==R.id.neg7){answers[15]="Spent the day humble and did not misbehave anyone";
            pos7.setEnabled(true);neg7.setEnabled(false);}

        if(v.getId()==R.id.voice_click){getSpeechInput(v);}

        if(v.getId()==R.id.save){
            getData();
            insertData();
            clearData();
            Toasty.success(getContext(), "Saved ur Day..", Toast.LENGTH_SHORT, true).show();
            startActivity(new Intent(getContext(),WorkingActivity.class));
        }
    }

    private void clearData() {
        an1.setText("");            an2.setText("");            an3.setText("");
        an4.setText("");            an5.setText("");            an6.setText("");
        an7.setText("");            an8.setText("");            an9.setText("");
        an10.setText("");           an11.setText("");           name.setText("");
    }

}
