package me.edgeconsult.flappybird;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
public class StartActivity extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start);
        //setContentView(new GameView(this,null));
        Button startButton = (Button)findViewById(R.id.button1);
        startButton.setOnClickListener(this);

        Button recordButton = (Button)findViewById(R.id.button2);
        recordButton.setOnClickListener(this);

        Button exitButton = (Button)findViewById(R.id.button3);
        exitButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
            }break;

            case R.id.button3: {
                finish();
            }break;

            default:
                break;
        }
    }
}