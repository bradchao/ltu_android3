package com.example.user.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tv, tv2;
    private MyHandler handler;
    private Timer timer;
    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new MyHandler();
        timer = new Timer();

        tv = (TextView)findViewById(R.id.tv);
        tv2 = (TextView)findViewById(R.id.tv2);

    }

    public void test1(View view){
        MyThread mt1 = new MyThread("A");
        mt1.start();
        MyThread mt2 = new MyThread("B");
        mt2.start();
    }
    public void test2(View view){
        myTask = new MyTask();
        timer.schedule(myTask, 0, 500);
    }
    public void test3(View view){
        if (myTask != null){
            myTask.cancel();
            myTask = null;
        }
    }

    private class MyTask extends TimerTask {
        int i;
        @Override
        public void run() {
            Log.i("brad", "i = " + i++);
        }
    }

    private class MyThread extends Thread {
        String name;
        MyThread(String name){
            this.name = name;
        }
        @Override
        public void run() {
            for (int i=0; i<20; i++){
                Log.i("brad", name + " = " + i);

                Message mesg = new Message();
                Bundle data = new Bundle();
                data.putString("name", name);
                data.putInt("i", i);
                mesg.setData(data);
                handler.sendMessage(mesg);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle data = msg.getData();
            String name = data.getString("name");
            if (name.equals("A")) {
                tv.setText(name + " = " + data.getInt("i"));
            }else if (name.equals("B")){
                tv2.setText(name + " = " + data.getInt("i"));
            }

        }
    }




}
