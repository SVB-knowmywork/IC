package interestcalculator.knowmywork.com.ic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by SVB on 20-11-2015.
 */
public class ResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;


    String principleAmount;
    String rateOfInterest;
    String type;
    private int year1;
    private int month1;
    private int day1;

    private int year2;
    private int month2;
    private int day2;

    private int startYear;
    private int lastYear;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        principleAmount = bundle.getString("principleAmount");
        rateOfInterest = bundle.getString("rateOfInterest");
        day1 = bundle.getInt("day1");
        month1= bundle.getInt("month1");
        year1= bundle.getInt("year1");

        day2 = bundle.getInt("day2");
        month2= bundle.getInt("month2");
        year2= bundle.getInt("year2");

        startYear = bundle.getInt("startYear");
        lastYear = bundle.getInt("lastYear");
        result = bundle.getDouble("result");

        type = bundle.getString("type");

        textView1 = (TextView) findViewById(R.id.TextView_3);
        textView2 = (TextView) findViewById(R.id.TextView_6);
        textView3 = (TextView) findViewById(R.id.TextView_9);
        textView4 = (TextView) findViewById(R.id.TextView_12);
        textView5 = (TextView) findViewById(R.id.TextView_15);
        textView6 = (TextView) findViewById(R.id.TextView_16);

        textView1.setText(principleAmount);
        textView2.setText(rateOfInterest);
        textView3.setText(new StringBuilder().append(day1)
                .append("-").append(month1 + 1).append("-").append(year1)
                .append(" "));
        textView4.setText(new StringBuilder().append(day2)
                .append("-").append(month2 + 1).append("-").append(year2)
                .append(" "));
        textView5.setText(new StringBuilder().append(startYear)
                .append("-").append(lastYear));
        textView6.setText(String.valueOf(result));

        final DbHandler db = new DbHandler(this);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * CRUD Operations
                 * */
                // Inserting Contacts

                db.addRecord(new Record(principleAmount, rateOfInterest, day1, day2,
                        month1, month2, year1, year2, startYear, lastYear, result, type));
                Snackbar.make(view, "Record Added", Snackbar.LENGTH_LONG).show();


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        Intent i;
                        i = new Intent(ResultActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }, 2000);

            }
        });
    }

}
