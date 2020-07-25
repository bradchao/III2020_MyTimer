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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button leftBtn, rightBtn;
    private boolean isStart;
    private TextView clock;
    private ListView lapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        clock = findViewById(R.id.timer);
        lapList = findViewById(R.id.lapList);
        initLapList();
    }

    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data = new LinkedList<>();
    private String[] from = {"time"};
    private int[] to = {R.id.lapitem_time};
    private void initLapList(){
        adapter = new SimpleAdapter(this, data, R.layout.lapitem, from, to);
        lapList.setAdapter(adapter);
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
            clock.setText(counterToClock(counter));
        }
    }

    private static String counterToClock(int i){
        int hs = i % 100;   // 小數點的部分
        int ts = i / 100;   // 總秒數
        int hh = ts / (60*60);
        int mm = (ts - hh*60*60)/60;
        int ss = ts % 60;

        return String.format("%d:%d:%d.%d", hh, mm, ss, hs);
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