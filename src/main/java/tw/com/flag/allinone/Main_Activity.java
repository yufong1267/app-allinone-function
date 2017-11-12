package tw.com.flag.allinone;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

public class Main_Activity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{




     public VideoView vdv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


//        vdv =  (VideoView)findViewById(R.id.log);
//        Uri uri = Uri.parse("android.resourse://" + getPackageName() + "/" + R.raw.testvideo);
//        vdv.setVideoURI(uri);
//        vdv.seekTo(2000);
//        vdv.requestFocus();
//        vdv.start();
//        vdv.setOnCompletionListener(this);
        Uri uri;
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logi);

        vdv = (VideoView) findViewById(R.id.log);
        //MediaController mediaCtrl = new MediaController(this);

        //vdv.setMediaController(mediaCtrl);
        //vdv.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logi));


        vdv.setVideoURI(uri);


        vdv.seekTo(2000);
        vdv.requestFocus();
        vdv.start();

        boolean b = vdv.isPlaying();
        vdv.setOnCompletionListener(this);
    }

    public void begin(View view){
        Intent iten = new Intent(this,Page3.class);
        startActivity(iten);
        finish();
        overridePendingTransition(R.anim.tran_in,R.anim.tran_out);
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        int len=vdv.getDuration();
        if (len == 13)
        {
            vdv.seekTo(2000);
            vdv.start();
        }
        vdv.start();
    }
}
