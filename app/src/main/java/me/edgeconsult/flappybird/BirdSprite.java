package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BirdSprite implements GameObject{
    private Bitmap image;
    private double scale = 0.1; // 0.2

    private final int BMP_ROWS = 6;
    private final int BMP_COLUMNS = 3;
    private int imageWidth, imageHeight;
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
    private double accY = 3;  // 1
    private int flapY = 50; // 10

    public BirdSprite(Bitmap image, int displayWidth, int displayHeight) {
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        currentFrame = 0;

        imageWidth = image.getWidth()/BMP_COLUMNS;
        imageHeight = image.getHeight()/BMP_ROWS;
        birdWidth = (int) (scale * displayWidth);
        birdHeight = birdWidth * imageHeight / imageWidth;

        posX = displayWidth / 2;
        posY = displayHeight / 2;
    }

    public void flap() {
        velY -= flapY;
    }

    public void update() {
        currentFrame++;
        if (posY < displayHeight) {
            velY += accY - 0.1 * velY;
            posY += velY;
        } else {
            velY = 0;
            posY = 0;
        }
    }

    public void draw(Canvas canvas) {
        int x = (int) posX;
        int y = (int) posY;
        int srcX = currentFrame / flitterCoef % BMP_COLUMNS * imageWidth;
        int srcY = imageHeight;
        Rect src = new Rect(srcX, srcY, srcX + imageWidth, srcY + imageHeight);
        Rect dst = new Rect(x - birdWidth / 2,
                y - birdHeight / 2,
                x + birdWidth / 2,
                y + birdHeight / 2);
        canvas.drawBitmap(image, src, dst, null);
    }

    public int getWidth() { return this.birdWidth; }

    public int getHeight() { return this.birdHeight; }

    public int getX() { return (int) this.posX; }

    public int getY() { return (int) this.posY; }
}
