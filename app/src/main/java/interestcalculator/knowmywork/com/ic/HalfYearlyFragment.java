package interestcalculator.knowmywork.com.ic;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;


public class HalfYearlyFragment extends Fragment{

    /* First Calculation Variables */
    private int l;   // modulus remainder
    private double k;
    private double m = 0;
    private double i;

    /* Second Calculation Variables */
    private int u;   // modulus remainder
    private int v;   // division
    private double t;
    private double s;

    private int year;
    private int month;
    private int day;
    private EditText editText_1;
    private EditText editText_2;
    private EditText editText_3;
    private EditText editText_4;
    NumberPicker np1,np2;
    Button btnClick = null;

    public int year1;
    public int month1;
    public int day1;

    public int year2;
    public int month2;
    public int day2;

    public String principleAmount;
    public String rateOfInterest;
    public String type = "H";

    public int startYear;
    public int lastYear;
    static final int DATE_PICKER_ID = 1111;
    static final int DATE_PICKER_ID1 = 1112;

    public HalfYearlyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_quarter, container, false);
        btnClick = (Button) rootView.findViewById(R.id.inform_1);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                editText_1 = (EditText) rootView.findViewById(R.id.EditText_1);
                editText_2 = (EditText) rootView.findViewById(R.id.EditText_2);
                principleAmount = editText_1.getText().toString();
                rateOfInterest = editText_2.getText().toString();
                double amount1;
                double amount2;

                if (!validatePrincipleAmount() || !validateRateOfInterest()
                        || !validateOpeningDate() || !validateMaturityDate()
                        || !validateStartYear() || !validateLastYear()) {
                    return;
                } else {

                    if(year2 < year1){
                        Toast.makeText(getContext(), "Maturity Date Is Wrong", Toast.LENGTH_LONG).show();
                        return;
                    }else if(year2 == year1){
                        if(month2 < month1){
                            Toast.makeText(getContext(), "Maturity Date Is Wrong", Toast.LENGTH_LONG).show();
                            return;
                        }else if(month2 == month1){
                            if(day2 < day1){
                                Toast.makeText(getContext(), "Maturity Date Is Wrong", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                    if(lastYear < year1){
                        Toast.makeText(getContext(), "Calculation Year Is Wrong", Toast.LENGTH_LONG).show();
                        return;
                    }else if(lastYear == year1){
                        if(3 < (month1 + 1)){
                            Toast.makeText(getContext(), "Calculation Year Is Wrong", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    if(startYear > year2){
                        Toast.makeText(getContext(), "Calculation Year Is Wrong", Toast.LENGTH_LONG).show();
                        return;
                    }else if(startYear == year2){
                        if((month2 + 1) < 4){
                            Toast.makeText(getContext(), "Calculation Year Is Wrong", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    // calling startCalculation method
                    startCalculation();
                    endCalculation();


                    if (i >= 0) {
                        if (i != m) {
                            amount1 = compoundInterest(Double.parseDouble(principleAmount), Double.parseDouble(rateOfInterest), m);
                            amount2 = compoundInterest(amount1, Double.parseDouble(rateOfInterest), (i - m + t));
                            amount2 = amount2 - compoundInterest(amount1, Double.parseDouble(rateOfInterest), (i - m));
                        } else {
                            amount1 = compoundInterest(Double.parseDouble(principleAmount), Double.parseDouble(rateOfInterest), m);
                            amount2 = compoundInterest(amount1, Double.parseDouble(rateOfInterest), t);
                            amount2 = amount2 - amount1;
                        }
                    } else {
                        double check = Math.abs(i);
                        check = check % (double) 6;
                        check = 6 - check;
                        if (check <= (i + t)) {
                            amount1 = compoundInterest(Double.parseDouble(principleAmount), Double.parseDouble(rateOfInterest), check);
                            amount2 = compoundInterest(amount1, Double.parseDouble(rateOfInterest), (i + t - check)) - Double.parseDouble(principleAmount);
                        } else {
                            amount2 = compoundInterest((Double.parseDouble(principleAmount)), Double.parseDouble(rateOfInterest), (i + t)) - Double.parseDouble(principleAmount);
                        }

                    }


                    Intent i;
                    i = new Intent(getContext(), ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("principleAmount", principleAmount);
                    bundle.putString("rateOfInterest", rateOfInterest);
                    bundle.putInt("day1", day1);
                    bundle.putInt("month1", month1);
                    bundle.putInt("year1", year1);
                    bundle.putInt("day2", day2);
                    bundle.putInt("month2", month2);
                    bundle.putInt("year2", year2);
                    bundle.putInt("startYear", startYear);
                    bundle.putInt("lastYear", lastYear);
                    bundle.putDouble("result", amount2);
                    bundle.putString("type", type);
                    i.putExtras(bundle);
                    startActivity(i);

                }
            }
        });

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        editText_3= (EditText) rootView.findViewById(R.id.EditText_3);
        editText_4= (EditText) rootView.findViewById(R.id.EditText_4);


        np1 = (NumberPicker) rootView.findViewById(R.id.numberPicker1);
        np2 = (NumberPicker) rootView.findViewById(R.id.numberPicker2);

        np1.setMinValue(2000);
        np1.setMaxValue(2030);
        np1.setWrapSelectorWheel(true);
        np2.setMinValue(2000);
        np2.setMaxValue(2030);
        np2.setWrapSelectorWheel(true);

        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                startYear = newVal;
                np2.setValue(newVal + 1);
                lastYear = newVal + 1;
            }
        });

        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                lastYear = newVal;
                np1.setValue(newVal - 1);
                startYear = newVal - 1;
            }
        });

        editText_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // On button click show datepicker dialog
                onCreateDialog(DATE_PICKER_ID);

            }

        });
        editText_4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // On button click show datepicker dialog
                onCreateDialog1(DATE_PICKER_ID1);

            }

        });
        return rootView;
    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datePicker dialog.
                // set date picker for current date
                // add pickerListener listener to date picker
                new DatePickerDialog(getContext() , pickerListener, year, month,day).show();
        }
        return null;
    }

    protected Dialog onCreateDialog1(int id) {
        switch (id) {
            case DATE_PICKER_ID1:

                // open datePicker dialog.
                // set date picker for current date
                // add pickerListener listener to date picker
                new DatePickerDialog(getContext() , pickerListener1, year, month,day).show();
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year1  = selectedYear;
            month1 = selectedMonth;
            day1   = selectedDay;

            // Show selected date

            editText_3.setText(new StringBuilder().append(day1).append("-")
                    .append(month1 + 1).append("-").append(year1)
                    .append(" "));

        }
    };

    private DatePickerDialog.OnDateSetListener pickerListener1 = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year2  = selectedYear;
            month2 = selectedMonth;
            day2   = selectedDay;

            // Show selected date

            editText_4.setText(new StringBuilder().append(day2)
                    .append("-").append(month2 + 1).append("-").append(year2)
                    .append(" "));

        }
    };


    // first calculation of total compounding period before firstYear
    private void startCalculation(){
        int year_diff = startYear - year1;

        if( (month1 + 1) > 3 && (month1 + 1) <=12 ){
            l = ( (month1 + 1) - 3 ) % 6;
        }else{
            l = ( (month1 + 1) + 9) % 6;
        }

        if(startYear > year1){
            if((month1 + 1) <= 9){
                k = 1;
                if(l != 0){
                    m = ((6 - l) * 30 +(AppConstant.constantDate - day1)) / (double)180 ;
                    k+=m;
                }else{
                    m = (AppConstant.constantDate - day1) / (double)180;
                    k += m;
                }
            }else{
                k = 0;
                m = ((6 - l) * 30 + (AppConstant.constantDate - day1)) / (double)180;
                k += m;
            }

            if(year_diff > 1){
                i = (year_diff - 1) * 2 + k;
            }else{
                i = k;
            }
        }else if(startYear == year1){
            if ((month1 + 1) <= 3) {
                if (l != 0) {
                    m = ((6 - l) * 30 + (AppConstant.constantDate - day1)) / (double) 180;
                } else {
                    m = (AppConstant.constantDate - day1) / (double) 180;
                }
                k = m;
                i = k;
            }else{
                if((month1 + 1) <= 9){
                    if(l != 0){
                        m = ((l * 30) + (day1 - AppConstant.constantDate)) / (double)180;
                        k = m;
                    }else{
                        m = ((6 * 30) + (day1 - AppConstant.constantDate)) / (double)180;
                        k = m;
                    }
                }else{
                    k = 1;
                    m = ((l * 30) + (day1 - AppConstant.constantDate)) / (double)180;
                    k += m;
                }
                i = -k;
            }
        }else{
            if(l != 0){
                m = ((l*30) + (day1 - AppConstant.constantDate)) / (double)180;
                k = 1 + m;
            }else{
                m = (day1 - AppConstant.constantDate) / (double)180;
                k = 2 + m;
            }
            i = -k;
        }
    }

    private void endCalculation(){
        if( (month2 + 1) > 3 && (month2 + 1) <=12 ){
            u = ( (month2 + 1) - 3 ) % 6;
            v = ( (month2 + 1) - 3 ) / 6;
        }else{
            u = ( (month2 + 1) + 9) % 6;
            v = ( (month2 + 1) + 9) / 6;
        }

        if(year2 > lastYear){
            t = 2;
        }else if(year2 == lastYear){
            if((month2 + 1) > 3){
                t = 2;
            }else{
                if(u != 0){
                    s = ((6 - u) * 30 + (AppConstant.constantDate - day2)) / (double)180;
                }else{
                    s = (AppConstant.constantDate - day2) / (double)180;
                }
                t = 2 - s;
            }
        }else{
            s = ((u * 30) + (day2 - AppConstant.constantDate)) / (double)180;
            t = v + s;
        }
    }

    public double compoundInterest(double principle,double rate, double t){
        double interest;
        interest = principle * Math.pow((1 + (rate / (double)200)), t);
        return interest;
    }

    private boolean validatePrincipleAmount() {
        if (editText_1.getText().toString().trim().isEmpty()) {
            editText_1.setError("Field Empty");
            editText_1.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            Toast.makeText(getContext(), "Add Principle Amount", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateRateOfInterest() {
        if (editText_2.getText().toString().trim().isEmpty()) {
            editText_2.setError("Field Empty");
            editText_2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            Toast.makeText(getContext(), "Add Rate Of Interest", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateOpeningDate() {
        if (editText_3.getText().toString().trim().isEmpty()) {
            editText_3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            Toast.makeText(getContext(), "Add Opening Date", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateMaturityDate() {
        if (editText_4.getText().toString().trim().isEmpty()) {
            editText_4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            Toast.makeText(getContext(), "Add Maturity Date ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateStartYear() {
        if (startYear == 0) {
            Toast.makeText(getContext(), "Add Start Year ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateLastYear() {
        if (lastYear == 0) {
            Toast.makeText(getContext(), "Add Last Year ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
