package king.echomood.compition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import king.echomood.compition.Question.Advancture;
import king.echomood.compition.Question.Classic;
import king.echomood.compition.Question.Four;
import king.echomood.compition.Question.Gathering;

public class Choose extends AppCompatActivity {

    Button Advanc, classic, four, Gather, Pro, Time;
    Intent net;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Advanc = (Button) findViewById(R.id.Advance_Btn);
        classic = (Button) findViewById(R.id.Classic_Btn);
        four = (Button) findViewById(R.id.Four_Btn);
        Gather = (Button) findViewById(R.id.Gathering_Btn);
        Pro = (Button) findViewById(R.id.Pro_Btn);
        Time = (Button) findViewById(R.id.Time_Btn);

       net = new Intent(Choose.this , Question_list.class);

        Advanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                net = new Intent(Choose.this, Advancture.class);
                net.putExtra("type" , "Advance");
                startActivity(net);
            }
        });

        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                net = new Intent(Choose.this, Classic.class);
                net.putExtra("type" , "Classic");
                startActivity(net);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                net = new Intent(Choose.this, Four.class);
                net.putExtra("type" , "Four");
                startActivity(net);
            }
        });

        Gather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                net = new Intent(Choose.this, Gathering.class);
                net.putExtra("type" , "Gathering");
                startActivity(net);
            }
        });

        Pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                net.putExtra("type" , "Pro");
                startActivity(net);
            }
        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                net.putExtra("type" , "Time");
                startActivity(net);
            }
        });

    }



}
