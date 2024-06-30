package com.bisu.herbalplantidentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class help extends AppCompatActivity {
    /* ImageView bac;
    TextView details,details1,details2,details3,details4,details5,details6,details7;
    LinearLayout layout,layout1,layout2,layout3,layout4,layout5,layout6,layout7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        details = (TextView) findViewById(R.id.details);
        details1 = (TextView) findViewById(R.id.details1);
        details2 = (TextView) findViewById(R.id.details2);
        details3 = (TextView) findViewById(R.id.details3);
        details4 = (TextView) findViewById(R.id.details4);
        details5 = (TextView) findViewById(R.id.details5);
        details6 = (TextView) findViewById(R.id.details6);
        details7 = (TextView) findViewById(R.id.details7);
        layout = (LinearLayout) findViewById(R.id.layout);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout4 = (LinearLayout) findViewById(R.id.layout4);
        layout5 = (LinearLayout) findViewById(R.id.layout5);
        layout6 = (LinearLayout) findViewById(R.id.layout6);
        layout7 = (LinearLayout) findViewById(R.id.layout7);

        bac = (ImageView) findViewById(R.id.back);
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(help.this,aboutapp.class);
                startActivity(a);
            }
        });
    }
    public void expand(View view){
        int v = (details.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout, new AutoTransition());
        details.setVisibility(v);
    }
    public void expand1(View view){
        int a = (details1.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout1, new AutoTransition());
        details1.setVisibility(a);
    }
    public void expand2(View view){
        int b = (details2.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout2, new AutoTransition());
        details2.setVisibility(b);
    }
    public void expand3(View view){
        int c = (details3.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout3, new AutoTransition());
        details3.setVisibility(c);
    }
    public void expand4(View view){
        int d = (details4.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout4, new AutoTransition());
        details4.setVisibility(d);
    }
    public void expand5(View view){
        int e = (details5.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout5, new AutoTransition());
        details5.setVisibility(e);
    }
    public void expand6(View view){
        int f = (details6.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout6, new AutoTransition());
        details6.setVisibility(f);
    }
    public void expand7(View view){
        int g = (details7.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        TransitionManager.beginDelayedTransition(layout7, new AutoTransition());
        details7.setVisibility(g);
    }
    */
}