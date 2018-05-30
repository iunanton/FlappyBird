package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class GameOverSprite implements GameObject {

    private Bitmap image;

    private int gameoverWidth, gameoverdHeight;

    private int displayWidth;
    private int displayHeight;

    private double posX, posY;
    private double velY = 10;

    public GameOverSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        gameoverWidth = image.getWidth();
        gameoverdHeight = image.getHeight();


        posX = displayWidth / 2 - gameoverWidth / 2;
        posY =  - gameoverdHeight;

    }

    public void update() {

        posY +=  velY;

        if (posY - gameoverdHeight > displayHeight/2 - displayHeight/4 ) {
            velY = 0;
        }

    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(image, (int) posX, (int) posY, null);
    }


}
