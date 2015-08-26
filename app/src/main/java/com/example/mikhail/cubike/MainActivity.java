package com.example.mikhail.cubike;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mikhail.cubike.adapters.TrackAdapter;
import com.example.mikhail.cubike.handlers.MessageController;
import com.example.mikhail.cubike.interfaces.UIactions;
import com.example.mikhail.cubike.interfaces.UpdateCallbackListener;
import com.example.mikhail.cubike.managers.RequestProcessor;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.model.Result;
import com.example.mikhail.cubike.model.Track;
import com.example.mikhail.cubike.requests.TracksRequest;

import java.util.List;


public class MainActivity extends FragmentActivity implements UpdateCallbackListener{

    private List<Preview> previews_;
    private  ListView listView_;
    private Context context_;
    private RequestProcessor processor_ = new RequestProcessor(this);
    private MessageController controller_ = new MessageController(processor_);
    private UpdateCallbackListener updateCallbackListener_;

    public void onUpdate(List<? extends Result> results){
        List<Track> tracks = (List<Track>) results;
        TrackAdapter trackAdapter = new TrackAdapter(this,tracks);
        listView_.setAdapter(trackAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_ = (ListView) this.findViewById(R.id.track_list);

        TracksRequest tracksRequest = new TracksRequest(controller_);
        processor_.execute(tracksRequest, 0);

        listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Position", Integer.toString(position));
                int itemId = previews_.get(position).getId_();

                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                intent.putExtra("SelectedItemId",itemId);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_update) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
