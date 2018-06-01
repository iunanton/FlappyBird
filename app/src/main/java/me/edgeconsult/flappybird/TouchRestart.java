package me.edgeconsult.flappybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


public class TouchRestart extends AppCompatActivity {

    public void restart () {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}