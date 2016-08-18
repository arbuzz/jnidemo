package com.jnidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jnidemo.task.CallJniTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callJniButton = (Button) findViewById(R.id.call_jni);
        callJniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CallJniTask(MainActivity.this).execute();
            }
        });
    }
}
