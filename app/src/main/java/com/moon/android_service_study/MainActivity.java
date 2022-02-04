package com.moon.android_service_study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(),MyService.class); //인텐트 객체 생성

                intent.putExtra("command","show"); //생성한 인텐트 객체에 부가 데이터들 넣어주기
                intent.putExtra("value",str);
                startService(intent); //서비스 시작하기MyService 클래스에 onStartCommand()메소드로 전달
            }
        });
    }
}