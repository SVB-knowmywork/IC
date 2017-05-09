package interestcalculator.knowmywork.com.ic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SVB on 05-01-2016.
 */
public class DbHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "InterestRecord";

    // Records table name
    private static final String TABLE_RECORDS = "Records";

    // Records Table Columns names
    private static final String KEY_PRINCIPLE_AMOUNT = "principle_amount";
    private static final String KEY_RATE_OF_INTEREST = "rate_of_interest";
    private static final String KEY_DAY_1 = "day_1";
    private static final String KEY_DAY_2 = "day_2";
    private static final String KEY_MONTH_1= "month_1";
    private static final String KEY_MONTH_2= "month_2";
    private static final String KEY_YEAR_1 = "year_1";
    private static final String KEY_YEAR_2= "year_2";
    private static final String KEY_START_YEAR = "start_year";
    private static final String KEY_LAST_YEAR = "last_year";
    private static final String KEY_RESULT = "result";
    private static final String KEY_TYPE = "type";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORDS_TABLE = "CREATE TABLE " + TABLE_RECORDS + "("
                + KEY_PRINCIPLE_AMOUNT + " TEXT," + KEY_RATE_OF_INTEREST + " TEXT,"
                + KEY_DAY_1 + " INTEGER," + KEY_DAY_2 + " INTEGER," + KEY_MONTH_1 + " INTEGER,"
                + KEY_MONTH_2 + " INTEGER," + KEY_YEAR_1 + " INTEGER," + KEY_YEAR_2 + " INTEGER,"
                + KEY_START_YEAR + " INTEGER," + KEY_LAST_YEAR + " INTEGER," + KEY_RESULT + " REAL,"
                + KEY_TYPE + " TEXT" + ")";
        db.execSQL(CREATE_RECORDS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);

        // Create tables again
        onCreate(db);
    }

    public void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRINCIPLE_AMOUNT, record.getPrincipleAmount());
        values.put(KEY_RATE_OF_INTEREST, record.getRateOfInterest());
        values.put(KEY_DAY_1, record.getDay1());
        values.put(KEY_DAY_2, record.getDay2());
        values.put(KEY_MONTH_1, record.getMonth1());
        values.put(KEY_MONTH_2, record.getMonth2());
        values.put(KEY_YEAR_1, record.getYear1());
        values.put(KEY_YEAR_2, record.getYear2());
        values.put(KEY_START_YEAR, record.getStartYear());
        values.put(KEY_LAST_YEAR, record.getLastYear());
        values.put(KEY_RESULT, record.getResult());
        values.put(KEY_TYPE, record.getType());

        db.insert(TABLE_RECORDS, null, values);
        db.close(); // Closing database connection
    }

    public List<Record> getAllRecords() {
        List<Record> recordList = new ArrayList<Record>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RECORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setPrincipleAmount(cursor.getString(0));
                record.setRateOfInterest(cursor.getString(1));
                record.setDay1(Integer.parseInt(cursor.getString(2)));
                record.setDay2(Integer.parseInt(cursor.getString(3)));
                record.setMonth1(Integer.parseInt(cursor.getString(4)));
                record.setMonth2(Integer.parseInt(cursor.getString(5)));
                record.setYear1(Integer.parseInt(cursor.getString(6)));
                record.setYear2(Integer.parseInt(cursor.getString(7)));
                record.setStartYear(Integer.parseInt(cursor.getString(8)));
                record.setLastYear(Integer.parseInt(cursor.getString(9)));
                record.setResult(Double.parseDouble(cursor.getString(10)));
                record.setType(cursor.getString(11));
                // Adding contact to list
                recordList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return record list
        return recordList;
    }

    public void deleteRecord(String principleAmount, String rateOfInterest, int day1, int day2, int month1, int month2, int year1, int year2,
                             int startYear, int lastYear, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE_RECORD = "DELETE FROM " + TABLE_RECORDS + " " + "WHERE " + KEY_PRINCIPLE_AMOUNT + " = " + principleAmount + " " + "AND " +
                KEY_RATE_OF_INTEREST + " = " + rateOfInterest + " " + "AND " +  KEY_DAY_1 + " = " + day1 + " " + "AND " +
                KEY_DAY_2 + " = " + day2 + " " + "AND " + KEY_MONTH_1 + " = " + month1 + " " + "AND " +
                KEY_MONTH_2 + " = " + month2 + " " + "AND " + KEY_YEAR_1 + " = " + year1 + " " + "AND " +
                KEY_YEAR_2 + " = " + year2 + " " + "AND " + KEY_START_YEAR + " = " + startYear + " " + "AND " +
                KEY_LAST_YEAR + " = " + lastYear + " " + "AND " + KEY_TYPE + " = '" + type + "'";

        db.execSQL(DELETE_RECORD);
        db.close(); // Closing Database Connection
    }
}
