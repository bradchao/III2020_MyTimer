package tw.brad.apps.mytimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button leftBtn, rightBtn;
    private boolean isStart;
    private TextView clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        clock = findViewById(R.id.timer);

    }

    public void doLeft(View view) {
        if (isStart){
            doLap();
        }else{
            doReset();
        }
    }

    private void doLap(){

    }

    private void doReset(){
        counter = 0;
        uiHandler.sendEmptyMessage(0);
    }

    public void doRight(View view) {
        isStart = !isStart;
        if (isStart){
            leftBtn.setText("LAP");
            rightBtn.setText("STOP");
            doStart();
        }else{
            leftBtn.setText("RESET");
            rightBtn.setText("START");
            doStop();
        }
    }

    private int counter;    // 0
    private Timer timer = new Timer();
    private MyTask myTask;

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            counter++;
            Log.v("bradlog", "counter = " + counter);
            //clock.setText(counter + "");
            uiHandler.sendEmptyMessage(0);
        }
    }
    private UIHandler uiHandler = new UIHandler();
    private class UIHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            clock.setText(counter + "");
        }
    }

    private void doStart(){
        myTask = new MyTask();
        timer.schedule(myTask, 10, 10);
    }

    private void doStop(){
        if (myTask != null){
            myTask.cancel();
        }
    }

    @Override
    public void finish() {
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        super.finish();
    }
}