package syndicatestudios.thegoodjournal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by Ashutosh on 06-07-2019.
 */

public class Q1 extends Fragment implements View.OnClickListener {
    Button b1,b2;
    public Q1(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.q1,container,false);
        b1=(Button)v.findViewById(R.id.button4);
        b1.setOnClickListener(this);
        b2=(Button)v.findViewById(R.id.button5);
        b2.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button4){
            AlertDialog.Builder al=new AlertDialog.Builder(getContext());
            al.setTitle("Reminder");
            al.setCancelable(true);
            al.setMessage("Days are hard. But there always exists something appriciable. It's our choice how we remember it. You may read these answers someday. Make the recall worth.");
            al.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FragmentTransaction trans= getFragmentManager().beginTransaction();
                    trans.remove(WorkingActivity.q);
                    QuestionsFragment qf=new QuestionsFragment();
                    trans.add(R.id.innerlay,qf);
                    trans.commit();
                }
            });
            al.show();
        }
        else if(v.getId()==R.id.button5){
            Toasty.info(getContext(), "You can come back after some time.", Toast.LENGTH_SHORT, true).show();
        }
    }
}
