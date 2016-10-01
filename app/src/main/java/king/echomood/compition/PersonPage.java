package king.echomood.compition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.compition.classes.*;
import king.echomood.compition.classes.LogIn;

public class PersonPage extends AppCompatActivity {

    private RealmConfiguration realmConfiguration;
    ImageView to_main_ques_page , sign_out;
    TextView name, neckName, stage, point;
     Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);

        final int id = getIntent().getIntExtra("id" , 0);
        final String userName = getIntent().getStringExtra("username");

        name = (TextView) findViewById(R.id.profile_personName);
        neckName = (TextView) findViewById(R.id.profile_personNeckName);
        stage = (TextView) findViewById(R.id.profile_Stage);
        point = (TextView) findViewById(R.id.profile_Point);
        sign_out = (ImageView) findViewById(R.id.profile_Setting);

        realmConfiguration = new RealmConfiguration.Builder(PersonPage.this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

         realm = Realm.getDefaultInstance();


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Users> r1 = realm.where(Users.class).equalTo("username" , userName).findAll();

                RealmResults<Question> r2 = realm.where(Question.class).findAll();


                if (r1.size() > 0 ){
                    name.setText(userName);
                    neckName.setText(r1.get(0).getNeckname());
                    stage.setText(r2.size() + "");
                }
            }
        });


        to_main_ques_page = (ImageView) findViewById(R.id.questPage);

        to_main_ques_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonPage.this , Choose.class));

            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        try {
                            RealmResults<king.echomood.compition.classes.LogIn> re2 = realm.where(LogIn.class).findAll();
                            if (re2.size() > 0) {

                                final LogIn logIn = re2.get(0);
                                logIn.deleteFromRealm();

                                startActivity(new Intent(PersonPage.this , king.echomood.compition.LogIn.class));
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }


                });
            }


        });
    }


}
