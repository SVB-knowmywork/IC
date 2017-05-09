package interestcalculator.knowmywork.com.ic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shubh on 2/23/2016.
 */
public class help extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView2;
    private TextView textView4;
    private String overview;
    private String howToUse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
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

        overview = "1. This application contains three different tabs on application start page namely 'QUARTERLY' , 'HALF-YEARLY' and 'RECORDS'.\n\n" +
                "2. The two tabs, 'QUARTERLY' and 'HALF-YEARLY', calculates interest on your fixed deposits compounded quarterly and" +
                " half-yearly respectively.\n\n" +
                "3. The third tab, 'RECORDS' ,shows all the records (calculated RESULTS) saved for later reference.\n\n" +
                "4. There is one more page, Result Page ,which shows the result after calculation and also provides an option to save" +
                " that result for later reference.\n\n" +
                "5. Settings page includes option to change the date at which interest is calculated, default is 25. ";

        howToUse = "1. First enter all the values (Principle Amount, rate of Interest, Opening Date & Maturity Date of the fixed deposit " +
                "and also select the calculation Year) on the page.\n\n" +
                "2. Please enter all the details correct and carefully.\n\n" +
                "3. After all the values have been filled, press the calculate Button.\n\n" +
                "4. After the button is pressed, another page will open with the calculated result and you can also save that result with the help " +
                "of a button in lower right corner.\n\n" +
                "5. You can see all your saved results in 'RECORDS' tab.\n\n" +
                "NOTE:\n\n" +
                "1. Calculation year is the financial year period during which you want to calculate interest on your fixed deposits.\n\n" +
                "2. Financial year is from April To March.\n\n" +
                "3. Calculation year can only be of period 1 year only.";

        textView2 = (TextView) findViewById(R.id.TextView2);
        textView4 = (TextView) findViewById(R.id.TextView4);
        textView2.setText(overview);
        textView4.setText(howToUse);





    }
}

