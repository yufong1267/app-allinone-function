package tw.com.flag.allinone;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Page4 extends AppCompatActivity {

    private GestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///動作預設
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //---------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        detector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){

            //當手指在上面滑動的時候會條用
            @Override
            public boolean onFling(MotionEvent e1 , MotionEvent e2 , float velocityX , float velocityY)
            {
                if((e2.getRawX() - e1.getRawX()) > 200)
                {
                    //顯示上一個頁面
                    showPre();
                    return true;
                }
                if ((e1.getRawX() - e2.getRawX()) > 200)
                {
                    //顯示下一個頁面

                    return true;
                }

                return super.onFling(e1,e2,velocityX,velocityY);
            }
        });

    }


public void Pre(View view){
    showPre();
}

    private void showPre() {
        Intent intent = new Intent(this,Page3.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_in,R.anim.tran_out);
    }

    //使用手勢識別器
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
