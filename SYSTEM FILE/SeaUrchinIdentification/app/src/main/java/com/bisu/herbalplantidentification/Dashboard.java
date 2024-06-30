package com.bisu.herbalplantidentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bisu.herbalplantidentification.env.Utils;

public class Dashboard extends AppCompatActivity {
    private RelativeLayout achiote,asunting,atayatay,ampalaya,atis,boongon;
    private RelativeLayout dahongmariya, bayabas, guyabano, insulin, kalabo;
    private RelativeLayout kamunggay, katakataka, lemoncito,lubilubi, luya;
    private RelativeLayout manjana, saminsamin, tawatawa, tubatuba;
    private ImageView appinfo,btnSlideDown;
    private View ilacheryline;
    private Button extbtn, intbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button scan = findViewById(R.id.scanButton);
        Button view = findViewById(R.id.viewBtn);

        achiote = (RelativeLayout) findViewById(R.id.achiote);
        asunting = (RelativeLayout) findViewById(R.id.asunting);
        atayatay = (RelativeLayout) findViewById(R.id.atayatay);
        katakataka = (RelativeLayout) findViewById(R.id.katakataka);
        ampalaya = (RelativeLayout) findViewById(R.id.ampalaya);
        atis = (RelativeLayout) findViewById(R.id.atis);
        bayabas = (RelativeLayout) findViewById(R.id.bayabas);
        tawatawa = (RelativeLayout) findViewById(R.id.tawatawa);
        extbtn = (Button) findViewById(R.id.ExternalButton);
        intbtn = (Button) findViewById(R.id.InternalButton);
        appinfo = (ImageView) findViewById(R.id.appinfor);

        ilacheryline= (View) findViewById(R.id.linenalang);

        ImageView icon1 = findViewById(R.id.plantobeanim);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_left);
        icon1.startAnimation(animation);

        View icon2 = findViewById(R.id.linenalang);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.sequential_animation);
        icon2.startAnimation(animation1);

        View icon3 = findViewById(R.id.your_name);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        icon3.startAnimation(animation2);

        View icon4 = findViewById(R.id.your_name2);
        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        icon4.startAnimation(animation3);

        View icon5 = findViewById(R.id.ViewLayout);
        Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.move_right);
        icon5.startAnimation(animation5);

        View icon6 = findViewById(R.id.ScanLayout);
        Animation animation6 = AnimationUtils.loadAnimation(this, R.anim.move_left_2);
        icon6.startAnimation(animation6);

        View icon7 = findViewById(R.id.scrollview1);
        Animation animation7 = AnimationUtils.loadAnimation(this, R.anim.dashboard);
        icon7.startAnimation(animation7);

        View icon8 = findViewById(R.id.scrollview2);
        Animation animation8 = AnimationUtils.loadAnimation(this, R.anim.dashboard);
        icon8.startAnimation(animation8);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, scanplantdimen.class);
                startActivity(i);
                finish();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        appinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        extbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        intbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}