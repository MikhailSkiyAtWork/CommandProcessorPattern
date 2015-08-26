package com.example.mikhail.cubike.managers;

import com.example.mikhail.cubike.database.DatabaseHelper;
import com.example.mikhail.cubike.interfaces.UIactions;
import com.example.mikhail.cubike.interfaces.UpdateCallbackListener;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.model.Result;
import com.example.mikhail.cubike.model.Track;
import com.example.mikhail.cubike.requests.CommonRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mikhail on 02.08.2015.
 */
public class RequestProcessor {

    private UpdateCallbackListener clientActivity_;

    public RequestProcessor(UpdateCallbackListener clienActivity) {
        this.clientActivity_ = clienActivity;
    }

    public void execute(CommonRequest request, int id) {
        request.sendRequest(id);
    }

    public void updateActivity(List<? extends Result> results) {
        clientActivity_.onUpdate(results);
    }
}
