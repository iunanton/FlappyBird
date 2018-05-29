package me.edgeconsult.flappybird;

import android.graphics.Canvas;

public interface GameObject {

    // calculate psysical equations here and prepare all coordinates
    public void update();

    // render object to canvas
    public void draw(Canvas canvas);
}
