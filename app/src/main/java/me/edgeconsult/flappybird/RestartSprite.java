package me.edgeconsult.flappybird;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class RestartSprite  implements GameObject {

private StartActivity startActivity;
    private Bitmap image;
    private double scale = 0.4;

    private int imageWidth, imageHeight;
    private int restartWidth, restartHeight;

    private int displayWidth;
    private int displayHeight;

    private double posX, posY;
    private double velY = 30;

    public RestartSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        imageWidth = image.getWidth();
        imageHeight = image.getHeight();

        restartWidth = (int) (scale * displayWidth);
        restartHeight = restartWidth * imageHeight / imageWidth;

        posX = displayWidth / 2;
        posY = displayHeight + restartHeight;

    }



    public void update() {
        posY -= velY;
        if (posY < displayHeight / 2 + displayHeight/4 + restartHeight) {
            velY = 0;
        }
    }

    public void draw(Canvas canvas) {
        int x = (int) posX;
        int y = (int) posY;
        Rect src = new Rect(0, 0, imageWidth, imageHeight);
        Rect dst = new Rect(x - restartWidth / 2,
                y - restartHeight / 2,
                x + restartWidth / 2,
                y + restartHeight / 2);
        canvas.drawBitmap(image, src, dst, null);
    }

public int getX() {
        return (int) posX;
    }

    public int getY() {
        return (int) posY;
    }

    public int getWidht() {
        return restartWidth;
    }

    public int getHeight() {
        return restartHeight;
    }
}

