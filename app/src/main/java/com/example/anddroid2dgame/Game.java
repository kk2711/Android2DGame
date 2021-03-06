package com.example.anddroid2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;


/**
 * Game manager all objects in the game and is responsible for updating all states and redner all
 * objects to the screen
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private  Player player;
    private  GameLoop gameLoop;
    private Context context;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle touch event actions
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.setPositions((double) event.getX(), (double) event.getY());
                return  true;

            case MotionEvent.ACTION_MOVE:
                player.setPositions((double) event.getX(), (double) event.getY());
                return  true;
        }
        return super.onTouchEvent(event);
    }

    public Game(Context context) {
        super(context);

        // Ge surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;

         gameLoop = new GameLoop(this, surfaceHolder);

         // Initialize player

        player = new Player(getContext(), 2 * 500, 2 * 500, 30);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
                gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            gameLoop.startLoop();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        player.draw(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context,R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context,R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {
        // Update game state

        player.update();


    }


}
