package me.edgeconsult.flappybird;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.Random;

public class PipeSprite implements GameObject {
    private Bitmap image;
    private double scale = 0.2; // scale pipe size
    private int imageWidth, imageHeight;
    private int pipeWidth, pipeHeight;
    //private int stop;

    //display constants
    private int displayWidth;
    private int displayHeight;

    // physical constants
    private int x, y;
    private double velX = 10;

    // gap value between pipes
    private int gap = 300;

    public PipeSprite(Bitmap image, int displayWidth, int displayHeight) {
        // TODO: масштабировать стоблик для корректного отображения
        this.image = image;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        pipeWidth = (int) (scale * displayWidth);
        pipeHeight = (int) (pipeWidth * imageHeight / imageWidth);
        gap = pipeWidth;
    }

    public boolean intersect(Rect rect) {
        Rect top = new Rect(x - pipeWidth / 2,
                y - gap / 2 - pipeHeight,
                x + pipeWidth / 2,
                y - gap / 2);
        Rect bottom = new Rect(x - pipeWidth / 2,
                y + gap / 2,
                x + pipeWidth / 2,
                y + gap / 2 + pipeHeight);
        return rect.intersect(top) || rect.intersect(bottom);
    }

    public void update() {
        x -= velX;
    }

    public void draw (Canvas canvas) {
        Rect src = new Rect(0, 0, imageWidth, imageHeight);
        Rect dst = new Rect(
                x - pipeWidth / 2,
                y + gap / 2,
                x + pipeWidth / 2,
                y + gap / 2 + pipeHeight);
        canvas.drawBitmap(image, src, dst, null);
        canvas.save();
        canvas.rotate(180, x, y);
        dst.set(
                x - pipeWidth / 2,
                y + gap / 2,
                x + pipeWidth / 2,
                y + gap / 2 + pipeHeight);
        canvas.drawBitmap(image, src, dst, null);
        canvas.restore();
    }

    public int getWidht() {
        return this.pipeWidth;
    }

    public int getHeight() {
        return this.pipeHeight;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
