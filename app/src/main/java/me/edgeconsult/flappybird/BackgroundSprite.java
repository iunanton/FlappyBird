package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BackgroundSprite {

    private Bitmap image;
    private int displayWidth;
    private int displayHeight;
    private int backgroundWidth, backgroundHeight;

    public BackgroundSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        backgroundWidth = image.getWidth() / 2;
        backgroundHeight = image.getHeight();
    }

    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, backgroundWidth, backgroundHeight);
        Rect dst = new Rect(0, 0, displayWidth, displayHeight);
        canvas.drawBitmap(image, src, dst, null);
    }
}
