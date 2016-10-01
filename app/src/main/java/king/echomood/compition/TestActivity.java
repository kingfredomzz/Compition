package king.echomood.compition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.compition.classes.Question;

public class TestActivity extends AppCompatActivity {

    private RealmConfiguration realmConfiguration;
    Realm realm;

    Button add;
    EditText where_Adding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        add = (Button) findViewById(R.id.add_number);
        where_Adding = (EditText) findViewById(R.id.addingNumbers);

        realmConfiguration = new RealmConfiguration.Builder(TestActivity.this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        realm = Realm.getDefaultInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Question> r1 = realm.where(Question.class).findAll();
                        String[] reass = new String[r1.size()];


                        Random r = new Random();
                        int i = r.nextInt(r1.size());
                        String ww = where_Adding.getText().toString();
                        where_Adding.setText( ww + i + " ");




                    }
                });

            }
        });

    }
}
