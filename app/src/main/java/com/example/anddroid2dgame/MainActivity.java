package com.example.anddroid2dgame;

import android.app.Activity;
import android.os.Bundle;

/**
 *
 * MainActivity is entry point to our application;
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set window to fullscreen (will hide status bar)


        // set contain view to game, so that objects to the game class be rendered to screen
        setContentView(new Game(this));
    }
}