package com.example.multithreading;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnForward, btnBackWard;
    TextView banner;
    TextView txt;
    int arr[] = new int[5];
    int i = 0;
    boolean forward = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner = findViewById(R.id.banner);
        txt = findViewById(R.id.textView);

        int[] location = new int[2];
        banner.getLocationOnScreen(location);
        //txt.setText(String.valueOf("Start Pos " + location[0] + " " + location[1]));

        btnForward = findViewById(R.id.btnForward);
        btnBackWard = findViewById(R.id.btnBackward);


        arr[0] = Color.RED;
        arr[1] = Color.BLUE;
        arr[2] = Color.GREEN;
        arr[3] = Color.YELLOW;
        arr[4] = Color.MAGENTA;

        btnForward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                forward = !forward;
                new Thread(new Runnable() {
                    public void run() {
                        // a potentially time consuming task
                        while(forward)
                        {
                            int[] location = new int[2];
                            banner.getLocationOnScreen(location);
                            txt.setText(String.valueOf("Start Pos " + location[0] + " " + location[1]));
                            //Toast.makeText(MainActivity.this,"X axis is "+location[0] +" and Y axis is "+location[1],Toast.LENGTH_LONG).show();
                            banner.setTranslationX(location[0]+40f);
                            if(location[0] > 800)
                                break;
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();

//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        // a potentially time consuming task
//                        int x = Integer.valueOf(banner.getText().toString())+410;
//                        banner.setText(String.valueOf(x));
//
//                    }
//                });

                new Thread(new Runnable() {
                    public void run() {
                        // a potentially time consuming task
                        while(forward)
                        {
                            banner.setBackgroundColor(arr[i]);
                            banner.setText(">>>");
                            i=(i+1)%5;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });

        btnBackWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forward = !forward;
                new Thread(new Runnable() {
                    public void run() {
                        // a potentially time consuming task
                        while(!forward)
                        {
                            int[] location = new int[2];
                            banner.getLocationOnScreen(location);
                            txt.setText(String.valueOf("Start Pos " + location[0] + " " + location[1]));
                            //banner.setText(String.valueOf(location[0] + " " + location[1]));
                            //Toast.makeText(MainActivity.this,"X axis is "+location[0] +" and Y axis is "+location[1],Toast.LENGTH_LONG).show();
                            banner.setTranslationX(location[0]-50f);
                            if(location[0] <0)
                                break;
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();

//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        // a potentially time consuming task
//                        int x = Integer.valueOf(banner.getText().toString())+410;
//                        banner.setText(String.valueOf(x));
//
//                    }
//                });

                new Thread(new Runnable() {
                    public void run() {
                        // a potentially time consuming task
                        while(!forward)
                        {
                            banner.setBackgroundColor(arr[i]);
                            banner.setText("<<<");
                            i=(i+1)%5;
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                banner.getLocationOnScreen(location);
                Toast.makeText(MainActivity.this,"X axis is "+location[0] +" and Y axis is "+location[1],Toast.LENGTH_LONG).show();
            }
        });
    }
}