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
        String command = intent.getStringExtra("command");
        String value = intent.getStringExtra("value");
        Log.d(TAG,"command : " + command + "| value : " + value);

        for(int i = 0;i < 5; i++){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.d(TAG,"Waiting : " + i + "sec");
        }

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