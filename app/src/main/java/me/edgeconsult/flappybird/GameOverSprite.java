package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class GameOverSprite implements GameObject {

    private Bitmap image;
    private double scale = 0.8;

    private int imageWidth, imageHeight;
    private int gameoverWidth, gameoverHeight;

    private int displayWidth;
    private int displayHeight;

    private double posX, posY;
    private double velY = 30;

    public GameOverSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        imageWidth = image.getWidth();
        imageHeight = image.getHeight();

        gameoverWidth = (int) (scale * displayWidth);
        gameoverHeight = gameoverWidth * imageHeight / imageWidth;

        posX = displayWidth / 2;
        posY = - gameoverHeight;

    }

    public void update() {
        posY += velY;
        if (posY > displayHeight / 2 - displayHeight/16 - gameoverHeight) {
            velY = 0;
        }
    }

    public void draw(Canvas canvas) {
        int x = (int) posX;
        int y = (int) posY;
        Rect src = new Rect(0, 0, imageWidth, imageHeight);
        Rect dst = new Rect(x - gameoverWidth / 2,
                y - gameoverHeight / 2,
                x + gameoverWidth / 2,
                y + gameoverHeight / 2);
        canvas.drawBitmap(image, src, dst, null);
    }


}
