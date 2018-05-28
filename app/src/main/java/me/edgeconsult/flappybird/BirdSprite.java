package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BirdSprite {
    private Bitmap image;
    private final int BMP_ROWS = 6;
    private final int BMP_COLUMNS = 3;
    private int targetFPS = 60;
    private int flitterCoef = 4;
    private int currentFrame;
    private int width, height;
    private int x,y;

    public BirdSprite(Bitmap image) {
        this.image = image;
        currentFrame = 0;
        width = image.getWidth()/BMP_COLUMNS;
        height = image.getHeight()/BMP_ROWS;
        x = 100;
        y = 100;
    }

    public void update() {
        currentFrame++;
        y++;
    }

    public void draw(Canvas canvas) {
        int srcX = currentFrame / flitterCoef % BMP_COLUMNS * width;
        int srcY = height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(image, src, dst, null);
    }
}
