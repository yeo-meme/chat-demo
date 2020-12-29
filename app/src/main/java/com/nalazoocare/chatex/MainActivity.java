package com.nalazoocare.chatex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.nalazoocare.chatex.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ListView mListView;
    private CustomAdapter mAdapter;
    private Handler handler;
    private int number = 0;

    private static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mAdapter = new CustomAdapter();
        binding.listView1.setAdapter(mAdapter);
        handler = new Handler();

        BackThread thread = new BackThread();  // 작업스레드 생성
        thread.setDaemon(true);  // 메인스레드와 종료 동기화
        thread.start();

//        Timer timer = new Timer();
//        timer.schedule(tt,0,3000);

//        mAdapter.add("이건 뭐지",1);
//        mAdapter.add("쿨쿨",1);
//        mAdapter.add("쿨쿨쿨쿨",0);
//        mAdapter.add("재미있게",1);
//        mAdapter.add("놀자라구나힐힐 감사합니다. 동해물과 백두산이 마르고 닳도록 놀자 놀자 우리 놀자",1);
//        mAdapter.add("재미있게",1);
//        mAdapter.add("재미있게",0);
//        mAdapter.add("2015/11/20",2);
//        mAdapter.add("재미있게",1);
//        mAdapter.add("재미있게",1);

    }

    class BackThread extends Thread {  // Thread 를 상속받은 작업스레드 생성

        @Override
        public void run() {
            while (true) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (number == 0) {
                            mAdapter.add("이건 뭐지", 1);
                        }
                        if (number == 1) {
                            mAdapter.add("호호호", 0);
                        }
                        if (number ==2) {
                            mAdapter.add("하하하하", 1);
                        }
                        if (number == 3 ) {
                            mAdapter.add("파이는 심심해", 0);
                            number = 0;
                        }
                        number++;
                        mAdapter.notifyDataSetChanged();
                    }
                });
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    } // end class BackThread


}
