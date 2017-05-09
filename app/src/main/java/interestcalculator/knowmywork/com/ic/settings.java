package interestcalculator.knowmywork.com.ic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by SVB on 06-02-2016.
 */
public class settings extends AppCompatActivity {

    private Toolbar toolbar;
    private Switch switchButton;
    private LinearLayout layout1;
    private EditText inputDate;
    private Button saveButton;
    PrefManager pref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
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

        // For switch button
        switchButton = (Switch) findViewById(R.id.Switch);
        layout1 = (LinearLayout) findViewById(R.id.Linear_Layout1);
        inputDate = (EditText) findViewById(R.id.input_date);
        saveButton = (Button) findViewById(R.id.Button);

        // Invoking Shared preferences
        pref = new PrefManager(this.getApplicationContext());

        // Switch States Management
        switchButton.setChecked(pref.getStateChanged());
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                pref.setStateChanged(bChecked);
                if (bChecked) {
                    layout1.setVisibility(View.VISIBLE);
                    inputDate.setText(String.valueOf(pref.getConstantDate()));
                } else {
                    layout1.setVisibility(View.INVISIBLE);
                    pref.setConstantDate(25);
                }
            }
        });

        // Save Button Management
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pref.setConstantDate(Integer.parseInt(inputDate.getText().toString()));
                Toast.makeText(getApplicationContext(), "Constant Date Changed", Toast.LENGTH_SHORT).show();
            }
        });

        if (switchButton.isChecked()) {
            layout1.setVisibility(View.VISIBLE);
            inputDate.setText(String.valueOf(pref.getConstantDate()));
        } else {
            layout1.setVisibility(View.INVISIBLE);
        }
    }
}
