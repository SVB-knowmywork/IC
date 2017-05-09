package interestcalculator.knowmywork.com.ic;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SVB on 30-01-2016.
 */
public class NoViewAdapter extends RecyclerView.Adapter<NoViewAdapter.NoViewHolder>{

    public class NoViewHolder extends RecyclerView.ViewHolder{
        CardView cv1;

        NoViewHolder(View itemView) {
            super(itemView);
            cv1 = (CardView)itemView.findViewById(R.id.cv1);
        }
    }
    NoViewAdapter() {

    }

    @Override
    public NoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.no_record_card_view, viewGroup, false);
        NoViewHolder rvh = new NoViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(NoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}