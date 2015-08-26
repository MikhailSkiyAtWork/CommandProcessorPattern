package com.example.mikhail.cubike.handlers;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.mikhail.cubike.helpers.States;
import com.example.mikhail.cubike.interfaces.UIactions;
import com.example.mikhail.cubike.managers.RequestProcessor;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.model.Track;

import java.util.List;

/**
 * Created by Mikhail on 02.08.2015.
 */
public class MessageController extends Handler {

    private RequestProcessor processor_;

    public MessageController(RequestProcessor manager) {
        this.processor_ = manager;
    }

    public void handleMessage(Message msg) {
        switch (msg.what) {
            case States.TRACKS_WERE_FOUND:
                if (msg.obj != null) {
                    List<Track> tracks = (List<Track>) msg.obj;
                    processor_.updateActivity(tracks);
                }
                break;

            case States.PLACES_WERE_FOUND:
                Log.v("STATUS", "PLACES_WERE_FOUND");
                if (msg.obj != null) {
                    List<Place> places = (List<Place>) msg.obj;
                    Log.v("Places", Integer.toString(places.size()));

                    processor_.updateActivity(places);
                }
                break;

            default:
                break;
        }
    }
}
