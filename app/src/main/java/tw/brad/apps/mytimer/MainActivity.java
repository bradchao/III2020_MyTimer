package tw.brad.apps.mytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button leftBtn, rightBtn;
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);

    }

    public void doLeft(View view) {
    }

    public void doRight(View view) {
        isStart = !isStart;
        if (isStart){
            leftBtn.setText("LAP");
            rightBtn.setText("STOP");
        }else{
            leftBtn.setText("RESET");
            rightBtn.setText("START");
        }
    }
}