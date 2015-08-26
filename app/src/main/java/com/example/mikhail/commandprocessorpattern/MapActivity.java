package com.example.mikhail.commandprocessorpattern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ListView;

import com.example.mikhail.commandprocessorpattern.adapters.FragmentPagerCustomAdapter;
import com.example.mikhail.commandprocessorpattern.handlers.MessageController;
import com.example.mikhail.commandprocessorpattern.interfaces.UpdateCallbackListener;
import com.example.mikhail.commandprocessorpattern.managers.RequestProcessor;
import com.example.mikhail.commandprocessorpattern.model.Place;
import com.example.mikhail.commandprocessorpattern.model.Result;
import com.example.mikhail.commandprocessorpattern.requests.PlacesRequest;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;


public class MapActivity extends FragmentActivity implements UpdateCallbackListener {

    private GoogleMap map_; // Might be null if Google Play services APK is not available.
    private ListView listView_;
    private RequestProcessor manager_ = new RequestProcessor(this);
    private MessageController handler_ = new MessageController(manager_);
    ViewPager vpPager;
    List<Place> places_;
    int selectedTrackId;
    FragmentPagerAdapter adapterViewPager;
    static Context context_;

    public void onUpdate(List<? extends Result> results){
        updateUI(results);
    }

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
         context_ = getApplicationContext();

        // Gets selected track id
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        selectedTrackId = extras.getInt("SelectedItemId", 0);

         PlacesRequest placesRequest = new PlacesRequest(handler_);
         manager_.execute(placesRequest,selectedTrackId);



        vpPager = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new FragmentPagerCustomAdapter(this,getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

         vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             Log.v("Page","onPageScrolled");

             }

             @Override
             public void onPageSelected(int position) {
                 Log.v("Page","onPageSelected");
                // showPlaces(position,places_);
             }

             @Override
             public void onPageScrollStateChanged(int state) {
                 Log.v("Page","onPageScrollStateChanged");
             }
         });

    }



    @Override
    public void onResume(){
        super.onResume();
    }





    public void updateUI(List<? extends Result> items) {
        places_ = (List<Place>) items;
        Log.v("Places",Integer.toString(items.size()));
        int index = vpPager.getCurrentItem();
        FragmentPagerCustomAdapter adapter = ((FragmentPagerCustomAdapter)vpPager.getAdapter());

        Fragment currentFragment = (Fragment) adapter.instantiateItem(vpPager,index);
        if ( currentFragment instanceof PlaceListFragment){
            Log.v("IN"," PlaceListFragmen");
            currentFragment = (PlaceListFragment)adapter.instantiateItem(vpPager,index);
            ((PlaceListFragment) currentFragment).updateUI(places_);
        }

        if (currentFragment instanceof TrackMapFragment) {
            Log.v("IN","TrackMapFragment");
        }


     //   PlaceListFragment placeListFragment = (PlaceListFragment) adapter.instantiateItem(vpPager,index);


        Context context = this;
        if (context!=null){
            Log.v("ACOntext","Not null");
        } else  Log.v("ACOntext","null");

      //  placeListFragment.updateUI(placesPreviews);
    }

    //  manager_.sendAsyncPlaceRequest(selectedTrackId);


//        List<Place> places= new ArrayList<Place>();
//
//        listView_ = (ListView) this.findViewById(R.id.places_list);
//
//        listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        map_.addMarker(new MarkerOptions().position(
//                                new LatLng(47.2096054, 38.9352142)).icon(
//                                BitmapDescriptorFactory.defaultMarker()));
//                        CameraUpdate center1 = CameraUpdateFactory.newLatLng(new LatLng(47.2096054, 38.9352142));
//                        CameraUpdate zoom1 = CameraUpdateFactory.zoomTo(16);
//                        map_.moveCamera(center1);
//                        map_.animateCamera(zoom1);
//
//                        break;
//                    case 1:
//                        map_.addMarker(new MarkerOptions().position(
//                                new LatLng(47.2105, 38.9338999)).icon(
//                                BitmapDescriptorFactory.defaultMarker()));
//                        CameraUpdate center2 = CameraUpdateFactory.newLatLng(new LatLng(47.2105, 38.9338999));
//                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
//
//
//                        map_.moveCamera(center2);
//                        map_.animateCamera(zoom);
//                        break;
//                }
//            }
//        });

}
