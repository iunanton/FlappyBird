package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class PipeSpriteManager implements GameObject {
    //display constants
    private int displayWidth;
    private int displayHeight;

    // здесь храним все столбики, которые доступны для вывода на экран
    // TODO: завести список для хранения всех столбиков
    private PipeSprite pipeSprite1;
    private PipeSprite pipeSprite2;

    private final Random random;

    // TODO: сделать конструктор класса
    public PipeSpriteManager(Bitmap image, int displayWidth, int displayHeight) {

        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        this.random = new Random();

        int x1 = displayWidth;
        int x2 = displayWidth + displayWidth/2;
        int y1 = random.nextInt((displayHeight)/2) + displayHeight/4;
        int y2 = random.nextInt((displayHeight)/2) + displayHeight/4;

        pipeSprite1 = new PipeSprite(image, displayWidth, displayHeight);
        pipeSprite1.setX(x1);
        pipeSprite1.setY(y1);
        pipeSprite2 = new PipeSprite(image, displayWidth, displayHeight);
        pipeSprite2.setX(x2);
        pipeSprite2.setY(y2);
    }

    public boolean intersect(Rect rect) {
        return pipeSprite1.intersect(rect) || pipeSprite2.intersect(rect);
    }

    // здесь проверяем, какие из уже созданных столбиков вышли из области выдимости
    // и удаляем их из памяти
    // в случае удаления создаем новый столбик с рандомными координантами

    // создавать новые столбики через копирование объектов
    // не нужно снова обращаться к файлам с картинкой
    // TODO: сделать метод айпдэйт
    public void update() {
        if (pipeSprite1.getX() < 0 - pipeSprite1.getWidht() ) {
            pipeSprite1.setX(displayWidth);
            pipeSprite1.setY(random.nextInt(displayHeight/2) + displayHeight/4);
        }
        if (pipeSprite2.getX() < 0 - pipeSprite2.getWidht() ) {
            pipeSprite2.setX(displayWidth);
            pipeSprite2.setY(random.nextInt(displayHeight/2) + displayHeight/4);
        }

        pipeSprite1.update();
        pipeSprite2.update();
    }

    // прорисовка всех столбов в цикле
    // просто вызываем в теле цикла метод pipeStripe.draw()
    // TODO: сделать метод драу
    public void draw(Canvas canvas) {
        pipeSprite1.draw(canvas);
        pipeSprite2.draw(canvas);
    }

    // TODO: сделать геттеры координат столбиков для определения столкновений с птицей
}
