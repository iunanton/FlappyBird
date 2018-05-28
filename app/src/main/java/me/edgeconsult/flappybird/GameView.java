package me.edgeconsult.flappybird;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

// TODO: добавить pipes

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private BackgroundSprite backgroundSprite;
    private GrassSprite grassSprite;
    private BirdSprite birdSprite;
    private Paint paint;
    private int displayWidth;
    private int displayHeight;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(30);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayWidth = displayMetrics.widthPixels;
        displayHeight = displayMetrics.heightPixels;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        backgroundSprite = new BackgroundSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.bgg),
                displayWidth, displayHeight);
        grassSprite = new GrassSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.grass),
                displayWidth, displayHeight);
        birdSprite = new BirdSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.bird),
                displayWidth, displayHeight);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            performClick(); // для обратной совместимости мы должны вызвать метод performClick
        }
        return true;
    }

    // здесь производим обработку нажатия
    @Override
    public boolean performClick() {
        super.performClick();
        birdSprite.flap();
        return true;
    }

    public void update() {
        grassSprite.update();
        birdSprite.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            backgroundSprite.draw(canvas);
            grassSprite.draw(canvas);
            canvas.drawText("FPS: " + thread.getAverageFPS(), 50,50, paint);
            canvas.drawText("DW: " + displayWidth, 50,100, paint);
            canvas.drawText("DH: " + displayHeight, 50,150, paint);
            birdSprite.draw(canvas);
        }
    }
}
