package interestcalculator.knowmywork.com.ic;

/**
 * Created by SVB on 05-01-2016.
 */
public class Record {
    //private variables
    String principle_amount;
    String rate_of_interest;
    String type;
    int day_1;
    int day_2;

    int month_1;
    int month_2;
    int year_1;
    int year_2;
    int start_year;
    int last_year;
    double result;

    // Empty constructor
    public Record() {

    }

    // constructor
    public Record(String principle_amount, String rate_of_interest, int day_1, int day_2,
                  int month_1, int month_2, int year1, int year2, int start_year, int last_year,
                  double result, String type) {
        this.principle_amount = principle_amount;
        this.rate_of_interest = rate_of_interest;
        this.day_1 = day_1;
        this.day_2 = day_2;
        this.month_1 = month_1;
        this.month_2 = month_2;
        this.year_1 = year1;
        this.year_2 = year2;
        this.start_year = start_year;
        this.last_year = last_year;
        this.result = result;
        this.type = type;
    }

    // getting PRINCIPLE_AMOUNT
    public String getPrincipleAmount(){
        return this.principle_amount;
    }

    // getting RATE_OF_INTEREST
    public String getRateOfInterest(){
        return this.rate_of_interest;
    }

    // getting DAY_1
    public int getDay1(){
        return this.day_1;
    }

    // getting DAY_2
    public int getDay2(){
        return this.day_2;
    }

    // getting MONTH_1
    public int getMonth1(){
        return this.month_1;
    }

    // getting MONTH_2
    public int getMonth2(){
        return this.month_2;
    }

    // getting YEAR_1
    public int getYear1(){
        return this.year_1;
    }

    // getting YEAR_2
    public int getYear2(){
        return this.year_2;
    }

    // getting START_YEAR
    public int getStartYear(){
        return this.start_year;
    }

    // getting LAST_YEAR
    public int getLastYear(){
        return this.last_year;
    }

    // getting RESULT
    public double getResult(){
        return this.result;
    }

    // getting TYPE
    public String getType(){
        return this.type;
    }


    /*-----------------Setting values----------------*/


    // setting PRINCIPLE_AMOUNT
    public void setPrincipleAmount(String PrincipleAmount){
        this.principle_amount = PrincipleAmount;
    }

    // setting RATE_OF_INTEREST
    public void setRateOfInterest(String RateOfInterest){
        this.rate_of_interest = RateOfInterest;
    }

    // setting DAY_1
    public void setDay1(int Day1){
        this.day_1 = Day1;
    }

    // setting DAY_2
    public void setDay2(int Day2){
        this.day_2 = Day2;
    }

    // setting MONTH_1
    public void setMonth1(int Month1){
        this.month_1 = Month1;
    }

    // setting MONTH_2
    public void setMonth2(int Month2){
        this.month_2 = Month2;
    }

    // setting YEAR_1
    public void setYear1(int Year1){
        this.year_1 = Year1;
    }

    // setting YEAR_2
    public void setYear2(int Year2){
        this.year_2 = Year2;
    }

    // setting START_YEAR
    public void setStartYear(int StartYear){
        this.start_year = StartYear;
    }

    // setting LAST_YEAR
    public void setLastYear(int LastYear){
        this.last_year = LastYear;
    }

    // setting RESULT
    public void setResult(double Result){
        this.result = Result;
    }

    // setting PRINCIPLE_AMOUNT
    public void setType(String Type){
        this.type = Type;
    }
}
