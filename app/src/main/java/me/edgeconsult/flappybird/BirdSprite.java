package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BirdSprite implements GameObject{
    private Bitmap image;
    private Bitmap image1;
    private double scale = 0.2;
    private double widht;
    private double height;
    private double widht1;
    private double height1;


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
    private int flapY = 20;

    public BirdSprite(Bitmap image, int displayWidth, int displayHeight) {
        // TODO: масштабировать птицу для корректного отображения
        this.image = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        currentFrame = 0;
        widht = scale*displayWidth;
        height = scale*displayHeight;
        birdWidth = (int) widht;
        birdHeight = (int) height;

        posX = (displayWidth - birdWidth) / 2;
        posY = (displayHeight - birdHeight) / 2;
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
        int srcX = currentFrame / flitterCoef % BMP_COLUMNS * birdWidth;
        int srcY = birdHeight;
        Rect src = new Rect(srcX, srcY, srcX + birdWidth, srcY + birdHeight);
        Rect dst = new Rect(x - (birdWidth / 2),
                y - (birdHeight / 2),
                x + (birdWidth / 2),
                y + (birdHeight / 2));

        widht1 = displayWidth*1.2;
        height1 = displayHeight*1.1;
        Bitmap image1 = Bitmap.createScaledBitmap(image, (int) widht1, (int) height1, true);
        canvas.drawBitmap(image1, src, dst, null);
    }

    public int getWidth() { return this.birdWidth; }

    public int getHeight() { return this.birdHeight; }

    public int getX() { return (int) this.posX; }

    public int getY() { return (int) this.posY; }
}
