package me.edgeconsult.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

// TODO: добавить pipes

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Bitmap bgg;
    private BirdSprite birdSprite;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bgg = BitmapFactory.decodeResource(getResources(), R.drawable.bgg);
        birdSprite = new BirdSprite(BitmapFactory.decodeResource(getResources(), R.drawable.bird));

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
        birdSprite.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            // TODO: получить размеры дисплея и адаптировать фоновое изображение
            canvas.drawBitmap(bgg, 0, 0, null);
            birdSprite.draw(canvas);
        }
    }
}
