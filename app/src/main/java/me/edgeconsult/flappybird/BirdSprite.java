package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BirdSprite {
    private Bitmap image;
    private final int BMP_ROWS = 6;
    private final int BMP_COLUMNS = 3;
    private int birdWidth, birdHeight;

    private int targetFPS = 60;
    private int flitterCoef = 4;
    private int currentFrame;

    // TODO: получить в переменные значения ширины и высоты экрана
    // TODO: вынести эти переменные в GameView класс
    //display constants
    private int screenWidth = 1400;
    private int screenHeight = 3200;

    // TODO: Привести переменные к типу double
    // TODO: Оформить эффект гравитации, увеличение скорости
    // physical constants
    private int x, y;
    private int flapY = 200;

    public BirdSprite(Bitmap image) {
        this.image = image;
        currentFrame = 0;
        birdWidth = image.getWidth()/BMP_COLUMNS;
        birdHeight = image.getHeight()/BMP_ROWS;

        x = screenWidth / 2;
        y = screenHeight / 2;
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
