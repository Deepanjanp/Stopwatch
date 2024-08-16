package com.example.digitalstopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv,start,hold;
    int seconds=0;
    boolean isrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.timer);
        start=findViewById(R.id.start);
        hold=findViewById(R.id.hold);

        startTimer();

    }

    public void onClickStart(View view)
    {
        isrunning=true;
        start.setText("START");
        hold.setText("PAUSE");

    }

    public void onClickHold(View view)
    {
        isrunning=false;
        hold.setText("PAUSED");
        start.setText("RESUME");

    }

    public void onClickReset(View view)
    {
        isrunning=false;
        start.setText("START");
        hold.setText("PAUSE");
        seconds=0;
    }

    public void startTimer()
    {
        Handler handler=new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int s=seconds%60;
                int m=seconds/60;
                int h=m/60;
                if (isrunning==true)
                {
                    seconds++;
                }
                String formatString = String.format(Locale.getDefault(),"%02d:%02d:%02d",h,m,s);
                tv.setText(formatString);
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
    }

}