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

public class Register extends AppCompatActivity {

    private RealmConfiguration realmConfig;

    EditText username, password, re_password, email, country, neckname;
    Button cancel, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        realmConfig = new RealmConfiguration.Builder(Register.this).build();
        Realm.setDefaultConfiguration(realmConfig);


        username = (EditText) findViewById(R.id.userName_setText);
        password  = (EditText) findViewById(R.id.password_setText);
        re_password  = (EditText) findViewById(R.id.re_password_setText);
        email  = (EditText) findViewById(R.id.email_setText);
        country  = (EditText) findViewById(R.id.country_setText);
        neckname  = (EditText) findViewById(R.id.neckname_setText);

        register = (Button) findViewById(R.id.RegisterButtonRegister);
        cancel = (Button) findViewById(R.id.CancelBtnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        try {

                            RealmResults<Users> r1 = realm.where(Users.class).findAll();
                            Users users = realm.createObject(Users.class);
                            RealmResults<Users> r2 = realm.where(Users.class).equalTo("username", username.getText().toString()).findAll();
                            if (r2.size() == 0) {
                                if (
                                        username.getText().toString() != "" &&
                                                email.getText().toString() != "" &&
                                                password.getText().toString() != "" &&
                                                neckname.getText().toString() != "") {
                                    if (password.getText().toString() != re_password.getText().toString()) {
                                        users.setUsername(username.getText().toString());
                                        users.setPassword(password.getText().toString());
                                        users.setId(r1.size() + 1);
                                        users.setEmail(email.getText().toString());
                                        users.setCountry(country.getText().toString());
                                        users.setNeckname(neckname.getText().toString());


                                    } else {

                                        Toast.makeText(getApplicationContext(), "الرقم السري لا يتطابق", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "إكمل الفراغات", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                username.setHint("الحساب مسجل مسبقا");
                                Toast.makeText(getApplicationContext(), "  الحساب مسجل مسبقا ", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
