package interestcalculator.knowmywork.com.ic;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SVB on 06-01-2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecordViewHolder>{
    private static final String TABLE_RECORDS = "Records";
    DbHandler db;
    public class RecordViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        CardView cv;
        TextView principleAmount;
        TextView rateOfInterest;
        TextView openingDate;
        TextView maturityDate;
        TextView calculationYear;
        TextView result;
        TextView type;


        RecordViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            principleAmount = (TextView)itemView.findViewById(R.id.TextView_3);
            rateOfInterest = (TextView)itemView.findViewById(R.id.TextView_6);
            openingDate = (TextView)itemView.findViewById(R.id.TextView_9);
            maturityDate = (TextView)itemView.findViewById(R.id.TextView_12);
            calculationYear = (TextView)itemView.findViewById(R.id.TextView_15);
            result = (TextView)itemView.findViewById(R.id.TextView_18);
            type = (TextView)itemView.findViewById(R.id.TextView_20);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            String principleAmount = records.get(getAdapterPosition()).getPrincipleAmount();
            String rateOfInterest = records.get(getAdapterPosition()).getRateOfInterest();
            int day1 = records.get(getAdapterPosition()).getDay1();
            int day2 = records.get(getAdapterPosition()).getDay2();
            int month1 = records.get(getAdapterPosition()).getMonth1();
            int month2 = records.get(getAdapterPosition()).getMonth2();
            int year1 = records.get(getAdapterPosition()).getYear1();
            int year2 = records.get(getAdapterPosition()).getYear2();
            int startYear = records.get(getAdapterPosition()).getStartYear();
            int lastYear = records.get(getAdapterPosition()).getLastYear();
            String type = records.get(getAdapterPosition()).getType();


            db = new DbHandler(view.getContext());
            db.deleteRecord(principleAmount, rateOfInterest, day1, day2, month1, month2, year1, year2, startYear, lastYear, type);

            delete(getAdapterPosition());
            return true;
        }
    }

    List<Record> records;

    RecyclerViewAdapter(List<Record> records){
        this.records = records;
    }


    public void delete(int position){
        records.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.record_card_view, viewGroup, false);
        RecordViewHolder rvh = new RecordViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RecordViewHolder recordViewHolder, int i) {
        recordViewHolder.principleAmount.setText(records.get(i).getPrincipleAmount());
        recordViewHolder.rateOfInterest.setText(records.get(i).getRateOfInterest());
        recordViewHolder.openingDate.setText(new StringBuilder().append(records.get(i).day_1).append("-")
                .append(records.get(i).month_1 + 1).append("-").append(records.get(i).year_1).append(" "));
        recordViewHolder.maturityDate.setText(new StringBuilder().append(records.get(i).day_2).append("-")
                .append(records.get(i).month_2 + 1).append("-").append(records.get(i).year_2).append(" "));
        recordViewHolder.calculationYear.setText(new StringBuilder().append(records.get(i).start_year).append("-")
                .append(records.get(i).last_year).append(" "));

        recordViewHolder.result.setText(Double.toString(records.get(i).getResult()));

        recordViewHolder.type.setText(records.get(i).getType());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}



