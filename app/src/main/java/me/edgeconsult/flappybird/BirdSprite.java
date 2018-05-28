package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BirdSprite {
    private Bitmap image;

    private final int BMP_ROWS = 6;
    private final int BMP_COLUMNS = 3;
    private int birdWidth, birdHeight;

    private int targetFPS = 30;
    private int flitterCoef = 2;
    private int currentFrame;

    //display constants
    private int displayWidth;
    private int displayHeight;

    // TODO: Привести переменные к типу double
    // TODO: Оформить эффект гравитации, увеличение скорости
    // physical constants
    private int x, y;
    private int flapY = 200;

    public BirdSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        currentFrame = 0;
        birdWidth = image.getWidth()/BMP_COLUMNS;
        birdHeight = image.getHeight()/BMP_ROWS;

        x = (displayWidth - birdWidth) / 2;
        y = (displayHeight - birdHeight) / 2;
    }

    public void flap() {
        y -= flapY;
    }

    public void update() {
        currentFrame++;
        y += 2;
    }

    public void draw(Canvas canvas) {
        int srcX = currentFrame / flitterCoef % BMP_COLUMNS * birdWidth;
        int srcY = birdHeight;
        Rect src = new Rect(srcX, srcY, srcX + birdWidth, srcY + birdHeight);
        Rect dst = new Rect(x, y, x + birdWidth, y + birdHeight);
        canvas.drawBitmap(image, src, dst, null);
    }
}
