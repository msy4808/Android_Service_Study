package com.moon.android_service_study;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService : ";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate() 호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand() 호출됨");
        if(intent == null){
            return Service.START_STICKY; //null이라면 서비스 재시작
        }else{ //intent가 null이 아니면 processCommand메서드 호출하기
            processCommand(intent, flags, startId);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent, int flags, int startId){
        String command = intent.getStringExtra("command"); //인텐트에서 부가데이터 가져오기
        String value = intent.getStringExtra("value");
        Log.d(TAG,"command : " + command + " | value : " + value);

        for(int i = 1;i <= 5; i++){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.d(TAG,"Waiting : " + i + "sec");
        }
        Intent showintent = new Intent(getApplicationContext(),MainActivity.class); //인텐트를 띄우기 위해 액티비티 객체 만들기
        //인텐트 안에 플래그 추가하기
        showintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showintent.putExtra("command","show To Service");
        showintent.putExtra("value", value+" first Service");
        startActivity(showintent);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}