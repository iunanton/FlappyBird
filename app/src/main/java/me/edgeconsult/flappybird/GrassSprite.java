package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GrassSprite {

    private Bitmap image;
    private int grassWidth, grassHeight;

    //display constants
    private int displayWidth;
    private int displayHeight;

    private int x,y;

    public GrassSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        grassWidth = image.getWidth();
        grassHeight = image.getHeight();
        x = 0;
        y = displayHeight - grassHeight / 2;
    }

    public void update() {
        x -= 10;
        x %= grassWidth;
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i <= displayWidth/grassWidth + 1; i++)
            canvas.drawBitmap(image, x + i * grassWidth, y, null);
    }
}
