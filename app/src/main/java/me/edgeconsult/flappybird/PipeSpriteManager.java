package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class PipeSpriteManager implements GameObject {

    private Bitmap image;

    // display constants
    private int displayWidth;
    private int displayHeight;

    private ArrayList<PipeSprite> pipeSprites;

    private final Random random;

        public PipeSpriteManager(Bitmap image, int displayWidth, int displayHeight) {

        this.image = image;

        pipeSprites = new ArrayList<>();

        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        this.random = new Random();

        // generate first random item
        addRandomPipe();
    }

    private void addRandomPipe() {
        int x = random.nextInt(displayWidth / 2) + displayWidth + displayWidth / 2;
        int y = random.nextInt((displayHeight) / 2) + displayHeight / 4;
        pipeSprites.add(new PipeSprite(image, displayWidth, displayHeight, x, y));
    }

    public boolean intersect(Rect rect) {
        for (PipeSprite pipeSprite : pipeSprites) {
            if (pipeSprite.intersect(rect)) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        PipeSprite first = pipeSprites.get(0);
        if ((first.getX() + first.getWidht() / 2) < 0) {
            // remove hidden element
            pipeSprites.remove(0);
        }
        PipeSprite last = pipeSprites.get(pipeSprites.size() - 1);
        if ((last.getX() + last.getWidht() / 2) <= displayWidth) {
            // create new element
            addRandomPipe();
        }

        for (PipeSprite pipeSprite : pipeSprites) {
            pipeSprite.update();
        }
    }

    public void draw(Canvas canvas) {
        for (PipeSprite pipeSprite : pipeSprites) {
            pipeSprite.draw(canvas);
        }
    }
}
