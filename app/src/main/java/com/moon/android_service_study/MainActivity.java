package com.moon.android_service_study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) { //메모리에 MainActivity가 이미 만들어져 있으면 onCreate()가 호출되지 않고 이 메서드가 호출됨. 인텐트도 onNewIntent의 파라미터로 전다로딤
        Log.d("MainActivity : ","onNewIntent() 호출됨");
        processIntent(intent);
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent != null){
            String command = intent.getStringExtra("command");
            String value = intent.getStringExtra("value");

            for(int i = 0; i < 5; i++){
                Toast.makeText(this, "command : "+command+" | value : "+value, Toast.LENGTH_SHORT).show();
            }
        }
    }
}