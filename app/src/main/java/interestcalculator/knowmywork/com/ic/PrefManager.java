package interestcalculator.knowmywork.com.ic;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SVB on 04-02-2016.
 */
public class PrefManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "IC";

    // predefined date for calculation of interest
    public static final String KEY_CONSTANT_DATE = "constant_date";
    public static final String CONSTANT_DATE_STATE = "state";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

    }

    /**
     * set constant date
     * */
    public void setConstantDate(int date) {
        editor = pref.edit();

        editor.putInt(KEY_CONSTANT_DATE, date);

        // commit changes
        editor.apply();
    }

    public int getConstantDate() {
        return pref.getInt(KEY_CONSTANT_DATE, AppConstant.constantDate);
    }

    public void setStateChanged(boolean state){
        editor = pref.edit();

        editor.putBoolean(CONSTANT_DATE_STATE, state);

        editor.apply();
    }

    public boolean getStateChanged(){
        return pref.getBoolean(CONSTANT_DATE_STATE, false);
    }

}
