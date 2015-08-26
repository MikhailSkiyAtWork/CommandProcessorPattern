package com.example.mikhail.cubike.interfaces;

import android.content.Context;

import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.model.Result;

import java.util.List;

/**
 * Created by Mikhail on 02.08.2015.
 */
public interface UIactions {
    public void updateUI(List<? extends Result>items);
    public Context getClientActivityContext();
}
