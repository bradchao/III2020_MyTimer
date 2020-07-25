package tw.brad.apps.mytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            counter++;
            Log.v("bradlog", "counter = " + counter);
        }
    }

    private void doStart(){
        timer.schedule(new MyTask(), 10, 10);
    }

    private void doStop(){

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