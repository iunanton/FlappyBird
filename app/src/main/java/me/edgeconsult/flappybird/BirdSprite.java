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

    // physical constants
    private double posX, posY;
    private double velY = 0;
    private double accY = 3;
    private int flapY = 50;

    public BirdSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        currentFrame = 0;
        birdWidth = image.getWidth()/BMP_COLUMNS;
        birdHeight = image.getHeight()/BMP_ROWS;

        posX = (displayWidth - birdWidth) / 2;
        posY = (displayHeight - birdHeight) / 2;
    }

    public void flap() {
        velY -= flapY;
    }

    public void update() {
        currentFrame++;
        velY += accY - 0.1 * velY;
        posY += velY;
    }

    public void draw(Canvas canvas) {
        int x = (int) posX;
        int y = (int) posY;
        int srcX = currentFrame / flitterCoef % BMP_COLUMNS * birdWidth;
        int srcY = birdHeight;
        Rect src = new Rect(srcX, srcY, srcX + birdWidth, srcY + birdHeight);
        Rect dst = new Rect(x, y, x + birdWidth, y + birdHeight);
        canvas.drawBitmap(image, src, dst, null);
    }

    public int getBirdWidth() {
        return this.birdWidth;
    }

    public int getBirdHeight() {
        return this.birdHeight;
    }

    public int getX() {
        return (int) this.posX;
    }

    public int getY() {
        return (int) this.posY;
    }
}
