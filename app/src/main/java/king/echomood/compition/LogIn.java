package king.echomood.compition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import king.echomood.compition.classes.Users;

public class LogIn extends AppCompatActivity {

    private RealmConfiguration realmConfiguration;
     Realm realm;
    EditText username, password;
    Button register , logIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        realmConfiguration = new RealmConfiguration.Builder(LogIn.this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

         realm = Realm.getDefaultInstance();



        username = (EditText) findViewById(R.id.username_LogIn_text);
        password = (EditText) findViewById(R.id.password_LogIn_text);


        register = (Button) findViewById(R.id.RegisterBtn);
        logIn = (Button) findViewById(R.id.LogInBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this , TestActivity.class));
            }
        });

        log_In_user_check();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, PersonPage.class);
                startActivity(intent);

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmQuery<Users> query = realm.where(Users.class);


                        query.equalTo("username" , username.getText().toString());
                        query.equalTo("password" , password.getText().toString());
                        RealmResults<Users> r1 = query.findAll();
                        try {
                            if (r1.size() > 0) {

                                Intent intent = new Intent(LogIn.this, PersonPage.class);
                                intent.putExtra("id" , r1.get(0).getId());
                                intent.putExtra("username" ,username.getText().toString() );
                                king.echomood.compition.classes.LogIn first = realm.createObject(king.echomood.compition.classes.LogIn.class);
                                first.setID(username.getText().toString());
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Bad", Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() , Toast.LENGTH_LONG).show();
                        }




                    }
                });
            }
        });

    }


    public void log_In_user_check(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmResults<king.echomood.compition.classes.LogIn> r1 = realm.where(king.echomood.compition.classes.LogIn.class).findAll();
                    if (r1.size() > 0) {
                        Intent intent = new Intent(LogIn.this , PersonPage.class);

                        intent.putExtra("username" , r1.get(0).getID());
                        startActivity(intent);
                    }
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString() , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
