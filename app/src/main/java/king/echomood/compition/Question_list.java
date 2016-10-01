package king.echomood.compition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.compition.Question.Advancture;
import king.echomood.compition.Question.Classic;
import king.echomood.compition.Question.Four;
import king.echomood.compition.Question.Gathering;
import king.echomood.compition.Question.Prof;
import king.echomood.compition.Question.Timing;
import king.echomood.compition.classes.Question;
import king.echomood.compition.classes.Users;

public class Question_list extends AppCompatActivity {


    ImageView quran , history, earth, math, scince, whoSaid, lots , people, arts ;
    String Quest_Type;
    Intent net;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        net = new Intent(Question_list.this , MainActivity.class);
        Quest_Type = getIntent().getStringExtra("type");

        quran = (ImageView) findViewById(R.id.Kuran_image);
        history = (ImageView) findViewById(R.id.history_Image);
        earth = (ImageView) findViewById(R.id.earth_Image);
        math = (ImageView) findViewById(R.id.math_Image);
        scince = (ImageView) findViewById(R.id.scince_Image);
        whoSaid = (ImageView) findViewById(R.id.whosaid_Image);
        lots = (ImageView) findViewById(R.id.lots_Image);
        people = (ImageView) findViewById(R.id.comuntiy_Image);
        arts = (ImageView) findViewById(R.id.art_Image);

        Toast.makeText(getApplicationContext(), Quest_Type , Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        clickType ();

    }

    void tapo (int i ) {
        try {

            if (Quest_Type == "Classic") {
                net = new Intent(Question_list.this, Classic.class);
            } else if (Quest_Type == "Pro") {
                net = new Intent(Question_list.this, Prof.class);
            } else if (Quest_Type == "Gahtering") {
                net = new Intent(Question_list.this, Gathering.class);

            } else if (Quest_Type == "Time") {
                net = new Intent(Question_list.this, Timing.class);
            } else if (Quest_Type == "Four") {
                net = new Intent(Question_list.this, Four.class);
            } else if (Quest_Type == "Advance") {
                net = new Intent(Question_list.this, Advancture.class);
            }

            net.putExtra("Question_type" , i);
            startActivity(net);
        }catch (Exception e){
            Toast.makeText(getApplication() , e.toString() , Toast.LENGTH_SHORT).show();

        }



    }




    public void clickType (){
        quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(0);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(1);
            }
        });

        earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(2);
            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(3);
            }
        });

        scince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(4);
            }
        });

        whoSaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(5);
            }
        });

        lots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(6);
            }
        });

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(7);
            }
        });

        arts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapo(8);
            }
        });
    }




}
