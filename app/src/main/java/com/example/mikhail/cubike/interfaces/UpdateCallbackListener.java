package com.example.mikhail.cubike.interfaces;

import com.example.mikhail.cubike.model.Result;

import java.util.List;

/**
 * Created by Mikhail Valuyskiy on 25.08.2015.
 */
public interface UpdateCallbackListener {
    void onUpdate(List<? extends Result> results);
}
