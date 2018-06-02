package me.edgeconsult.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.media.AudioManager;
import android.media.SoundPool;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private BackgroundSprite backgroundSprite;
    private GrassSprite grassSprite;
    private BirdSprite birdSprite;
    private PipeSpriteManager pipeSpriteManager;
    private GameOverSprite gameOverSprite;
    private RestartSprite restartSprite;
    private MainActivity mainActivity;
    private Paint paint;
    private int displayWidth;
    private int displayHeight;

    private SoundPool sounds;
    private int sExplosionFlap;
    private int sExplosionGameOver;

    private boolean gameover = false;

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

        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        sExplosionFlap = sounds.load(context, R.raw.sfx_wing, 1);
        sExplosionGameOver = sounds.load(context, R.raw.sfx_die, 1);
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
        pipeSpriteManager = new PipeSpriteManager(
                BitmapFactory.decodeResource(getResources(), R.drawable.pipe),
                displayWidth, displayHeight);
        gameOverSprite = new GameOverSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.die),
                displayWidth, displayHeight);
        restartSprite = new RestartSprite(
                BitmapFactory.decodeResource(getResources(), R.drawable.restart),
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
            performClick();
            if(!gameover) {
                sounds.play(sExplosionFlap, 1.0f, 1.0f, 0, 0, 1.5f);
            }

            if (gameover) {
                //Log.d("Event", "X: " + event.getX() + " Y: " + event.getY());
                if (restartSprite.contains((int)event.getX(), (int)event.getY())) {
                    //Log.d("Event", "Match!");
                    backgroundSprite = new BackgroundSprite(
                            BitmapFactory.decodeResource(getResources(), R.drawable.bgg),
                            displayWidth, displayHeight);
                    grassSprite = new GrassSprite(
                            BitmapFactory.decodeResource(getResources(), R.drawable.grass),
                            displayWidth, displayHeight);
                    birdSprite = new BirdSprite(
                            BitmapFactory.decodeResource(getResources(), R.drawable.bird),
                            displayWidth, displayHeight);
                    pipeSpriteManager = new PipeSpriteManager(
                            BitmapFactory.decodeResource(getResources(), R.drawable.pipe),
                            displayWidth, displayHeight);
                    gameOverSprite = new GameOverSprite(
                            BitmapFactory.decodeResource(getResources(), R.drawable.die),
                            displayWidth, displayHeight);
                    restartSprite = new RestartSprite(
                            BitmapFactory.decodeResource(getResources(), R.drawable.restart),
                            displayWidth, displayHeight);
                    gameover = false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean performClick() {
        if (!gameover) {
            super.performClick();
            birdSprite.flap();
        }
        return true;
    }

    public void isCollision () {
        if (!gameover) {
            Rect rect = new Rect(
                    birdSprite.getX() - birdSprite.getWidth() / 2,
                    birdSprite.getY() - birdSprite.getHeight() / 2,
                    birdSprite.getX() + birdSprite.getWidth() / 2,
                    birdSprite.getY() + birdSprite.getHeight() / 2);
            if (pipeSpriteManager.intersect(rect) ||
                    (birdSprite.getY() + birdSprite.getHeight() / 2) > backgroundSprite.getHeight()) {
                gameover = true;
                sounds.play(
                        sExplosionGameOver,
                        1.0f,
                        1.0f,
                        0,
                        0,
                        1.5f);
            }
        }
    }

    public void update() {
        isCollision();
        if (!gameover) {
            grassSprite.update();
            birdSprite.update();
            pipeSpriteManager.update();
        } else {
            gameOverSprite.update();
            restartSprite.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            backgroundSprite.draw(canvas);
            pipeSpriteManager.draw(canvas);
            grassSprite.draw(canvas);
            canvas.drawText("FPS: " + thread.getAverageFPS(), 50,50, paint);
            canvas.drawText("DW: " + displayWidth, 50,100, paint);
            canvas.drawText("DH: " + displayHeight, 50,150, paint);
            canvas.drawText("PX: " + birdSprite.getX(), 50,200, paint);
            canvas.drawText("PY: " + birdSprite.getY(), 50,250, paint);
            birdSprite.draw(canvas);
            gameOverSprite.draw(canvas);
            restartSprite.draw(canvas);
        }
    }
}
