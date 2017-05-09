package interestcalculator.knowmywork.com.ic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;


public class ListViewFragment extends Fragment{
    RecyclerView rv;
    DbHandler db;
    List<Record> records;
    RecyclerViewAdapter adapter;
    NoViewAdapter adapter1;

    public ListViewFragment() {
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

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);
        rv = (RecyclerView)rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getBaseContext());
        rv.setLayoutManager(llm);

        db = new DbHandler(getActivity().getBaseContext());

        records = db.getAllRecords();


     if(records.size()>0) {
         Collections.reverse(records);
         adapter = new RecyclerViewAdapter(records);
         rv.setAdapter(adapter);
     }else{
         adapter1 = new NoViewAdapter();
         rv.setAdapter(adapter1);
     }

       return rootView;
    }

}
