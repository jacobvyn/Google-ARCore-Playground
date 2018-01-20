package com.arcore.example.arcoremanager.drawer;

import android.content.Context;

import com.arcore.example.core.ARCanvas;

public interface Drawer {

    void prepare(Context context);

    void onDraw(ARCanvas arCanvas);
}
