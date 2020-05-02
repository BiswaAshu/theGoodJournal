package syndicatestudios.thegoodjournal;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Ashutosh on 06-07-2019.
 */

public class SliderAdapter extends PagerAdapter {
    Context c;
    LayoutInflater li;
    String[] hea={" "," "," "," ","the Good Journal"};
    String[] des={
            "Things, People, Feelings, Stuff happen to us.",
            "Do we Appriciate them? " +
                    "Note them Down?, " +
                    "Think about them?",
            "Do we even ask ourselves the basic questions about our lives and the day?",
            "Or are we simply too busy to recall the goodness in and around us?",
            "answering ourselves is better than answering no one"+"  "};
    public SliderAdapter(Context c){
        this.c=c;
    }

    @Override
    public int getCount() {
        return des.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (RelativeLayout)object==view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        li=(LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        View v=li.inflate(R.layout.slidelayout,container,false);
        TextView tv=(TextView)v.findViewById(R.id.head);
        TextView tv1=(TextView)v.findViewById(R.id.des);
        tv.setText(hea[position].toString());
        tv1.setText(des[position].toString());
        container.addView(v);
        return v;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);

    }
}

