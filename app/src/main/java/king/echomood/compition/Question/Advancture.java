package king.echomood.compition.Question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.compition.Question_list;
import king.echomood.compition.R;
import king.echomood.compition.adding_Question;
import king.echomood.compition.classes.Question;

public class Advancture extends AppCompatActivity {
    TextView question, type ,score_Text , generate , Question_Number;

    Button Answ1_Btn , Answ2_Btn , Answ3_Btn , Answ4_Btn ;
    int[] num  ;
    ImageView adding , next;
    int locat =0 ,Question_Count = 1 , real_Answer , i;
    int ff = 0;

    private RealmConfiguration realmConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.questText);
        score_Text = (TextView) findViewById(R.id.pointText);
        type = (TextView) findViewById(R.id.stage);
        Question_Number = (TextView) findViewById(R.id.pointText);

        realmConfiguration = new RealmConfiguration.Builder(Advancture.this).build();
        Realm.setDefaultConfiguration(realmConfiguration);


        Answ1_Btn = (Button) findViewById(R.id.answ1Btn);
        Answ2_Btn = (Button) findViewById(R.id.answ2Btn);
        Answ3_Btn = (Button) findViewById(R.id.answ3Btn);
        Answ4_Btn = (Button) findViewById(R.id.answ4Btn);
        generate = (TextView) findViewById(R.id.generateTaxt);
        adding = (ImageView) findViewById(R.id.nextBtn);
        next = (ImageView) findViewById(R.id.prevwBtn);




        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestion_Classic();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        setQuestion_Classic();



        Answ1_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_Answer(1);
            }
        });

        Answ2_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_Answer(2);
            }
        });

        Answ3_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_Answer(3);
            }
        });

        Answ4_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_Answer(4);
            }
        });





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Advancture.this, Question_list.class));
            }
        });



        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Advancture.this , adding_Question.class));
            }
        });


    }

    Realm realm;
    public void setQuestion_Classic() {
        try {


            Question_Number.setText( Question_Count + "");
            realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Question> r1 = realm.where(Question.class).findAll();

                    String[] nam = new String[r1.size()];

                    for (int j = 0; j < nam.length; j++) {
                        ff = j;
                    }


                    num = new int[ff];


                    Random r = new Random();
                    i = r.nextInt(ff);

                    num[locat] = i;

                    locat++;

                    final Question questio1 = r1.get(i);
                    real_Answer = questio1.getReal_answer();

                    question.setText(questio1.getQuestion().toString());
                    Answ1_Btn.setText(questio1.getAnsw1().toString());
                    Answ2_Btn.setText(questio1.getAnsw2().toString());
                    Answ3_Btn.setText(questio1.getAnsw3().toString());
                    Answ4_Btn.setText(questio1.getAnsw4().toString());




                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
        }
    }


    void check_Answer (int answ) {

        if (Question_Count <= 3 && Question_Count <= ff) {
            if (real_Answer == answ) {
                Toast.makeText(getApplicationContext(), " wow ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), " Bad ", Toast.LENGTH_SHORT).show();
            }
            setQuestion_Classic();

            Question_Count++;
        } else {
            finish();
        }
    }


}
