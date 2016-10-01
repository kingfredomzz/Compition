package king.echomood.compition;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import king.echomood.compition.classes.Question;

public class adding_Question extends AppCompatActivity {


    // init
    private RealmConfiguration realmConfiguration;
    EditText question, answ1 , answ2, answ3 , answ4 , type , stage;
    RadioButton ch1 , ch2 , ch3, ch4 , one , one_20 , one_45, two_15;
    Button add , cancel;
    String    typo , stago;
    int choice , time ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding__question);


        // declare the varibles for the main input (Question and answers)
        question = (EditText) findViewById(R.id.add_question);
        answ1 = (EditText) findViewById(R.id.set_Answ1);
        answ2 = (EditText) findViewById(R.id.set_Answ2);
        answ3 = (EditText) findViewById(R.id.set_Answ3);
        answ4 = (EditText) findViewById(R.id.set_Answ4);
        type = (EditText) findViewById(R.id.add_Type);
        stage = (EditText) findViewById(R.id.Add_stage);

        // the  variables of true answer
        ch1 = (RadioButton) findViewById(R.id.Choice_1);
        ch2 = (RadioButton) findViewById(R.id.Choice_2);
        ch3 = (RadioButton) findViewById(R.id.Choice_3);
        ch4 = (RadioButton) findViewById(R.id.Choice_4);

        // the time
        one = (RadioButton) findViewById(R.id.one_minit);
        one_20 = (RadioButton) findViewById(R.id.one_20);
        one_45 = (RadioButton) findViewById(R.id.one_45);
        two_15 = (RadioButton) findViewById(R.id.two_15);

        add = (Button) findViewById(R.id.add_Btn);
        cancel = (Button) findViewById(R.id.cancelBtn);





        //  choose true answer
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = 1;
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = 2;
            }
        });

        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = 3;

            }
        });

        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = 4;
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = 1;
            }
        });

        one_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = 2;
            }
        });

        one_45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = 3;

            }
        });

        two_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = 4;
            }
        });


        // add the stage and the type of the question
        typo = type.getText().toString();
        stago = stage.getText().toString();

        // add to database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    realmConfiguration = new RealmConfiguration.Builder(adding_Question.this).build();
                    Realm.setDefaultConfiguration(realmConfiguration);

                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                        Question ques = realm.createObject(Question.class);

                        ques.setQuestion(question.getText().toString());
                        ques.setAnsw1(answ1.getText().toString());
                        ques.setAnsw2(answ2.getText().toString());
                        ques.setAnsw3(answ3.getText().toString());
                        ques.setAnsw4(answ4.getText().toString());
                        ques.setReal_answer(choice);
                        ques.setTime(time);
                        ques.setStage(stago);
                        ques.setType(typo);


                            Toast.makeText(getApplicationContext(), "Done" , Toast.LENGTH_LONG).show();


                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString() , Toast.LENGTH_LONG).show();
                }
            }
        });





    }

    // function to export database and its data
    public void exportDatabase() {

        // init realm
        Realm realm = Realm.getInstance(realmConfiguration);

        File exportRealmFile = null;
        try {
            // get or create an "export.realm" file
            exportRealmFile = new File(getApplicationContext().getExternalCacheDir(), "export.realm");

            // if "export.realm" already exists, delete
            exportRealmFile.delete();


            // copy current realm to "export.realm"
            realm.writeCopyTo(exportRealmFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        realm.close();

        // init email intent and add export.realm as attachment
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "YOUR MAIL");
        intent.putExtra(Intent.EXTRA_SUBJECT, "YOUR SUBJECT");
        intent.putExtra(Intent.EXTRA_TEXT, "YOUR TEXT");
        Uri u = Uri.fromFile(exportRealmFile);
        intent.putExtra(Intent.EXTRA_STREAM, u);

        // start email intent
        startActivity(Intent.createChooser(intent, "YOUR CHOOSER TITLE"));
    }





}
