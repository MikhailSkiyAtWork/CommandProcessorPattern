package com.example.mikhail.commandprocessorpattern;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mikhail.commandprocessorpattern.adapters.PlacesAdapter;
import com.example.mikhail.commandprocessorpattern.interfaces.PlaceFragmentListener;
import com.example.mikhail.commandprocessorpattern.managers.RequestProcessor;
import com.example.mikhail.commandprocessorpattern.model.Place;
import com.example.mikhail.commandprocessorpattern.model.Preview;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;


public class PlaceListFragment extends Fragment {


    // private OnFragmentInteractionListener mListener;
    private ListView listView_;
    private SupportMapFragment fragment;
    private PlaceFragmentListener listener;
    private PlaceFragmentListener mCallback;
    PlacesAdapter placesAdapter;
    RequestProcessor manager_ ;
    List<Preview> previews_;

    View view_;

    public static int Value = 3;


    public static PlaceListFragment newInstance(int index) {
        Log.v("Action", "PlaceListFragment newInstance");
        PlaceListFragment f = new PlaceListFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view_ = inflater.inflate(R.layout.fragment_place_list, container,false);
        Log.v("Action", "PlaceListFragment onCreateView");
        return view_;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void updateUI(List<Place> placesPreviews) {
        Context context = this.getActivity();
        if (context!=null){
            Log.v("COntext","Not null");
        } else  Log.v("COntext","null");

        placesAdapter = new PlacesAdapter(this.getActivity(), placesPreviews);

        listView_ = (ListView) view_.findViewById(R.id.placeListView);
        listView_.setAdapter(placesAdapter);



        listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "First item was clicked", Toast.LENGTH_LONG).show();

                        break;
                    case 1:
                        Toast.makeText(getActivity(), "2 item was clicked", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }
}
