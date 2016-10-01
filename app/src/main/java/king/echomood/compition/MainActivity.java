package king.echomood.compition;

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
import king.echomood.compition.classes.Question;

public class MainActivity extends AppCompatActivity {

    // init
    TextView question, type ,score_Text , generate;
    Button Answ1_Btn , Answ2_Btn , Answ3_Btn , Answ4_Btn ;
    int[] num  ;
    ImageView adding , next;
    int locat =0 , real_Answer , i;
    private RealmConfiguration realmConfiguration;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.questText);
        score_Text = (TextView) findViewById(R.id.pointText);
        type = (TextView) findViewById(R.id.stage);
        realmConfiguration = new RealmConfiguration.Builder(MainActivity.this).build();
        Realm.setDefaultConfiguration(realmConfiguration);


        Answ1_Btn = (Button) findViewById(R.id.answ1Btn);
        Answ2_Btn = (Button) findViewById(R.id.answ2Btn);
        Answ3_Btn = (Button) findViewById(R.id.answ3Btn);
        Answ4_Btn = (Button) findViewById(R.id.answ4Btn);

        adding = (ImageView) findViewById(R.id.nextBtn);
        next = (ImageView) findViewById(R.id.prevwBtn);






    }

    @Override
    protected void onResume() {
        super.onResume();

        // create a question
        setQuestion_Classic();


        // choose the answers
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
              startActivity(new Intent(MainActivity.this, Question_list.class));
            }
        });



        // to add a question
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , adding_Question.class));
            }
        });


    }


    // function to import a question from the database
    // and fill it in the layout

    public void setQuestion_Classic() {
        try {

            realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    // import all the questions
                    RealmResults<Question> r1 = realm.where(Question.class).findAll();

                    // calculate the number of question
                    String[] nam = new String[r1.size()];
                    int ff = 0;
                    for (int j = 0; j < nam.length; j++) {
                        ff = j;
                    }

                    // get random number to choose question from database
                    Random r = new Random();
                    i = r.nextInt(ff);

                    // get the question
                    final Question questio1 = r1.get(i);

                    // fill in the layout
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



    // function to check if the answer is correct or no
    void check_Answer (int answ) {

        if (real_Answer == answ) {
            Toast.makeText(getApplicationContext(), " wow ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), " Bad ", Toast.LENGTH_SHORT).show();
        }
          setQuestion_Classic();

    }


}
