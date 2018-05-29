package me.edgeconsult.flappybird;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.Random;

public class PipeSprite {
    private Bitmap image;
    private final Random random = new Random();

    //display constants
    private int displayWidth;
    private int displayHeight;

    // physical constants
    private double posX, posY, posX1, posY1; // позиции для первого и второго столбиков
    private double velX = 1;
    private double velX1 = 1;

    public PipeSprite( Bitmap image, int displayWidth, int displayHeight ) {

        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        posX = displayWidth;
        posX1 = displayWidth + displayWidth/2;
        posY = random.nextInt((displayHeight)/2) + displayHeight/4;
        posY1 = random.nextInt((displayHeight)/2) + displayHeight/4;

    }

    public void update() {
        //st2 = SpriteBird.getSt();
        //if (st2 == 1 ) {
          //  velX = 0;
        //}
        if (posX < 0 - image.getWidth() ) {
            posX = displayWidth;
            posY = random.nextInt(displayHeight/2) + displayHeight/4;
        }
        if (posX1 < 0 - image.getWidth() ) {
            posX1 = displayWidth;
            posY1 = random.nextInt(displayHeight/2) + displayHeight/4;
        }
        posX = posX - velX;
        posX1 = posX1 - velX1;

    }

    public void Draw (Canvas canvas) {
        canvas.drawBitmap(image, (int)posX , (int)posY, null);
        canvas.save();
        canvas.rotate(180, (float)posX , (float) posY);
        canvas.drawBitmap(image, (int) posX - image.getWidth(), (int) posY + 70, null );
        canvas.restore();
        canvas.drawBitmap(image, (int) posX1, (int) posY1, null);
        canvas.save();
        canvas.rotate(180, (float)posX1 ,(float) posY1);
        canvas.drawBitmap(image, (int) posX1 - image.getWidth(), (int) posY1 + 70, null );
        canvas.restore();
    }
}
