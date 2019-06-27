package com.example.shopping_v3.avtivity.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.shopping_v3.R;

public class MediaPlayerService extends Service {

    private MediaPlayer mPlayer;

    private MyBind mBind;


        public class MyBind extends Binder {

            public void play(){
                if( mPlayer != null){
                    if(!mPlayer.isPlaying()){
                        mPlayer.start();
                    }
                }
            }

            public void pause(){
                if(mPlayer != null){
                    if(mPlayer.isPlaying()){
                        mPlayer.pause();
                    }
                }
            }

            public void stop(){
                if(mPlayer != null){
                    mPlayer.stop();
                    try{
                        mPlayer = MediaPlayer.create(MediaPlayerService.this, R.raw.abc);
                        mPlayer.prepare();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }


        @Override
        public IBinder onBind(Intent intent){
            Log.i("MyLog","服务被绑定.....");
            return mBind;
        }

        @Override
        public void onCreate(){
            super.onCreate();
            mPlayer = MediaPlayer.create(this, R.raw.abc);
            mBind = new MediaPlayerService.MyBind();
            try{
                mPlayer.prepare();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId){
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public boolean onUnbind(Intent intent){
            return super.onUnbind(intent);
        }

        @Override
        public void onDestroy(){
            super.onDestroy();
            mPlayer.stop();
            mPlayer.release();
        }
}
