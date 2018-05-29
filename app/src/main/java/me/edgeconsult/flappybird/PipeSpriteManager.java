package me.edgeconsult.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class PipeSpriteManager implements GameObject {
    // здесь храним все столбики, которые доступны для вывода на экран
    // TODO: завести список для хранения всех столбиков
    private PipeSprite pipeSprite;

    // TODO: сделать конструктор класса
    public PipeSpriteManager(Bitmap image, int displayWidth, int displayHeight) {
        pipeSprite = new PipeSprite(image, displayWidth, displayHeight);
    }

    // здесь проверяем, какие из уже созданных столбиков вышли из области выдимости
    // и удаляем их из памяти
    // в случае удаления создаем новый столбик с рандомными координантами

    // создавать новые столбики через копирование объектов
    // не нужно снова обращаться к файлам с картинкой
    // TODO: сделать метод айпдэйт
    public void update() {
        pipeSprite.update();
    }

    // прорисовка всех столбов в цикле
    // просто вызываем в теле цикла метод pipeStripe.draw()
    // TODO: сделать метод драу
    public void draw(Canvas canvas) {
        pipeSprite.draw(canvas);
    }

    // TODO: сделать геттеры координат столбиков для определения столкновений с птицей
}
