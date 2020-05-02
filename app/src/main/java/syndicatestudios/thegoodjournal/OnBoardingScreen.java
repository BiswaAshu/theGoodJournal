package syndicatestudios.thegoodjournal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnBoardingScreen extends AppCompatActivity implements View.OnClickListener{

    ViewPager vp;
    SliderAdapter sa;
    int activePage=1,flag=0;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        SharedPreferences sh=getSharedPreferences("dataset",MODE_PRIVATE);
        if(!sh.getString("name","").isEmpty()){
            Intent i=new Intent(this,WorkingActivity.class);
            finish();
            startActivity(i);
        }
        vp=(ViewPager)findViewById(R.id.slideViewPager);
        sa= new SliderAdapter(this);
        vp.setAdapter(sa);
        vp.addOnPageChangeListener(vl);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(this);//prev
        b2.setOnClickListener(this);//next
    }
    ViewPager.OnPageChangeListener vl= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            activePage=position;
            if(position==0){
                b2.setEnabled(true);
                b1.setEnabled(false);
                b1.setVisibility(View.INVISIBLE );
                b2.setText("Next");
                b1.setText("");
            }
            else if(position==4){
                b2.setEnabled(true);
                b1.setEnabled(true);
                b1.setVisibility(View.VISIBLE );
                b2.setVisibility(View.VISIBLE );
                b2.setText("Finish");
                b1.setText("Back");
                flag++;
            }
            else{
                b2.setEnabled(true);
                b1.setEnabled(true);
                b1.setVisibility(View.VISIBLE );
                b2.setVisibility(View.VISIBLE );
                b2.setText("Next");
                b1.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button1) {
            vp.setCurrentItem(activePage - 1);

        }
        if(v.getId()==R.id.button2) {
            if(flag==1) {
                Intent i = new Intent(this, loginpage.class);
                finish();
                startActivity(i);
            }
            vp.setCurrentItem(activePage + 1);
        }
    }
}
